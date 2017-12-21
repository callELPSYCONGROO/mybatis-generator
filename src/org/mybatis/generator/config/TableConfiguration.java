/*
 *  Copyright 2005 The Apache Software Foundation
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
package org.mybatis.generator.config;

import static org.mybatis.generator.internal.util.EqualsUtil.areEqual;
import static org.mybatis.generator.internal.util.HashCodeUtil.hash;
import static org.mybatis.generator.internal.util.HashCodeUtil.SEED;
import static org.mybatis.generator.internal.util.messages.Messages.getString;
import static org.mybatis.generator.internal.util.StringUtility.composeFullyQualifiedTableName;
import static org.mybatis.generator.internal.util.StringUtility.isTrue;
import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * 
 * @author Jeff Butler
 */
public class TableConfiguration extends PropertyHolder {
    private boolean insertStatementEnabled;

    private boolean selectByPrimaryKeyStatementEnabled;

    private boolean selectByCriteriaStatementEnabled;
    
    private boolean selectByModelStatementEnabled;

    private boolean updateByPrimaryKeyStatementEnabled;

    private boolean deleteByPrimaryKeyStatementEnabled;

    private boolean deleteByCriteriaStatementEnabled;
    
    private boolean deleteByModelStatementEnabled;

    private boolean countByCriteriaStatementEnabled;
    
    private boolean countByModelStatementEnabled;

    private boolean updateByCriteriaStatementEnabled;
    
    private boolean updateByModelStatementEnabled;

    private List<ColumnOverride> columnOverrides;

    private Map<IgnoredColumn, Boolean> ignoredColumns;

    private GeneratedKey generatedKey;

    private String selectByPrimaryKeyQueryId;

    private String selectByCriteriaQueryId;
    
    private String selectByModelQueryId;

    private String catalog;
    private String schema;
    private String tableName;
    private String domainObjectName;
    private String alias;
    private ModelType modelType;
    private boolean wildcardEscapingEnabled;
    private String configuredModelType;
    private boolean delimitIdentifiers;

    private ColumnRenamingRule columnRenamingRule;
    private boolean isAllColumnDelimitingEnabled;

    public TableConfiguration(Context context) {
        super();

        this.modelType = context.getDefaultModelType();

        columnOverrides = new ArrayList<ColumnOverride>();
        ignoredColumns = new HashMap<IgnoredColumn, Boolean>();

        insertStatementEnabled = true;
        selectByPrimaryKeyStatementEnabled = true;
        selectByCriteriaStatementEnabled = true;
        selectByCriteriaStatementEnabled = true;
        selectByModelStatementEnabled = true;
        updateByPrimaryKeyStatementEnabled = true;
        deleteByPrimaryKeyStatementEnabled = true;
        deleteByCriteriaStatementEnabled = true;
        deleteByModelStatementEnabled = true;
        countByCriteriaStatementEnabled = true;
        countByModelStatementEnabled = true;
        updateByCriteriaStatementEnabled = true;
        updateByModelStatementEnabled = true;
    }

    public boolean isDeleteByPrimaryKeyStatementEnabled() {
        return deleteByPrimaryKeyStatementEnabled;
    }

    public void setDeleteByPrimaryKeyStatementEnabled(
            boolean deleteByPrimaryKeyStatementEnabled) {
        this.deleteByPrimaryKeyStatementEnabled = deleteByPrimaryKeyStatementEnabled;
    }

    public boolean isInsertStatementEnabled() {
        return insertStatementEnabled;
    }

    public void setInsertStatementEnabled(boolean insertStatementEnabled) {
        this.insertStatementEnabled = insertStatementEnabled;
    }

    public boolean isSelectByPrimaryKeyStatementEnabled() {
        return selectByPrimaryKeyStatementEnabled;
    }

    public void setSelectByPrimaryKeyStatementEnabled(
            boolean selectByPrimaryKeyStatementEnabled) {
        this.selectByPrimaryKeyStatementEnabled = selectByPrimaryKeyStatementEnabled;
    }
    

    public boolean isUpdateByPrimaryKeyStatementEnabled() {
        return updateByPrimaryKeyStatementEnabled;
    }

    public void setUpdateByPrimaryKeyStatementEnabled(
            boolean updateByPrimaryKeyStatementEnabled) {
        this.updateByPrimaryKeyStatementEnabled = updateByPrimaryKeyStatementEnabled;
    }

    public boolean isColumnIgnored(String columnName) {
        for (Map.Entry<IgnoredColumn, Boolean> entry : ignoredColumns
                .entrySet()) {
            IgnoredColumn ic = entry.getKey();
            if (ic.isColumnNameDelimited()) {
                if (columnName.equals(ic.getColumnName())) {
                    entry.setValue(Boolean.TRUE);
                    return true;
                }
            } else {
                if (columnName.equalsIgnoreCase(ic.getColumnName())) {
                    entry.setValue(Boolean.TRUE);
                    return true;
                }
            }
        }

        return false;
    }

