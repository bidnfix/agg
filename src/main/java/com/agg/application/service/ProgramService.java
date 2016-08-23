package com.agg.application.service;

import java.util.List;

import com.agg.application.model.AccountDO;
import com.agg.application.model.ProgramDO;
import com.agg.application.model.QuoteDO;

public interface ProgramService {

	List<ProgramDO> getPrograms(AccountDO accountDO);

	Long saveProgram(ProgramDO program);
	
	int saveProgramsAsDealr(QuoteDO quoteDO, AccountDO accountDO);

	ProgramDO getProgram(Long id);	

	void deleteProgram(Long id);
	
	long editProgram(ProgramDO programDO);

}
