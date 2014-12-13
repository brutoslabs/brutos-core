/*
 * Brutos Web MVC http://www.brutosframework.com.br/
 * Copyright (C) 2009 Afonso Brandao. (afonso.rbn@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.brandao.brutos.annotation;

import java.util.*;
import junit.framework.Assert;
import org.brandao.brutos.BrutosConstants;
import org.brandao.brutos.BrutosException;
import org.brandao.brutos.ConfigurableApplicationContext;
import org.brandao.brutos.annotation.helper.*;
import org.brandao.brutos.annotation.helper.bean.BeanConstructorTest;
import org.brandao.brutos.annotation.helper.bean.CustomArrayList;
import org.brandao.brutos.mapping.*;
import org.brandao.brutos.EnumerationType;
import org.brandao.brutos.ScopeType;
import org.brandao.brutos.type.DefaultTypeFactory;
import org.brandao.brutos.type.IntegerWrapperType;
import org.brandao.brutos.type.ObjectType;
import org.brandao.brutos.type.StringType;
import org.brandao.brutos.TypeManager;
import org.brandao.brutos.type.TypeUtil;
/**
 *
 * @author Brandao
 */
public class AnnotationApplicationContextBeanTest 
    extends AbstractWebAnnotationApplicationContextTest{

    public AnnotationApplicationContextBeanTest(){
    }
    
    @Override
    public ConfigurableApplicationContext getApplication(Class[] clazz, String complement){
        ConfigurableApplicationContext context = super.getApplication(clazz, complement);
        context.getTypeManager().remove(List.class);
        return context;
    }
    
    public void testBean1() throws NoSuchMethodException{
        Class clazz = BeanTest1Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyA");
        Assert.assertEquals(int.class, property.getClassType());
        Assert.assertEquals("propertyA", property.getName());
        Assert.assertEquals("propertyA", property.getParameterName());
        Assert.assertNull(property.getMapping());
        
    }

    public void testBean2() throws NoSuchMethodException{
        Class clazz = BeanTest1Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyB");
        Assert.assertEquals(String.class, property.getClassType());
        Assert.assertEquals("propertyB", property.getName());
        Assert.assertEquals("prop", property.getParameterName());
        Assert.assertNull(property.getMapping());
        
    }

    public void testBean3() throws NoSuchMethodException{
        Class clazz = BeanTest1Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyC");
        Assert.assertEquals(Date.class, property.getClassType());
        Assert.assertEquals("propertyC", property.getName());
        Assert.assertEquals("propertyC", property.getParameterName());
        Assert.assertEquals("dd/MM/yyyy", property.getTemporalType());
        Assert.assertNull(property.getMapping());
        
    }

    public void testBean4() throws NoSuchMethodException{
        Class clazz = BeanTest1Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyD");
        Assert.assertEquals(Date.class, property.getClassType());
        Assert.assertEquals("propertyD", property.getName());
        Assert.assertEquals("propertyD", property.getParameterName());
        Assert.assertEquals("yyyy-MM-dd", property.getTemporalType());
        Assert.assertNull(property.getMapping());
        
    }

    public void testBean5() throws NoSuchMethodException{
        Class clazz = BeanTest1Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyE");
        Assert.assertEquals(EnumTest.class, property.getClassType());
        Assert.assertEquals("propertyE", property.getName());
        Assert.assertEquals("propertyE", property.getParameterName());
        Assert.assertEquals(org.brandao.brutos.EnumerationType.ORDINAL, property.getEnumProperty());
        Assert.assertNull(property.getMapping());
        
    }

    public void testBean6() throws NoSuchMethodException{
        Class clazz = BeanTest1Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyF");
        Assert.assertEquals(EnumTest.class, property.getClassType());
        Assert.assertEquals("propertyF", property.getName());
        Assert.assertEquals("propertyF", property.getParameterName());
        Assert.assertEquals(org.brandao.brutos.EnumerationType.ORDINAL, property.getEnumProperty());
        Assert.assertNull(property.getMapping());
        
    }

    public void testBean7() throws NoSuchMethodException{
        Class clazz = BeanTest1Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyG");
        Assert.assertEquals(EnumTest.class, property.getClassType());
        Assert.assertEquals("propertyG", property.getName());
        Assert.assertEquals("propertyG", property.getParameterName());
        Assert.assertEquals(org.brandao.brutos.EnumerationType.STRING, property.getEnumProperty());
        Assert.assertNull(property.getMapping());
        
    }

    public void testBean8() throws NoSuchMethodException{
        Class clazz = BeanTest1Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyH");
        org.brandao.brutos.mapping.Bean beanProperty = property.getBean();
        Assert.assertEquals(TypeUtil.getDefaultMapType(), beanProperty.getClassType());
        
        DependencyBean key = ((MapBean)beanProperty).getKey();
        Assert.assertEquals("myKey", key.getParameterName());
        Assert.assertEquals(Integer.class, key.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, key.getScopeType());
        Assert.assertEquals(EnumerationType.STRING, key.getEnumProperty());
        Assert.assertEquals("yyyy-MM-dd", key.getTemporalType());
        Assert.assertEquals(IntegerWrapperType.class,key.getType().getClass());
        Assert.assertNull(key.getValue());

        DependencyBean element = ((MapBean)beanProperty).getCollection();
        Assert.assertEquals("myElement", element.getParameterName());
        Assert.assertEquals(String.class, element.getClassType());
        Assert.assertEquals(ScopeType.REQUEST, element.getScopeType());
        Assert.assertEquals(EnumerationType.STRING, element.getEnumProperty());
        Assert.assertEquals("yyyy-MM-dd", element.getTemporalType());
        Assert.assertEquals(StringType.class,element.getType().getClass());
        Assert.assertNull(element.getValue());
        
    }

    public void testBean9() throws NoSuchMethodException{
        Class clazz = BeanTest1Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyI");
        org.brandao.brutos.mapping.Bean beanProperty = property.getBean();
        Assert.assertEquals(LinkedHashMap.class, beanProperty.getClassType());
        
        DependencyBean key = ((MapBean)beanProperty).getKey();
        Assert.assertEquals("key", key.getParameterName());
        Assert.assertEquals(String.class, key.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, key.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, key.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, key.getTemporalType());
        Assert.assertEquals(StringType.class,key.getType().getClass());
        Assert.assertNull(key.getValue());

        DependencyBean element = ((MapBean)beanProperty).getCollection();
        Assert.assertEquals("element", element.getParameterName());
        Assert.assertEquals(Integer.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertEquals(IntegerWrapperType.class,element.getType().getClass());
        Assert.assertNull(element.getValue());
        
    }

    public void testBean10() throws NoSuchMethodException{
        Class clazz = BeanTest1Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyJ");
        org.brandao.brutos.mapping.Bean beanProperty = property.getBean();
        Assert.assertEquals(HashMap.class, beanProperty.getClassType());
        
        DependencyBean key = ((MapBean)beanProperty).getKey();
        Assert.assertEquals("key", key.getParameterName());
        Assert.assertEquals(BeanConstructorTest.class, key.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, key.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, key.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, key.getTemporalType());
        Assert.assertNotNull(key.getMapping());
        Assert.assertNull(key.getType());
        Assert.assertNull(key.getValue());

        DependencyBean element = ((MapBean)beanProperty).getCollection();
        Assert.assertEquals("element", element.getParameterName());
        Assert.assertEquals(Integer.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNull(element.getMapping());
        Assert.assertEquals(IntegerWrapperType.class,element.getType().getClass());
        Assert.assertNull(element.getValue());
        
    }

    public void testBean11() throws NoSuchMethodException{
        Class clazz = BeanTest1Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyK");
        org.brandao.brutos.mapping.Bean beanProperty = property.getBean();
        Assert.assertEquals(HashMap.class, beanProperty.getClassType());
        
        DependencyBean key = ((MapBean)beanProperty).getKey();
        Assert.assertEquals("key", key.getParameterName());
        Assert.assertEquals(String.class, key.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, key.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, key.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, key.getTemporalType());
        Assert.assertNull(key.getMapping());
        Assert.assertEquals(StringType.class,key.getType().getClass());
        Assert.assertNull(key.getValue());

        DependencyBean element = ((MapBean)beanProperty).getCollection();
        Assert.assertEquals("element", element.getParameterName());
        Assert.assertEquals(BeanConstructorTest.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNotNull(element.getMapping());
        Assert.assertNull(element.getType());
        Assert.assertNull(element.getValue());
        
    }

    public void testBean12() throws NoSuchMethodException{
        Class clazz = BeanTest1Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = null;
        annotationApplicationContext = getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyL");
        org.brandao.brutos.mapping.Bean beanProperty = property.getBean();
        Assert.assertEquals(HashMap.class, beanProperty.getClassType());
        
        DependencyBean key = ((MapBean)beanProperty).getKey();
        Assert.assertEquals("key", key.getParameterName());
        Assert.assertEquals(String.class, key.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, key.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, key.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, key.getTemporalType());
        Assert.assertNull(key.getMapping());
        Assert.assertEquals(StringType.class,key.getType().getClass());
        Assert.assertNull(key.getValue());

        DependencyBean element = ((MapBean)beanProperty).getCollection();
        Assert.assertEquals("myElement", element.getParameterName());
        Assert.assertEquals(BeanConstructorTest.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNotNull(element.getMapping());
        Assert.assertNull(element.getType());
        Assert.assertNull(element.getValue());
        
    }

    public void testBean13() throws NoSuchMethodException{
        Class clazz = BeanTest1Controller.class;

        ConfigurableApplicationContext annotationApplicationContext =
            getApplication(new Class[]{clazz});
        
        
        org.brandao.brutos.mapping.Controller
            controller = annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyM");
        org.brandao.brutos.mapping.Bean beanProperty = property.getBean();
        Assert.assertEquals(TypeUtil.getDefaultListType(), beanProperty.getClassType());
        
        DependencyBean element = ((CollectionBean)beanProperty).getCollection();
        Assert.assertEquals("myElement", element.getParameterName());
        Assert.assertEquals(Integer.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNull(element.getMapping());
        Assert.assertEquals(IntegerWrapperType.class,element.getType().getClass());
        Assert.assertNull(element.getValue());
    }

    public void testBean14() throws NoSuchMethodException{
        Class clazz = BeanTest1Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyN");
        org.brandao.brutos.mapping.Bean beanProperty = property.getBean();
        Assert.assertEquals(LinkedList.class, beanProperty.getClassType());
        
        DependencyBean element = ((CollectionBean)beanProperty).getCollection();
        Assert.assertEquals("element", element.getParameterName());
        Assert.assertEquals(Integer.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNull(element.getMapping());
        Assert.assertEquals(IntegerWrapperType.class,element.getType().getClass());
        Assert.assertNull(element.getValue());
        
    }

    public void testBean15() throws NoSuchMethodException{
        Class clazz = BeanTest1Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyO");
        org.brandao.brutos.mapping.Bean beanProperty = property.getBean();
        Assert.assertEquals(TypeUtil.getDefaultListType(), beanProperty.getClassType());
        
        DependencyBean element = ((CollectionBean)beanProperty).getCollection();
        Assert.assertEquals("element", element.getParameterName());
        Assert.assertEquals(Integer.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNull(element.getMapping());
        Assert.assertEquals(IntegerWrapperType.class,element.getType().getClass());
        Assert.assertNull(element.getValue());
        
    }

    public void testBean16() throws NoSuchMethodException{
        Class clazz = BeanTest1Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyP");
        org.brandao.brutos.mapping.Bean beanProperty = property.getBean();
        Assert.assertEquals(TypeUtil.getDefaultListType(), beanProperty.getClassType());
        
        DependencyBean element = ((CollectionBean)beanProperty).getCollection();
        Assert.assertEquals("element", element.getParameterName());
        Assert.assertEquals(BeanConstructorTest.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNotNull(element.getMapping());
        Assert.assertNull(element.getType());
        Assert.assertNull(element.getValue());
        
    }

    public void testBean17() throws NoSuchMethodException{
        Class clazz = BeanTest1Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyQ");
        org.brandao.brutos.mapping.Bean beanProperty = property.getBean();
        Assert.assertEquals(TypeUtil.getDefaultListType(), beanProperty.getClassType());
        
        DependencyBean element = ((CollectionBean)beanProperty).getCollection();
        Assert.assertEquals("myElement", element.getParameterName());
        Assert.assertEquals(BeanConstructorTest.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNull(element.getMapping());
        Assert.assertNotNull(element.getType());
        Assert.assertNull(element.getValue());
    }

    public void testBean18() throws NoSuchMethodException{
        Class clazz = BeanTest1Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = null;
        annotationApplicationContext = getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyR");
        org.brandao.brutos.mapping.Bean beanProperty = property.getBean();
        Assert.assertEquals(TypeUtil.getDefaultMapType(), beanProperty.getClassType());
        
        DependencyBean key = ((MapBean)beanProperty).getKey();
        Assert.assertEquals("myKey", key.getParameterName());
        Assert.assertEquals(BeanConstructorTest.class, key.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, key.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, key.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, key.getTemporalType());
        Assert.assertNotNull(key.getMapping());
        Assert.assertNull(key.getType());
        Assert.assertNull(key.getValue());

        DependencyBean element = ((MapBean)beanProperty).getCollection();
        Assert.assertEquals("myElement", element.getParameterName());
        Assert.assertEquals(BeanConstructorTest.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNotNull(element.getMapping());
        Assert.assertNull(element.getType());
        Assert.assertNull(element.getValue());
        
    }

    public void testBean19() throws NoSuchMethodException{
        Class clazz = BeanTest1Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = null;
        annotationApplicationContext = getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyS");
        org.brandao.brutos.mapping.Bean beanProperty = property.getBean();
        Assert.assertEquals(TypeUtil.getDefaultListType(), beanProperty.getClassType());
        
        DependencyBean element = ((CollectionBean)beanProperty).getCollection();
        Assert.assertEquals("myElement", element.getParameterName());
        Assert.assertEquals(BeanConstructorTest.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNotNull(element.getMapping());
        Assert.assertNull(element.getType());
        Assert.assertNull(element.getValue());
        
    }

    public void testBean20() throws NoSuchMethodException{
        Class clazz = BeanTest1Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyT");
        org.brandao.brutos.mapping.Bean beanProperty = property.getBean();
        Assert.assertEquals(TypeUtil.getDefaultMapType(), beanProperty.getClassType());
        
        DependencyBean key = ((MapBean)beanProperty).getKey();
        Assert.assertEquals("key", key.getParameterName());
        Assert.assertEquals(String.class, key.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, key.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, key.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, key.getTemporalType());
        Assert.assertNull(key.getMapping());
        Assert.assertEquals(StringType.class,key.getType().getClass());
        Assert.assertNull(key.getValue());

        DependencyBean element = ((MapBean)beanProperty).getCollection();
        Assert.assertEquals("element", element.getParameterName());
        Assert.assertEquals(TypeUtil.getDefaultListType(), element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNotNull(element.getMapping());
        Assert.assertNull(element.getType());
        Assert.assertNull(element.getValue());
        
        DependencyBean subElement = ((CollectionBean)element.getBean()).getCollection();
        Assert.assertEquals("element", subElement.getParameterName());
        Assert.assertEquals(BeanConstructorTest.class, subElement.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, subElement.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, subElement.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, subElement.getTemporalType());
        Assert.assertNotNull(subElement.getMapping());
        Assert.assertNull(subElement.getType());
        Assert.assertNull(subElement.getValue());
        
    }

    public void testBean21() throws NoSuchMethodException{
        Class clazz = BeanTest1Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyU");
        org.brandao.brutos.mapping.Bean beanProperty = property.getBean();
        Assert.assertEquals(TypeUtil.getDefaultMapType(), beanProperty.getClassType());
        
        DependencyBean key = ((MapBean)beanProperty).getKey();
        Assert.assertEquals("key", key.getParameterName());
        Assert.assertEquals(String.class, key.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, key.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, key.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, key.getTemporalType());
        Assert.assertNull(key.getMapping());
        Assert.assertEquals(StringType.class,key.getType().getClass());
        Assert.assertNull(key.getValue());

        DependencyBean element = ((MapBean)beanProperty).getCollection();
        Assert.assertEquals("element", element.getParameterName());
        Assert.assertEquals(CustomArrayList.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNotNull(element.getMapping());
        Assert.assertNull(element.getType());
        Assert.assertNull(element.getValue());
        
        DependencyBean subElement = ((CollectionBean)element.getBean()).getCollection();
        Assert.assertEquals("myElement", subElement.getParameterName());
        Assert.assertEquals(BeanConstructorTest.class, subElement.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, subElement.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, subElement.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, subElement.getTemporalType());
        Assert.assertNotNull(subElement.getMapping());
        Assert.assertNull(subElement.getType());
        Assert.assertNull(subElement.getValue());
        
    }

    public void testBean22() throws NoSuchMethodException{
        Class clazz = BeanTest1Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyV");
        org.brandao.brutos.mapping.Bean beanProperty = property.getBean();
        Assert.assertEquals(TypeUtil.getDefaultMapType(), beanProperty.getClassType());
        
        DependencyBean key = ((MapBean)beanProperty).getKey();
        Assert.assertEquals("key", key.getParameterName());
        Assert.assertEquals(String.class, key.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, key.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, key.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, key.getTemporalType());
        Assert.assertNull(key.getMapping());
        Assert.assertEquals(StringType.class,key.getType().getClass());
        Assert.assertNull(key.getValue());

        DependencyBean element = ((MapBean)beanProperty).getCollection();
        Assert.assertEquals("myElement2", element.getParameterName());
        Assert.assertEquals(CustomArrayList.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNotNull(element.getMapping());
        Assert.assertNull(element.getType());
        Assert.assertNull(element.getValue());
        
        DependencyBean subElement = ((CollectionBean)element.getBean()).getCollection();
        Assert.assertEquals("myElement", subElement.getParameterName());
        Assert.assertEquals(BeanConstructorTest.class, subElement.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, subElement.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, subElement.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, subElement.getTemporalType());
        Assert.assertNotNull(subElement.getMapping());
        Assert.assertNull(subElement.getType());
        Assert.assertNull(subElement.getValue());
        
    }
    
    
    public void testBean22X() throws NoSuchMethodException{
        Class clazz = BeanTest1Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyX");
        Assert.assertEquals(BeanConstructorTest.class, property.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, property.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, property.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, property.getTemporalType());
        Assert.assertNotNull(property.getMapping());
        Assert.assertNotNull(property.getType());
        Assert.assertNull(property.getValue());
        
    }
    
    public void testBean23() throws NoSuchMethodException{
        Class clazz = BeanTest2Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyA");
        Assert.assertEquals(int.class, property.getClassType());
        Assert.assertEquals("propertyA", property.getName());
        Assert.assertEquals("propertyA", property.getParameterName());
        Assert.assertNull(property.getMapping());
        
    }

    public void testBean24() throws NoSuchMethodException{
        Class clazz = BeanTest2Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyB");
        Assert.assertEquals(String.class, property.getClassType());
        Assert.assertEquals("propertyB", property.getName());
        Assert.assertEquals("prop", property.getParameterName());
        Assert.assertNull(property.getMapping());
        
    }

    public void testBean25() throws NoSuchMethodException{
        Class clazz = BeanTest2Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyC");
        Assert.assertEquals(Date.class, property.getClassType());
        Assert.assertEquals("propertyC", property.getName());
        Assert.assertEquals("propertyC", property.getParameterName());
        Assert.assertEquals("dd/MM/yyyy", property.getTemporalType());
        Assert.assertNull(property.getMapping());
        
    }

    public void testBean26() throws NoSuchMethodException{
        Class clazz = BeanTest2Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyD");
        Assert.assertEquals(Date.class, property.getClassType());
        Assert.assertEquals("propertyD", property.getName());
        Assert.assertEquals("propertyD", property.getParameterName());
        Assert.assertEquals("yyyy-MM-dd", property.getTemporalType());
        Assert.assertNull(property.getMapping());
        
    }

    public void testBean27() throws NoSuchMethodException{
        Class clazz = BeanTest2Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyE");
        Assert.assertEquals(EnumTest.class, property.getClassType());
        Assert.assertEquals("propertyE", property.getName());
        Assert.assertEquals("propertyE", property.getParameterName());
        Assert.assertEquals(org.brandao.brutos.EnumerationType.ORDINAL, property.getEnumProperty());
        Assert.assertNull(property.getMapping());
        
    }

    public void testBean28() throws NoSuchMethodException{
        Class clazz = BeanTest2Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyF");
        Assert.assertEquals(EnumTest.class, property.getClassType());
        Assert.assertEquals("propertyF", property.getName());
        Assert.assertEquals("propertyF", property.getParameterName());
        Assert.assertEquals(org.brandao.brutos.EnumerationType.ORDINAL, property.getEnumProperty());
        Assert.assertNull(property.getMapping());
        
    }

    public void testBean29() throws NoSuchMethodException{
        Class clazz = BeanTest2Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyG");
        Assert.assertEquals(EnumTest.class, property.getClassType());
        Assert.assertEquals("propertyG", property.getName());
        Assert.assertEquals("propertyG", property.getParameterName());
        Assert.assertEquals(org.brandao.brutos.EnumerationType.STRING, property.getEnumProperty());
        Assert.assertNull(property.getMapping());
        
    }

    public void testBean30() throws NoSuchMethodException{
        Class clazz = BeanTest2Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyH");
        org.brandao.brutos.mapping.Bean beanProperty = property.getBean();
        Assert.assertEquals(TypeUtil.getDefaultMapType(), beanProperty.getClassType());
        
        DependencyBean key = ((MapBean)beanProperty).getKey();
        Assert.assertEquals("myKey", key.getParameterName());
        Assert.assertEquals(Integer.class, key.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, key.getScopeType());
        Assert.assertEquals(EnumerationType.STRING, key.getEnumProperty());
        Assert.assertEquals("yyyy-MM-dd", key.getTemporalType());
        Assert.assertEquals(IntegerWrapperType.class,key.getType().getClass());
        Assert.assertNull(key.getValue());

        DependencyBean element = ((MapBean)beanProperty).getCollection();
        Assert.assertEquals("myElement", element.getParameterName());
        Assert.assertEquals(String.class, element.getClassType());
        Assert.assertEquals(ScopeType.REQUEST, element.getScopeType());
        Assert.assertEquals(EnumerationType.STRING, element.getEnumProperty());
        Assert.assertEquals("yyyy-MM-dd", element.getTemporalType());
        Assert.assertEquals(StringType.class,element.getType().getClass());
        Assert.assertNull(element.getValue());
        
    }

    public void testBean31() throws NoSuchMethodException{
        Class clazz = BeanTest2Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyI");
        org.brandao.brutos.mapping.Bean beanProperty = property.getBean();
        Assert.assertEquals(LinkedHashMap.class, beanProperty.getClassType());
        
        DependencyBean key = ((MapBean)beanProperty).getKey();
        Assert.assertEquals("key", key.getParameterName());
        Assert.assertEquals(String.class, key.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, key.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, key.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, key.getTemporalType());
        Assert.assertEquals(StringType.class,key.getType().getClass());
        Assert.assertNull(key.getValue());

        DependencyBean element = ((MapBean)beanProperty).getCollection();
        Assert.assertEquals("element", element.getParameterName());
        Assert.assertEquals(Integer.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertEquals(IntegerWrapperType.class,element.getType().getClass());
        Assert.assertNull(element.getValue());
        
    }

    public void testBean32() throws NoSuchMethodException{
        Class clazz = BeanTest2Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyJ");
        org.brandao.brutos.mapping.Bean beanProperty = property.getBean();
        Assert.assertEquals(HashMap.class, beanProperty.getClassType());
        
        DependencyBean key = ((MapBean)beanProperty).getKey();
        Assert.assertEquals("key", key.getParameterName());
        Assert.assertEquals(BeanConstructorTest.class, key.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, key.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, key.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, key.getTemporalType());
        Assert.assertNotNull(key.getMapping());
        Assert.assertNull(key.getType());
        Assert.assertNull(key.getValue());

        DependencyBean element = ((MapBean)beanProperty).getCollection();
        Assert.assertEquals("element", element.getParameterName());
        Assert.assertEquals(Integer.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNull(element.getMapping());
        Assert.assertEquals(IntegerWrapperType.class,element.getType().getClass());
        Assert.assertNull(element.getValue());
        
    }

    public void testBean33() throws NoSuchMethodException{
        Class clazz = BeanTest2Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyK");
        org.brandao.brutos.mapping.Bean beanProperty = property.getBean();
        Assert.assertEquals(HashMap.class, beanProperty.getClassType());
        
        DependencyBean key = ((MapBean)beanProperty).getKey();
        Assert.assertEquals("key", key.getParameterName());
        Assert.assertEquals(String.class, key.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, key.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, key.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, key.getTemporalType());
        Assert.assertNull(key.getMapping());
        Assert.assertEquals(StringType.class,key.getType().getClass());
        Assert.assertNull(key.getValue());

        DependencyBean element = ((MapBean)beanProperty).getCollection();
        Assert.assertEquals("element", element.getParameterName());
        Assert.assertEquals(BeanConstructorTest.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNotNull(element.getMapping());
        Assert.assertNull(element.getType());
        Assert.assertNull(element.getValue());
        
    }

    public void testBean34() throws NoSuchMethodException{
        Class clazz = BeanTest2Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = null;
        annotationApplicationContext = getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyL");
        org.brandao.brutos.mapping.Bean beanProperty = property.getBean();
        Assert.assertEquals(HashMap.class, beanProperty.getClassType());
        
        DependencyBean key = ((MapBean)beanProperty).getKey();
        Assert.assertEquals("key", key.getParameterName());
        Assert.assertEquals(String.class, key.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, key.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, key.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, key.getTemporalType());
        Assert.assertNull(key.getMapping());
        Assert.assertEquals(StringType.class,key.getType().getClass());
        Assert.assertNull(key.getValue());

        DependencyBean element = ((MapBean)beanProperty).getCollection();
        Assert.assertEquals("myElement", element.getParameterName());
        Assert.assertEquals(BeanConstructorTest.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNotNull(element.getMapping());
        Assert.assertNull(element.getType());
        Assert.assertNull(element.getValue());
        
    }

    public void testBean35() throws NoSuchMethodException{
        Class clazz = BeanTest2Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyM");
        org.brandao.brutos.mapping.Bean beanProperty = property.getBean();
        Assert.assertEquals(TypeUtil.getDefaultListType(), beanProperty.getClassType());
        
        DependencyBean element = ((CollectionBean)beanProperty).getCollection();
        Assert.assertEquals("myElement", element.getParameterName());
        Assert.assertEquals(Integer.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNull(element.getMapping());
        Assert.assertEquals(IntegerWrapperType.class,element.getType().getClass());
        Assert.assertNull(element.getValue());
        
    }

    public void testBean36() throws NoSuchMethodException{
        Class clazz = BeanTest2Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyN");
        org.brandao.brutos.mapping.Bean beanProperty = property.getBean();
        Assert.assertEquals(LinkedList.class, beanProperty.getClassType());
        
        DependencyBean element = ((CollectionBean)beanProperty).getCollection();
        Assert.assertEquals("element", element.getParameterName());
        Assert.assertEquals(Integer.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNull(element.getMapping());
        Assert.assertEquals(IntegerWrapperType.class,element.getType().getClass());
        Assert.assertNull(element.getValue());
        
    }

    public void testBean37() throws NoSuchMethodException{
        Class clazz = BeanTest2Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyO");
        org.brandao.brutos.mapping.Bean beanProperty = property.getBean();
        Assert.assertEquals(TypeUtil.getDefaultListType(), beanProperty.getClassType());
        
        DependencyBean element = ((CollectionBean)beanProperty).getCollection();
        Assert.assertEquals("element", element.getParameterName());
        Assert.assertEquals(Integer.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNull(element.getMapping());
        Assert.assertEquals(IntegerWrapperType.class,element.getType().getClass());
        Assert.assertNull(element.getValue());
        
    }

    public void testBean38() throws NoSuchMethodException{
        Class clazz = BeanTest2Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyP");
        org.brandao.brutos.mapping.Bean beanProperty = property.getBean();
        Assert.assertEquals(TypeUtil.getDefaultListType(), beanProperty.getClassType());
        
        DependencyBean element = ((CollectionBean)beanProperty).getCollection();
        Assert.assertEquals("element", element.getParameterName());
        Assert.assertEquals(BeanConstructorTest.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNotNull(element.getMapping());
        Assert.assertNull(element.getType());
        Assert.assertNull(element.getValue());
        
    }

    public void testBean39() throws NoSuchMethodException{
        Class clazz = BeanTest2Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyQ");
        org.brandao.brutos.mapping.Bean beanProperty = property.getBean();
        Assert.assertEquals(TypeUtil.getDefaultListType(), beanProperty.getClassType());
        
        DependencyBean element = ((CollectionBean)beanProperty).getCollection();
        Assert.assertEquals("myElement", element.getParameterName());
        Assert.assertEquals(BeanConstructorTest.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNull(element.getMapping());
        Assert.assertNotNull(element.getType());
        Assert.assertNull(element.getValue());
        
    }

    public void testBean40() throws NoSuchMethodException{
        Class clazz = BeanTest2Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = null;
        annotationApplicationContext = getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyR");
        org.brandao.brutos.mapping.Bean beanProperty = property.getBean();
        Assert.assertEquals(TypeUtil.getDefaultMapType(), beanProperty.getClassType());
        
        DependencyBean key = ((MapBean)beanProperty).getKey();
        Assert.assertEquals("myKey", key.getParameterName());
        Assert.assertEquals(BeanConstructorTest.class, key.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, key.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, key.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, key.getTemporalType());
        Assert.assertNotNull(key.getMapping());
        Assert.assertNull(key.getType());
        Assert.assertNull(key.getValue());

        DependencyBean element = ((MapBean)beanProperty).getCollection();
        Assert.assertEquals("myElement", element.getParameterName());
        Assert.assertEquals(BeanConstructorTest.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNotNull(element.getMapping());
        Assert.assertNull(element.getType());
        Assert.assertNull(element.getValue());
        
    }

    public void testBean41() throws NoSuchMethodException{
        Class clazz = BeanTest2Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = null;
        annotationApplicationContext = getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyS");
        org.brandao.brutos.mapping.Bean beanProperty = property.getBean();
        Assert.assertEquals(TypeUtil.getDefaultListType(), beanProperty.getClassType());
        
        DependencyBean element = ((CollectionBean)beanProperty).getCollection();
        Assert.assertEquals("myElement", element.getParameterName());
        Assert.assertEquals(BeanConstructorTest.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNotNull(element.getMapping());
        Assert.assertNull(element.getType());
        Assert.assertNull(element.getValue());
        
    }

    public void testBean42() throws NoSuchMethodException{
        Class clazz = BeanTest2Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyT");
        org.brandao.brutos.mapping.Bean beanProperty = property.getBean();
        Assert.assertEquals(TypeUtil.getDefaultMapType(), beanProperty.getClassType());
        
        DependencyBean key = ((MapBean)beanProperty).getKey();
        Assert.assertEquals("key", key.getParameterName());
        Assert.assertEquals(String.class, key.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, key.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, key.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, key.getTemporalType());
        Assert.assertNull(key.getMapping());
        Assert.assertEquals(StringType.class,key.getType().getClass());
        Assert.assertNull(key.getValue());

        DependencyBean element = ((MapBean)beanProperty).getCollection();
        Assert.assertEquals("element", element.getParameterName());
        Assert.assertEquals(TypeUtil.getDefaultListType(), element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNotNull(element.getMapping());
        Assert.assertNull(element.getType());
        Assert.assertNull(element.getValue());
        
        DependencyBean subElement = ((CollectionBean)element.getBean()).getCollection();
        Assert.assertEquals("element", subElement.getParameterName());
        Assert.assertEquals(BeanConstructorTest.class, subElement.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, subElement.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, subElement.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, subElement.getTemporalType());
        Assert.assertNotNull(subElement.getMapping());
        Assert.assertNull(subElement.getType());
        Assert.assertNull(subElement.getValue());
        
    }

    public void testBean43() throws NoSuchMethodException{
        Class clazz = BeanTest2Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyU");
        org.brandao.brutos.mapping.Bean beanProperty = property.getBean();
        Assert.assertEquals(TypeUtil.getDefaultMapType(), beanProperty.getClassType());
        
        DependencyBean key = ((MapBean)beanProperty).getKey();
        Assert.assertEquals("key", key.getParameterName());
        Assert.assertEquals(String.class, key.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, key.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, key.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, key.getTemporalType());
        Assert.assertNull(key.getMapping());
        Assert.assertEquals(StringType.class,key.getType().getClass());
        Assert.assertNull(key.getValue());

        DependencyBean element = ((MapBean)beanProperty).getCollection();
        Assert.assertEquals("element", element.getParameterName());
        Assert.assertEquals(CustomArrayList.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNotNull(element.getMapping());
        Assert.assertNull(element.getType());
        Assert.assertNull(element.getValue());
        
        DependencyBean subElement = ((CollectionBean)element.getBean()).getCollection();
        Assert.assertEquals("myElement", subElement.getParameterName());
        Assert.assertEquals(BeanConstructorTest.class, subElement.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, subElement.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, subElement.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, subElement.getTemporalType());
        Assert.assertNotNull(subElement.getMapping());
        Assert.assertNull(subElement.getType());
        Assert.assertNull(subElement.getValue());
        
    }

    public void testBean44() throws NoSuchMethodException{
        Class clazz = BeanTest2Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();

        PropertyBean property = bean.getProperty("propertyV");
        org.brandao.brutos.mapping.Bean beanProperty = property.getBean();
        Assert.assertEquals(TypeUtil.getDefaultMapType(), beanProperty.getClassType());
        
        DependencyBean key = ((MapBean)beanProperty).getKey();
        Assert.assertEquals("key", key.getParameterName());
        Assert.assertEquals(String.class, key.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, key.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, key.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, key.getTemporalType());
        Assert.assertNull(key.getMapping());
        Assert.assertEquals(StringType.class,key.getType().getClass());
        Assert.assertNull(key.getValue());

        DependencyBean element = ((MapBean)beanProperty).getCollection();
        Assert.assertEquals("myElement2", element.getParameterName());
        Assert.assertEquals(CustomArrayList.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNotNull(element.getMapping());
        Assert.assertNull(element.getType());
        Assert.assertNull(element.getValue());
        
        DependencyBean subElement = ((CollectionBean)element.getBean()).getCollection();
        Assert.assertEquals("myElement", subElement.getParameterName());
        Assert.assertEquals(BeanConstructorTest.class, subElement.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, subElement.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, subElement.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, subElement.getTemporalType());
        Assert.assertNotNull(subElement.getMapping());
        Assert.assertNull(subElement.getType());
        Assert.assertNull(subElement.getValue());
        
    }
    
    public void testConstructorBean() throws NoSuchMethodException{
        
        Class clazz = BeanTest3Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();
        
        ConstructorBean constructor = bean.getConstructor();
        Assert.assertEquals(1,constructor.size());
        ConstructorArgBean arg = constructor.getConstructorArg(0);
        
        Assert.assertEquals(int.class, arg.getClassType());
        Assert.assertEquals("arg0", arg.getParameterName());
        Assert.assertNull(arg.getMapping());
    }

    public void testConstructoBean2() throws NoSuchMethodException{
        
        Class clazz = BeanTest4Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();
        
        ConstructorBean constructor = bean.getConstructor();
        Assert.assertEquals(1,constructor.size());
        ConstructorArgBean arg = constructor.getConstructorArg(0);
        
        Assert.assertEquals(String.class, arg.getClassType());
        Assert.assertEquals("prop", arg.getParameterName());
        Assert.assertNull(arg.getMapping());
    }

    public void testConstructoBean3() throws NoSuchMethodException{
        
        Class clazz = BeanTest5Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();
        
        ConstructorBean constructor = bean.getConstructor();
        Assert.assertEquals(1,constructor.size());
        ConstructorArgBean arg = constructor.getConstructorArg(0);
        
        Assert.assertEquals(Date.class, arg.getClassType());
        Assert.assertEquals("arg0", arg.getParameterName());
        Assert.assertEquals("dd/MM/yyyy", arg.getTemporalType());
        Assert.assertNull(arg.getMapping());
    }

    public void testConstructoBean4() throws NoSuchMethodException{
        
        Class clazz = BeanTest6Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();
        
        ConstructorBean constructor = bean.getConstructor();
        Assert.assertEquals(1,constructor.size());
        ConstructorArgBean arg = constructor.getConstructorArg(0);
        
        Assert.assertEquals(Date.class, arg.getClassType());
        Assert.assertEquals("arg0", arg.getParameterName());
        Assert.assertEquals("yyyy-MM-dd", arg.getTemporalType());
        Assert.assertNull(arg.getMapping());
    }

    public void testConstructoBean5() throws NoSuchMethodException{
        
        Class clazz = BeanTest7Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();
        
        ConstructorBean constructor = bean.getConstructor();
        Assert.assertEquals(1,constructor.size());
        ConstructorArgBean arg = constructor.getConstructorArg(0);
        
        Assert.assertEquals(EnumTest.class, arg.getClassType());
        Assert.assertEquals("arg0", arg.getParameterName());
        Assert.assertEquals(org.brandao.brutos.EnumerationType.ORDINAL, arg.getEnumProperty());
        Assert.assertNull(arg.getMapping());
    }

    public void testConstructoBean6() throws NoSuchMethodException{
        
        Class clazz = BeanTest8Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();
        
        ConstructorBean constructor = bean.getConstructor();
        Assert.assertEquals(1,constructor.size());
        ConstructorArgBean arg = constructor.getConstructorArg(0);
        
        Assert.assertEquals(EnumTest.class, arg.getClassType());
        Assert.assertEquals("arg0", arg.getParameterName());
        Assert.assertEquals(org.brandao.brutos.EnumerationType.ORDINAL, arg.getEnumProperty());
        Assert.assertNull(arg.getMapping());
    }

    public void testConstructoBean7() throws NoSuchMethodException{
        
        Class clazz = BeanTest9Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();
        
        ConstructorBean constructor = bean.getConstructor();
        Assert.assertEquals(1,constructor.size());
        ConstructorArgBean arg = constructor.getConstructorArg(0);
        
        Assert.assertEquals(EnumTest.class, arg.getClassType());
        Assert.assertEquals("arg0", arg.getParameterName());
        Assert.assertEquals(org.brandao.brutos.EnumerationType.STRING, arg.getEnumProperty());
        Assert.assertNull(arg.getMapping());
    }

    public void testConstructoBean8() throws NoSuchMethodException{
        
        try{
            Class clazz = BeanTest10Controller.class;
            getApplication(new Class[]{clazz});
            Assert.fail();
        }
        catch(BrutosException e){
        }
        
    }

    public void testConstructoBean9() throws NoSuchMethodException{
        
        Class clazz = BeanTest11Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();
        
        ConstructorBean constructor = bean.getConstructor();
        Assert.assertEquals(1,constructor.size());
        ConstructorArgBean arg = constructor.getConstructorArg(0);
        
        Assert.assertEquals(EnumTest.class, arg.getClassType());
        Assert.assertEquals("arg0", arg.getParameterName());
        Assert.assertEquals(org.brandao.brutos.EnumerationType.STRING, arg.getEnumProperty());
        Assert.assertNull(arg.getMapping());
    }

    public void testConstructoBean10() throws NoSuchMethodException{
        
        Class clazz = BeanTest12Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();
        
        ConstructorBean constructor = bean.getConstructor();
        Assert.assertEquals(1,constructor.size());
        ConstructorArgBean arg = constructor.getConstructorArg(0);
        
        Assert.assertEquals(int.class, arg.getClassType());
        Assert.assertEquals("arg0", arg.getParameterName());
        Assert.assertNull(arg.getMapping());
    }

    public void testConstructoBean11() throws NoSuchMethodException{
        
        try{
            Class clazz = BeanTest13Controller.class;
            getApplication(new Class[]{clazz});
            Assert.fail();
        }
        catch(BrutosException e){
        }
    }

    public void testConstructoBean12() throws NoSuchMethodException{
        
        Class clazz = BeanTest14Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();
        
        ConstructorBean constructor = bean.getConstructor();
        Assert.assertEquals(1,constructor.size());
        ConstructorArgBean arg = constructor.getConstructorArg(0);
        org.brandao.brutos.mapping.Bean beanProperty = arg.getBean();
        Assert.assertEquals(TypeUtil.getDefaultMapType(), beanProperty.getClassType());
        
        DependencyBean key = ((MapBean)beanProperty).getKey();
        Assert.assertEquals("myKey", key.getParameterName());
        Assert.assertEquals(Integer.class, key.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, key.getScopeType());
        Assert.assertEquals(EnumerationType.STRING, key.getEnumProperty());
        Assert.assertEquals("yyyy-MM-dd", key.getTemporalType());
        Assert.assertEquals(IntegerWrapperType.class,key.getType().getClass());
        Assert.assertNull(key.getValue());

        DependencyBean element = ((MapBean)beanProperty).getCollection();
        Assert.assertEquals("myElement", element.getParameterName());
        Assert.assertEquals(String.class, element.getClassType());
        Assert.assertEquals(ScopeType.REQUEST, element.getScopeType());
        Assert.assertEquals(EnumerationType.STRING, element.getEnumProperty());
        Assert.assertEquals("yyyy-MM-dd", element.getTemporalType());
        Assert.assertEquals(StringType.class,element.getType().getClass());
        Assert.assertNull(element.getValue());
    }

    public void testConstructoBean13() throws NoSuchMethodException{
        Class clazz = BeanTest15Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();
        
        ConstructorBean constructor = bean.getConstructor();
        Assert.assertEquals(1,constructor.size());
        ConstructorArgBean arg = constructor.getConstructorArg(0);
        org.brandao.brutos.mapping.Bean beanProperty = arg.getBean();
        Assert.assertEquals(LinkedHashMap.class, beanProperty.getClassType());
        
        DependencyBean key = ((MapBean)beanProperty).getKey();
        Assert.assertEquals("key", key.getParameterName());
        Assert.assertEquals(String.class, key.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, key.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, key.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, key.getTemporalType());
        Assert.assertEquals(StringType.class,key.getType().getClass());
        Assert.assertNull(key.getValue());

        DependencyBean element = ((MapBean)beanProperty).getCollection();
        Assert.assertEquals("element", element.getParameterName());
        Assert.assertEquals(Integer.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertEquals(IntegerWrapperType.class,element.getType().getClass());
        Assert.assertNull(element.getValue());
        
    }

    public void testConstructoBean14() throws NoSuchMethodException{
        Class clazz = BeanTest16Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();
        
        ConstructorBean constructor = bean.getConstructor();
        Assert.assertEquals(1,constructor.size());
        ConstructorArgBean arg = constructor.getConstructorArg(0);
        org.brandao.brutos.mapping.Bean beanProperty = arg.getBean();
        Assert.assertEquals(HashMap.class, beanProperty.getClassType());
        
        DependencyBean key = ((MapBean)beanProperty).getKey();
        Assert.assertEquals("key", key.getParameterName());
        Assert.assertEquals(BeanConstructorTest.class, key.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, key.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, key.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, key.getTemporalType());
        Assert.assertNotNull(key.getMapping());
        Assert.assertNull(key.getType());
        Assert.assertNull(key.getValue());

        DependencyBean element = ((MapBean)beanProperty).getCollection();
        Assert.assertEquals("element", element.getParameterName());
        Assert.assertEquals(Integer.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNull(element.getMapping());
        Assert.assertEquals(IntegerWrapperType.class,element.getType().getClass());
        Assert.assertNull(element.getValue());
        
    }

    public void testConstructoBean15() throws NoSuchMethodException{
        Class clazz = BeanTest17Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();
        
        ConstructorBean constructor = bean.getConstructor();
        Assert.assertEquals(1,constructor.size());
        ConstructorArgBean arg = constructor.getConstructorArg(0);
        org.brandao.brutos.mapping.Bean beanProperty = arg.getBean();
        Assert.assertEquals(HashMap.class, beanProperty.getClassType());
        
        DependencyBean key = ((MapBean)beanProperty).getKey();
        Assert.assertEquals("key", key.getParameterName());
        Assert.assertEquals(String.class, key.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, key.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, key.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, key.getTemporalType());
        Assert.assertNull(key.getMapping());
        Assert.assertEquals(StringType.class,key.getType().getClass());
        Assert.assertNull(key.getValue());

        DependencyBean element = ((MapBean)beanProperty).getCollection();
        Assert.assertEquals("element", element.getParameterName());
        Assert.assertEquals(BeanConstructorTest.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNotNull(element.getMapping());
        Assert.assertNull(element.getType());
        Assert.assertNull(element.getValue());
        
    }

    public void testConstructoBean16() throws NoSuchMethodException{
        Class clazz = BeanTest18Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = null;
        annotationApplicationContext = getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();
        
        ConstructorBean constructor = bean.getConstructor();
        Assert.assertEquals(1,constructor.size());
        ConstructorArgBean arg = constructor.getConstructorArg(0);
        org.brandao.brutos.mapping.Bean beanProperty = arg.getBean();
        Assert.assertEquals(HashMap.class, beanProperty.getClassType());
        
        DependencyBean key = ((MapBean)beanProperty).getKey();
        Assert.assertEquals("key", key.getParameterName());
        Assert.assertEquals(String.class, key.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, key.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, key.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, key.getTemporalType());
        Assert.assertNull(key.getMapping());
        Assert.assertEquals(StringType.class,key.getType().getClass());
        Assert.assertNull(key.getValue());

        DependencyBean element = ((MapBean)beanProperty).getCollection();
        Assert.assertEquals("myElement", element.getParameterName());
        Assert.assertEquals(BeanConstructorTest.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNotNull(element.getMapping());
        Assert.assertNull(element.getType());
        Assert.assertNull(element.getValue());
        
    }

    public void testConstructoBean17() throws NoSuchMethodException{
        Class clazz = BeanTest19Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();
        
        ConstructorBean constructor = bean.getConstructor();
        Assert.assertEquals(1,constructor.size());
        ConstructorArgBean arg = constructor.getConstructorArg(0);
        org.brandao.brutos.mapping.Bean beanProperty = arg.getBean();
        Assert.assertEquals(TypeUtil.getDefaultListType(), beanProperty.getClassType());
        
        DependencyBean element = ((CollectionBean)beanProperty).getCollection();
        Assert.assertEquals("myElement", element.getParameterName());
        Assert.assertEquals(Integer.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNull(element.getMapping());
        Assert.assertEquals(IntegerWrapperType.class,element.getType().getClass());
        Assert.assertNull(element.getValue());
        
    }

    public void testConstructoBean18() throws NoSuchMethodException{
        Class clazz = BeanTest20Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();
        
        ConstructorBean constructor = bean.getConstructor();
        Assert.assertEquals(1,constructor.size());
        ConstructorArgBean arg = constructor.getConstructorArg(0);
        org.brandao.brutos.mapping.Bean beanProperty = arg.getBean();
        Assert.assertEquals(LinkedList.class, beanProperty.getClassType());
        
        DependencyBean element = ((CollectionBean)beanProperty).getCollection();
        Assert.assertEquals("element", element.getParameterName());
        Assert.assertEquals(Integer.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNull(element.getMapping());
        Assert.assertEquals(IntegerWrapperType.class,element.getType().getClass());
        Assert.assertNull(element.getValue());
        
    }

    public void testConstructoBean19() throws NoSuchMethodException{
        Class clazz = BeanTest21Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();
        
        ConstructorBean constructor = bean.getConstructor();
        Assert.assertEquals(1,constructor.size());
        ConstructorArgBean arg = constructor.getConstructorArg(0);
        org.brandao.brutos.mapping.Bean beanProperty = arg.getBean();
        Assert.assertEquals(TypeUtil.getDefaultListType(), beanProperty.getClassType());
        
        DependencyBean element = ((CollectionBean)beanProperty).getCollection();
        Assert.assertEquals("element", element.getParameterName());
        Assert.assertEquals(Integer.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNull(element.getMapping());
        Assert.assertEquals(IntegerWrapperType.class,element.getType().getClass());
        Assert.assertNull(element.getValue());
        
    }

    public void testConstructoBean20() throws NoSuchMethodException{
        Class clazz = BeanTest22Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();
        
        ConstructorBean constructor = bean.getConstructor();
        Assert.assertEquals(1,constructor.size());
        ConstructorArgBean arg = constructor.getConstructorArg(0);
        org.brandao.brutos.mapping.Bean beanProperty = arg.getBean();
        Assert.assertEquals(TypeUtil.getDefaultListType(), beanProperty.getClassType());
        
        DependencyBean element = ((CollectionBean)beanProperty).getCollection();
        Assert.assertEquals("element", element.getParameterName());
        Assert.assertEquals(BeanConstructorTest.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNotNull(element.getMapping());
        Assert.assertNull(element.getType());
        Assert.assertNull(element.getValue());
        
    }

    public void testConstructoBean21() throws NoSuchMethodException{
        Class clazz = BeanTest23Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();
        
        ConstructorBean constructor = bean.getConstructor();
        Assert.assertEquals(1,constructor.size());
        ConstructorArgBean arg = constructor.getConstructorArg(0);
        org.brandao.brutos.mapping.Bean beanProperty = arg.getBean();
        Assert.assertEquals(TypeUtil.getDefaultListType(), beanProperty.getClassType());
        
        DependencyBean element = ((CollectionBean)beanProperty).getCollection();
        Assert.assertEquals("myElement", element.getParameterName());
        Assert.assertEquals(BeanConstructorTest.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNull(element.getMapping());
        Assert.assertNotNull(element.getType());
        Assert.assertNull(element.getValue());
        
    }

    public void testConstructoBean22() throws NoSuchMethodException{
        Class clazz = BeanTest24Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = null;
        annotationApplicationContext = getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();
        
        ConstructorBean constructor = bean.getConstructor();
        Assert.assertEquals(1,constructor.size());
        ConstructorArgBean arg = constructor.getConstructorArg(0);
        org.brandao.brutos.mapping.Bean beanProperty = arg.getBean();
        Assert.assertEquals(TypeUtil.getDefaultMapType(), beanProperty.getClassType());
        
        DependencyBean key = ((MapBean)beanProperty).getKey();
        Assert.assertEquals("myKey", key.getParameterName());
        Assert.assertEquals(BeanConstructorTest.class, key.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, key.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, key.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, key.getTemporalType());
        Assert.assertNotNull(key.getMapping());
        Assert.assertNull(key.getType());
        Assert.assertNull(key.getValue());

        DependencyBean element = ((MapBean)beanProperty).getCollection();
        Assert.assertEquals("myElement", element.getParameterName());
        Assert.assertEquals(BeanConstructorTest.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNotNull(element.getMapping());
        Assert.assertNull(element.getType());
        Assert.assertNull(element.getValue());
        
    }

    public void testConstructoBean23() throws NoSuchMethodException{
        Class clazz = BeanTest25Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = null;
        annotationApplicationContext = getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();
        
        ConstructorBean constructor = bean.getConstructor();
        Assert.assertEquals(1,constructor.size());
        ConstructorArgBean arg = constructor.getConstructorArg(0);
        org.brandao.brutos.mapping.Bean beanProperty = arg.getBean();
        Assert.assertEquals(TypeUtil.getDefaultListType(), beanProperty.getClassType());
        
        DependencyBean element = ((CollectionBean)beanProperty).getCollection();
        Assert.assertEquals("myElement", element.getParameterName());
        Assert.assertEquals(BeanConstructorTest.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNotNull(element.getMapping());
        Assert.assertNull(element.getType());
        Assert.assertNull(element.getValue());
        
    }

    public void testConstructoBean24() throws NoSuchMethodException{
        Class clazz = BeanTest26Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();
        
        ConstructorBean constructor = bean.getConstructor();
        Assert.assertEquals(1,constructor.size());
        ConstructorArgBean arg = constructor.getConstructorArg(0);
        org.brandao.brutos.mapping.Bean beanProperty = arg.getBean();
        Assert.assertEquals(TypeUtil.getDefaultMapType(), beanProperty.getClassType());
        
        DependencyBean key = ((MapBean)beanProperty).getKey();
        Assert.assertEquals("key", key.getParameterName());
        Assert.assertEquals(String.class, key.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, key.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, key.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, key.getTemporalType());
        Assert.assertNull(key.getMapping());
        Assert.assertEquals(StringType.class,key.getType().getClass());
        Assert.assertNull(key.getValue());

        DependencyBean element = ((MapBean)beanProperty).getCollection();
        Assert.assertEquals("element", element.getParameterName());
        Assert.assertEquals(TypeUtil.getDefaultListType(), element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNotNull(element.getMapping());
        Assert.assertNull(element.getType());
        Assert.assertNull(element.getValue());
        
        DependencyBean subElement = ((CollectionBean)element.getBean()).getCollection();
        Assert.assertEquals("element", subElement.getParameterName());
        Assert.assertEquals(BeanConstructorTest.class, subElement.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, subElement.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, subElement.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, subElement.getTemporalType());
        Assert.assertNotNull(subElement.getMapping());
        Assert.assertNull(subElement.getType());
        Assert.assertNull(subElement.getValue());
        
    }

    public void testConstructoBean25() throws NoSuchMethodException{
        Class clazz = BeanTest27Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();
        
        ConstructorBean constructor = bean.getConstructor();
        Assert.assertEquals(1,constructor.size());
        ConstructorArgBean arg = constructor.getConstructorArg(0);
        org.brandao.brutos.mapping.Bean beanProperty = arg.getBean();
        Assert.assertEquals(TypeUtil.getDefaultMapType(), beanProperty.getClassType());
        
        DependencyBean key = ((MapBean)beanProperty).getKey();
        Assert.assertEquals("key", key.getParameterName());
        Assert.assertEquals(String.class, key.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, key.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, key.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, key.getTemporalType());
        Assert.assertNull(key.getMapping());
        Assert.assertEquals(StringType.class,key.getType().getClass());
        Assert.assertNull(key.getValue());

        DependencyBean element = ((MapBean)beanProperty).getCollection();
        Assert.assertEquals("element", element.getParameterName());
        Assert.assertEquals(CustomArrayList.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNotNull(element.getMapping());
        Assert.assertNull(element.getType());
        Assert.assertNull(element.getValue());
        
        DependencyBean subElement = ((CollectionBean)element.getBean()).getCollection();
        Assert.assertEquals("myElement", subElement.getParameterName());
        Assert.assertEquals(BeanConstructorTest.class, subElement.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, subElement.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, subElement.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, subElement.getTemporalType());
        Assert.assertNotNull(subElement.getMapping());
        Assert.assertNull(subElement.getType());
        Assert.assertNull(subElement.getValue());
        
    }

    public void testConstructoBean26() throws NoSuchMethodException{
        Class clazz = BeanTest28Controller.class;
        
        ConfigurableApplicationContext annotationApplicationContext = 
                getApplication(new Class[]{clazz});
        
        org.brandao.brutos.mapping.Controller controller = 
                annotationApplicationContext
                    .getControllerManager().getController(clazz);
        
        String prefix = 
            "/" + clazz.getSimpleName().replaceAll("Controller$", "") + "/";
        org.brandao.brutos.mapping.Action action = 
                controller.getActionByName("/my");
        
        ParameterAction param = action.getParameter(0);
        
        org.brandao.brutos.mapping.Bean bean = param.getMapping();
        
        ConstructorBean constructor = bean.getConstructor();
        Assert.assertEquals(1,constructor.size());
        ConstructorArgBean arg = constructor.getConstructorArg(0);
        org.brandao.brutos.mapping.Bean beanProperty = arg.getBean();
        Assert.assertEquals(TypeUtil.getDefaultMapType(), beanProperty.getClassType());
        
        DependencyBean key = ((MapBean)beanProperty).getKey();
        Assert.assertEquals("key", key.getParameterName());
        Assert.assertEquals(String.class, key.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, key.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, key.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, key.getTemporalType());
        Assert.assertNull(key.getMapping());
        Assert.assertEquals(StringType.class,key.getType().getClass());
        Assert.assertNull(key.getValue());

        DependencyBean element = ((MapBean)beanProperty).getCollection();
        Assert.assertEquals("myElement2", element.getParameterName());
        Assert.assertEquals(CustomArrayList.class, element.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, element.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, element.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, element.getTemporalType());
        Assert.assertNotNull(element.getMapping());
        Assert.assertNull(element.getType());
        Assert.assertNull(element.getValue());
        
        DependencyBean subElement = ((CollectionBean)element.getBean()).getCollection();
        Assert.assertEquals("myElement", subElement.getParameterName());
        Assert.assertEquals(BeanConstructorTest.class, subElement.getClassType());
        Assert.assertEquals(BrutosConstants.DEFAULT_SCOPETYPE, subElement.getScopeType());
        Assert.assertEquals(BrutosConstants.DEFAULT_ENUMERATIONTYPE, subElement.getEnumProperty());
        Assert.assertEquals(BrutosConstants.DEFAULT_TEMPORALPROPERTY, subElement.getTemporalType());
        Assert.assertNotNull(subElement.getMapping());
        Assert.assertNull(subElement.getType());
        Assert.assertNull(subElement.getValue());
        
    }
    
}
