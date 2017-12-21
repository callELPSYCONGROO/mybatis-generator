package org.wuxb.generator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PatternTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String str = "andCompanyIDNotEqualTo(department.getCompanyID())";
		// System.out.println(str.matches("and(.*)NotEqualTo"));
		//String regEx = ".*and(.*)(NotEqualTo|EqualTo)\\((.*)\\).*";
		String str = "public int insertSelective(ActivityNews record) {";
		String regEx = ".*[insert|insertSelective]\\((\\w+).*";
		Pattern pattern = Pattern.compile(regEx,Pattern.DOTALL);
		Matcher matcher = pattern.matcher(str);
		while(matcher.find()){ 
			System.out.println(matcher.group(1).trim());
			int n = matcher.groupCount();
			for(int i=0;i<=n;i++){
				System.out.println(matcher.group(i).trim());
			}
			//System.out.println(matcher.group(1).trim());
			//System.out.println(matcher.group(3));
		} 
		/*
		String str = "if (departmentService.selectByCriteria(criteria).size() > 0) {";
		System.out.println(str.replaceAll("selectByCriteria\\(\\w+\\)", "selectByCondition(map)"));
		*/
	}

}
