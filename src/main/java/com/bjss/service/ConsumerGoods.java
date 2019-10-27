package com.bjss.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import com.bjss.common.CashierDataException;
import com.bjss.model.Good;

/**
 * Goods Data
 * Read and initialize all available goods
 */
public class ConsumerGoods {

	private HashMap<String, Good> goodsMap = new HashMap<String, Good>();

	private ConsumerGoods() {

		String fileName = "src/main/resources/ConsumersData";

		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

			br.lines().filter(line -> !line.trim().isEmpty() && !line.startsWith("#") ).forEach(line -> {

				Good good = new Good(line.split(","));
				goodsMap.computeIfAbsent(good.getName(), v -> good);

			});

		} catch (IOException e) {
			throw new CashierDataException("0001");
		}
	}

	public static ConsumerGoods initializeGoods() throws CashierDataException {
		return new ConsumerGoods();
	}

	public HashMap<String, Good> getGoodsMap() {
		return goodsMap;
	}

	public void setGoodsMap(HashMap<String, Good> goodsMap) {
		this.goodsMap = goodsMap;
	}
	
	
//	public static void main(String[] args) {
//		ConsumerGoods gooddb = ConsumerGoods.initializeGoods();
//
//		for (String g : gooddb.getGoodsMap().keySet())
//			System.out.println(g);
//	}
}
