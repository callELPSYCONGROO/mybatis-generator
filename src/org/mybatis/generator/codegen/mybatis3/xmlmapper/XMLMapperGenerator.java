/*
 *  Copyright 2009 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.mybatis.generator.codegen.mybatis3.xmlmapper;

import static org.mybatis.generator.internal.util.messages.Messages.getString;

import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.XmlConstants;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.BaseColumnListElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.BlobColumnListElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.CountByCriteriaElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.CountByModelElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.DeleteByCriteriaElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.DeleteByPrimaryKeyElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.CriteriaWhereClauseElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.InsertElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.InsertSelectiveElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.ModelWhereClauseElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.ResultMapWithBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.ResultMapWithoutBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.SelectByCriteriaWithBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.SelectByCriteriaWithoutBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.SelectByModelWithBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.SelectByModelWithoutBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.SelectByPrimaryKeyElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByCriteriaSelectiveElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByCriteriaWithBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByCriteriaWithoutBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByPrimaryKeySelectiveElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByPrimaryKeyWithBLOBsElementGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.UpdateByPrimaryKeyWithoutBLOBsElementGenerator;

/**
 * 
 * @author Jeff Butler
 * 
 */
public class XMLMapperGenerator extends AbstractXmlGenerator {

    public XMLMapperGenerator() {
        super();
    }

    protected XmlElement getSqlMapElement() {
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        progressCallback.startTask(getString(
                "Progress.12", table.toString())); //$NON-NLS-1$
        XmlElement answer = new XmlElement("mapper"); //$NON-NLS-1$
        String namespace = introspectedTable.getMyBatis3SqlMapNamespace();
        answer.addAttribute(new Attribute("namespace", //$NON-NLS-1$
                namespace));

        context.getCommentGenerator().addRootComment(answer);

        addResultMapWithoutBLOBsElement(answer);
        addResultMapWithBLOBsElement(answer);
        addCriteriaWhereClauseElement(answer);
        addMyBatis3UpdateByCriteriaWhereClauseElement(answer);
        addBaseColumnListElement(answer);
        addBlobColumnListElement(answer);
        addSelectByCriteriaWithBLOBsElement(answer);
        addSelectByCriteriaWithoutBLOBsElement(answer);
        addSelectByPrimaryKeyElement(answer);
        addDeleteByPrimaryKeyElement(answer);
        addDeleteByCriteriaElement(answer);
        addInsertElement(answer);
        addInsertSelectiveElement(answer);
        addCountByCriteriaElement(answer);
        addUpdateByCriteriaSelectiveElement(answer);
        addUpdateByCriteriaWithBLOBsElement(answer);
        addUpdateByCriteriaWithoutBLOBsElement(answer);
        addUpdateByPrimaryKeySelectiveElement(answer);
        addUpdateByPrimaryKeyWithBLOBsElement(answer);
        addUpdateByPrimaryKeyWithoutBLOBsElement(answer);

        addModelWhereClauseElement(answer);
        addCountByModelElement(answer);
        addSelectByModelWithBLOBsElement(answer);
        addSelectByModelWithoutBLOBsElement(answer);
        
        return answer;
    }


	protected void addResultMapWithoutBLOBsElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateBaseResultMap()) {
            AbstractXmlElementGenerator elementGenerator = new ResultMapWithoutBLOBsElementGenerator(false);
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addResultMapWithBLOBsElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateResultMapWithBLOBs()) {
            AbstractXmlElementGenerator elementGenerator = new ResultMapWithBLOBsElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addCriteriaWhereClauseElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateSQLCriteriaWhereClause()) {
            AbstractXmlElementGenerator elementGenerator = new CriteriaWhereClauseElementGenerator(
                    false);
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }
    
    protected void addModelWhereClauseElement(XmlElement parentElement) {
    	if (introspectedTable.getRules().generateSQLModelWhereClause()) {
    		AbstractXmlElementGenerator elementGenerator = new ModelWhereClauseElementGenerator(
    				false);
    		initializeAndExecuteGenerator(elementGenerator, parentElement);
    	}
    }

    protected void addMyBatis3UpdateByCriteriaWhereClauseElement(
            XmlElement parentElement) {
        if (introspectedTable.getRules()
                .generateMyBatis3UpdateByCriteriaWhereClause()) {
            AbstractXmlElementGenerator elementGenerator = new CriteriaWhereClauseElementGenerator(
                    true);
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addBaseColumnListElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateBaseColumnList()) {
            AbstractXmlElementGenerator elementGenerator = new BaseColumnListElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addBlobColumnListElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateBlobColumnList()) {
            AbstractXmlElementGenerator elementGenerator = new BlobColumnListElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addSelectByCriteriaWithoutBLOBsElement(
            XmlElement parentElement) {
        if (introspectedTable.getRules().generateSelectByCriteriaWithoutBLOBs()) {
            AbstractXmlElementGenerator elementGenerator = new SelectByCriteriaWithoutBLOBsElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addSelectByCriteriaWithBLOBsElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateSelectByCriteriaWithBLOBs()) {
            AbstractXmlElementGenerator elementGenerator = new SelectByCriteriaWithBLOBsElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }
    protected void addSelectByModelWithoutBLOBsElement(
    		XmlElement parentElement) {
    	if (introspectedTable.getRules().generateSelectByModelWithoutBLOBs()) {
    		AbstractXmlElementGenerator elementGenerator = new SelectByModelWithoutBLOBsElementGenerator();
    		initializeAndExecuteGenerator(elementGenerator, parentElement);
    	}
    }
    
    protected void addSelectByModelWithBLOBsElement(XmlElement parentElement) {
    	if (introspectedTable.getRules().generateSelectByModelWithBLOBs()) {
    		AbstractXmlElementGenerator elementGenerator = new SelectByModelWithBLOBsElementGenerator();
    		initializeAndExecuteGenerator(elementGenerator, parentElement);
    	}
    }

    protected void addSelectByPrimaryKeyElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateSelectByPrimaryKey()) {
            AbstractXmlElementGenerator elementGenerator = new SelectByPrimaryKeyElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addDeleteByCriteriaElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateDeleteByCriteria()) {
            AbstractXmlElementGenerator elementGenerator = new DeleteByCriteriaElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addDeleteByPrimaryKeyElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateDeleteByPrimaryKey()) {
            AbstractXmlElementGenerator elementGenerator = new DeleteByPrimaryKeyElementGenerator(false);
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addInsertElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateInsert()) {
            AbstractXmlElementGenerator elementGenerator = new InsertElementGenerator(false);
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addInsertSelectiveElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateInsertSelective()) {
            AbstractXmlElementGenerator elementGenerator = new InsertSelectiveElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addCountByCriteriaElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateCountByCriteria()) {
            AbstractXmlElementGenerator elementGenerator = new CountByCriteriaElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }
    
    protected void addCountByModelElement(XmlElement parentElement) {
    	if (introspectedTable.getRules().generateCountByModel()) {
    		AbstractXmlElementGenerator elementGenerator = new CountByModelElementGenerator();
    		initializeAndExecuteGenerator(elementGenerator, parentElement);
    	}
    }

    protected void addUpdateByCriteriaSelectiveElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateUpdateByCriteriaSelective()) {
            AbstractXmlElementGenerator elementGenerator = new UpdateByCriteriaSelectiveElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addUpdateByCriteriaWithBLOBsElement(XmlElement parentElement) {
        if (introspectedTable.getRules().generateUpdateByCriteriaWithBLOBs()) {
            AbstractXmlElementGenerator elementGenerator = new UpdateByCriteriaWithBLOBsElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addUpdateByCriteriaWithoutBLOBsElement(
            XmlElement parentElement) {
        if (introspectedTable.getRules().generateUpdateByCriteriaWithoutBLOBs()) {
            AbstractXmlElementGenerator elementGenerator = new UpdateByCriteriaWithoutBLOBsElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addUpdateByPrimaryKeySelectiveElement(
            XmlElement parentElement) {
        if (introspectedTable.getRules().generateUpdateByPrimaryKeySelective()) {
            AbstractXmlElementGenerator elementGenerator = new UpdateByPrimaryKeySelectiveElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addUpdateByPrimaryKeyWithBLOBsElement(
            XmlElement parentElement) {
        if (introspectedTable.getRules().generateUpdateByPrimaryKeyWithBLOBs()) {
            AbstractXmlElementGenerator elementGenerator = new UpdateByPrimaryKeyWithBLOBsElementGenerator();
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void addUpdateByPrimaryKeyWithoutBLOBsElement(
            XmlElement parentElement) {
        if (introspectedTable.getRules()
                .generateUpdateByPrimaryKeyWithoutBLOBs()) {
            AbstractXmlElementGenerator elementGenerator = new UpdateByPrimaryKeyWithoutBLOBsElementGenerator(false);
            initializeAndExecuteGenerator(elementGenerator, parentElement);
        }
    }

    protected void initializeAndExecuteGenerator(
            AbstractXmlElementGenerator elementGenerator,
            XmlElement parentElement) {
        elementGenerator.setContext(context);
        elementGenerator.setIntrospectedTable(introspectedTable);
        elementGenerator.setProgressCallback(progressCallback);
        elementGenerator.setWarnings(warnings);
        elementGenerator.addElements(parentElement);
    }

    @Override
    public Document getDocument() {
        Document document = new Document(
                XmlConstants.MYBATIS3_MAPPER_PUBLIC_ID,
                XmlConstants.MYBATIS3_MAPPER_SYSTEM_ID);
        document.setRootElement(getSqlMapElement());

        if (!context.getPlugins().sqlMapDocumentGenerated(document,
                introspectedTable)) {
            document = null;
        }

        return document;
    }
}
