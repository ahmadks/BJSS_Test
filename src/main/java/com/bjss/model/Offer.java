package com.bjss.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bjss.common.CashierDataException;

public class Offer {

	private String offerId;
	private Integer requiredUnits;
	private Date validFrom;
	private Date validTo;
	private String applyFor;
	private Float discount;

	DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");

	public Offer(String[] offer) {
		if (offer.length < 6)
			throw new CashierDataException("0007");

		try {
			this.offerId = offer[0].toLowerCase();
			this.requiredUnits = Integer.parseInt(offer[1]);
			this.validFrom = sourceFormat.parse(offer[2]);
			this.validTo = sourceFormat.parse(offer[3]);
			this.applyFor = offer[4].toLowerCase();
			this.discount = Float.parseFloat(offer[5]);
		} catch (Exception e) {
			throw new CashierDataException("0006");
		}
	}



	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public Integer getRequiredUnits() {
		return requiredUnits;
	}

	public void setRequiredUnits(Integer requiredUnits) {
		this.requiredUnits = requiredUnits;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public String getApplyFor() {
		return applyFor;
	}

	public void setApplyFor(String applyFor) {
		this.applyFor = applyFor;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

}
