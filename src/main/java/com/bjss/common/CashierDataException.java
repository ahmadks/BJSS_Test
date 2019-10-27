package com.bjss.common;

public class CashierDataException extends RuntimeException {

	public CashierDataException(String id) {
        super("Application error code: " + id);
    }
}
