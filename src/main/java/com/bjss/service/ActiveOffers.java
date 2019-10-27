package com.bjss.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import com.bjss.common.CashierDataException;
import com.bjss.model.Offer;

/**
 * Offers Data
 * Read and initialize all available Offers
 */
public class ActiveOffers {

	private HashMap<String, Offer> OffersMap = new HashMap<String, Offer>();

	private ActiveOffers() {

		String fileName = "src/main/resources/OffersData";

		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

			br.lines().filter(line -> !line.trim().isEmpty() && !line.startsWith("#") ).forEach(line -> {

				Offer Offer = new Offer(line.split(","));
				OffersMap.computeIfAbsent(Offer.getOfferId(), v -> Offer);

			});

		} catch (IOException e) {
			throw new CashierDataException("0001");
		}
	}

	public static ActiveOffers initializeOffers() throws CashierDataException {
		return new ActiveOffers();
	}

	public HashMap<String, Offer> getOffersMap() {
		return OffersMap;
	}

	public void setOffersMap(HashMap<String, Offer> OffersMap) {
		this.OffersMap = OffersMap;
	}
	
	
//	public static void main(String[] args) {
//		ActiveOffers Offerdb = ActiveOffers.initializeOffers();
//
//		for (String g : Offerdb.getOffersMap().keySet())
//			System.out.println(g);
//	}
}
