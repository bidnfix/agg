package com.agg.application.service;

import java.util.List;

import com.agg.application.entity.Sprogram;
import com.agg.application.model.Program;

public interface ProgramService {

	List<Sprogram> getPrograms();

	int saveProgram(Program program);

	Program getProgram(int id);

}