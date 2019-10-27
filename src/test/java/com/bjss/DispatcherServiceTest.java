package com.bjss;

import java.util.Arrays;

import org.junit.Test;

import com.bjss.model.Basket;
import com.bjss.service.DispatcherService;

import junit.framework.Assert;


public class DispatcherServiceTest {

	@Test
	public void testNoOffer() {
		String input ="PriceBasket Soup Milk Bread";
		DispatcherService cashier = DispatcherService.getDispatcher(getBasket(input));
		Assert.assertEquals("Subtotal: £2.75", cashier.calculateSubTotal());
		Assert.assertEquals("[(no offers available)]", Arrays.toString(cashier.calculateDiscount().toArray()));
		Assert.assertEquals("Total: £2.75", cashier.getTotal());


	}
	@Test
	public void testApplesOffer() {
		String input ="PriceBasket Apples Milk Bread";
		DispatcherService cashier = DispatcherService.getDispatcher(getBasket(input));
		Assert.assertEquals("Subtotal: £3.10", cashier.calculateSubTotal());
		Assert.assertEquals("[Apples 10% off: -10p]", Arrays.toString(cashier.calculateDiscount().toArray()));
		Assert.assertEquals("Total: £3.00", cashier.getTotal());


	}
	@Test
	public void testSoupOffer() {
		String input ="PriceBasket Soup Soup Bread";
		DispatcherService cashier = DispatcherService.getDispatcher(getBasket(input));
		Assert.assertEquals("Subtotal: £2.10", cashier.calculateSubTotal());
		Assert.assertEquals("[Bread 50% off: -40p]", Arrays.toString(cashier.calculateDiscount().toArray()));
		Assert.assertEquals("Total: £1.70", cashier.getTotal());


	}
	@Test
	public void testMultipleOffers() {
		String input ="PriceBasket Apples Soup Soup Bread Apples";
		DispatcherService cashier = DispatcherService.getDispatcher(getBasket(input));
		Assert.assertEquals("Subtotal: £4.10", cashier.calculateSubTotal());
		Assert.assertEquals("[Bread 50% off: -40p, Apples 10% off: -20p]", Arrays.toString(cashier.calculateDiscount().toArray()));
		Assert.assertEquals("Total: £3.50", cashier.getTotal());


	}
	@Test
	public void testInvalidBasket() {
		String input ="PriceBasket kk lk dd";
		DispatcherService cashier = DispatcherService.getDispatcher(getBasket(input));
		Assert.assertEquals("Subtotal: 0p", cashier.calculateSubTotal());
		Assert.assertEquals("[(no offers available)]", Arrays.toString(cashier.calculateDiscount().toArray()));
		Assert.assertEquals("Total: 0p", cashier.getTotal());


	}
	
	private Basket getBasket(String input) {
		String line[] = input.split(" ");
		Basket currentBasket = Basket.createNewBasket();

		for (int i = 1; i < line.length; i++)
			currentBasket.addGood(line[i]);

		return currentBasket;
	}
}
