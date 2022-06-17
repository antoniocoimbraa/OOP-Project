package com.oop.project;

public class Statistics {

	// Play is of enum type
	private final static int size = Play.values().length;
	private final static int[] statistics = new int[size];
	
	Statistics() { }
	
	static {
		int i = 0;
		for(i = 0; i < size; i++)
			statistics[i] = 0;
	}
	
	public String sum(Play play) {
		int index = play.ordinal();
		int sum = ++statistics[index];
		statistics[index] = sum;
		return String.valueOf(sum);
	}
	
	public String balance() {
		int balance = 0;
		return String.valueOf(balance);
	}
	
	public String percentage() {
		int percentage = 0;
		return String.valueOf(percentage);
	}
	
	public String total() {
		int i = 0;
		int total = 0;
		for(i = 0; i < size;i++)
			total = total + statistics[i];
		return String.valueOf(total);
	}
	
	@Override
	public String toString() {
		String header = "Hand\t\tNb\n";
		String total = String.format("Total\t\t%s", total());
		String balance = String.format("Credit\t\t%s (%s%s)",balance(),percentage(),"%");
		String line = "--------------------";
		return header + line + total + line + balance;
	}
}
