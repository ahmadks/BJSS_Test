package com.bjss.model;

import java.util.HashMap;

public class Basket {

	private HashMap<String, Integer> goods;

	private Basket() {
		this.goods = new HashMap<String, Integer>();
	}

	static public Basket createNewBasket() {
		return new Basket();
	}

	public void addGood(String good) {
		goods.merge(good.toLowerCase(), 1, Integer::sum);
	}

	public HashMap<String, Integer> getBasket() {
		return this.goods;
	}
}
