package com.guo.dao;

import java.util.List;

import com.entity.Topics;

public interface ITopicDao {
	public Topics geTopic(long id);
	public void closeSession();
	public long updateInfo(Topics topics,long directionIds[], String privilege);
	public List<Topics> findtopics(String primaryKey,String year);
	public List<Topics> findtopicsByName(String primaryKey,String year);
}
