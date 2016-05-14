package com.agg.application.service;

import java.util.List;

import com.agg.application.entity.Sprogram;
import com.agg.application.model.ProgramDO;

public interface ProgramService {

	List<ProgramDO> getPrograms();

	Long saveProgram(ProgramDO program);

	ProgramDO getProgram(Long id);

	void deleteProgram(Long id);

}
