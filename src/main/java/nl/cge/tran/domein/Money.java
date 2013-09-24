package nl.cge.tran.domein;

public class Money {
	
	private Long amount = 0L;
	
	public Money addAmount(Double bedrag) {
		Long multiplied = (long) (bedrag * 100);
		amount += multiplied;
		return this;
	}
	
	public Double doubleValue() {
		return ((double) (amount)) / 100;
	}
	
	public static Money create() {
		return new Money();
	}

}
