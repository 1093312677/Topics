package com.common;
/**
 * 条件查询数据库的条件进行封装
 * @author kone
 *
 */
public class QueryCondition {
	private int conunt;//条件个数
	
	private String table;
	
	private String row1;// 条件
	private String value1; //值
	private String row2;
	private String value2;
	private String row3;
	private String value3;
	private String row4;
	private String value4;
	public int getConunt() {
		return conunt;
	}
	public void setConunt(int conunt) {
		this.conunt = conunt;
	}
	public String getRow1() {
		return row1;
	}
	public void setRow1(String row1) {
		this.row1 = row1;
	}
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public String getRow2() {
		return row2;
	}
	public void setRow2(String row2) {
		this.row2 = row2;
	}
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	public String getRow3() {
		return row3;
	}
	public void setRow3(String row3) {
		this.row3 = row3;
	}
	public String getValue3() {
		return value3;
	}
	public void setValue3(String value3) {
		this.value3 = value3;
	}
	public String getRow4() {
		return row4;
	}
	public void setRow4(String row4) {
		this.row4 = row4;
	}
	public String getValue4() {
		return value4;
	}
	public void setValue4(String value4) {
		this.value4 = value4;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	
	
}
