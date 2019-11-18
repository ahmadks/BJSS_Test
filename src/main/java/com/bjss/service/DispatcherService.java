package com.bjss.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;

import com.bjss.model.Basket;
import com.bjss.model.Good;
import com.bjss.model.Offer;

public class DispatcherService {

	private Float subTotal;
	private Float discount;
	private Float total;
	private Basket basket;
	private ActiveOffers offers;
	private ConsumerGoods goods;

	private DispatcherService(Basket basket) {
		this.total = 0f;
		this.basket = basket;
		this.offers = ActiveOffers.initializeOffers();
		this.goods = ConsumerGoods.initializeGoods();
	}

	public static DispatcherService getDispatcher(Basket basket) {
		return new DispatcherService(basket);
	}

	public String getTotal() {
		if (subTotal == null)
			calculateSubTotal();
		if (discount == null)
			calculateDiscount();
		this.total = subTotal - (Float.valueOf(((int) (discount*100))))/100f;

		return "Total: " + getFormattedCurrency(total);
	}

	public String calculateSubTotal() {
		this.subTotal = 0f;

		for (Entry<String, Integer> gd : basket.getBasket().entrySet()) {
			Good good = goods.getGoodsMap().get(gd.getKey());
			if (good != null) {
				this.subTotal += good.getPrice() * gd.getValue();
			}
		}
		return "Subtotal: " + getFormattedCurrency(subTotal);
	}

	public List<String> calculateDiscount() {
		List<String> respMsg = new ArrayList<String>();
		this.discount = 0f;
		for (Entry<String, Offer> of : offers.getOffersMap().entrySet()) {
			Date today = new Date();
			if (today.after(of.getValue().getValidFrom()) && today.before(of.getValue().getValidTo())) {
				Integer basketUnit = basket.getBasket().get(of.getKey());
				if (basketUnit != null && basketUnit >= of.getValue().getRequiredUnits()) {
					String applyFor = of.getValue().getApplyFor();
					Integer goodUnitDis = basket.getBasket().get(applyFor);
					Integer unitDis = basketUnit / of.getValue().getRequiredUnits();
					if (goodUnitDis !=null) {
						unitDis = Math.min(goodUnitDis, unitDis);
						String msg = applyFor.substring(0, 1).toUpperCase() + applyFor.substring(1) + " "
								+ (int) (of.getValue().getDiscount() * 100) + "% off: -";
						Good good = goods.getGoodsMap().get(applyFor);

						float disc = good.getPrice() * unitDis * of.getValue().getDiscount();
						this.discount += disc;
						respMsg.add(msg.concat(getFormattedCurrency(disc)));
					}
				}
			}

		}
		if (respMsg.isEmpty())
			respMsg.add("(no offers available)");
		return respMsg;
	}

	private String getFormattedCurrency(Float balance) {
		if (balance < 1)
			return (int) (balance * 100) + "p";
		return DecimalFormat.getCurrencyInstance(Locale.UK).format(balance);
	}
}
