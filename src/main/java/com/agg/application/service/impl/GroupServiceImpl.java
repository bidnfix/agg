package com.agg.application.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agg.application.dao.GroupDAO;
import com.agg.application.entity.GroupConstant;
import com.agg.application.model.GroupDO;
import com.agg.application.service.GroupService;
import com.google.common.collect.Lists;
@Service
public class GroupServiceImpl implements GroupService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private GroupDAO groupDAO;
	
	@Override
	public List<GroupDO> getGroups() {
		List<GroupConstant>  groupList =  Lists.newArrayList(groupDAO.findAll());
		List<GroupDO> groupDOList = null;
		if(groupList != null && !groupList.isEmpty()){
			groupDOList = new ArrayList<GroupDO>();
			GroupDO groupDO = null;
			GroupConstant groupConstant = null;
			Iterator<GroupConstant> it = groupList.iterator();
			while(it.hasNext()){
				groupDO = new GroupDO();
				groupConstant = it.next();
				groupDO.setGroupId(groupConstant.getGroupId());
				groupDO.setComm(groupConstant.getComm());
				groupDO.setLol(groupConstant.getLol());
				groupDO.setMsrp(groupConstant.getMsrp());
				groupDO.setOldId(groupConstant.getOldId());
				groupDO.setRate(groupConstant.getRate());
				groupDO.setSales(groupConstant.getSales());
				groupDO.setTax(groupConstant.getTax());
				StringBuffer tooltip = new StringBuffer().append("Old Group "+groupConstant.getGroupId()+'\r')
						.append(" MRP "+groupConstant.getMsrp()+'\r')
						.append(" Sales "+groupConstant.getSales()+'\r')
						.append(" Tax "+groupConstant.getTax()+'\r')
						.append(" LOL "+groupConstant.getLol()+'\r')
						.append(" Rate "+groupConstant.getRate());
				logger.debug("Tooltip 2"+tooltip);
				groupDO.setTips(tooltip.toString());
				groupDOList.add(groupDO);
			}
		}
		return groupDOList;
	}

}
