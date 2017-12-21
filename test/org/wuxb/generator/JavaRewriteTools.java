package org.wuxb.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class JavaRewriteTools {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String javaFilePath = "D:/distributed/clsp/client-weixin/src/main/java/com/jhrz/clsp/controller/manager/system/";
		// String[] files = new File(javaFilePath).list();
		String[] files = { "DepartmentController.java" };
		for (String fileName : files) {
			if (fileName.endsWith(".java")) {
				BufferedReader reader = null;
				StringBuffer sb = new StringBuffer();
				File file = new File(javaFilePath + fileName);
				try {
					reader = new BufferedReader(new FileReader(file));
					String tempString = null;
					int line = 1;
					while ((tempString = reader.readLine()) != null) {
						// 显示行号
						if (tempString.contains("Criteria();")
								&& !tempString.trim().startsWith("//")) {
							tempString = "//" + tempString+"\n";
							sb.append(tempString);
							tempString = "Map<String,Object> map = new HashMap<String,Object>();"+"\n";
						}

						if (tempString.contains("EqualTo(")
								&& !tempString.trim().startsWith("//")) {
							// System.out.println(tempString);
							// tempString = "//"+tempString;
							sb.append("//"+tempString+"\n");
							String[] strs = tempString.split("\\)\\.");
							for (String str : strs) {
								if (!str.endsWith(");"))str = str + ")";
								String regEx = ".*and(.*)(EqualTo)\\((.*)\\).*";
								Pattern pattern = Pattern.compile(regEx);
								Matcher matcher = pattern.matcher(str.trim());
								 while(matcher.find()){  
									String paraName = StringUtils.uncapitalize(matcher.group(1));
									String paraValue = matcher.group(3);
									System.out.println(paraName + " -- " + paraValue);
									if(!paraName.endsWith("Not")){
									     tempString = "map.put(\"" + paraName+ "\"," + paraValue + ");";
									}else {
										tempString = "---NotEqualTo 手动填充代码 ---";
									}
									sb.append(tempString+"\n");
								} 
							}
							// System.out.println("line " + line + ": " +
							// tempString);
						}else{
							tempString = tempString.replaceAll("selectByCriteria\\(\\w+\\)", "selectByCondition(map)");
							sb.append(tempString+"\n");
						}
						
						line++;
					}
					System.out.println(sb.toString());
					reader.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			} else {
				System.out.println(fileName + " 不是一个Java源文件！");
			}
		}

	}

}
