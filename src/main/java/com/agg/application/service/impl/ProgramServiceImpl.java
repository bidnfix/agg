package com.agg.application.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agg.application.dao.DealerDAO;
import com.agg.application.dao.ProgramDAO;
import com.agg.application.entity.Sprogram;
import com.agg.application.model.Program;
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
	public List<Sprogram> getPrograms() {
		logger.debug("In getPrograms");
		return Util.toList(programDAO.findAll());
	}

	@Override
	public Long saveProgram(Program program) {
		logger.debug("In saveProgram");
		Sprogram progEnt = new Sprogram();
		progEnt.setPrName(program.getName());
		progEnt.setPrDesc(program.getDescription());
		progEnt.setPrIsActive((byte) 1);
		progEnt.setDealer(dealerDAO.findOne(2l));
		progEnt.setPrGroup("1");
		progEnt.setPrCType(program.getType());
		progEnt.setPrCHours(program.getHours());
		progEnt.setPrCTerm(program.getTerm());
		progEnt.setPrCost(program.getCost());
		progEnt.setPrIsArchive((byte)0);
		progEnt.setPrAServicing((byte)1);
		
		//TODO To be implemented after dealer services
		//progEnt.setDealer(dealer);
		progEnt = programDAO.save(progEnt);
		return progEnt.getPrId();
	}

	@Override
	public Program getProgram(Long id) {
		Sprogram sProgram = programDAO.findOne(id);
		Program program = new Program();
		program.setId(sProgram.getPrId());
		program.setName(sProgram.getPrName());
		program.setDescription(sProgram.getPrDesc());
		program.setType(sProgram.getPrCType());
		program.setDealerName(sProgram.getDealer().getFirstName()+" "+sProgram.getDealer().getLastName());
		return program;
	}

	@Override
	public void deleteProgram(Long id) {
		Sprogram sProgram = programDAO.findOne(id);
		programDAO.delete(id);;
	}
}

