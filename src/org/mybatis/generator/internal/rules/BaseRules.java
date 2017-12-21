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
package org.mybatis.generator.internal.rules;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.IntrospectedTable.TargetRuntime;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.internal.util.StringUtility;

/**
 * This class centralizes all the rules related to code generation - including
 * the methods and objects to create, and certain attributes related to those
 * objects.
 * 
 * @author Jeff Butler
 */
public abstract class BaseRules implements Rules {

    protected TableConfiguration tableConfiguration;
    protected IntrospectedTable introspectedTable;
    protected final boolean isModelOnly;

    /**
     * 
     */
    public BaseRules(IntrospectedTable introspectedTable) {
        super();
        this.introspectedTable = introspectedTable;
        this.tableConfiguration = introspectedTable.getTableConfiguration();
        String modelOnly = tableConfiguration.getProperty(PropertyRegistry.TABLE_MODEL_ONLY);
        isModelOnly = StringUtility.isTrue(modelOnly);
    }

    /**
     * Implements the rule for generating the insert SQL Map element and DAO
     * method. If the insert statement is allowed, then generate the element and
     * method.
     * 
     * @return true if the element and method should be generated
     */
    public boolean generateInsert() {
        if (isModelOnly) {
            return false;
        }
        
        return tableConfiguration.isInsertStatementEnabled();
    }

    /**
     * Implements the rule for generating the insert selective SQL Map element
     * and DAO method. If the insert statement is allowed, then generate the
     * element and method.
     * 
     * @return true if the element and method should be generated
     */
    public boolean generateInsertSelective() {
        if (isModelOnly) {
            return false;
        }
        
        return tableConfiguration.isInsertStatementEnabled();
    }

    /**
     * Calculates the class that contains all fields. This class is used as the
     * insert statement parameter, as well as the returned value from the select
     * by primary key method. The actual class depends on how the domain model
     * is generated.
     * 
     * @return the type of the class that holds all fields
     */
    public FullyQualifiedJavaType calculateAllFieldsClass() {

        String answer;

        if (generateRecordWithBLOBsClass()) {
            answer = introspectedTable.getRecordWithBLOBsType();
        } else if (generateBaseRecordClass()) {
            answer = introspectedTable.getBaseRecordType();
        } else {
            answer = introspectedTable.getPrimaryKeyType();
        }

        return new FullyQualifiedJavaType(answer);
    }

    /**
     * Implements the rule for generating the update by primary key without
     * BLOBs SQL Map element and DAO method. If the table has a primary key as
     * well as other non-BLOB fields, and the updateByPrimaryKey statement is
     * allowed, then generate the element and method.
     * 
     * @return true if the element and method should be generated
     */
    public boolean generateUpdateByPrimaryKeyWithoutBLOBs() {
        if (isModelOnly) {
            return false;
        }
        
        boolean rc = tableConfiguration.isUpdateByPrimaryKeyStatementEnabled()
                && introspectedTable.hasPrimaryKeyColumns()
                && introspectedTable.hasBaseColumns();

        return rc;
    }

    /**
     * Implements the rule for generating the update by primary key with BLOBs
     * SQL Map element and DAO method. If the table has a primary key as well as
     * other BLOB fields, and the updateByPrimaryKey statement is allowed, then
     * generate the element and method.
     * 
     * @return true if the element and method should be generated
     */
    public boolean generateUpdateByPrimaryKeyWithBLOBs() {
        if (isModelOnly) {
            return false;
        }
        
        boolean rc = tableConfiguration.isUpdateByPrimaryKeyStatementEnabled()
                && introspectedTable.hasPrimaryKeyColumns()
                && introspectedTable.hasBLOBColumns();

        return rc;
    }

    /**
     * Implements the rule for generating the update by primary key selective
     * SQL Map element and DAO method. If the table has a primary key as well as
     * other fields, and the updateByPrimaryKey statement is allowed, then
     * generate the element and method.
     * 
     * @return true if the element and method should be generated
     */
    public boolean generateUpdateByPrimaryKeySelective() {
        if (isModelOnly) {
            return false;
        }
        
        boolean rc = tableConfiguration.isUpdateByPrimaryKeyStatementEnabled()
                && introspectedTable.hasPrimaryKeyColumns()
                && (introspectedTable.hasBLOBColumns() || introspectedTable
                        .hasBaseColumns());

        return rc;
    }

    /**
     * Implements the rule for generating the delete by primary key SQL Map
     * element and DAO method. If the table has a primary key, and the
     * deleteByPrimaryKey statement is allowed, then generate the element and
     * method.
     * 
     * @return true if the element and method should be generated
     */
    public boolean generateDeleteByPrimaryKey() {
        if (isModelOnly) {
            return false;
        }
        
        boolean rc = tableConfiguration.isDeleteByPrimaryKeyStatementEnabled()
                && introspectedTable.hasPrimaryKeyColumns();

        return rc;
    }

