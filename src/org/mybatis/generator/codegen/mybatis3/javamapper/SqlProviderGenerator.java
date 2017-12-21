/*
 *  Copyright 2010 The MyBatis Team
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

import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.AbstractJavaProviderMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderApplyWhereMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderCountByCriteriaMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderDeleteByCriteriaMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderInsertSelectiveMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderSelectByCriteriaWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderSelectByCriteriaWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderUpdateByCriteriaSelectiveMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderUpdateByCriteriaWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderUpdateByCriteriaWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderUpdateByPrimaryKeySelectiveMethodGenerator;

/**
 * 
 * @author Jeff Butler
 * 
 */
public class SqlProviderGenerator extends AbstractJavaGenerator {

    public SqlProviderGenerator() {
        super();
    }

    @Override
    public List<CompilationUnit> getCompilationUnits() {
        progressCallback.startTask(getString("Progress.18", //$NON-NLS-1$
                introspectedTable.getFullyQualifiedTable().toString()));
        CommentGenerator commentGenerator = context.getCommentGenerator();

        FullyQualifiedJavaType type = new FullyQualifiedJavaType(
                introspectedTable.getMyBatis3SqlProviderType());
        TopLevelClass topLevelClass = new TopLevelClass(type);
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        commentGenerator.addJavaFileComment(topLevelClass);

        boolean addApplyWhereMethod = false;
        addApplyWhereMethod |= addCountByCriteriaMethod(topLevelClass);
        addApplyWhereMethod |= addDeleteByCriteriaMethod(topLevelClass);
        addInsertSelectiveMethod(topLevelClass);
        addApplyWhereMethod |= addSelectByCriteriaWithBLOBsMethod(topLevelClass);
        addApplyWhereMethod |= addSelectByCriteriaWithoutBLOBsMethod(topLevelClass);
        addApplyWhereMethod |= addUpdateByCriteriaSelectiveMethod(topLevelClass);
        addApplyWhereMethod |= addUpdateByCriteriaWithBLOBsMethod(topLevelClass);
        addApplyWhereMethod |= addUpdateByCriteriaWithoutBLOBsMethod(topLevelClass);
        addUpdateByPrimaryKeySelectiveMethod(topLevelClass);

        if (addApplyWhereMethod) {
            addApplyWhereMethod(topLevelClass);
        }
        
        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
        
        if (topLevelClass.getMethods().size() > 0) {
            if (context.getPlugins().providerGenerated(topLevelClass,
                introspectedTable)) {
                answer.add(topLevelClass);
            }
        }

        return answer;
    }

    protected boolean addCountByCriteriaMethod(TopLevelClass topLevelClass) {
        boolean rc = false;
        if (introspectedTable.getRules().generateCountByCriteria()) {
            AbstractJavaProviderMethodGenerator methodGenerator = new ProviderCountByCriteriaMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass);
            rc = true;
        }

        return rc;
    }

    protected boolean addDeleteByCriteriaMethod(TopLevelClass topLevelClass) {
        boolean rc = false;
        if (introspectedTable.getRules().generateDeleteByCriteria()) {
            AbstractJavaProviderMethodGenerator methodGenerator = new ProviderDeleteByCriteriaMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass);
            rc = true;
        }

        return rc;
    }

    protected void addInsertSelectiveMethod(TopLevelClass topLevelClass) {
        if (introspectedTable.getRules().generateInsertSelective()) {
            AbstractJavaProviderMethodGenerator methodGenerator = new ProviderInsertSelectiveMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass);
        }
    }

    protected boolean addSelectByCriteriaWithBLOBsMethod(
            TopLevelClass topLevelClass) {
        boolean rc = false;
        if (introspectedTable.getRules().generateSelectByCriteriaWithBLOBs()) {
            AbstractJavaProviderMethodGenerator methodGenerator = new ProviderSelectByCriteriaWithBLOBsMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass);
            rc = true;
        }

        return rc;
    }

    protected boolean addSelectByCriteriaWithoutBLOBsMethod(
            TopLevelClass topLevelClass) {
        boolean rc = false;
        if (introspectedTable.getRules().generateSelectByCriteriaWithoutBLOBs()) {
            AbstractJavaProviderMethodGenerator methodGenerator = new ProviderSelectByCriteriaWithoutBLOBsMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass);
            rc = true;
        }

        return rc;
    }

    protected boolean addUpdateByCriteriaSelectiveMethod(
            TopLevelClass topLevelClass) {
        boolean rc = false;
        if (introspectedTable.getRules().generateUpdateByCriteriaSelective()) {
            AbstractJavaProviderMethodGenerator methodGenerator = new ProviderUpdateByCriteriaSelectiveMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass);
            rc = true;
        }

        return rc;
    }

    protected boolean addUpdateByCriteriaWithBLOBsMethod(
            TopLevelClass topLevelClass) {
        boolean rc = false;
        if (introspectedTable.getRules().generateUpdateByCriteriaWithBLOBs()) {
            AbstractJavaProviderMethodGenerator methodGenerator = new ProviderUpdateByCriteriaWithBLOBsMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass);
            rc = true;
        }

        return rc;
    }

    protected boolean addUpdateByCriteriaWithoutBLOBsMethod(
            TopLevelClass topLevelClass) {
        boolean rc = false;
        if (introspectedTable.getRules().generateUpdateByCriteriaWithoutBLOBs()) {
            AbstractJavaProviderMethodGenerator methodGenerator = new ProviderUpdateByCriteriaWithoutBLOBsMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass);
            rc = true;
        }

        return rc;
    }

    protected void addUpdateByPrimaryKeySelectiveMethod(
            TopLevelClass topLevelClass) {
        if (introspectedTable.getRules().generateUpdateByPrimaryKeySelective()) {
            AbstractJavaProviderMethodGenerator methodGenerator = new ProviderUpdateByPrimaryKeySelectiveMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass);
        }
    }

    protected void addApplyWhereMethod(TopLevelClass topLevelClass) {
        AbstractJavaProviderMethodGenerator methodGenerator = new ProviderApplyWhereMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator, topLevelClass);
    }

    protected void initializeAndExecuteGenerator(
            AbstractJavaProviderMethodGenerator methodGenerator,
            TopLevelClass topLevelClass) {
        methodGenerator.setContext(context);
        methodGenerator.setIntrospectedTable(introspectedTable);
        methodGenerator.setProgressCallback(progressCallback);
        methodGenerator.setWarnings(warnings);
        methodGenerator.addClassElements(topLevelClass);
    }
}
