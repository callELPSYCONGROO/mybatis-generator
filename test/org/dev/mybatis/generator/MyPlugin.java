package org.dev.mybatis.generator;


import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

public class MyPlugin extends PluginAdapter {

	public boolean validate(List<String> arg0) {
		return true;
	}

	@Override
	public boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {
		element.getAttributes().remove(2);
		element.addAttribute(new Attribute("parameterType", introspectedTable
				.getBaseRecordType()));
		return super.sqlMapSelectByPrimaryKeyElementGenerated(element,
				introspectedTable);
	}

	@Override
	public boolean sqlMapDeleteByPrimaryKeyElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {
		element.getAttributes().remove(1);
		element.addAttribute(new Attribute("parameterType", introspectedTable
				.getBaseRecordType()));
		return super.sqlMapDeleteByPrimaryKeyElementGenerated(element,
				introspectedTable);
	}

	@Override
	public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method,
			Interface interfaze, IntrospectedTable introspectedTable) {
		method.getParameters().remove(0);
		String type = introspectedTable.getBaseRecordType();
		method.addParameter(new Parameter(new FullyQualifiedJavaType(type),
				type.substring(type.lastIndexOf(".") + 1).toLowerCase()));
		return super.clientDeleteByPrimaryKeyMethodGenerated(method, interfaze,
				introspectedTable);
	}

	@Override
	public boolean clientSelectByPrimaryKeyMethodGenerated(Method method,
			Interface interfaze, IntrospectedTable introspectedTable) {
		method.getParameters().remove(0);
		String type = introspectedTable.getBaseRecordType();
		method.addParameter(new Parameter(new FullyQualifiedJavaType(type),
				type.substring(type.lastIndexOf(".") + 1).toLowerCase()));
		return super.clientSelectByPrimaryKeyMethodGenerated(method, interfaze,
				introspectedTable);
	}

	@Override
	public boolean sqlMapUpdateByCriteriaSelectiveElementGenerated(
			XmlElement element, IntrospectedTable introspectedTable) {
		element.getElements().remove(0);
		StringBuilder sb = new StringBuilder();
		sb.append("update ");
		sb.append(introspectedTable
				.getAliasedFullyQualifiedTableNameAtRuntime().replace(
						"${tablesite}", "${record.tablesite}"));
		element.addElement(0, new TextElement(sb.toString()));
		return super.sqlMapUpdateByCriteriaSelectiveElementGenerated(element,
				introspectedTable);
	}

	@Override
	public boolean sqlMapUpdateByCriteriaWithoutBLOBsElementGenerated(
			XmlElement element, IntrospectedTable introspectedTable) {
		element.getElements().remove(0);
		StringBuilder sb = new StringBuilder();
		sb.append("update ");
		sb.append(introspectedTable
				.getAliasedFullyQualifiedTableNameAtRuntime().replace(
						"${tablesite}", "${record.tablesite}"));
		element.addElement(0, new TextElement(sb.toString()));
		return super.sqlMapUpdateByCriteriaWithoutBLOBsElementGenerated(element,
				introspectedTable);
	}

	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		addTableSite(topLevelClass, introspectedTable, "tablesite");
		return super.modelBaseRecordClassGenerated(topLevelClass,
				introspectedTable);
	}

	@Override
	public boolean modelCriteriaClassGenerated(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		addTableSite(topLevelClass, introspectedTable, "tablesite");
		return super.modelCriteriaClassGenerated(topLevelClass,
				introspectedTable);
	}

	private void addTableSite(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable, String name) {
		CommentGenerator commentGenerator = context.getCommentGenerator();
		Field field = new Field();
		field.setVisibility(JavaVisibility.PROTECTED);
		field.setType(FullyQualifiedJavaType.getIntInstance());
		field.setName(name);
		field.setInitializationString("-1");
		commentGenerator.addFieldComment(field, introspectedTable);
		topLevelClass.addField(field);
		char c = name.charAt(0);
		String camel = Character.toUpperCase(c) + name.substring(1);
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("set" + camel);
		method.addParameter(new Parameter(FullyQualifiedJavaType
				.getIntInstance(), name));
		method.addBodyLine("this." + name + "=" + name + ";");
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		topLevelClass.addMethod(method);
		method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(FullyQualifiedJavaType.getIntInstance());
		method.setName("get" + camel);
		method.addBodyLine("return " + name + ";");
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		topLevelClass.addMethod(method);
	}

	@Override
	public boolean sqlMapBaseColumnListElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {
		List<Element> elist = element.getElements();
		int size = elist.size();
		Element e = elist.get(size - 1);
		String str = e.getFormattedContent(0);
		e = new TextElement(str + ", ${tablesite} AS tablesite");
		elist.remove(size - 1);
		elist.add(size - 1, e);
		return super.sqlMapBaseColumnListElementGenerated(element,
				introspectedTable);
	}

	@Override
	public boolean sqlMapResultMapWithoutBLOBsElementGenerated(
			XmlElement element, IntrospectedTable introspectedTable) {
		List<Element> elist = element.getElements();
		XmlElement xe = new XmlElement("result");
		xe.addAttribute(new Attribute("column", "tablesite"));
		xe.addAttribute(new Attribute("property", "tablesite"));
		xe.addAttribute(new Attribute("jdbcType", "INTEGER"));
		elist.add(xe);
		return super.sqlMapResultMapWithoutBLOBsElementGenerated(element,
				introspectedTable);
	}

	@Override
	public boolean sqlMapResultMapWithBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {
		List<Element> elist = element.getElements();
		XmlElement xe = new XmlElement("result");
		xe.addAttribute(new Attribute("column", "tablesite"));
		xe.addAttribute(new Attribute("property", "tablesite"));
		xe.addAttribute(new Attribute("jdbcType", "INTEGER"));
		elist.add(xe);
		return super.sqlMapResultMapWithoutBLOBsElementGenerated(element,
				introspectedTable);
	}
	
	
}