    /**
     * Implements the rule for generating the delete by criteria SQL Map element
     * and DAO method. If the deleteByCriteria statement is allowed, then
     * generate the element and method.
     * 
     * @return true if the element and method should be generated
     */
    public boolean generateDeleteByCriteria() {
        if (isModelOnly) {
            return false;
        }
        
        boolean rc = tableConfiguration.isDeleteByCriteriaStatementEnabled();

        return rc;
    }

    /**
     * Implements the rule for generating the result map without BLOBs. If
     * either select method is allowed, then generate the result map.
     * 
     * @return true if the result map should be generated
     */
    public boolean generateBaseResultMap() {
        if (isModelOnly) {
            return true;
        }
        
        boolean rc = tableConfiguration.isSelectByCriteriaStatementEnabled()
                || tableConfiguration.isSelectByPrimaryKeyStatementEnabled();

        return rc;
    }

    /**
     * Implements the rule for generating the result map with BLOBs. If the
     * table has BLOB columns, and either select method is allowed, then
     * generate the result map.
     * 
     * @return true if the result map should be generated
     */
    public boolean generateResultMapWithBLOBs() {
        boolean rc;
        
        if (introspectedTable.hasBLOBColumns()) {
            if (isModelOnly) {
                rc = true;
            } else {
                rc = tableConfiguration.isSelectByCriteriaStatementEnabled() 
                        || tableConfiguration.isSelectByPrimaryKeyStatementEnabled()
                	|| tableConfiguration.isSelectByModelStatementEnabled();
            }
        } else {
            rc = false;
        }
        
        return rc;
    }

    /**
     * Implements the rule for generating the SQL criteria where clause element.
     * 
     * In iBATIS2, generate the element if the selectByCriteria, deleteByCriteria,
     * updateByCriteria, or countByCriteria statements are allowed.
     * 
     * In MyBatis3, generate the element if the selectByCriteria,
     * deleteByCriteria, or countByCriteria statements are allowed.
     * 
     * @return true if the SQL where clause element should be generated
     */
    public boolean generateSQLCriteriaWhereClause() {
        if (isModelOnly) {
            return false;
        }
        
        boolean rc = tableConfiguration.isSelectByCriteriaStatementEnabled()
                || tableConfiguration.isDeleteByCriteriaStatementEnabled()
                || tableConfiguration.isCountByCriteriaStatementEnabled();

        if (introspectedTable.getTargetRuntime() == TargetRuntime.IBATIS2) {
            rc |= tableConfiguration.isUpdateByCriteriaStatementEnabled();
        }

        return rc;
    }
    
    /**
     * Implements the rule for generating the SQL model where clause element.
     * 
     * In iBATIS2, generate the element if the selectByCriteria, deleteByCriteria,
     * updateByCriteria, or countByCriteria statements are allowed.
     * 
     * In MyBatis3, generate the element if the selectByCriteria,
     * deleteByCriteria, or countByCriteria statements are allowed.
     * 
     * @return true if the SQL where clause element should be generated
     */
    public boolean generateSQLModelWhereClause() {
    	if (isModelOnly) {
    		return false;
    	}
    	
    	boolean rc = tableConfiguration.isSelectByModelStatementEnabled()
    			|| tableConfiguration.isDeleteByModelStatementEnabled()
    			|| tableConfiguration.isCountByModelStatementEnabled();
    	
    	if (introspectedTable.getTargetRuntime() == TargetRuntime.IBATIS2) {
    		rc |= tableConfiguration.isUpdateByModelStatementEnabled();
    	}
    	
    	return rc;
    }

    /**
     * Implements the rule for generating the SQL criteria where clause element
     * specifically for use in the update by criteria methods.
     * 
     * In iBATIS2, do not generate the element.
     * 
     * In MyBatis3, generate the element if the updateByCriteria statements are
     * allowed.
     * 
     * @return true if the SQL where clause element should be generated
     */
    public boolean generateMyBatis3UpdateByCriteriaWhereClause() {
        if (isModelOnly) {
            return false;
        }
        
        return introspectedTable.getTargetRuntime() == TargetRuntime.MYBATIS3
                && tableConfiguration.isUpdateByCriteriaStatementEnabled();
    }

    /**
     * Implements the rule for generating the select by primary key SQL Map
     * element and DAO method. If the table has a primary key as well as other
     * fields, and the selectByPrimaryKey statement is allowed, then generate
     * the element and method.
     * 
     * @return true if the element and method should be generated
     */
    public boolean generateSelectByPrimaryKey() {
        if (isModelOnly) {
            return false;
        }
        
        boolean rc = tableConfiguration.isSelectByPrimaryKeyStatementEnabled()
                && introspectedTable.hasPrimaryKeyColumns()
                && (introspectedTable.hasBaseColumns() || introspectedTable
                        .hasBLOBColumns());

        return rc;
    }

