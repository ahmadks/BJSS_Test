package com.bjss.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public enum GoodUnitsEnum {
    TIN("tin"),
    LOAF("loaf"),
    BOTTLE("bottle"),
    BAG("bag"),
    ;
	
	private static Map<String, GoodUnitsEnum> goodsMap;

	static {
		Map<String, GoodUnitsEnum> temp = new HashMap<String, GoodUnitsEnum>(GoodUnitsEnum.values().length);
		for (GoodUnitsEnum good : GoodUnitsEnum.values()) {
			temp.put(good.getValue(), good);
		}
		goodsMap = Collections.unmodifiableMap(temp);

	}
	private final String value;

	private GoodUnitsEnum(String value) {
        this.value = value;
    }

	private  String getValue() {
		return this.value;
	}

	public static GoodUnitsEnum getUnit(String key) {
		return goodsMap.get(key);
	}
}
