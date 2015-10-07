package com.prochainvol.api.provider.odigeo;

import com.prochainvol.Constants;

public enum ODIGEO_PROVIDER {
	GV("http://tracking.publicidees.com/clic.php?promoid=106266&progid=515&partid=&url=http://eulerian.govoyages.com/dynclick/govoyages/?etf-publisher=publicidees-metasearch&etf-name=prochainvol&etf-prdref=XXX&eurl=", "GO_VOYAGES"),
	OP("http://tracking.publicidees.com/clic.php?promoid=106267&progid=1487&cmp=fr-afpi-prochainvol&partid=&url=", "OPODO"),
	ED("http://tracking.publicidees.com/clic.php?promoid=106269&progid=1811&partid=&url=", "EDREAMS");
	
	private final String format;

	private final String fullName;

	private ODIGEO_PROVIDER(String format, String fullName) {
		this.format = format.replace("partid=","partid="+Constants.ODIGEO_PARTID);
		this.fullName = fullName;
	}
	public String getFormat() {
		return format;
	}

	public String getFullName() {
		return fullName;
	}
}
