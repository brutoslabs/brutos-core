package org.brandao.brutos.annotation.helper.any.app3.fail;

import java.util.Map;

import org.brandao.brutos.annotation.Any;
import org.brandao.brutos.annotation.Basic;
import org.brandao.brutos.annotation.KeyCollection;
import org.brandao.brutos.annotation.MetaValue;
import org.brandao.brutos.annotation.helper.any.app1.DecimalProperty;
import org.brandao.brutos.annotation.helper.any.app1.Property;
import org.brandao.brutos.annotation.helper.any.app1.SetProperty;

public class Test2FailAnyBean {

	private Map<Property,String> property;

	public Map<Property,String> getProperty() {
		return property;
	}

	@KeyCollection(
		bean="key",
		any=
			@Any(
					metaBean=@Basic,
					metaValues={
							@MetaValue(name="0", target=DecimalProperty.class),
							@MetaValue(name="1", target=SetProperty.class)
						})
		)
	public void setProperty(Map<Property,String> property) {
		this.property = property;
	}


}
