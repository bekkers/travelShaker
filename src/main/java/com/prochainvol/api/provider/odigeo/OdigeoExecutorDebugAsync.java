package com.prochainvol.api.provider.odigeo;

public class OdigeoExecutorDebugAsync extends AbstractOdigeoAsyncExecutor {
	
	public OdigeoExecutorDebugAsync() {
		this.operator = new DebugOperator();
	}

	public OdigeoExecutorDebugAsync(String[] fileName) {
		this.operator = new DebugOperator(fileName);
	}

}
