package nl.cge.tran.domein;

import java.io.Serializable;

public class Money implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long amount = 0L;
	
	public Money addAmount(Double bedrag) {
		Long multiplied = (long) (bedrag * 100);
		amount += multiplied;
		return this;
	}
	
	public Double doubleValue() {
		return ((double) (amount)) / 100;
	}
	
	public Integer integerValue() {
		return (int) (amount / 100);
	}
	
	public static Money create() {
		return new Money();
	}

}
