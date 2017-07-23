package com.dao;

import com.entity.IntentionTopic;

public interface IIntentionDao {
	/**
	 * 保存意向题目
	 * @param IntentionTopic
	 * @return
	 */
	public boolean saveIntention(IntentionTopic intentionTopic);
	
	/**
	 * 确认更新志愿，先删除该批次，该志愿的选项，再进行添加
	 * @param IntentionTopic intentionTopic
	 * @return
	 */
	public boolean choiceDeleteIntention(IntentionTopic intentionTopic);
	
	
}
