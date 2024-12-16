package com.masintec.manageroads.factory;

import com.masintec.manageroads.model.WarningSign;
import com.masintec.manageroads.model.RegulatorySign;
import com.masintec.manageroads.model.GuideSign;
import com.masintec.manageroads.model.InformationSign;
import com.masintec.manageroads.model.Sign.SignType;
import com.masintec.manageroads.model.Sign;
import com.masintec.manageroads.model.UnknownSign;

public final class SignFactory {
	
	public static Sign createSign(Sign sign) {
		Sign created = null;

		switch (sign.getSignType().getSType()) {
			case SignType.Values.WARNING_VALUE:
				created = new WarningSign();
				break;
			case SignType.Values.REGULATORY_VALUE:
				created = new RegulatorySign();
				break;
			case SignType.Values.GUIDE_VALUE:
				created = new GuideSign();
				break;
			case SignType.Values.INFORMATION_VALUE:
				created = new InformationSign();
				break;
			default:
				created = new UnknownSign();
				break;
		}
		
		// map values
		created.setId(sign.getId());
		created.setName(sign.getName());
		created.setDescription(sign.getDescription());
		created.setPoint(sign.getPoint());
		created.setSide(sign.getSide());
		created.setRoad(sign.getRoad());
		
		return created;
	}
}
