package org.wuxb.generator;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

public class GeneratorServiceByMapperFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//文件路径
		String mapperPath = "E:\\GeneratorCode\\com\\vcyber\\rzqc\\mapper";
		String servicePath = "E:\\GeneratorCode\\com\\vcyber\\rzqc\\service";
		//基本包路径（子包 domain/service/mapper）
		String basePackage = "com.vcyber.rzqc";

		// 1.生成Service接口层
		generatorServiceByMapper(mapperPath, servicePath,basePackage);
		
		//2.生成controller层
	}

	private static void generatorServiceByMapper(String mapperPath, String servicePath,String basePackage) {
		File file = new File(mapperPath);
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files) {
				String fileName = f.getName();
				if (FilenameUtils.isExtension(fileName, "java")) {
					String destFileName = servicePath + File.separator + fileName.replace("Mapper", "Service");
					try {
						String content = FileUtils.readFileToString(f, "utf-8");
						FileUtils.writeStringToFile(new File(destFileName), content.replace("mapper", "service").replace("Mapper", "Service"), "utf-8");
						
						String serviceImplFileName = servicePath + File.separator+ "impl"+File.separator+fileName.replace("Mapper", "ServiceImpl");
						//生成实现类
						StringBuffer sb = new StringBuffer();
						String domainName = FilenameUtils.getBaseName(fileName).replace("Mapper", "");
						sb.append("package ").append(basePackage).append(".service.impl;");
						sb.append("\n").append("\n");
						sb.append("import java.util.List;");
						sb.append("\n").append("\n");
						sb.append("import javax.annotation.Resource;");
						sb.append("\n").append("\n");
						sb.append("import org.springframework.stereotype.Service;");
						sb.append("\n").append("\n");
						sb.append("import ").append(basePackage).append(".domain.").append(domainName).append(";");
						sb.append("\n");
						sb.append("import ").append(basePackage).append(".domain.").append(domainName).append("Criteria;");
						sb.append("\n");
						sb.append("import ").append(basePackage).append(".mapper.").append(domainName).append("Mapper;");
						sb.append("\n");
						sb.append("import ").append(basePackage).append(".service.").append(domainName).append("Service;");
						sb.append("\n").append("\n");
						sb.append("@Service(\"").append(StringUtils.uncapitalize(domainName)).append("Service\")");
						sb.append("\n");
						sb.append("public class ").append(domainName).append("ServiceImpl implements ").append(domainName).append("Service {");
						sb.append("\n").append("\n");
						sb.append("\t").append("@Resource");
						sb.append("\n");
						sb.append("\t").append("private ").append(domainName).append("Mapper ").append(StringUtils.uncapitalize(domainName)).append("Mapper;");
						sb.append("\n").append("\n");
						sb.append("\t").append("@Override");
						sb.append("\n");
						sb.append("\t").append("public int countByCriteria(").append(domainName).append("Criteria criteria) {");
						sb.append("\n");
						sb.append("\t").append("\t").append("return ").append(StringUtils.uncapitalize(domainName)).append("Mapper.countByCriteria(criteria);");
						sb.append("\t").append("}");
						sb.append("\n").append("\n");
						
						sb.append("\t").append("@Override");
						sb.append("\n");
						sb.append("\t").append("public int deleteByCriteria(").append(domainName).append("Criteria criteria) {");
						sb.append("\n");
						sb.append("\t").append("\t").append("return ").append(StringUtils.uncapitalize(domainName)).append("Mapper.deleteByCriteria(criteria);");
						sb.append("\t").append("}");
						sb.append("\n").append("\n");
						
						sb.append("\t").append("@Override");
						sb.append("\n");
						sb.append("\t").append("public int deleteByPrimaryKey(Long id) {");
						sb.append("\n");
						sb.append("\t").append("\t").append("return ").append(StringUtils.uncapitalize(domainName)).append("Mapper.deleteByPrimaryKey(id);");
						sb.append("\t").append("}");
						sb.append("\n").append("\n");
						
						
						sb.append("\t").append("@Override");
						sb.append("\n");
						sb.append("\t").append("public int insert(").append(domainName).append(" record) {");
						sb.append("\n");
						sb.append("\t").append("\t").append("return ").append(StringUtils.uncapitalize(domainName)).append("Mapper.insert(record);");
						sb.append("\t").append("}");
						sb.append("\n").append("\n");
						
						sb.append("\t").append("@Override");
						sb.append("\n");
						sb.append("\t").append("public int insertSelective(").append(domainName).append(" record) {");
						sb.append("\n");
						sb.append("\t").append("\t").append("return ").append(StringUtils.uncapitalize(domainName)).append("Mapper.insertSelective(record);");
						sb.append("\t").append("}");
						sb.append("\n").append("\n");
						
						sb.append("\t").append("@Override");
						sb.append("\n");
						sb.append("\t").append("public List<").append(domainName).append("> selectByCriteria(").append(domainName).append("Criteria criteria) {");
						sb.append("\n");
						sb.append("\t").append("\t").append("return ").append(StringUtils.uncapitalize(domainName)).append("Mapper.selectByCriteria(criteria);");
						sb.append("\t").append("}");
						sb.append("\n").append("\n");
						
						sb.append("\t").append("@Override");
						sb.append("\n");
						sb.append("\t").append("public ").append(domainName).append(" selectByPrimaryKey(Long id) {");
						sb.append("\n");
						sb.append("\t").append("\t").append("return ").append(StringUtils.uncapitalize(domainName)).append("Mapper.selectByPrimaryKey(id);");
						sb.append("\t").append("}");
						sb.append("\n").append("\n");
						
						sb.append("\t").append("@Override");
						sb.append("\n");
						sb.append("\t").append("public int updateByCriteriaSelective(").append(domainName).append(" record, ").append(domainName).append("Criteria criteria) {");
						sb.append("\n");
						sb.append("\t").append("\t").append("return ").append(StringUtils.uncapitalize(domainName)).append("Mapper.updateByCriteriaSelective(record, criteria);");
						sb.append("\t").append("}");
						sb.append("\n").append("\n");
						
						sb.append("\t").append("@Override");
						sb.append("\n");
						sb.append("\t").append("public int updateByCriteria(").append(domainName).append(" record, ").append(domainName).append("Criteria criteria) {");
						sb.append("\n");
						sb.append("\t").append("\t").append("return ").append(StringUtils.uncapitalize(domainName)).append("Mapper.updateByCriteria(record, criteria);");
						sb.append("\t").append("}");
						sb.append("\n").append("\n");
						
						sb.append("\t").append("@Override");
						sb.append("\n");
						sb.append("\t").append("public int updateByPrimaryKeySelective(").append(domainName).append(" record) {");
						sb.append("\n");
						sb.append("\t").append("\t").append("return ").append(StringUtils.uncapitalize(domainName)).append("Mapper.updateByPrimaryKeySelective(record);");
						sb.append("\t").append("}");
						sb.append("\n").append("\n");
						
						sb.append("\t").append("@Override");
						sb.append("\n");
						sb.append("\t").append("public int updateByPrimaryKey(").append(domainName).append(" record) {");
						sb.append("\n");
						sb.append("\t").append("\t").append("return ").append(StringUtils.uncapitalize(domainName)).append("Mapper.updateByPrimaryKey(record);");
						sb.append("\t").append("}");
						sb.append("\n").append("\n");
						
						sb.append("\t").append("@Override");
						sb.append("\n");
						sb.append("\t").append("public int countByModel(").append(domainName).append(" model) {");
						sb.append("\n");
						sb.append("\t").append("\t").append("return ").append(StringUtils.uncapitalize(domainName)).append("Mapper.countByModel(model);");
						sb.append("\t").append("}");
						sb.append("\n").append("\n");
						
						sb.append("\t").append("@Override");
						sb.append("\n");
						sb.append("\t").append("public List<").append(domainName).append("> selectByModel(").append(domainName).append(" model) {");
						sb.append("\n");
						sb.append("\t").append("\t").append("return ").append(StringUtils.uncapitalize(domainName)).append("Mapper.selectByModel(model);");
						sb.append("\t").append("}");
						sb.append("\n").append("\n");
						
						sb.append("}");
						
						FileUtils.writeStringToFile(new File(serviceImplFileName), sb.toString(), "utf-8");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			System.out.println("Service层代码文件生成成功！");
		}
	}
}
