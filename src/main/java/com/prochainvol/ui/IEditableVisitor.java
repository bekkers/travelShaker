package com.prochainvol.ui;

import com.prochainvol.ProchainvolConfig;
import com.prochainvol.ProchainvolException;
import com.prochainvol.api.request.RequestParams;
import com.prochainvol.api.request.filter.Filter;

public interface IEditableVisitor extends IResponsiveVisitor {
	public void visit(Filter filter);
	public void visit(ProchainvolConfig prochainvolConfig);
	public void visit(RequestParams requestparams) throws ProchainvolException;

}
