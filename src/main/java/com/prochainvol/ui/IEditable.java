package com.prochainvol.ui;

import com.prochainvol.ProchainvolException;

public interface IEditable  {
	void accept(IEditableVisitor visitor) throws ProchainvolException; // Affichable have to provide accept().

}
