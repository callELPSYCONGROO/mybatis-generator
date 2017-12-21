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

package org.mybatis.generator.api;

/**
 * This interface is used to supply names for DAO methods. Users can provide
 * different implementations if the supplied implementations aren't sufficient.
 * 
 * @author Jeff Butler
 * 
 */
public interface DAOMethodNameCalculator {
    /**
     * Calculates and returns a name for the insert method.
     * 
     * @param introspectedTable
     * @return the calculated name
     */
    String getInsertMethodName(IntrospectedTable introspectedTable);

    /**
     * Calculates and returns a name for the insert selective method.
     * 
     * @param introspectedTable
     * @return the calculated name
     */
    String getInsertSelectiveMethodName(IntrospectedTable introspectedTable);

    /**
     * Calculates and returns a name for the update by primary key without BLOBs
     * method.
     * 
     * @param introspectedTable
     * @return the calculated name
     */
    String getUpdateByPrimaryKeyWithoutBLOBsMethodName(
            IntrospectedTable introspectedTable);

    /**
     * Calculates and returns a name for the update by primary key with BLOBs
     * method.
     * 
     * @param introspectedTable
     * @return the calculated name
     */
    String getUpdateByPrimaryKeyWithBLOBsMethodName(
            IntrospectedTable introspectedTable);

    /**
     * Calculates and returns a name for the update by primary key selective
     * method.
     * 
     * @param introspectedTable
     * @return the calculated name
     */
    String getUpdateByPrimaryKeySelectiveMethodName(
            IntrospectedTable introspectedTable);

    /**
     * Calculates and returns a name for the select by primary key method.
     * 
     * @param introspectedTable
     * @return the calculated name
     */
    String getSelectByPrimaryKeyMethodName(IntrospectedTable introspectedTable);

    /**
     * Calculates and returns a name for the select by criteria method.
     * 
     * @param introspectedTable
     * @return the calculated name
     */
    String getSelectByCriteriaWithoutBLOBsMethodName(
            IntrospectedTable introspectedTable);

    /**
     * Calculates and returns a name for the select by criteria with BLOBs
     * method. If the table contains BLOBs, then we will generate different
     * select by criteria methods - one including BLOBs, one not including BLOBs.
     * 
     * @param introspectedTable
     * @return the calculated name
     */
    String getSelectByCriteriaWithBLOBsMethodName(
            IntrospectedTable introspectedTable);

    /**
     * Calculates and returns a name for the delete by primary key method.
     * 
     * @param introspectedTable
     * @return the calculated name
     */
    String getDeleteByPrimaryKeyMethodName(IntrospectedTable introspectedTable);

    /**
     * Calculates and returns a name for the delete by criteria method.
     * 
     * @param introspectedTable
     * @return the calculated name
     */
    String getDeleteByCriteriaMethodName(IntrospectedTable introspectedTable);

    /**
     * Calculates and returns a name for the count by criteria method.
     * 
     * @param introspectedTable
     * @return the calculated name
     */
    String getCountByCriteriaMethodName(IntrospectedTable introspectedTable);

    /**
     * Calculates and returns a name for the update by criteria selective method.
     * 
     * @param introspectedTable
     * @return the calculated name
     */
    String getUpdateByCriteriaSelectiveMethodName(
            IntrospectedTable introspectedTable);

    /**
     * Calculates and returns a name for the update by criteria with BLOBs
     * method.
     * 
     * @param introspectedTable
     * @return the calculated name
     */
    String getUpdateByCriteriaWithBLOBsMethodName(
            IntrospectedTable introspectedTable);

    /**
     * Calculates and returns a name for the update by criteria without BLOBs
     * method.
     * 
     * @param introspectedTable
     * @return the calculated name
     */
    String getUpdateByCriteriaWithoutBLOBsMethodName(
            IntrospectedTable introspectedTable);
}
