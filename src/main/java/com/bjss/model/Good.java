package com.bjss.model;

import com.bjss.common.CashierDataException;

public class Good {

	private String name;
	private Float price;
	private GoodUnitsEnum unit;

	public Good(String name, Float price, GoodUnitsEnum unit) {
		super();
		this.name = name;
		this.price = price;
		this.unit = unit;
	}

	public Good(String[] good) {
		if (good.length < 3)
			throw new CashierDataException("0005");

		this.name = good[0].toLowerCase();
		this.unit = GoodUnitsEnum.getUnit(good[2]);
		try {
			this.price = Float.parseFloat(good[1]);

		} catch (Exception e) {
			throw new CashierDataException("0002");
		}
		if (name == null || name.isEmpty())
			throw new CashierDataException("0003");

		if (unit == null)
			throw new CashierDataException("0004");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public GoodUnitsEnum getUnit() {
		return unit;
	}

	public void setUnit(GoodUnitsEnum unit) {
		this.unit = unit;
	}

}