    public void addIgnoredColumn(IgnoredColumn ignoredColumn) {
        ignoredColumns.put(ignoredColumn, Boolean.FALSE);
    }

    public void addColumnOverride(ColumnOverride columnOverride) {
        columnOverrides.add(columnOverride);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof TableConfiguration)) {
            return false;
        }

        TableConfiguration other = (TableConfiguration) obj;

        return areEqual(this.catalog, other.catalog)
                && areEqual(this.schema, other.schema)
                && areEqual(this.tableName, other.tableName);
    }

    @Override
    public int hashCode() {
        int result = SEED;
        result = hash(result, catalog);
        result = hash(result, schema);
        result = hash(result, tableName);

        return result;
    }

    public boolean isSelectByCriteriaStatementEnabled() {
        return selectByCriteriaStatementEnabled;
    }
    
    public boolean isSelectByModelStatementEnabled() {
    	return selectByModelStatementEnabled;
    }

    public void setSelectByCriteriaStatementEnabled(
            boolean selectByCriteriaStatementEnabled) {
        this.selectByCriteriaStatementEnabled = selectByCriteriaStatementEnabled;
    }
    
    public void setSelectByModelStatementEnabled(
    		boolean selectByModelStatementEnabled) {
    	this.selectByModelStatementEnabled = selectByModelStatementEnabled;
    }

    /**
     * May return null if the column has not been overridden
     * 
     * @param columnName
     * @return the column override (if any) related to this column
     */
    public ColumnOverride getColumnOverride(String columnName) {
        for (ColumnOverride co : columnOverrides) {
            if (co.isColumnNameDelimited()) {
                if (columnName.equals(co.getColumnName())) {
                    return co;
                }
            } else {
                if (columnName.equalsIgnoreCase(co.getColumnName())) {
                    return co;
                }
            }
        }

        return null;
    }

    public GeneratedKey getGeneratedKey() {
        return generatedKey;
    }

    public String getSelectByCriteriaQueryId() {
        return selectByCriteriaQueryId;
    }

    public void setSelectByCriteriaQueryId(String selectByCriteriaQueryId) {
        this.selectByCriteriaQueryId = selectByCriteriaQueryId;
    }
    
    public String getSelectByModelQueryId() {
    	return selectByModelQueryId;
    }

    public void setSelectByModelQueryId(String selectByModelQueryId) {
        this.selectByModelQueryId = selectByModelQueryId;
    }

    public String getSelectByPrimaryKeyQueryId() {
        return selectByPrimaryKeyQueryId;
    }

    public void setSelectByPrimaryKeyQueryId(String selectByPrimaryKeyQueryId) {
        this.selectByPrimaryKeyQueryId = selectByPrimaryKeyQueryId;
    }

    public boolean isDeleteByCriteriaStatementEnabled() {
        return deleteByCriteriaStatementEnabled;
    }

    public void setDeleteByCriteriaStatementEnabled(
            boolean deleteByCriteriaStatementEnabled) {
        this.deleteByCriteriaStatementEnabled = deleteByCriteriaStatementEnabled;
    }
    
    public boolean isDeleteByModelStatementEnabled() {
    	return deleteByModelStatementEnabled;
    }
    
    public void setDeleteByModelStatementEnabled(
    		boolean deleteByModelStatementEnabled) {
    	this.deleteByModelStatementEnabled = deleteByModelStatementEnabled;
    }
    

    public boolean areAnyStatementsEnabled() {
        return selectByCriteriaStatementEnabled
                || selectByPrimaryKeyStatementEnabled || insertStatementEnabled
                || updateByPrimaryKeyStatementEnabled
                || deleteByCriteriaStatementEnabled
                || deleteByPrimaryKeyStatementEnabled
                || countByCriteriaStatementEnabled
                || updateByCriteriaStatementEnabled;
    }

    public void setGeneratedKey(GeneratedKey generatedKey) {
        this.generatedKey = generatedKey;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getDomainObjectName() {
        return domainObjectName;
    }

    public void setDomainObjectName(String domainObjectName) {
        this.domainObjectName = domainObjectName;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<ColumnOverride> getColumnOverrides() {
        return columnOverrides;
    }

    /**
     * This method returns an iterator of Strings. The values are the columns
     * that were specified to be ignored in the table, but do not exist in the
     * table.
     * 
     * @return an List of Strings - the columns that were improperly configured
     *         as ignored columns
     */
    public List<String> getIgnoredColumnsInError() {
        List<String> answer = new ArrayList<String>();

        for (Map.Entry<IgnoredColumn, Boolean> entry : ignoredColumns
                .entrySet()) {
            if (Boolean.FALSE.equals(entry.getValue())) {
                answer.add(entry.getKey().getColumnName());
            }
        }

        return answer;
    }

    public ModelType getModelType() {
        return modelType;
    }

    public void setConfiguredModelType(String configuredModelType) {
        this.configuredModelType = configuredModelType;
        this.modelType = ModelType.getModelType(configuredModelType);
    }

    public boolean isWildcardEscapingEnabled() {
        return wildcardEscapingEnabled;
    }

    public void setWildcardEscapingEnabled(boolean wildcardEscapingEnabled) {
        this.wildcardEscapingEnabled = wildcardEscapingEnabled;
    }

    public XmlElement toXmlElement() {
        XmlElement xmlElement = new XmlElement("table"); //$NON-NLS-1$
        xmlElement.addAttribute(new Attribute("tableName", tableName)); //$NON-NLS-1$

        if (stringHasValue(catalog)) {
            xmlElement.addAttribute(new Attribute("catalog", catalog)); //$NON-NLS-1$
        }

        if (stringHasValue(schema)) {
            xmlElement.addAttribute(new Attribute("schema", schema)); //$NON-NLS-1$
        }

        if (stringHasValue(alias)) {
            xmlElement.addAttribute(new Attribute("alias", alias)); //$NON-NLS-1$
        }

        if (stringHasValue(domainObjectName)) {
            xmlElement.addAttribute(new Attribute(
                    "domainObjectName", domainObjectName)); //$NON-NLS-1$
        }

        if (!insertStatementEnabled) {
            xmlElement.addAttribute(new Attribute("enableInsert", "false")); //$NON-NLS-1$ //$NON-NLS-2$
        }

        if (!selectByPrimaryKeyStatementEnabled) {
            xmlElement.addAttribute(new Attribute(
                    "enableSelectByPrimaryKey", "false")); //$NON-NLS-1$ //$NON-NLS-2$
        }

        if (!selectByCriteriaStatementEnabled) {
            xmlElement.addAttribute(new Attribute(
                    "enableSelectByCriteria", "false")); //$NON-NLS-1$ //$NON-NLS-2$
        }
        
        if (!selectByModelStatementEnabled) {
        	xmlElement.addAttribute(new Attribute(
        			"enableSelectByModel", "false")); //$NON-NLS-1$ //$NON-NLS-2$
        }

        if (!updateByPrimaryKeyStatementEnabled) {
            xmlElement.addAttribute(new Attribute(
                    "enableUpdateByPrimaryKey", "false")); //$NON-NLS-1$ //$NON-NLS-2$
        }

        if (!deleteByPrimaryKeyStatementEnabled) {
            xmlElement.addAttribute(new Attribute(
                    "enableDeleteByPrimaryKey", "false")); //$NON-NLS-1$ //$NON-NLS-2$
        }

        if (!deleteByCriteriaStatementEnabled) {
            xmlElement.addAttribute(new Attribute(
                    "enableDeleteByCriteria", "false")); //$NON-NLS-1$ //$NON-NLS-2$
        }

        if (!countByCriteriaStatementEnabled) {
            xmlElement.addAttribute(new Attribute(
                    "enableCountByCriteria", "false")); //$NON-NLS-1$ //$NON-NLS-2$
        }
        
        if (!countByModelStatementEnabled) {
        	xmlElement.addAttribute(new Attribute(
        			"enableCountByModel", "false")); //$NON-NLS-1$ //$NON-NLS-2$
        }

        if (!updateByCriteriaStatementEnabled) {
            xmlElement.addAttribute(new Attribute(
                    "enableUpdateByCriteria", "false")); //$NON-NLS-1$ //$NON-NLS-2$
        }

        if (stringHasValue(selectByPrimaryKeyQueryId)) {
            xmlElement.addAttribute(new Attribute(
                    "selectByPrimaryKeyQueryId", selectByPrimaryKeyQueryId)); //$NON-NLS-1$
        }

        if (stringHasValue(selectByCriteriaQueryId)) {
            xmlElement.addAttribute(new Attribute(
                    "selectByCriteriaQueryId", selectByCriteriaQueryId)); //$NON-NLS-1$
        }

        if (configuredModelType != null) {
            xmlElement.addAttribute(new Attribute(
                    "modelType", configuredModelType)); //$NON-NLS-1$
        }

        if (wildcardEscapingEnabled) {
            xmlElement.addAttribute(new Attribute("escapeWildcards", "true")); //$NON-NLS-1$ //$NON-NLS-2$
        }

        if (isAllColumnDelimitingEnabled) {
            xmlElement.addAttribute(new Attribute("delimitAllColumns", "true")); //$NON-NLS-1$ //$NON-NLS-2$
        }

        if (delimitIdentifiers) {
            xmlElement
                    .addAttribute(new Attribute("delimitIdentifiers", "true")); //$NON-NLS-1$ //$NON-NLS-2$
        }

        addPropertyXmlElements(xmlElement);

        if (generatedKey != null) {
            xmlElement.addElement(generatedKey.toXmlElement());
        }

        if (columnRenamingRule != null) {
            xmlElement.addElement(columnRenamingRule.toXmlElement());
        }

        if (ignoredColumns.size() > 0) {
            for (IgnoredColumn ignoredColumn : ignoredColumns.keySet()) {
                xmlElement.addElement(ignoredColumn.toXmlElement());
            }
        }

        if (columnOverrides.size() > 0) {
            for (ColumnOverride columnOverride : columnOverrides) {
                xmlElement.addElement(columnOverride.toXmlElement());
            }
        }

        return xmlElement;
    }

    @Override
    public String toString() {
        return composeFullyQualifiedTableName(catalog, schema,
                tableName, '.');
    }

    public boolean isDelimitIdentifiers() {
        return delimitIdentifiers;
    }

    public void setDelimitIdentifiers(boolean delimitIdentifiers) {
        this.delimitIdentifiers = delimitIdentifiers;
    }

    public boolean isCountByCriteriaStatementEnabled() {
        return countByCriteriaStatementEnabled;
    }
    
    public void setCountByCriteriaStatementEnabled(
    		boolean countByCriteriaStatementEnabled) {
    	this.countByCriteriaStatementEnabled = countByCriteriaStatementEnabled;
    }
    
    public boolean isCountByModelStatementEnabled() {
    	return countByModelStatementEnabled;
    }
    
    public void setCountByModelStatementEnabled(
    		boolean countByModelStatementEnabled) {
    	this.countByModelStatementEnabled = countByModelStatementEnabled;
    }
    
    public boolean isUpdateByCriteriaStatementEnabled() {
        return updateByCriteriaStatementEnabled;
    }

    public void setUpdateByCriteriaStatementEnabled(
            boolean updateByCriteriaStatementEnabled) {
        this.updateByCriteriaStatementEnabled = updateByCriteriaStatementEnabled;
    }
    
    public boolean isUpdateByModelStatementEnabled() {
    	return updateByModelStatementEnabled;
    }
    
    public void setUpdateByModelStatementEnabled(
    		boolean updateByModelStatementEnabled) {
    	this.updateByModelStatementEnabled = updateByModelStatementEnabled;
    }

    public void validate(List<String> errors, int listPosition) {
        if (!stringHasValue(tableName)) {
            errors.add(getString(
                    "ValidationError.6", Integer.toString(listPosition))); //$NON-NLS-1$
        }

        String fqTableName = composeFullyQualifiedTableName(
                catalog, schema, tableName, '.');

        if (generatedKey != null) {
            generatedKey.validate(errors, fqTableName);
        }

        if (isTrue(getProperty(PropertyRegistry.TABLE_USE_COLUMN_INDEXES))) {
            // when using column indexes, either both or neither query ids
            // should be set
            if (selectByCriteriaStatementEnabled
                    && selectByPrimaryKeyStatementEnabled) {
                boolean queryId1Set = stringHasValue(selectByCriteriaQueryId);
                boolean queryId2Set = stringHasValue(selectByPrimaryKeyQueryId);

                if (queryId1Set != queryId2Set) {
                    errors.add(getString("ValidationError.13", //$NON-NLS-1$
                            fqTableName));
                }
            }
        }

        if (columnRenamingRule != null) {
            columnRenamingRule.validate(errors, fqTableName);
        }

        for (ColumnOverride columnOverride : columnOverrides) {
            columnOverride.validate(errors, fqTableName);
        }

        for (IgnoredColumn ignoredColumn : ignoredColumns.keySet()) {
            ignoredColumn.validate(errors, fqTableName);
        }
    }

    public ColumnRenamingRule getColumnRenamingRule() {
        return columnRenamingRule;
    }

    public void setColumnRenamingRule(ColumnRenamingRule columnRenamingRule) {
        this.columnRenamingRule = columnRenamingRule;
    }

    public boolean isAllColumnDelimitingEnabled() {
        return isAllColumnDelimitingEnabled;
    }

    public void setAllColumnDelimitingEnabled(
            boolean isAllColumnDelimitingEnabled) {
        this.isAllColumnDelimitingEnabled = isAllColumnDelimitingEnabled;
    }
}
