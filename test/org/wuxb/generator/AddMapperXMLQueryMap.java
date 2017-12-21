package org.wuxb.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class AddMapperXMLQueryMap {

	public static void main(String[] args) {
		//XML中添加分页信息
		try {
			File[] files = new File("E:\\GeneratorCode\\sql-mapper").listFiles();
			generatorMapperXML(files);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//在Mapper接口中添加分页接口
		try {
			File[] files = new File("E:\\GeneratorCode\\com\\rzqc\\mazda\\mapper").listFiles();
			generatorMapperServiceMethod(files);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		try {
//			File[] files = new File("D:/workspaces/business/src/main/java/com/business/service/impl").listFiles();
//			generatorServiceImplMethod(files);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	private static void generatorServiceImplMethod(File[] files) throws IOException{
		for (File file : files) {
			if (FilenameUtils.isExtension(file.getName(), "java")&& !file.getName().contains("WeixinProcessServiceImpl")) {
				List<String> lines = FileUtils.readLines(file, "UTF-8");
				List<String> newList = new LinkedList<String>();
				String mapperName = null;
				for(int i=0;i<lines.size();i++){
					String line = lines.get(i);
					if(line.startsWith("import java.util.List;")){
						newList.add(line);
						newList.add("import java.util.Map;");
					}else {
						if(line.contains("private")){
							mapperName = line.split(" ")[1];
						}
						//System.out.println("mapperName =" + mapperName);
						if(i==(lines.size()-1) && mapperName !=null){
							newList.add("\r\n\t@Override");
							newList.add("\tpublic List<"+mapperName.replace("Mapper", "")+"> selectByPage(Map<String, Object> map) {");
							newList.add("\t\t// TODO Auto-generated method stub");
							newList.add("\t\treturn "+StringUtils.uncapitalize(mapperName)+".selectByPage(map);");
							newList.add("\t}");
							
							newList.add("\t@Override");
							newList.add("\tpublic List<"+mapperName.replace("Mapper", "")+"> selectByCondition(Map<String, Object> map) {");
							newList.add("\t\t// TODO Auto-generated method stub");
							newList.add("\t\treturn "+StringUtils.uncapitalize(mapperName)+".selectByCondition(map);");
							newList.add("\t}");
							
							newList.add("\t@Override");
							newList.add("\tpublic int countByCondition(Map<String, Object> map) {");
							newList.add("\t\t// TODO Auto-generated method stub");
							newList.add("\t\treturn "+StringUtils.uncapitalize(mapperName)+".countByCondition(map);");
							newList.add("\t}");
						}
						newList.add(line);
					}
				}
				FileUtils.writeLines(file, "UTF-8", newList);
			}else{
				System.out.println(file.getName() +" 不是java源文件！");
			}
			System.out.println("代码生成完成！");
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void generatorMapperServiceMethod(File[] files) throws IOException{
		for (File file : files) {
			if (FilenameUtils.isExtension(file.getName(), "java")) {
				List<String> lines = FileUtils.readLines(file, "UTF-8");
				List<String> newList = new LinkedList<String>();
				String modelName = null;
				for(String line : lines){
					if(line.startsWith("import java.util.List;")){
						newList.add(line);
						newList.add("import java.util.Map;");
					}else {
						if(line.contains("int insert("))modelName = line.substring(line.indexOf("(")+1, line.lastIndexOf(" "));
						if(line.equals("}")){
							if(modelName==null)modelName="?";
							newList.add("\r\n\tList<"+modelName+"> selectByPage(Map<String, Object> map);");
							newList.add("\r\n\tList<"+modelName+"> selectByCondition(Map<String, Object> map);");
							newList.add("\r\n\tint countByCondition(Map<String, Object> map);");
						}
						newList.add(line);
					}
				}
				FileUtils.writeLines(file, "UTF-8", newList);
				
			}else {
				System.out.println(file.getName() +" 不是java源文件！");
			}
		}
		System.out.println("Mapper文件添加分页查询方法完成！");
	}
	
	
	@SuppressWarnings("unchecked")
	private static void generatorMapperXML(File[] files) throws DocumentException, IOException {
		for (File file : files) {
			if (FilenameUtils.isExtension(file.getName(), "xml")) {
				SAXReader reader = new SAXReader();

				Document doc = reader.read(file);
				Element root = doc.getRootElement();

				// 处理insert插入时的，主键值更新返回值
				/*Element idElement = root.element("resultMap").element("id");
				Attribute idColumnAttr = idElement.attribute("column");
				String idColumnName = idColumnAttr.getValue();
				for (Iterator<Element> iter = root.elementIterator("insert"); iter.hasNext();) {
					Element element = iter.next();
					if (element.attribute("useGeneratedKeys") == null) {
						XMLWriter writer = null;// 声明写XML的对象
						OutputFormat format = OutputFormat.createPrettyPrint();
						format.setIndent(true);
						format.setIndent("\t");
						format.setLineSeparator("\n");
						format.setEncoding("UTF-8");
						element.addAttribute("useGeneratedKeys", "true");
						element.addAttribute("keyProperty", idColumnName);
						writer = new XMLWriter(new FileWriter(file), format);
						writer.write(doc);
						writer.close();
					}
				}*/

				XMLWriter writer = null;// 声明写XML的对象
				OutputFormat format = OutputFormat.createPrettyPrint();
				format.setExpandEmptyElements(true);
				//format.setTrimText(false);
				format.setIndent(true); // 设置是否缩进
				//format.setIndent("   "); // 以空格方式实现缩进
				format.setNewlines(true); // 设置是否换行
				//format.setSuppressDeclaration(true);
				//format.setNewLineAfterDeclaration(false);
				format.setEncoding("UTF-8");
				// 增加Map接口分页查询方法
				// 1、先增加一个查询where条件
				Element sqlElement = root.addElement("sql");
				sqlElement.addAttribute("id", "where_condition");
				Element trimElement = sqlElement.addElement("trim");
				trimElement.addAttribute("prefix", "WHERE");
				trimElement.addAttribute("prefixOverrides", "AND |OR ");
				String type = root.element("resultMap").attribute("type").getValue();
				for (Iterator<Element> iter = root.element("resultMap").elementIterator(); iter.hasNext();) {
					Element element = iter.next();
					Element ifElement = trimElement.addElement("if");
					String jdbcType =  element.attribute("jdbcType").getValue();
					switch (jdbcType) {
					case "BIGINT":
						ifElement.addAttribute("test", element.attribute("column").getValue() + " != null and " + element.attribute("column").getValue() + " != ''");
						ifElement.addText("  AND " + element.attribute("column").getValue() + " = #{" + element.attribute("column").getValue() + ",jdbcType=" + element.attribute("jdbcType").getValue() + "}");
						break;
					case "BIT":
						ifElement.addAttribute("test", element.attribute("column").getValue() + " != null and " + element.attribute("column").getValue() + " != ''");
						ifElement.addText("  AND " + element.attribute("column").getValue() + " = #{" + element.attribute("column").getValue() + ",jdbcType=" + element.attribute("jdbcType").getValue() + "}");
						break;
					case "INTEGER":
						ifElement.addAttribute("test", element.attribute("column").getValue() + " != null and " + element.attribute("column").getValue() + " != ''");
						ifElement.addText("  AND " + element.attribute("column").getValue() + " = #{" + element.attribute("column").getValue() + ",jdbcType=" + element.attribute("jdbcType").getValue() + "}");
						break;
					case "TINYINT":
						ifElement.addAttribute("test", element.attribute("column").getValue() + " != null and " + element.attribute("column").getValue() + " != ''");
						ifElement.addText("  AND " + element.attribute("column").getValue() + " = #{" + element.attribute("column").getValue() + ",jdbcType=" + element.attribute("jdbcType").getValue() + "}");
						break;
					case "VARCHAR":
						ifElement.addAttribute("test", element.attribute("column").getValue() + " != null and " + element.attribute("column").getValue() + " != ''");
						ifElement.addText("  AND " + element.attribute("column").getValue() + "  LIKE  CONCAT('%',#{" + element.attribute("column").getValue() + ",jdbcType=" + element.attribute("jdbcType").getValue() + "},'%' )");
						break;
					case "TIMESTAMP":
						ifElement.addAttribute("test", "startTime != null and startTime != '' and endTime != null and endTime != ''");
						ifElement.addText("  AND " + element.attribute("column").getValue() + "  BETWEEN #{startTime} AND #{endTime}");
						break;
					default:
						ifElement.addAttribute("test", element.attribute("column").getValue() + " != null and " + element.attribute("column").getValue() + " != ''");
						ifElement.addText("  AND " + element.attribute("column").getValue() + "  LIKE  CONCAT('%',#{" + element.attribute("column").getValue() + ",jdbcType=" + element.attribute("jdbcType").getValue() + "},'%' )");
						break;
					}
				}
				
				//获取表名
				String[] tempString = root.element("insert").getTextTrim().split(" ");
				String tableName = null;
				for(String str:tempString){
					if(str.toLowerCase().startsWith("ta_"))tableName=str;
				}
				System.out.println("表名："+ tableName);
				// 2、添加带分页的查询
				Element selectPageElement = root.addElement("select");
				selectPageElement.addAttribute("id", "selectByPage");
				selectPageElement.addAttribute("parameterType", "Map");
				selectPageElement.addAttribute("resultType", type);
				selectPageElement.addText("select <include refid=\"Base_Column_List\" /> \r\n from "+ tableName +" \r\n <include refid=\"where_condition\"/> \r\n <if test=\"sort != null and order != null\"> \r\n order by ${sort}  ${order}	\r\n</if>	\r\n<choose >\r\n	<when test=\"limitStart != null and pageSize != null and pageSize != 0\" > \r\n limit ${limitStart} , ${pageSize}\r\n	</when>	\r\n<when test=\"limitStart != null and limitStart != 0\" >\r\n  limit ${limitStart} \r\n</when> \r\n</choose>");

				//3、添加按条件统计查询
				Element countPageElement = root.addElement("select");
				countPageElement.addAttribute("id", "countByCondition");
				countPageElement.addAttribute("parameterType", "Map");
				countPageElement.addAttribute("resultType", "Integer");
				countPageElement.addText("select \r\n	count(*) \r\n    from "+ tableName +" \r\n		<include refid=\"where_condition\"/>");
				
				//4、添加按条件查询
				Element selectConditionElement = root.addElement("select");
				selectConditionElement.addAttribute("id", "selectByCondition");
				selectConditionElement.addAttribute("parameterType", "Map");
				selectConditionElement.addAttribute("resultType", type);
				selectConditionElement.addText("select <include refid=\"Base_Column_List\" /> \r\n from "+ tableName +" \r\n <include refid=\"where_condition\"/> \r\n <if test=\"sort != null and order != null\"> \r\n order by ${sort}  ${order}\r\n</if>");

				
				writer = new XMLWriter(new FileWriter(file), format);
				writer.setEscapeText(false);
				writer.write(doc);
				writer.close();
				System.out.println(file.getName() +"文件添加Map查询成功！");
				
			} else {
				System.out.println("文件类型不是xml文件");
			}
		}
	}
}
