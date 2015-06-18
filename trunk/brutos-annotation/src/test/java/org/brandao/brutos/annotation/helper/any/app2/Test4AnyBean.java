package org.brandao.brutos.annotation.helper.any.app2;

import java.util.Date;
import java.util.List;

import org.brandao.brutos.annotation.Any;
import org.brandao.brutos.annotation.Basic;
import org.brandao.brutos.annotation.ElementCollection;
import org.brandao.brutos.annotation.MetaValue;
import org.brandao.brutos.annotation.Transient;

public class Test4AnyBean {

	@Basic(bean="propertyA")
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
	public List<Property> property1;
	
	private List<Property> property2;

	@Transient
	private List<Property> property3;
	
	public Test4AnyBean(
			@Basic(bean="propertyC")
			@ElementCollection(
				any=
					@Any(
						metaBean=@Basic(bean="propertyType3"),
						metaType=Date.class,
						metaTemporal="yyyy-MM-dd",
						metaValues={
							@MetaValue(name="2015-01-01", target=DecimalProperty.class),
							@MetaValue(name="2015-01-02", target=SetProperty.class)
						}
					)
				)
			List<Property> property3 ){
		this.property3 = property3;
	}
	
	public List<Property> getProperty2() {
		return property2;
	}

	@Basic(bean="propertyB")
	@ElementCollection(
		any=
			@Any(
				metaBean=@Basic(bean="propertyType2"),
				metaType=Date.class,
				metaTemporal="yyyy-MM-dd",
				metaValues={
					@MetaValue(name="2015-01-01", target=DecimalProperty.class),
					@MetaValue(name="2015-01-02", target=SetProperty.class)
				}
			)
		)
	public void setProperty2(List<Property> property2) {
		this.property2 = property2;
	}

	public List<Property> getProperty3() {
		return property3;
	}

	public void setProperty3(List<Property> property3) {
		this.property3 = property3;
	}

	
}
