package com.guo.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.DealData;
import com.entity.Direction;
import com.entity.Topics;
import com.guo.dao.IDirectionDao;
import com.guo.dao.ITopicDao;
import com.guo.service.ITopicService;

@Service(value="topicService1")
public class TopicService implements ITopicService {
	@Autowired
	private DealData<?> dealData;
	public DealData getDealData() {
		return dealData;
	}

	public void setDealData(DealData dealData) {
		this.dealData = dealData;
	}
	@Resource(name="topicDao1")
	ITopicDao topicDao;
	@Resource(name="directionDao1")
	IDirectionDao directionDao;
	@Override
	public Topics geTopic(long id) {
		return topicDao.geTopic(id);
	}

	@Override
	public void closeSession() {
		topicDao.closeSession();
	}

	@Override
	public void updateInfo(Topics topics,long directionIds[]) {
		topicDao.updateInfo(topics,directionIds);
	}

	@Override
	public List<Topics> findtopics(String findType, String primaryKey) {
		List<Topics>topics=new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		if (month >= 8)
			year = year + 1;
		if(findType.equals("teachername")) {			
			topics=dealData.dealTopics(topicDao.findtopics(primaryKey,String.valueOf(year-3)));
			topicDao.closeSession();
			
		}
		else if(findType.equals("topicName")){
			System.out.println(primaryKey);
			topics=dealData.dealTopics(topicDao.findtopicsByName(primaryKey,String.valueOf(year-3)));
			topicDao.closeSession();
		}
		return topics;
	}

}
