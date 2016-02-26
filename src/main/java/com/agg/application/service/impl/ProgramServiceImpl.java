package com.agg.application.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	@Override
	public List<Sprogram> getPrograms() {
		logger.debug("In getPrograms");
		return Util.toList(programDAO.findAll());
	}

	@Override
	public int saveProgram(Program program) {
		logger.debug("In saveProgram");
		Sprogram progEnt = new Sprogram();
		progEnt.setPrName(program.getName());
		progEnt.setPrDesc(program.getDescription());
		progEnt.setPrIsActive((byte) 1);
		//TODO To be implemented after dealer services
		//progEnt.setDealer(dealer);
		progEnt = programDAO.save(progEnt);
		return progEnt.getPrId();
	}

	@Override
	public Program getProgram(int id) {
		Sprogram sProgram = programDAO.findOne((long)id);
		Program program = new Program();
		program.setName(sProgram.getPrName());
		program.setDescription(sProgram.getPrDesc());
		program.setType(sProgram.getPrCType());
		return program;
	}
}

