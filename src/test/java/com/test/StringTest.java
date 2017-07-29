package com.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class StringTest {

	@Test
	public void strTest() {
		StringBuffer sb = new StringBuffer();
		List<Long> ids = new ArrayList<Long>();
		ids.add(new Long(1));
		ids.add(new Long(2));
		ids.add(new Long(3));
		String str = ids.toString();
		str = str.substring(1,str.lastIndexOf(']'));
		sb.append(1+",");
		sb.append(2+",");
		System.out.println(str);
	}
}
