package com.oop.project;

public class Statistics {
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
	
	// TODO:
	public String balance() {
		int balance = 0;
		return String.valueOf(balance);
	}
	
	// TODO:
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
		String header = "Hand\t\t\t   Nb\n";
		String total = String.format("Total\t\t\t    %s\n", total());
		String balance = String.format("Credit\t\t\t%s (%s%s)\n",balance(),percentage(),"%");
		String line = "-------------------------------\n";
		String body = "";
		
		int index = 0;
		String playName = "";
		for(Play play:Play.values()) {
			playName = play.getPlay();
			index = play.ordinal();
			if(Play.FLUSH == play || Play.OTHER == play)
				body = body + String.format("%s\t\t\t    %s\n", playName, statistics[index]);
			else
				body = body + String.format("%s\t\t    %s\n", playName, statistics[index]);
		}
		return header + line + body + line + total + line + balance;
	}
}
