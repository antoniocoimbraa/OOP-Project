package com.oop.project;

import java.util.HashMap;

enum Command {
	BET("b"), CREDIT("$"), DEAL("d"), HOLD("h"), ADVICE("a"), STATISTICS("s");
	
	private final String command;
	private final static HashMap<String,Command> map;
	
	Command(String command) {
		this.command = command;
	}
	
	public String getCommand() {
		return command;
	}
	
	static {
		map = new HashMap<String,Command>();

		for(Command c:Command.values())
			try {
				map.put(c.getCommand(),c);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public static Command getConstant(String constant) {
		return map.get(constant);
	}
}
