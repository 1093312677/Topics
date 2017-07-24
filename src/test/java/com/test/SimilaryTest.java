package com.test;

import org.junit.Test;

import com.common.SimilarAlgorithm;
import com.common.Similarity;

public class SimilaryTest {
	@Test
	public void similary() {
		String strA = "aa";  
		  
        String strB = "ba";  
  
        
		Similarity s = new Similarity();
		double result=s.SimilarDegree(strA, strB); 
		System.out.println(result);
		
		double d =SimilarAlgorithm.getSimilarityRatio("", "");
		System.out.println(d);
	}
}
	