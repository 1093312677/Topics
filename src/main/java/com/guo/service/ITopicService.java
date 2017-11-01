package com.guo.service;

import java.util.List;

import com.entity.Topics;

public interface ITopicService {
	public Topics geTopic(long id);
	public void closeSession();
	public long updateInfo(Topics topics,long directionIds[], String privilege);
	public List<Topics> findtopics(String findType,String primaryKey);
}
