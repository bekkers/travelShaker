package com.prochainvol.ui;

import com.prochainvol.ProchainvolException;

public interface IAffichable {

	void accept(IAffichableVisitor visitor) throws ProchainvolException; // Affichable have to provide accept().
}
