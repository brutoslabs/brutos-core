package org.brandao.brutos.annotation.helper.any.app2;

import java.util.Date;
import java.util.List;

import org.brandao.brutos.annotation.Any;
import org.brandao.brutos.annotation.Basic;
import org.brandao.brutos.annotation.Controller;
import org.brandao.brutos.annotation.ElementCollection;
import org.brandao.brutos.annotation.EnumerationType;
import org.brandao.brutos.annotation.MetaValue;
import org.brandao.brutos.annotation.Transient;

@Controller("/controller")
public class Test1AnyController {

	@Transient
	private List<Property> property;
	
	public void test1Action(
			@Basic(bean="property")
			@ElementCollection(
				any=
				@Any(
					metaBean=@Basic(bean="propertyType"),
					metaType=String.class,
					metaValues={
						@MetaValue(name="decimal", target=DecimalProperty.class),
						@MetaValue(name="set", target=SetProperty.class)
					}
				)
			)
			List<Property> property){
		this.property = property;
	}

	public void test2Action(
			@Basic(bean="property")
			@ElementCollection(
				any=
					@Any(
						metaBean=@Basic(bean="propertyType"),
						metaType=PropertyType.class,
						metaEnumerated=EnumerationType.STRING,
						metaValues={
							@MetaValue(name="DECIMAL", target=DecimalProperty.class),
							@MetaValue(name="SET", target=SetProperty.class)
						}
					)
				)
			List<Property> property){
		this.property = property;
	}
	
	public void test3Action(
			@Basic(bean="property")
			@ElementCollection(
				any=
				@Any(
					metaBean=@Basic(bean="propertyType"),
					metaType=Integer.class,
					metaValues={
						@MetaValue(name="0", target=DecimalProperty.class),
						@MetaValue(name="1", target=SetProperty.class)
					}
				)
				)
			List<Property> property){
		this.property = property;
	}

	public void test4Action(
			@Basic(bean="property")
			@ElementCollection(
				any=
				@Any(
					metaBean=@Basic(bean="propertyType"),
					metaType=Date.class,
					metaTemporal="yyyy-MM-dd",
					metaValues={
						@MetaValue(name="2015-01-01", target=DecimalProperty.class),
						@MetaValue(name="2015-01-02", target=SetProperty.class)
					}
				)
				)
			List<Property> property){
		this.property = property;
	}
	
	public List<Property> getProperty() {
		return property;
	}

	public void setProperty(List<Property> property) {
		this.property = property;
	}
	
	
}