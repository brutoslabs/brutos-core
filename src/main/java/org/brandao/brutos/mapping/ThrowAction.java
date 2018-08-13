package org.brandao.brutos.mapping;

import org.brandao.brutos.Invoker;
import org.brandao.brutos.StackRequestElement;

public class ThrowAction 
	extends Action{

	public DataTypeMap getRequestTypes() {
		StackRequestElement sre = 
				Invoker.getInstance()
					.getStackRequestElement()
					.getPreviousStackRequestElement();
		
		return sre.getAction().getRequestTypes();
	}
	
	public DataTypeMap getResponseTypes() {
		StackRequestElement sre = 
				Invoker.getInstance()
					.getStackRequestElement()
					.getPreviousStackRequestElement();
		
		return sre.getAction().getResponseTypes();
	}
	
}
