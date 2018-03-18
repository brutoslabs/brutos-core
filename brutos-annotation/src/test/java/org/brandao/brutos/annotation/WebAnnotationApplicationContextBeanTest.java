package org.brandao.brutos.annotation;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.brandao.brutos.annotation.WebAnnotationApplicationContextBeanTestHelper.DateTest;
import org.brandao.brutos.annotation.WebAnnotationApplicationContextBeanTestHelper.EnumTest;
import org.brandao.brutos.annotation.WebAnnotationApplicationContextBeanTestHelper.EnumValues;
import org.brandao.brutos.annotation.WebAnnotationApplicationContextBeanTestHelper.FieldTest;
import org.brandao.brutos.annotation.WebAnnotationApplicationContextBeanTestHelper.ListTest;
import org.brandao.brutos.annotation.WebAnnotationApplicationContextBeanTestHelper.Values;
import org.brandao.brutos.annotation.web.test.MockAnnotationWebApplicationContext;
import org.brandao.brutos.web.ConfigurableWebApplicationContext;
import org.brandao.brutos.web.ContextLoader;
import org.brandao.brutos.web.test.BasicWebApplicationTester;
import org.brandao.brutos.web.test.WebApplicationContextTester;

import com.mockrunner.mock.web.MockHttpServletRequest;

import junit.framework.TestCase;

public class WebAnnotationApplicationContextBeanTest extends BrutosTestCase{

	public void testField(){
		WebApplicationContextTester.run(
			"/fieldtest", 
			new BasicWebApplicationTester(){
				
                public void prepareContext(Map<String, String> parameters) {
                    parameters.put(
                            ContextLoader.CONTEXT_CLASS,
                            MockAnnotationWebApplicationContext.class.getName()
                    );

                    parameters.put(
                            MockAnnotationWebApplicationContext.IGNORE_RESOURCES,
                            "true"
                    );
                }
				
				public void prepareRequest(Map<String, String> parameters,
						Map<String, String> header, Map<String, Object> properties) {
					
					parameters.put("fieldTest.propertyA", "1");
					parameters.put("fieldTest.propertyB", "teste");
					parameters.put("fieldTest.propertyC", "2000-01-01");
					parameters.put("fieldTest.propertyD", "2");
					parameters.put("fieldTest.property",  "3");
				}
				
				public void prepareSession(Map<String, Object> parameters) {
					parameters.put("fieldTest.proprty",  "4");
				}
				
				public void checkResult(HttpServletRequest request,
						HttpServletResponse response, ServletContext context,
						ConfigurableWebApplicationContext applicationContext) {
					
					WebAnnotationApplicationContextBeanTestHelper.FieldTest bean =
							(FieldTest) request.getAttribute("fieldTest");
					
					assertEquals(1, bean.propertyA);
					assertEquals("teste", bean.propertyB);
					assertEquals(Values.dateValue, bean.propertyC);
					assertEquals(new Integer(2), bean.propertyD);
					assertEquals(new Integer(3), bean.propertyE);
				}
				
				public void checkException(Throwable e) {
					e.printStackTrace();
				}
				
			}, 
			new Class[]{WebAnnotationApplicationContextBeanTestHelper.ControllerTest.class}
		);
	}

	public void testEnum(){
		WebApplicationContextTester.run(
			"/enumtest", 
			new BasicWebApplicationTester(){
				
                public void prepareContext(Map<String, String> parameters) {
                    parameters.put(
                            ContextLoader.CONTEXT_CLASS,
                            MockAnnotationWebApplicationContext.class.getName()
                    );

                    parameters.put(
                            MockAnnotationWebApplicationContext.IGNORE_RESOURCES,
                            "true"
                    );
                }
				
				public void prepareRequest(Map<String, String> parameters,
						Map<String, String> header, Map<String, Object> properties) {
					
					parameters.put("enumTest.propertyA", "VALUE1");
					parameters.put("enumTest.propertyB", "0");
					parameters.put("enumTest.propertyC", "VALUE2");
					parameters.put("enumTest.property", "1");
					parameters.put("enumTest.property2", "0");
				}
				
				public void prepareSession(Map<String, Object> parameters) {
				}
				
				public void checkResult(HttpServletRequest request,
						HttpServletResponse response, ServletContext context,
						ConfigurableWebApplicationContext applicationContext) {
					
					EnumTest bean =
							(EnumTest) request.getAttribute("enumTest");
					
					assertEquals(EnumValues.VALUE1, bean.propertyA);
					assertEquals(EnumValues.VALUE1, bean.propertyB);
					assertEquals(EnumValues.VALUE2, bean.propertyC);
					assertEquals(EnumValues.VALUE2, bean.propertyD);
					assertEquals(EnumValues.VALUE1, bean.propertyE);
				}
				
				public void checkException(Throwable e) {
					e.printStackTrace();
				}
				
			}, 
			new Class[]{WebAnnotationApplicationContextBeanTestHelper.ControllerTest.class}
		);
	}
	
