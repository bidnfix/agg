package com.agg.application.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agg.application.dao.DealerDAO;
import com.agg.application.dao.DealerNoteDAO;
import com.agg.application.entity.Dealer;
import com.agg.application.entity.DealerNote;
import com.agg.application.model.DealerDO;
import com.agg.application.service.DealerService;
import com.agg.application.utils.Util;

@Service
public class DealerServiceImpl implements DealerService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	DealerDAO dealerDAO;
	
	@Autowired
	DealerNoteDAO dealerNoteDAO;
	
	@Override
	public List<DealerDO> getDealers() {
		logger.debug("In getPrograms");
		List<DealerDO> dealerDOList = null;
		List<Dealer> dealerList = Util.toList(dealerDAO.findAll());
		return dealerDOList;
	}

	@Override
	@Transactional
	public long saveDealer(DealerDO dealerDO) {
		logger.debug("In saveDealer");
		Dealer dealer = new Dealer();
		Timestamp date = new Timestamp(new Date().getTime());
		dealer.setAddress(dealerDO.getAddress1());
		dealer.setCity(dealerDO.getCity());
		dealer.setContact(dealerDO.getContact());
		
		dealer.setInvoiceEmail(dealerDO.getInvoiceEmail());
		dealer.setLastUpdate(date);
		//TODO
		dealer.setLId(1);
		dealer.setLocation(dealerDO.getLocation());
		dealer.setMarketEmail(dealerDO.getMarketEmail());
		//TODO
		dealer.setName(dealerDO.getUserName());
		dealer.setPassword(dealerDO.getPassword());
		dealer.setPhone(dealerDO.getPhone());
		//TODO
		dealer.setRId(1);
		//TODO
		dealer.setRole("Admin");
		dealer.setStatus(1);
		dealer.setState(dealerDO.getState());
		dealer.setUrl(dealerDO.getDealerUrl());
		dealer.setUsername(dealerDO.getUserName());
		dealer.setZip(dealerDO.getZip());
		//TODO
		dealer.setDealerId(1);
		dealer.setActiveDate(date);
		dealer.setLastUpdate(date);
		
		
		DealerNote dealerNote = new DealerNote();
		dealerNote.setLastUpdate(date);
		dealerNote.setNotes(dealerDO.getNotes());
		dealerNote.setDealer(dealer);
		
		
		dealerNote = dealerNoteDAO.save(dealerNote);
		
		return dealer.getDealerId();
	}

	@Override
	public DealerDO getDealer(long id) {
		Dealer dealer = dealerDAO.findOne((long)id);
		DealerDO dealerDO = new DealerDO();
		
		return dealerDO;
	}
	

}

