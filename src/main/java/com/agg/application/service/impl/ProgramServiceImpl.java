package com.agg.application.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agg.application.dao.DealerDAO;
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
				
				programDO.setPrName(program.getPrName());
				programDO.setPrCType(program.getPrCType());
				
				
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
		Sprogram progEnt = new Sprogram();
		progEnt.setPrName(program.getPrName());
		progEnt.setPrDesc(program.getPrDesc());
		progEnt.setPrIsActive((byte) 1);
		progEnt.setDealer(dealerDAO.findOne(2l));
		progEnt.setPrGroup("1");
		progEnt.setPrCType(program.getPrCType());
		progEnt.setPrCHours(program.getPrCHours());
		progEnt.setPrCTerm(program.getPrCTerm());
		progEnt.setPrCost(program.getPrCost());
		progEnt.setPrIsArchive((byte)0);
		progEnt.setPrAServicing((byte)1);
		
		//TODO To be implemented after dealer services
		//progEnt.setDealer(dealer);
		progEnt = programDAO.save(progEnt);
		return progEnt.getPrId();
	}

	@Override
	public ProgramDO getProgram(Long id) {
		Sprogram sProgram = programDAO.findOne(id);
		ProgramDO program = new ProgramDO();
		program.setPrId(sProgram.getPrId());
		program.setPrName(sProgram.getPrName());
		program.setPrDesc(sProgram.getPrDesc());
		program.setPrCType(sProgram.getPrCType());
		
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

