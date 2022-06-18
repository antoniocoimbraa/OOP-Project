package com.oop.project;

//Source: https://docs.oracle.com/javase/specs/jls/se7/html/jls-8.html#jls-8.9

public class Main {
	
	public static void main(String[] args) {
		Machine machine = new Machine(args);
		System.out.println(machine);
		machine.play();
	}
}
