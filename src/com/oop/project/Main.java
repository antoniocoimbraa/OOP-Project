package com.oop.project;

//Source: https://docs.oracle.com/javase/specs/jls/se7/html/jls-8.html#jls-8.9

public class Main {
	public static void main(String[] args) {
		/*
		boolean[] seq = {true,true,true,false,false,false,false,
				false,false,false,false,false,true};
		
		int i = 0, j = 0, tot = 0;
		int band = 5;
		
		for(i = 0; i < seq.length; i++) {
			for(j = i, tot = 0; j < i + band; j++) {
				if(j >= seq.length)
					if(seq[j - seq.length])
						tot++;
				if(j < seq.length)
					if(seq[j])
						tot++;
			}
			if(tot == 4)
				System.out.println("got it!");
		}
		*/
		
		Machine machine = new Machine(args);
	}
}
