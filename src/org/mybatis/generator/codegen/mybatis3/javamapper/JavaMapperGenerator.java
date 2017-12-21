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

package org.mybatis.generator.codegen.mybatis3.javamapper;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.CountByCriteriaMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.CountByModelMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.DeleteByCriteriaMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.DeleteByPrimaryKeyMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.InsertMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.InsertSelectiveMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.SelectByCriteriaWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.SelectByCriteriaWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.SelectByModelWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.SelectByModelWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.SelectByPrimaryKeyMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByCriteriaSelectiveMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByCriteriaWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByCriteriaWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByPrimaryKeySelectiveMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByPrimaryKeyWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByPrimaryKeyWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.XMLMapperGenerator;
import org.mybatis.generator.config.PropertyRegistry;

/**
 * @author Jeff Butler
 * 
 */
public class JavaMapperGenerator extends AbstractJavaClientGenerator {

    /**
     * 
     */
    public JavaMapperGenerator() {
        super(true);
    }

    public JavaMapperGenerator(boolean requiresMatchedXMLGenerator) {
        super(requiresMatchedXMLGenerator);
    }
    
    @Override
    public List<CompilationUnit> getCompilationUnits() {
        progressCallback.startTask(getString("Progress.17", //$NON-NLS-1$
                introspectedTable.getFullyQualifiedTable().toString()));
        CommentGenerator commentGenerator = context.getCommentGenerator();

        FullyQualifiedJavaType type = new FullyQualifiedJavaType(
                introspectedTable.getMyBatis3JavaMapperType());
        Interface interfaze = new Interface(type);
        interfaze.setVisibility(JavaVisibility.PUBLIC);
        commentGenerator.addJavaFileComment(interfaze);

        String rootInterface = introspectedTable
            .getTableConfigurationProperty(PropertyRegistry.ANY_ROOT_INTERFACE);
        if (!stringHasValue(rootInterface)) {
            rootInterface = context.getJavaClientGeneratorConfiguration()
                .getProperty(PropertyRegistry.ANY_ROOT_INTERFACE);
        }

        if (stringHasValue(rootInterface)) {
            FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType(
                    rootInterface);
            interfaze.addSuperInterface(fqjt);
            interfaze.addImportedType(fqjt);
        }
        
        addCountByCriteriaMethod(interfaze);
        addDeleteByCriteriaMethod(interfaze);
        addDeleteByPrimaryKeyMethod(interfaze);
        addInsertMethod(interfaze);
        addInsertSelectiveMethod(interfaze);
        addSelectByCriteriaWithBLOBsMethod(interfaze);
        addSelectByCriteriaWithoutBLOBsMethod(interfaze);
        addSelectByPrimaryKeyMethod(interfaze);
        addUpdateByCriteriaSelectiveMethod(interfaze);
        addUpdateByCriteriaWithBLOBsMethod(interfaze);
        addUpdateByCriteriaWithoutBLOBsMethod(interfaze);
        addUpdateByPrimaryKeySelectiveMethod(interfaze);
        addUpdateByPrimaryKeyWithBLOBsMethod(interfaze);
        addUpdateByPrimaryKeyWithoutBLOBsMethod(interfaze);
        
        addCountByModelMethod(interfaze);
        addSelectByModelWithBLOBsMethod(interfaze);
        addSelectByModelWithoutBLOBsMethod(interfaze);
//        addDeleteByModelMethod(interfaze);
//        addSelectByModelWithBLOBsMethod(interfaze);
//        addUpdateByModelSelectiveMethod(interfaze);
//        addUpdateByModelWithBLOBsMethod(interfaze);
//        addUpdateByModelWithoutBLOBsMethod(interfaze);
//        
//        addSelectPageByModelWithBLOBsMethod(interfaze);
//        addSelectPageByModelWithoutBLOBsMethod(interfaze);
//        
//        addSelectPageByCriteriaWithBLOBsMethod(interfaze);
//        addSelectPageByCriteriaWithoutBLOBsMethod(interfaze);
        
        

        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
        if (context.getPlugins().clientGenerated(interfaze, null,
                introspectedTable)) {
            answer.add(interfaze);
        }
        
        List<CompilationUnit> extraCompilationUnits = getExtraCompilationUnits();
        if (extraCompilationUnits != null) {
            answer.addAll(extraCompilationUnits);
        }

        return answer;
    }


    protected void addCountByModelMethod(Interface interfaze) {
    	 if (introspectedTable.getRules().generateCountByModel()) {
             AbstractJavaMapperMethodGenerator methodGenerator = new CountByModelMethodGenerator();
             initializeAndExecuteGenerator(methodGenerator, interfaze);
         }
	}

