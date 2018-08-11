package org.brandao.brutos.mapping;

public class ThrowActionID extends ActionID{

	private static final long serialVersionUID = 3996106863352031176L;
	
	private Class<?> type;
	
	public ThrowActionID(Class<?> type) {
		super(type.getSimpleName() + "[ExP]");
		this.type = type;
	}

	public Class<?> getType() {
		return type;
	}

}
