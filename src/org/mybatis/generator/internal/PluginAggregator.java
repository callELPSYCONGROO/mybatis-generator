/*
 *  Copyright 2008 The Apache Software Foundation
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

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.Context;

/**
 * This class is for internal use only. It contains a list of plugins for the
 * current context and is used to aggregate plugins together. This class
 * implements the rule that if any plugin returns "false" from a method, then no
 * other plugin is called.
 * <p>
 * This class does not follow the normal plugin lifecycle and should not be
 * subclassed by clients.
 * 
 * @author Jeff Butler
 * 
 */
public final class PluginAggregator implements Plugin {
    private List<Plugin> plugins;

    public PluginAggregator() {
        plugins = new ArrayList<Plugin>();
    }

    public void addPlugin(Plugin plugin) {
        plugins.add(plugin);
    }

    public void setContext(Context context) {
        throw new UnsupportedOperationException();
    }

    public void setProperties(Properties properties) {
        throw new UnsupportedOperationException();
    }

    public boolean validate(List<String> warnings) {
        throw new UnsupportedOperationException();
    }

    public boolean modelBaseRecordClassGenerated(TopLevelClass tlc,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.modelBaseRecordClassGenerated(tlc, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass tlc,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.modelRecordWithBLOBsClassGenerated(tlc,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

	public boolean sqlMapCountByModelElementGenerated(XmlElement element, IntrospectedTable table) {
		boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapCountByModelElementGenerated(element, table)) {
                rc = false;
                break;
            }
        }

        return rc;
	}
    public boolean sqlMapCountByCriteriaElementGenerated(XmlElement element,
            IntrospectedTable table) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapCountByCriteriaElementGenerated(element, table)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapDeleteByCriteriaElementGenerated(XmlElement element,
            IntrospectedTable table) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapDeleteByCriteriaElementGenerated(element, table)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapDeleteByPrimaryKeyElementGenerated(XmlElement element,
            IntrospectedTable table) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin
                    .sqlMapDeleteByPrimaryKeyElementGenerated(element, table)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean modelCriteriaClassGenerated(TopLevelClass tlc,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.modelCriteriaClassGenerated(tlc, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(
            IntrospectedTable introspectedTable) {
        List<GeneratedJavaFile> answer = new ArrayList<GeneratedJavaFile>();
        for (Plugin plugin : plugins) {
            List<GeneratedJavaFile> temp = plugin
                    .contextGenerateAdditionalJavaFiles(introspectedTable);
            if (temp != null) {
                answer.addAll(temp);
            }
        }
        return answer;
    }

    public List<GeneratedXmlFile> contextGenerateAdditionalXmlFiles(
            IntrospectedTable introspectedTable) {
        List<GeneratedXmlFile> answer = new ArrayList<GeneratedXmlFile>();
        for (Plugin plugin : plugins) {
            List<GeneratedXmlFile> temp = plugin
                    .contextGenerateAdditionalXmlFiles(introspectedTable);
            if (temp != null) {
                answer.addAll(temp);
            }
        }
        return answer;
    }

    public boolean modelPrimaryKeyClassGenerated(TopLevelClass tlc,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.modelPrimaryKeyClassGenerated(tlc, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapResultMapWithoutBLOBsElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapResultMapWithoutBLOBsElementGenerated(element,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapCriteriaWhereClauseElementGenerated(XmlElement element,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapCriteriaWhereClauseElementGenerated(element,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapInsertElementGenerated(XmlElement element,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin
                    .sqlMapInsertElementGenerated(element, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapResultMapWithBLOBsElementGenerated(XmlElement element,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapResultMapWithBLOBsElementGenerated(element,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapSelectByCriteriaWithoutBLOBsElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapSelectByCriteriaWithoutBLOBsElementGenerated(
                    element, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }
    
    public boolean sqlMapSelectByModelWithoutBLOBsElementGenerated(
    		XmlElement element, IntrospectedTable introspectedTable) {
    	boolean rc = true;
    	
    	for (Plugin plugin : plugins) {
    		if (!plugin.sqlMapSelectByModelWithoutBLOBsElementGenerated(
    				element, introspectedTable)) {
    			rc = false;
    			break;
    		}
    	}
    	
    	return rc;
    }

    public boolean sqlMapSelectByCriteriaWithBLOBsElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapSelectByCriteriaWithBLOBsElementGenerated(element,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }
    
    public boolean sqlMapSelectByModelWithBLOBsElementGenerated(
    		XmlElement element, IntrospectedTable introspectedTable) {
    	boolean rc = true;
    	
    	for (Plugin plugin : plugins) {
    		if (!plugin.sqlMapSelectByModelWithBLOBsElementGenerated(element,
    				introspectedTable)) {
    			rc = false;
    			break;
    		}
    	}
    	
    	return rc;
    }

    public boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement element,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapSelectByPrimaryKeyElementGenerated(element,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapGenerated(GeneratedXmlFile sqlMap,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapGenerated(sqlMap, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapUpdateByCriteriaSelectiveElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapUpdateByCriteriaSelectiveElementGenerated(element,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapUpdateByCriteriaWithBLOBsElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapUpdateByCriteriaWithBLOBsElementGenerated(element,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapUpdateByCriteriaWithoutBLOBsElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapUpdateByCriteriaWithoutBLOBsElementGenerated(
                    element, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapUpdateByPrimaryKeySelectiveElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapUpdateByPrimaryKeySelectiveElementGenerated(
                    element, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated(
                    element, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(
                    element, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientCountByCriteriaMethodGenerated(Method method,
            Interface interfaze, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientCountByCriteriaMethodGenerated(method, interfaze,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientCountByCriteriaMethodGenerated(Method method,
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientCountByCriteriaMethodGenerated(method, topLevelClass,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }
    
    

	public boolean clientCountByModelMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		   boolean rc = true;

	        for (Plugin plugin : plugins) {
	            if (!plugin.clientCountByModelMethodGenerated(method, interfaze,
	                    introspectedTable)) {
	                rc = false;
	                break;
	            }
	        }

	        return rc;
	}
    

    public boolean clientDeleteByCriteriaMethodGenerated(Method method,
            Interface interfaze, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientDeleteByCriteriaMethodGenerated(method, interfaze,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientDeleteByCriteriaMethodGenerated(Method method,
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientDeleteByCriteriaMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method,
            Interface interfaze, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientDeleteByPrimaryKeyMethodGenerated(method, interfaze,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method,
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientDeleteByPrimaryKeyMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientInsertMethodGenerated(Method method, Interface interfaze,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientInsertMethodGenerated(method, interfaze,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientInsertMethodGenerated(Method method,
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientInsertMethodGenerated(method, topLevelClass,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientGenerated(Interface interfaze,
            TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientGenerated(interfaze, topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientSelectAllMethodGenerated(Method method,
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientSelectAllMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientSelectAllMethodGenerated(Method method,
            Interface interfaze, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientSelectAllMethodGenerated(method,
                    interfaze, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientSelectByCriteriaWithBLOBsMethodGenerated(Method method,
            Interface interfaze, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientSelectByCriteriaWithBLOBsMethodGenerated(method,
                    interfaze, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }
    
    public boolean clientSelectByModelWithBLOBsMethodGenerated(Method method,
    		Interface interfaze, IntrospectedTable introspectedTable) {
    	boolean rc = true;
    	
    	for (Plugin plugin : plugins) {
    		if (!plugin.clientSelectByModelWithBLOBsMethodGenerated(method,
    				interfaze, introspectedTable)) {
    			rc = false;
    			break;
    		}
    	}
    	
    	return rc;
    }

    public boolean clientSelectByCriteriaWithBLOBsMethodGenerated(Method method,
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientSelectByCriteriaWithBLOBsMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }
    
    public boolean clientSelectByModelWithBLOBsMethodGenerated(Method method,
    		TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    	boolean rc = true;
    	
    	for (Plugin plugin : plugins) {
    		if (!plugin.clientSelectByModelWithBLOBsMethodGenerated(method,
    				topLevelClass, introspectedTable)) {
    			rc = false;
    			break;
    		}
    	}
    	
    	return rc;
    }

    public boolean clientSelectByCriteriaWithoutBLOBsMethodGenerated(Method method,
            Interface interfaze, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientSelectByCriteriaWithoutBLOBsMethodGenerated(method,
                    interfaze, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }
    
    public boolean clientSelectByModelWithoutBLOBsMethodGenerated(Method method,
    		Interface interfaze, IntrospectedTable introspectedTable) {
    	boolean rc = true;
    	
    	for (Plugin plugin : plugins) {
    		if (!plugin.clientSelectByModelWithoutBLOBsMethodGenerated(method,
    				interfaze, introspectedTable)) {
    			rc = false;
    			break;
    		}
    	}
    	
    	return rc;
    }

    public boolean clientSelectByCriteriaWithoutBLOBsMethodGenerated(Method method,
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientSelectByCriteriaWithoutBLOBsMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }
    
    public boolean clientSelectByModelWithoutBLOBsMethodGenerated(Method method,
    		TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    	boolean rc = true;
    	
    	for (Plugin plugin : plugins) {
    		if (!plugin.clientSelectByModelWithoutBLOBsMethodGenerated(method,
    				topLevelClass, introspectedTable)) {
    			rc = false;
    			break;
    		}
    	}
    	
    	return rc;
    }

    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method,
            Interface interfaze, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientSelectByPrimaryKeyMethodGenerated(method, interfaze,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method,
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientSelectByPrimaryKeyMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientUpdateByCriteriaSelectiveMethodGenerated(Method method,
            Interface interfaze, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientUpdateByCriteriaSelectiveMethodGenerated(method,
                    interfaze, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientUpdateByCriteriaSelectiveMethodGenerated(Method method,
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientUpdateByCriteriaSelectiveMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientUpdateByCriteriaWithBLOBsMethodGenerated(Method method,
            Interface interfaze, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientUpdateByCriteriaWithBLOBsMethodGenerated(method,
                    interfaze, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientUpdateByCriteriaWithBLOBsMethodGenerated(Method method,
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientUpdateByCriteriaWithBLOBsMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientUpdateByCriteriaWithoutBLOBsMethodGenerated(Method method,
            Interface interfaze, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientUpdateByCriteriaWithoutBLOBsMethodGenerated(method,
                    interfaze, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientUpdateByCriteriaWithoutBLOBsMethodGenerated(Method method,
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientUpdateByCriteriaWithoutBLOBsMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientUpdateByPrimaryKeySelectiveMethodGenerated(Method method,
            Interface interfaze, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientUpdateByPrimaryKeySelectiveMethodGenerated(method,
                    interfaze, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientUpdateByPrimaryKeySelectiveMethodGenerated(Method method,
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientUpdateByPrimaryKeySelectiveMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(Method method,
            Interface interfaze, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(method,
                    interfaze, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(Method method,
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(
            Method method, Interface interfaze,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(
                    method, interfaze, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(
            Method method, TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(
                    method, topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles() {
        List<GeneratedJavaFile> answer = new ArrayList<GeneratedJavaFile>();
        for (Plugin plugin : plugins) {
            List<GeneratedJavaFile> temp = plugin
                    .contextGenerateAdditionalJavaFiles();
            if (temp != null) {
                answer.addAll(temp);
            }
        }
        return answer;
    }

    public List<GeneratedXmlFile> contextGenerateAdditionalXmlFiles() {
        List<GeneratedXmlFile> answer = new ArrayList<GeneratedXmlFile>();
        for (Plugin plugin : plugins) {
            List<GeneratedXmlFile> temp = plugin
                    .contextGenerateAdditionalXmlFiles();
            if (temp != null) {
                answer.addAll(temp);
            }
        }
        return answer;
    }

    public boolean sqlMapDocumentGenerated(Document document,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapDocumentGenerated(document, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean modelFieldGenerated(Field field,
            TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
            IntrospectedTable introspectedTable,
            Plugin.ModelClassType modelClassType) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.modelFieldGenerated(field, topLevelClass,
                    introspectedColumn, introspectedTable, modelClassType)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean modelGetterMethodGenerated(Method method,
            TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
            IntrospectedTable introspectedTable,
            Plugin.ModelClassType modelClassType) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.modelGetterMethodGenerated(method, topLevelClass,
                    introspectedColumn, introspectedTable, modelClassType)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean modelSetterMethodGenerated(Method method,
            TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
            IntrospectedTable introspectedTable,
            Plugin.ModelClassType modelClassType) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.modelSetterMethodGenerated(method, topLevelClass,
                    introspectedColumn, introspectedTable, modelClassType)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapInsertSelectiveElementGenerated(element,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientInsertSelectiveMethodGenerated(Method method,
            Interface interfaze, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientInsertSelectiveMethodGenerated(method, interfaze,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean clientInsertSelectiveMethodGenerated(Method method,
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.clientInsertSelectiveMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public void initialized(IntrospectedTable introspectedTable) {
        for (Plugin plugin : plugins) {
            plugin.initialized(introspectedTable);
        }
    }

    public boolean sqlMapBaseColumnListElementGenerated(XmlElement element,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapBaseColumnListElementGenerated(element,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapBlobColumnListElementGenerated(XmlElement element,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapBlobColumnListElementGenerated(element,
                    introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean providerGenerated(TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.providerGenerated(topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean providerApplyWhereMethodGenerated(Method method,
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.providerApplyWhereMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean providerCountByCriteriaMethodGenerated(Method method,
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.providerCountByCriteriaMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean providerDeleteByCriteriaMethodGenerated(Method method,
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.providerDeleteByCriteriaMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean providerInsertSelectiveMethodGenerated(Method method,
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.providerInsertSelectiveMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean providerSelectByCriteriaWithBLOBsMethodGenerated(
            Method method, TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.providerSelectByCriteriaWithBLOBsMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean providerSelectByCriteriaWithoutBLOBsMethodGenerated(
            Method method, TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.providerSelectByCriteriaWithoutBLOBsMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean providerUpdateByCriteriaSelectiveMethodGenerated(
            Method method, TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.providerUpdateByCriteriaSelectiveMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean providerUpdateByCriteriaWithBLOBsMethodGenerated(
            Method method, TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.providerUpdateByCriteriaWithBLOBsMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean providerUpdateByCriteriaWithoutBLOBsMethodGenerated(
            Method method, TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.providerUpdateByCriteriaWithoutBLOBsMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean providerUpdateByPrimaryKeySelectiveMethodGenerated(
            Method method, TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.providerUpdateByPrimaryKeySelectiveMethodGenerated(method,
                    topLevelClass, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }

    public boolean sqlMapSelectAllElementGenerated(XmlElement element,
            IntrospectedTable introspectedTable) {
        boolean rc = true;

        for (Plugin plugin : plugins) {
            if (!plugin.sqlMapSelectAllElementGenerated(element, introspectedTable)) {
                rc = false;
                break;
            }
        }

        return rc;
    }
}