	protected void addCountByCriteriaMethod(Interface interfaze) {
        if (introspectedTable.getRules().generateCountByCriteria()) {
            AbstractJavaMapperMethodGenerator methodGenerator = new CountByCriteriaMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }

    protected void addDeleteByCriteriaMethod(Interface interfaze) {
        if (introspectedTable.getRules().generateDeleteByCriteria()) {
            AbstractJavaMapperMethodGenerator methodGenerator = new DeleteByCriteriaMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }

    protected void addDeleteByPrimaryKeyMethod(Interface interfaze) {
        if (introspectedTable.getRules().generateDeleteByPrimaryKey()) {
            AbstractJavaMapperMethodGenerator methodGenerator = new DeleteByPrimaryKeyMethodGenerator(false);
            initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }

    protected void addInsertMethod(Interface interfaze) {
        if (introspectedTable.getRules().generateInsert()) {
            AbstractJavaMapperMethodGenerator methodGenerator = new InsertMethodGenerator(false);
            initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }

    protected void addInsertSelectiveMethod(Interface interfaze) {
        if (introspectedTable.getRules().generateInsertSelective()) {
            AbstractJavaMapperMethodGenerator methodGenerator = new InsertSelectiveMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }

    protected void addSelectByCriteriaWithBLOBsMethod(Interface interfaze) {
        if (introspectedTable.getRules().generateSelectByCriteriaWithBLOBs()) {
            AbstractJavaMapperMethodGenerator methodGenerator = new SelectByCriteriaWithBLOBsMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }

    protected void addSelectByCriteriaWithoutBLOBsMethod(Interface interfaze) {
        if (introspectedTable.getRules().generateSelectByCriteriaWithoutBLOBs()) {
            AbstractJavaMapperMethodGenerator methodGenerator = new SelectByCriteriaWithoutBLOBsMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }
    

    protected void addSelectByModelWithoutBLOBsMethod(Interface interfaze) {
    	 if (introspectedTable.getRules().generateSelectByModelWithoutBLOBs()) {
             AbstractJavaMapperMethodGenerator methodGenerator = new SelectByModelWithoutBLOBsMethodGenerator();
             initializeAndExecuteGenerator(methodGenerator, interfaze);
         }
	}
    
    protected void addSelectByModelWithBLOBsMethod(Interface interfaze) {
    	if (introspectedTable.getRules().generateSelectByModelWithBLOBs()) {
    		AbstractJavaMapperMethodGenerator methodGenerator = new SelectByModelWithBLOBsMethodGenerator();
    		initializeAndExecuteGenerator(methodGenerator, interfaze);
    	}
    }

    protected void addSelectByPrimaryKeyMethod(Interface interfaze) {
        if (introspectedTable.getRules().generateSelectByPrimaryKey()) {
            AbstractJavaMapperMethodGenerator methodGenerator = new SelectByPrimaryKeyMethodGenerator(false);
            initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }

    protected void addUpdateByCriteriaSelectiveMethod(Interface interfaze) {
        if (introspectedTable.getRules().generateUpdateByCriteriaSelective()) {
            AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByCriteriaSelectiveMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }

    protected void addUpdateByCriteriaWithBLOBsMethod(Interface interfaze) {
        if (introspectedTable.getRules().generateUpdateByCriteriaWithBLOBs()) {
            AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByCriteriaWithBLOBsMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }

    protected void addUpdateByCriteriaWithoutBLOBsMethod(Interface interfaze) {
        if (introspectedTable.getRules().generateUpdateByCriteriaWithoutBLOBs()) {
            AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByCriteriaWithoutBLOBsMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }

    protected void addUpdateByPrimaryKeySelectiveMethod(Interface interfaze) {
        if (introspectedTable.getRules().generateUpdateByPrimaryKeySelective()) {
            AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByPrimaryKeySelectiveMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }

    protected void addUpdateByPrimaryKeyWithBLOBsMethod(Interface interfaze) {
        if (introspectedTable.getRules().generateUpdateByPrimaryKeyWithBLOBs()) {
            AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByPrimaryKeyWithBLOBsMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }

    protected void addUpdateByPrimaryKeyWithoutBLOBsMethod(Interface interfaze) {
        if (introspectedTable.getRules()
                .generateUpdateByPrimaryKeyWithoutBLOBs()) {
            AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByPrimaryKeyWithoutBLOBsMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }

    protected void initializeAndExecuteGenerator(
            AbstractJavaMapperMethodGenerator methodGenerator,
            Interface interfaze) {
        methodGenerator.setContext(context);
        methodGenerator.setIntrospectedTable(introspectedTable);
        methodGenerator.setProgressCallback(progressCallback);
        methodGenerator.setWarnings(warnings);
        methodGenerator.addInterfaceElements(interfaze);
    }

    public List<CompilationUnit> getExtraCompilationUnits() {
        return null;
    }

    @Override
    public AbstractXmlGenerator getMatchedXMLGenerator() {
        return new XMLMapperGenerator();
    }
}