    /**
     * Implements the rule for generating the select by criteria without BLOBs
     * SQL Map element and DAO method. If the selectByCriteria statement is
     * allowed, then generate the element and method.
     * 
     * @return true if the element and method should be generated
     */
    public boolean generateSelectByCriteriaWithoutBLOBs() {
        if (isModelOnly) {
            return false;
        }
        
        return tableConfiguration.isSelectByCriteriaStatementEnabled();
    }
    /**
     * Implements the rule for generating the select by model without BLOBs
     * SQL Map element and DAO method. If the selectByCriteria statement is
     * allowed, then generate the element and method.
     * 
     * @return true if the element and method should be generated
     */
    public boolean generateSelectByModelWithoutBLOBs() {
    	if (isModelOnly) {
    		return false;
    	}
    	
    	return tableConfiguration.isSelectByModelStatementEnabled();
    }

    /**
     * Implements the rule for generating the select by criteria with BLOBs SQL
     * Map element and DAO method. If the table has BLOB fields and the
     * selectByCriteria statement is allowed, then generate the element and
     * method.
     * 
     * @return true if the element and method should be generated
     */
    public boolean generateSelectByCriteriaWithBLOBs() {
        if (isModelOnly) {
            return false;
        }
        
        boolean rc = tableConfiguration.isSelectByCriteriaStatementEnabled()
                && introspectedTable.hasBLOBColumns();

        return rc;
    }
    
    /**
     * Implements the rule for generating the select by criteria with BLOBs SQL
     * Map element and DAO method. If the table has BLOB fields and the
     * selectByCriteria statement is allowed, then generate the element and
     * method.
     * 
     * @return true if the element and method should be generated
     */
    public boolean generateSelectByModelWithBLOBs() {
    	if (isModelOnly) {
    		return false;
    	}
    	
    	boolean rc = tableConfiguration.isSelectByModelStatementEnabled()
    			&& introspectedTable.hasBLOBColumns();
    	
    	return rc;
    }

    /**
     * Implements the rule for generating an criteria class. The class should be
     * generated if the selectByCriteria or deleteByCriteria or countByCriteria
     * methods are allowed.
     * 
     * @return true if the criteria class should be generated
     */
    public boolean generateCriteriaClass() {
        if (introspectedTable.getContext().getSqlMapGeneratorConfiguration() == null
                && introspectedTable.getContext().getJavaClientGeneratorConfiguration() == null) {
            // this is a model only context - don't generate the criteria class
            return false;
        }
        
        if (isModelOnly) {
            return false;
        }
        
        boolean rc = tableConfiguration.isSelectByCriteriaStatementEnabled()
                || tableConfiguration.isDeleteByCriteriaStatementEnabled()
                || tableConfiguration.isCountByCriteriaStatementEnabled()
                || tableConfiguration.isUpdateByCriteriaStatementEnabled();

        return rc;
    }

    public boolean generateCountByCriteria() {
        if (isModelOnly) {
            return false;
        }
        
        boolean rc = tableConfiguration.isCountByCriteriaStatementEnabled();

        return rc;
    }
    
    public boolean generateCountByModel() {
    	
    	boolean rc = tableConfiguration.isCountByModelStatementEnabled();
    	
    	return rc;
    }

    public boolean generateUpdateByCriteriaSelective() {
        if (isModelOnly) {
            return false;
        }
        
        boolean rc = tableConfiguration.isUpdateByCriteriaStatementEnabled();

        return rc;
    }

    public boolean generateUpdateByCriteriaWithoutBLOBs() {
        if (isModelOnly) {
            return false;
        }
        
        boolean rc = tableConfiguration.isUpdateByCriteriaStatementEnabled()
                && (introspectedTable.hasPrimaryKeyColumns() || introspectedTable
                        .hasBaseColumns());

        return rc;
    }

    public boolean generateUpdateByCriteriaWithBLOBs() {
        if (isModelOnly) {
            return false;
        }
        
        boolean rc = tableConfiguration.isUpdateByCriteriaStatementEnabled()
                && introspectedTable.hasBLOBColumns();

        return rc;
    }

    public IntrospectedTable getIntrospectedTable() {
        return introspectedTable;
    }

    public boolean generateBaseColumnList() {
        if (isModelOnly) {
            return false;
        }
        
        return generateSelectByPrimaryKey()
                || generateSelectByCriteriaWithoutBLOBs();
    }

    public boolean generateBlobColumnList() {
        if (isModelOnly) {
            return false;
        }
        
        return introspectedTable.hasBLOBColumns()
                && (tableConfiguration.isSelectByCriteriaStatementEnabled() || tableConfiguration
                        .isSelectByPrimaryKeyStatementEnabled());
    }

    public boolean generateJavaClient() {
        return !isModelOnly;
    }
}
