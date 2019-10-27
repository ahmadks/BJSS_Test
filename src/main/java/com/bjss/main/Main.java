package com.bjss.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import com.bjss.model.Basket;
import com.bjss.service.DispatcherService;

public class Main {

	public static void main(String[] args) throws IOException {
		InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
		BufferedReader in = new BufferedReader(reader);
		// Expected input 1 unique line formatted like: PriceBasket item1 item2 item3 ..
		String line[] = in.readLine().split(" ");
		Basket currentBasket = Basket.createNewBasket();
		for (int i = 1; i < line.length; i++)
			currentBasket.addGood(line[i]);

		DispatcherService cashier = DispatcherService.getDispatcher(currentBasket);
		System.out.println(cashier.calculateSubTotal());
		for (String msg : cashier.calculateDiscount())
			System.out.println(msg);
		System.out.println(cashier.getTotal());

	}

}
