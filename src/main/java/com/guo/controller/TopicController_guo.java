package com.guo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.dto.DealData;
import com.entity.Direction;
import com.entity.Student;
import com.entity.Teacher;
import com.entity.Topics;
import com.guo.service.IDirectionService;
import com.guo.service.ITopicService;

@Controller
@RequestMapping("topic")
public class TopicController_guo {
	@Autowired
	private DealData<?> dealData;
	public DealData getDealData() {
		return dealData;
	}

	public void setDealData(DealData dealData) {
		this.dealData = dealData;
	}
	@Resource(name="topicService1")
	ITopicService topicService;
	@Resource(name="directionService1")
	IDirectionService directionService;
	@RequestMapping(value="update.do")
	public String update(HttpServletRequest request){
		int id=Integer.parseInt(request.getParameter("id"));
		try {
			Topics topics=topicService.geTopic(id);
			long gradeId=topics.getGrade().getId();
			List<Direction> directions=new ArrayList<>();
			List<Direction>directions1=directionService.directionsByGrade(gradeId);
			List<Direction>directions2=new ArrayList<>();
			for(int i=0;i<directions1.size();i++){
				int flag=0;
				Direction direction=new Direction();
				for(int j=0;j<topics.getDirections().size();j++){
					if(directions1.get(i).getId()==topics.getDirections().get(j).getId()) {
						flag=1;
						break;
					}
				}
				direction=directions1.get(i);
				if(flag==1) directions2.add(direction);
				else directions.add(direction);
			}
			
			
			request.setAttribute("directions1", directions2);
			request.setAttribute("directions", directions);
			request.setAttribute("topic", topics);	
		} finally {
			topicService.closeSession();
			directionService.closeSession();
		}
		return "topic/updateTopic";	
	}
	@RequestMapping(value="updateInfo.do")
	public String updateInfo(Topics topics,Long gradeid,long directionIds[],HttpServletRequest request){
		long gradeId=topicService.updateInfo(topics,directionIds);
		String url;
		try {
			url = "redirect:/topic/viewTopic.do?state=1&gradeId="+gradeId+"";
		} finally{
			topicService.closeSession();
		}
		return url;	
	}
	@RequestMapping(value="findtopics.do")
	public String findtopics(String findType,String primaryKey,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		List<Topics>topics=topicService.findtopics(findType, primaryKey);	
		JSONObject json = new JSONObject();
		json.put("topics", topics);
		List<Student> students = (List<Student>) session.getAttribute("infor");
		if(students.size() > 0) {
			if(students.get(0).getTopics() == null) {
				json.put("selected", "no");
			} else {
				json.put("selected", "yes");
			}
		}		
		try {
			response.getWriter().println(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
}
