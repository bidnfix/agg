package com.agg.application.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agg.application.dao.DealerDAO;
import com.agg.application.dao.ManufacturerDAO;
import com.agg.application.dao.ProgramDAO;
import com.agg.application.entity.Dealer;
import com.agg.application.entity.Sprogram;
import com.agg.application.model.DealerDO;
import com.agg.application.model.ProgramDO;
import com.agg.application.service.ProgramService;
import com.agg.application.utils.Util;

@Service
public class ProgramServiceImpl implements ProgramService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProgramDAO programDAO;
	
	@Autowired
	DealerDAO dealerDAO;
	
	@Autowired
	private ManufacturerDAO manufacturerDAO;
	
	@Override
	public List<ProgramDO> getPrograms() {
		logger.debug("In getPrograms");
		List<Sprogram> programList = Util.toList(programDAO.findAll());
		
		List<ProgramDO> programDOList = null;
		if(!programList.isEmpty()){
			logger.debug("programList size:"+programList.size());
			programDOList = new ArrayList<ProgramDO>();
			ProgramDO programDO = null;
			Sprogram program = null;
			DealerDO dealerDO = null;
			
			Iterator<Sprogram> it = programList.iterator();
			while(it.hasNext()){
				programDO = new ProgramDO();
				program = it.next();
				
				programDO.setName(program.getPrName());
				programDO.setcType(program.getPrCType());
				
				
				dealerDO = new DealerDO();
				Dealer dealer = program.getDealer();
				if(dealer!=null)
				{
					dealerDO.setName(dealer.getName());
					dealerDO.setId(dealer.getId());
				}
				programDO.setDealer(dealerDO);
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
		logger.debug("-->"+program.getDealer());
		//progEnt.setDealer(dealerDAO.findOne(Long.valueOf(program.getDealer().getId())));
		progEnt.setDealer(dealerDAO.findOne(Long.valueOf(14)));
		progEnt.setPrGroup(program.getGroup());
		progEnt.setPrDeductible(program.getDeductible());
		progEnt.setPrCType(program.getcType());
		progEnt.setPrCHours(program.getcHours());
		progEnt.setPrCTerm(program.getcTerm());
		progEnt.setPrCost(program.getCost());
		progEnt.setPrLol(program.getLol());
		progEnt.setPrIsArchive((byte)0);
		progEnt.setPrAServicing((byte)1);
		//progEnt.setManufacturer(manufacturerDAO.findOne(Long.valueOf(program.getManufacturerDO().getId())));
		progEnt.setManufacturer(manufacturerDAO.findOne(Long.valueOf(8)));
		//TODO To be implemented after dealer services
		//progEnt.setDealer(dealer);
		progEnt.setPrLastUpdate(date);
		progEnt = programDAO.save(progEnt);
		return progEnt.getPrId();
	}

	@Override
	public ProgramDO getProgram(Long id) {
		Sprogram sProgram = programDAO.findOne(id);
		ProgramDO program = new ProgramDO();
		program.setId(sProgram.getPrId());
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
		program.setDealer(dealerDO);
		
		return program;
	}

	@Override
	public void deleteProgram(Long id) {
		Sprogram sProgram = programDAO.findOne(id);
		programDAO.delete(id);;
	}
}

