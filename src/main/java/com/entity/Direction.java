package com.entity;

import java.io.Serializable;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="direction")
/**
 * 方向实体类
 * @author kone
 * 2017-1-7
 */
public class Direction implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	@OneToMany(mappedBy="direction",cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	private List<Clazz> clazzs = new ArrayList<Clazz>();
	
	@ManyToMany(mappedBy="directions",targetEntity = Topics.class,fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Topics> topics = new ArrayList<Topics>();
	
	public Direction() {
		super();
	}
	public Direction(long id, String directionName) {
		super();
		this.id = id;
		this.directionName = directionName;
	}
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
