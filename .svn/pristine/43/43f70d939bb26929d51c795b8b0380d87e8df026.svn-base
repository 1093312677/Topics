package com.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;


@Entity
@Table(name="direction")
/**
 * 方向实体类
 * @author kone
 * 2017-1-7
 */
public class Direction {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String directionName;
//	和专业实现多对一
	@ManyToOne
	@JoinColumn(name="specialtyId")
	@Basic(fetch=FetchType.LAZY)
	private Specialty spceialty;
	
//	实现和班级的一对多关联
	@OneToMany(mappedBy="direction",cascade={CascadeType.ALL})
	private List<Clazz> clazzs = new ArrayList<Clazz>();
	
//	和选题实现多对多关系
//	@ManyToMany(cascade=CascadeType.ALL,mappedBy="directions")    
//	@JoinTable(name="t_topic_direction",                     //中间表的名称
//		joinColumns={@JoinColumn(name="directions_id")},   //本表与中间表的外键对应关系
//		inverseJoinColumns={@JoinColumn(name="topics_id")}) //另一张表与中间表的外键的对应关系
	@ManyToMany(mappedBy="directions",targetEntity = Topics.class,fetch = FetchType.LAZY)
	private List<Topics> topics = new ArrayList<Topics>();
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDirectionName() {
		return directionName;
	}
	public void setDirectionName(String directionName) {
		this.directionName = directionName;
	}
	public Specialty getSpceialty() {
		return spceialty;
	}
	public void setSpceialty(Specialty spceialty) {
		this.spceialty = spceialty;
	}
	public List<Clazz> getClazzs() {
		return clazzs;
	}
	public void setClazzs(List<Clazz> clazzs) {
		this.clazzs = clazzs;
	}
	public List<Topics> getTopics() {
		return topics;
	}
	public void setTopics(List<Topics> topics) {
		this.topics = topics;
	}
	
	
	
}
