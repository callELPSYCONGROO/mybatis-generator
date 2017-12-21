package org.wuxb.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class GeneratorDubboConfigXML {

	public static void main(String[] args) throws DocumentException, IOException {
		// TODO Auto-generated method stub
	/*	
		String xmlFile = "D:/distributed/clsp/server-clsp/src/java/resources/config/spring/spring-dubbo-server.xml";
		String servicePackagePath = "D:/distributed/clsp/common-interface/src/main/java/com/jhrz/clsp/common/interfaces/weixin";
		String packageName = "com.jhrz.clsp.common.interfaces.weixin";
		generateServerConfig(xmlFile, servicePackagePath, packageName);
		*/
		String xmlFile = "D:/distributed/clsp/client-weixin/src/main/resources/config/spring/spring-client-weixin.xml";
		String servicePackagePath = "D:/distributed/clsp/common-interface/src/main/java/com/jhrz/clsp/common/interfaces/base";
		String packageName = "com.jhrz.clsp.common.interfaces.base";
		generateClientConfig(xmlFile, servicePackagePath, packageName);
	}
	
	private static void generateServerConfig(String xmlFile,String servicePackagePath,String packageName) throws DocumentException, IOException{
		File file = new File(xmlFile);
		if (FilenameUtils.isExtension(file.getName(), "xml")) {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(file);
			Element root = doc.getRootElement();
			XMLWriter writer = null;// 声明写XML的对象
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setExpandEmptyElements(true);
			// format.setTrimText(false);
			format.setIndent(true); // 设置是否缩进
			// format.setIndent("   "); // 以空格方式实现缩进
			format.setNewlines(true); // 设置是否换行
			// format.setSuppressDeclaration(true);
			// format.setNewLineAfterDeclaration(false);
			format.setEncoding("UTF-8");

			File f = new File(servicePackagePath);
			if (f.isDirectory()) {
				String[] files = f.list();
				for (String fileName : files) {
					if (fileName.endsWith(".java")) {
						Element dubboElement = root.addElement("dubbo:service");
						dubboElement.addAttribute("interface", packageName
								+ "." + StringUtils.stripEnd(fileName, ".java"));
						dubboElement.addAttribute("ref",
								StringUtils.uncapitalize(StringUtils.stripEnd(fileName, ".java")));
					}
				}

			} else {
				System.out.println("错误：接口包路径不是一个有效的目录！");
			}
			writer = new XMLWriter(new FileWriter(file), format);
			writer.setEscapeText(false);
			writer.write(doc);
			writer.close();
			System.out.println("dubbo配置文件生成完成！");
		}
	}
	
	public static void generateClientConfig(String xmlFile,String servicePackagePath,String packageName) throws DocumentException, IOException{
		File file = new File(xmlFile);
		if (FilenameUtils.isExtension(file.getName(), "xml")) {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(file);
			Element root = doc.getRootElement();
			XMLWriter writer = null;// 声明写XML的对象
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setExpandEmptyElements(true);
			// format.setTrimText(false);
			format.setIndent(true); // 设置是否缩进
			// format.setIndent("   "); // 以空格方式实现缩进
			format.setNewlines(true); // 设置是否换行
			// format.setSuppressDeclaration(true);
			// format.setNewLineAfterDeclaration(false);
			format.setEncoding("UTF-8");

			File f = new File(servicePackagePath);
			if (f.isDirectory()) {
				String[] files = f.list();
				for (String fileName : files) {
					if (fileName.endsWith(".java")) {
						Element dubboElement = root.addElement("dubbo:reference");
						dubboElement.addAttribute("id",
								StringUtils.uncapitalize(StringUtils.stripEnd(fileName, ".java")));
						dubboElement.addAttribute("interface", packageName
								+ "." + StringUtils.stripEnd(fileName, ".java"));
					}
				}

			} else {
				System.out.println("错误：接口包路径不是一个有效的目录！");
			}
			writer = new XMLWriter(new FileWriter(file), format);
			writer.setEscapeText(false);
			writer.write(doc);
			writer.close();
			System.out.println("dubbo配置文件生成完成！");
		}
	}
}
