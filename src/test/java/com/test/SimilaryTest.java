package com.test;

import org.junit.Test;

import com.common.Similarity;

public class SimilaryTest {
	@Test
	public void similary() {
		String strA = "AR室内查看";  
		  
        String strB = "AR景区查看";  
  
        
		Similarity s = new Similarity();
		double result=s.SimilarDegree(strA, strB); 
		System.out.println(result);
	}
}
