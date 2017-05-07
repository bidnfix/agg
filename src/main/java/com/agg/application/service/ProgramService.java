package com.agg.application.service;

import java.util.List;

import com.agg.application.model.AccountDO;
import com.agg.application.model.MachineInfoDO;
import com.agg.application.model.ProgramDO;
import com.agg.application.model.QuoteDO;

public interface ProgramService {

	List<ProgramDO> getPrograms(AccountDO accountDO);

	Long saveProgram(ProgramDO program);
	
	QuoteDO saveProgramsAsDealr(QuoteDO quoteDO, AccountDO accountDO, String appUrl) throws Exception;

	ProgramDO getProgram(Long id, AccountDO accountDO);	

	void deleteProgram(Long id);
	
	long editProgram(ProgramDO programDO);
	
	public List<MachineInfoDO> getModelByCodes(List<Long> modelLst);

}
