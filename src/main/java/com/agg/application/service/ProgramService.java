package com.agg.application.service;

import java.util.List;

import com.agg.application.model.AccountDO;
import com.agg.application.model.ProgramDO;

public interface ProgramService {

	List<ProgramDO> getPrograms(AccountDO accountDO);

	Long saveProgram(ProgramDO program);

	ProgramDO getProgram(Long id);

	void deleteProgram(Long id);
	
	long editProgram(ProgramDO programDO);

}