	public void testDate(){
		WebApplicationContextTester.run(
			"/datetest", 
			new BasicWebApplicationTester(){
				
                public void prepareContext(Map<String, String> parameters) {
                    parameters.put(
                            ContextLoader.CONTEXT_CLASS,
                            MockAnnotationWebApplicationContext.class.getName()
                    );

                    parameters.put(
                            MockAnnotationWebApplicationContext.IGNORE_RESOURCES,
                            "true"
                    );
                }
				
				public void prepareRequest(Map<String, String> parameters,
						Map<String, String> header, Map<String, Object> properties) {
					
					parameters.put("enumTest.propertyA", "2000-01-01");
					parameters.put("enumTest.propertyB", "01/01/2000");
					parameters.put("enumTest.property", "01/01/2000");
				}
				
				public void prepareSession(Map<String, Object> parameters) {
				}
				
				public void checkResult(HttpServletRequest request,
						HttpServletResponse response, ServletContext context,
						ConfigurableWebApplicationContext applicationContext) {
					
					DateTest bean =
							(DateTest) request.getAttribute("dateTest");
					
					assertEquals(Values.dateValue, bean.propertyA);
					assertEquals(Values.dateValue, bean.propertyB);
					assertEquals(Values.dateValue, bean.propertyC);
					
				}
				
				public void checkException(Throwable e) {
					e.printStackTrace();
				}
				
			}, 
			new Class[]{WebAnnotationApplicationContextBeanTestHelper.ControllerTest.class}
		);
	}

