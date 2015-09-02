package com.prochainvol.api.provider.odigeo;

public class OdigeoExecutorBamAsync extends AbstractOdigeoAsyncExecutor {
	
	public OdigeoExecutorBamAsync() {
		this.operator = new BamOperator();
	}

}
