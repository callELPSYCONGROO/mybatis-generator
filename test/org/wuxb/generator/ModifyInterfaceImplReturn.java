package org.wuxb.generator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModifyInterfaceImplReturn {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String javaFilePath = "D:/distributed/clsp/server-clsp/src/main/java/com/jhrz/clsp/server/app/service/impl/";
		//String[] files = new File(javaFilePath).list();
		String[] files = { "ActivityNewsServiceImpl.java" };
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
						if (tempString.contains("insert(") || tempString.contains("insertSelective(")) {
							String regEx = ".*[insert|insertSelective]\\((\\w+).*";
							Pattern pattern = Pattern.compile(regEx, Pattern.DOTALL);
							Matcher matcher = pattern.matcher(tempString);
							while (matcher.find()) {
								String typeName = matcher.group(1).trim();
								tempString = tempString.replace("int", typeName);
							}

						}
						sb.append(tempString + "\n");
						line++;
					}
					System.out.println(sb.toString());
					reader.close();

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter(file));
					out.write(sb.toString());
					out.close();
				} catch (IOException e) {
				}
			} else {
				System.out.println(fileName + " 不是一个Java源文件！");
			}
		}

	}

}
