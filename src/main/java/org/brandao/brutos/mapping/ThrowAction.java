package org.brandao.brutos.mapping;

import org.brandao.brutos.Invoker;
import org.brandao.brutos.StackRequestElement;

public class ThrowAction 
	extends Action{

	public boolean isResolvedView() {
		if(super.getView() != null){
			return super.isResolvedView();
		}
		
		StackRequestElement sre = 
				Invoker.getInstance()
					.getStackRequestElement()
					.getPreviousStackRequestElement();		
		
		return
			sre == null?
				super.isResolvedView() :
			sre.getAction().getMethodForm().isResolvedView();
	}

	public String getView(){
		if(super.getView() != null){
			return super.getView();
		}
		
		StackRequestElement sre = 
				Invoker.getInstance()
					.getStackRequestElement()
					.getPreviousStackRequestElement();		
		
		return
			sre == null?
			super.getView() :
			sre.getAction().getMethodForm().getView();
	}
	
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
