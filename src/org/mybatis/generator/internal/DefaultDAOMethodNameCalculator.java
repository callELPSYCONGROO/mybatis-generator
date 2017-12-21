/*
 *  Copyright 2006 The Apache Software Foundation
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

package org.mybatis.generator.internal;

import org.mybatis.generator.api.DAOMethodNameCalculator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.internal.rules.Rules;

/**
 * @author Jeff Butler
 * 
 */
public class DefaultDAOMethodNameCalculator implements DAOMethodNameCalculator {

    /**
     * 
     */
    public DefaultDAOMethodNameCalculator() {
        super();
    }

    public String getInsertMethodName(IntrospectedTable introspectedTable) {
        return "insert"; //$NON-NLS-1$
    }

    /**
     * 1. if this will be the only updateByPrimaryKey, then the result should be
     * updateByPrimaryKey. 2. If the other method is enabled, but there are
     * seperate base and blob classes, then the method name should be
     * updateByPrimaryKey 3. Else the method name should be
     * updateByPrimaryKeyWithoutBLOBs
     */
    public String getUpdateByPrimaryKeyWithoutBLOBsMethodName(
            IntrospectedTable introspectedTable) {
        Rules rules = introspectedTable.getRules();

        if (!rules.generateUpdateByPrimaryKeyWithBLOBs()) {
            return "updateByPrimaryKey"; //$NON-NLS-1$
        } else if (rules.generateRecordWithBLOBsClass()) {
            return "updateByPrimaryKey"; //$NON-NLS-1$
        } else {
            return "updateByPrimaryKeyWithoutBLOBs"; //$NON-NLS-1$
        }
    }

    /**
     * 1. if this will be the only updateByPrimaryKey, then the result should be
     * updateByPrimaryKey. 2. If the other method is enabled, but there are
     * seperate base and blob classes, then the method name should be
     * updateByPrimaryKey 3. Else the method name should be
     * updateByPrimaryKeyWithBLOBs
     */
    public String getUpdateByPrimaryKeyWithBLOBsMethodName(
            IntrospectedTable introspectedTable) {
        Rules rules = introspectedTable.getRules();

        if (!rules.generateUpdateByPrimaryKeyWithoutBLOBs()) {
            return "updateByPrimaryKey"; //$NON-NLS-1$
        } else if (rules.generateRecordWithBLOBsClass()) {
            return "updateByPrimaryKey"; //$NON-NLS-1$
        } else {
            return "updateByPrimaryKeyWithBLOBs"; //$NON-NLS-1$
        }
    }

    public String getDeleteByCriteriaMethodName(
            IntrospectedTable introspectedTable) {
        return "deleteByCriteria"; //$NON-NLS-1$
    }

    public String getDeleteByPrimaryKeyMethodName(
            IntrospectedTable introspectedTable) {
        return "deleteByPrimaryKey"; //$NON-NLS-1$
    }

    /**
     * 1. if this will be the only selectByCriteria, then the result should be
     * selectByCriteria. 2. Else the method name should be
     * selectByCriteriaWithoutBLOBs
     */
    public String getSelectByCriteriaWithoutBLOBsMethodName(
            IntrospectedTable introspectedTable) {
        Rules rules = introspectedTable.getRules();

        if (!rules.generateSelectByCriteriaWithBLOBs()) {
            return "selectByCriteria"; //$NON-NLS-1$
        } else {
            return "selectByCriteriaWithoutBLOBs"; //$NON-NLS-1$
        }
    }

    /**
     * 1. if this will be the only selectByCriteria, then the result should be
     * selectByCriteria. 2. Else the method name should be
     * selectByCriteriaWithBLOBs
     */
    public String getSelectByCriteriaWithBLOBsMethodName(
            IntrospectedTable introspectedTable) {
        Rules rules = introspectedTable.getRules();

        if (!rules.generateSelectByCriteriaWithoutBLOBs()) {
            return "selectByCriteria"; //$NON-NLS-1$
        } else {
            return "selectByCriteriaWithBLOBs"; //$NON-NLS-1$
        }
    }

    public String getSelectByPrimaryKeyMethodName(
            IntrospectedTable introspectedTable) {
        return "selectByPrimaryKey"; //$NON-NLS-1$
    }

    public String getUpdateByPrimaryKeySelectiveMethodName(
            IntrospectedTable introspectedTable) {
        return "updateByPrimaryKeySelective"; //$NON-NLS-1$
    }

    public String getCountByCriteriaMethodName(
            IntrospectedTable introspectedTable) {
        return "countByCriteria"; //$NON-NLS-1$
    }

    public String getUpdateByCriteriaSelectiveMethodName(
            IntrospectedTable introspectedTable) {
        return "updateByCriteriaSelective"; //$NON-NLS-1$
    }

    public String getUpdateByCriteriaWithBLOBsMethodName(
            IntrospectedTable introspectedTable) {
        Rules rules = introspectedTable.getRules();

        if (!rules.generateUpdateByCriteriaWithoutBLOBs()) {
            return "updateByCriteria"; //$NON-NLS-1$
        } else if (rules.generateRecordWithBLOBsClass()) {
            return "updateByCriteria"; //$NON-NLS-1$
        } else {
            return "updateByCriteriaWithBLOBs"; //$NON-NLS-1$
        }
    }

    public String getUpdateByCriteriaWithoutBLOBsMethodName(
            IntrospectedTable introspectedTable) {
        Rules rules = introspectedTable.getRules();

        if (!rules.generateUpdateByCriteriaWithBLOBs()) {
            return "updateByCriteria"; //$NON-NLS-1$
        } else if (rules.generateRecordWithBLOBsClass()) {
            return "updateByCriteria"; //$NON-NLS-1$
        } else {
            return "updateByCriteriaWithoutBLOBs"; //$NON-NLS-1$
        }
    }

    public String getInsertSelectiveMethodName(
            IntrospectedTable introspectedTable) {
        return "insertSelective"; //$NON-NLS-1$
    }
}
