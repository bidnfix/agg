package com.agg.application.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agg.application.dao.AccountDAO;
import com.agg.application.dao.DealerDAO;
import com.agg.application.dao.MachineInfoDAO;
import com.agg.application.dao.ManufacturerDAO;
import com.agg.application.dao.ProgramDAO;
import com.agg.application.entity.Account;
import com.agg.application.entity.Dealer;
import com.agg.application.entity.MachineInfo;
import com.agg.application.entity.Manufacturer;
import com.agg.application.entity.Sprogram;
import com.agg.application.model.AccountDO;
import com.agg.application.model.DealerDO;
import com.agg.application.model.MachineDO;
import com.agg.application.model.MachineInfoDO;
import com.agg.application.model.ManufacturerDO;
import com.agg.application.model.ProgramDO;
import com.agg.application.service.ProgramService;
import com.agg.application.utils.AggConstants;
import com.agg.application.utils.Util;

@Service
public class ProgramServiceImpl implements ProgramService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProgramDAO programDAO;
	
	@Autowired
	DealerDAO dealerDAO;
	
	@Autowired
	AccountDAO accountDAO;
	
	@Autowired
	private ManufacturerDAO manufacturerDAO;
	
	@Autowired
	private MachineInfoDAO machineInfoDAO;
	
	@Override
	public List<ProgramDO> getPrograms(AccountDO accountDO) {
		logger.debug("In getPrograms");
		List<Sprogram> programList = null;
		if(accountDO.getRoleDO().getAccountType().equalsIgnoreCase(AggConstants.ACCOUNT_TYPE_ADMIN)){
			programList = Util.toList(programDAO.findAll());
		}else{
			Account account = accountDAO.findOne(accountDO.getId());
			if(account != null){
				programList = programDAO.findByDealerId(account.getDealer().getId());
			}
		}
		
		List<ProgramDO> programDOList = null;
		if(!programList.isEmpty()){
			logger.debug("programList size:"+programList.size());
			programDOList = new ArrayList<ProgramDO>();
			ProgramDO programDO = null;
			Sprogram program = null;
			DealerDO dealerDO = null;
			ManufacturerDO manfDO = null;
			List<MachineInfoDO> machineInfoDOList = null; 
			
			Iterator<Sprogram> it = programList.iterator();
			while(it.hasNext()){
				programDO = new ProgramDO();
				program = it.next();
				
				programDO.setName(program.getPrName());
				programDO.setcType(program.getPrCType());
				programDO.setPrId(program.getPrId());
				
				dealerDO = new DealerDO();
				Dealer dealer = program.getDealer();
				if(dealer!=null)
				{
					dealerDO.setName(dealer.getName());
					dealerDO.setId(dealer.getId());
				}
				programDO.setDealerDO(dealerDO);
				
				manfDO = new ManufacturerDO();
				Manufacturer manf = program.getManufacturer();
				if(manf!=null)
				{
					manfDO.setName(manf.getManfName());
					manfDO.setId(manf.getManfId());
				}
				programDO.setManufacturerDO(manfDO);
				
				machineInfoDOList = new ArrayList<MachineInfoDO>();
				List<MachineInfo> machineInfoLst = program.getMachineInfos();
				if(machineInfoLst!=null)
				{
					for(MachineInfo macineInfo : machineInfoLst)
					{
						MachineInfoDO macInfDO = new MachineInfoDO();
						macInfDO.setModel(macineInfo.getModel());
						macInfDO.setMachineId(macineInfo.getMachineId());
						
						machineInfoDOList.add(macInfDO);
					}
					programDO.setMachineInfoDO(machineInfoDOList);
				}
				programDO.setDealerDO(dealerDO);
				
				programDOList.add(programDO);
			}
		}
		logger.debug(""+programDOList.size());
		
		return programDOList;
	}

	@Override
	public Long saveProgram(ProgramDO program) {
		logger.debug("In saveProgram");
		Timestamp date = new Timestamp(new Date().getTime());
		Sprogram progEnt = new Sprogram();
		progEnt.setPrName(program.getName());
		progEnt.setPrDesc(program.getDesc());
		progEnt.setPrIsActive((byte) 1);
		progEnt.setPrAServicing((byte) 1);
		progEnt.setPrCondition((byte) 1);
		progEnt.setPrCType(program.getcType());
		logger.debug("-->"+program.getDealerDO());
		progEnt.setDealer(dealerDAO.findOne(Long.valueOf(program.getDealerDO().getId())));
		//progEnt.setDealer(dealerDAO.findOne(Long.valueOf(14)));
		progEnt.setPrGroup(program.getGroup());
		progEnt.setPrDeductible(program.getDeductible());
		progEnt.setPrCType(program.getcType());
		progEnt.setPrCHours(program.getcHours());
		progEnt.setPrCTerm(program.getcTerm());
		progEnt.setPrCost(program.getCost());
		progEnt.setPrLol(program.getLol());
		progEnt.setPrIsArchive((byte)0);
		progEnt.setPrAServicing((byte)1);
		progEnt.setManufacturer(manufacturerDAO.findOne(Long.valueOf(program.getManufacturerDO().getId())));
		//progEnt.setManufacturer(manufacturerDAO.findOne(Long.valueOf(8)));
		//TODO To be implemented after dealer services
		//progEnt.setDealer(dealer);
		
		List<MachineInfoDO> machineInfoDOs =  program.getMachineInfoDO();
		
		
		
		MachineInfo machineInfo = null;
		List<MachineInfo> machineInfoList = new ArrayList<MachineInfo>();
		for(MachineInfoDO machineInfoDO : machineInfoDOs)
		{
			logger.debug("--machineInfoDO--"+machineInfoDO.getMachineId());
			machineInfo = machineInfoDAO.findOne(machineInfoDO.getMachineId());
			if(machineInfo!=null)
			{
				logger.debug("--machineInfo --"+machineInfo.getMachineId());
				machineInfoList.add(machineInfo);
			}
		}
		
		progEnt.setMachineInfos(machineInfoList);
		
		progEnt.setPrLastUpdate(date);
		progEnt = programDAO.save(progEnt);
		return progEnt.getPrId();
	}

	@Override
	public ProgramDO getProgram(Long id) {
		Sprogram sProgram = programDAO.findOne(id);
		ProgramDO program = new ProgramDO();
		program.setPrId(sProgram.getPrId());
		program.setName(sProgram.getPrName());
		program.setDesc(sProgram.getPrDesc());
		program.setcType(sProgram.getPrCType());
		
		DealerDO dealerDO = new DealerDO();
		Dealer dealer = sProgram.getDealer();
		if(dealer!=null)
		{
			dealerDO.setName(dealer.getName());
			dealerDO.setId(dealer.getId());
		}
		program.setDealerDO(dealerDO);
		
		return program;
	}

	@Override
	public void deleteProgram(Long id) {
		Sprogram sProgram = programDAO.findOne(id);
		programDAO.delete(id);;
	}
	
	@Override
	@Transactional
	public long editProgram(ProgramDO programDO) {
		logger.debug("In editProgram : "+programDO.getPrId());
		Sprogram sProgram = programDAO.findOne(programDO.getPrId());
		Timestamp date = new Timestamp(new Date().getTime());
		
		sProgram.setPrDesc(programDO.getDesc());
		sProgram.setPrDesc(programDO.getDesc());
		sProgram.setDealer(dealerDAO.findOne(Long.valueOf(programDO.getDealerDO().getId())));

		sProgram.setPrLastUpdate(date);
		
		sProgram = programDAO.save(sProgram);
		
		return sProgram.getPrId();
	}
	
}