	public void testList(){
		WebApplicationContextTester.run(
			"/listtest", 
			new BasicWebApplicationTester(){
				
                public void prepareContext(Map<String, String> parameters) {
                    parameters.put(
                            ContextLoader.CONTEXT_CLASS,
                            MockAnnotationWebApplicationContext.class.getName()
                    );

                    parameters.put(
                            MockAnnotationWebApplicationContext.IGNORE_RESOURCES,
                            "true"
                    );
                }
				
            	public void prepareRequest(MockHttpServletRequest request) {
            		request.setupAddParameter("listTest.propertyA", String.valueOf(Values.intValue));
            		request.setupAddParameter("listTest.propertyA", String.valueOf(Values.otherIntValue));
            		request.setupAddParameter("listTest.propertyA", String.valueOf(Values.intValue));
            		
            		request.setupAddParameter("listTest.propertyA.itens[0]", String.valueOf(Values.intValue));
            		request.setupAddParameter("listTest.propertyA.itens[1]", String.valueOf(Values.otherIntValue));
            		request.setupAddParameter("listTest.propertyA.itens[2]", String.valueOf(Values.intValue));
            		
            		request.setupAddParameter("listTest.propertyC", String.valueOf(Values.enumValue.ordinal()));
            		request.setupAddParameter("listTest.propertyC", String.valueOf(Values.otherEnumValue.ordinal()));
            		request.setupAddParameter("listTest.propertyc", String.valueOf(Values.enumValue.ordinal()));
            		
            		request.setupAddParameter("listTest.propertyD.itens[0]", Values.enumValue.name());
            		request.setupAddParameter("listTest.propertyD.itens[1]", Values.otherEnumValue.name());
            		request.setupAddParameter("listTest.propertyD.itens[2]", Values.enumValue.name());
            		
            		request.setupAddParameter("listTest.propertyE", String.valueOf(Values.enumValue.ordinal()));
            		request.setupAddParameter("listTest.propertyE", String.valueOf(Values.otherEnumValue.ordinal()));
            		request.setupAddParameter("listTest.propertyE", String.valueOf(Values.enumValue.ordinal()));
            		
            		request.setupAddParameter("listTest.propertyF.itens[0]", String.valueOf(Values.enumValue.ordinal()));
            		request.setupAddParameter("listTest.propertyF.itens[1]", String.valueOf(Values.otherEnumValue.ordinal()));
            		request.setupAddParameter("listTest.propertyF.itens[2]", String.valueOf(Values.enumValue.ordinal()));

            		request.setupAddParameter("listTest.propertyG", Values.stringValue);
            		request.setupAddParameter("listTest.propertyG", Values.otherStringValue);
            		request.setupAddParameter("listTest.propertyG", Values.stringValue);
            		
            		request.setupAddParameter("listTest.propertyH.itens[0]", Values.stringValue);
            		request.setupAddParameter("listTest.propertyH.itens[1]", Values.otherStringValue);
            		request.setupAddParameter("listTest.propertyH.itens[2]", Values.stringValue);

            		request.setupAddParameter("listTest.propertyI", Values.brDateStringValue);
            		request.setupAddParameter("listTest.propertyI", Values.otherBRDateStringValue);
            		request.setupAddParameter("listTest.propertyI", Values.brDateStringValue);
            		
            		request.setupAddParameter("listTest.propertyJ.itens[0]", Values.brDateStringValue);
            		request.setupAddParameter("listTest.propertyJ.itens[1]", Values.otherBRDateStringValue);
            		request.setupAddParameter("listTest.propertyJ.itens[2]", Values.brDateStringValue);
            		
            		request.setupAddParameter("listTest.propertyK.property", String.valueOf(Values.intValue));
            		request.setupAddParameter("listTest.propertyK.property", String.valueOf(Values.otherIntValue));
            		request.setupAddParameter("listTest.propertyK.property", String.valueOf(Values.intValue));
            		
            		request.setupAddParameter("listTest.propertyL.itens[0].property", String.valueOf(Values.intValue));
            		request.setupAddParameter("listTest.propertyL.itens[1].property", String.valueOf(Values.otherIntValue));
            		request.setupAddParameter("listTest.propertyL.itens[2].property", String.valueOf(Values.intValue));

            		request.setupAddParameter("listTest.propertyN.itens[0].myElement[0].property", String.valueOf(Values.intValue));
            		request.setupAddParameter("listTest.propertyN.itens[1].myElement[0].property", String.valueOf(Values.otherIntValue));
            		request.setupAddParameter("listTest.propertyN.itens[2].myElement[0].property", String.valueOf(Values.intValue));
            		
            		request.setupAddParameter("listTest.propertyP.itens[0]", String.valueOf(Values.intValue));
            		request.setupAddParameter("listTest.propertyP.itens[1]", String.valueOf(Values.otherIntValue));
            		request.setupAddParameter("listTest.propertyP.itens[2]", String.valueOf(Values.intValue));
            		
            	}
            	
				
				public void prepareSession(Map<String, Object> parameters) {
				}
				
				public void checkResult(HttpServletRequest request,
						HttpServletResponse response, ServletContext context,
						ConfigurableWebApplicationContext applicationContext) {
					
					ListTest bean =
							(ListTest) request.getAttribute("listTest");
					
					assertEquals(bean.propertyA, Arrays.asList(Values.intValue, Values.otherIntValue, Values.intValue));
					
					assertEquals(bean.propertyB, Arrays.asList(Values.intValue, Values.otherIntValue, Values.intValue));
					
					assertEquals(bean.propertyC, Arrays.asList(Values.enumValue, Values.otherEnumValue, Values.enumValue));
            		
					assertEquals(bean.propertyD, Arrays.asList(Values.enumValue, Values.otherEnumValue, Values.enumValue));
            		
					assertEquals(bean.propertyE, Arrays.asList(Values.enumValue, Values.otherEnumValue, Values.enumValue));
            		
					assertEquals(bean.propertyF, Arrays.asList(Values.enumValue, Values.otherEnumValue, Values.enumValue));

					assertEquals(bean.propertyG, Arrays.asList(Values.dateValue, Values.otherDateValue, Values.dateValue));
					
					assertEquals(bean.propertyH, Arrays.asList(Values.dateValue, Values.otherDateValue, Values.dateValue));
					
					assertEquals(bean.propertyI, Arrays.asList(Values.dateValue, Values.otherDateValue, Values.dateValue));
					
					assertEquals(bean.propertyJ, Arrays.asList(Values.dateValue, Values.otherDateValue, Values.dateValue));
					
					assertEquals(bean.propertyK, Arrays.asList(Values.constructorTestValue, Values.otherConstructorTestValue, Values.constructorTestValue));
					
					assertEquals(bean.propertyL, Arrays.asList(Values.constructorTestValue, Values.otherConstructorTestValue, Values.constructorTestValue));
            		
					assertEquals(bean.propertyN, Arrays.asList(Values.constructorTestValue, Values.otherConstructorTestValue, Values.constructorTestValue));
					
					assertEquals(bean.propertyP, Arrays.asList(Values.constructorTestValue, Values.otherConstructorTestValue, Values.constructorTestValue));
					
				}
				
				public void checkException(Throwable e) {
					e.printStackTrace();
				}
				
			}, 
			new Class[]{WebAnnotationApplicationContextBeanTestHelper.ControllerTest.class}
		);
	}
	
}