package com.dao;

import com.entity.IntentionTopic;

import java.util.List;

public interface IIntentionDao {
	/**
	 * 保存意向题目
	 * @param intentionTopic
	 * @return
	 */
	public boolean saveIntention(IntentionTopic intentionTopic);
	
	/**
	 * 确认更新志愿，先删除该批次，该志愿的选项，再进行添加
	 * @param intentionTopic
	 * @return
	 */
	public boolean choiceDeleteIntention(IntentionTopic intentionTopic);

	/**
	 * 查找意向题目
	 * @param topicId
	 * @param batch
	 * @param choice
	 * @return
	 */
	List<IntentionTopic> getIntentions(Long topicId, int batch, int choice);
	
	
}
