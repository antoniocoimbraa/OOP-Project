package com.oop.project;


/**
 * Store the value of the pays multipliers.
 */
public enum Bonus {
	ROYALFLUSH(250),STRAIGHTFLUSH(50),FOURACES(160),FOUR24(80),FOUR5K(50),
	FULLHOUSE(10),FLUSH(7),STRAIGHT(5),THREEOFAKIND(3),TWOPAIR(1),JACKSORBETTER(1);
	
	/**
	 * Store the values of the plays multipliers 
	*/
	private final Integer bonus;
	
	/**
	 * Constructor
	 * @param pay multiplier value for the game
	 */
	Bonus(Integer bonus) {
		this.bonus = bonus;
	}
	
	/**
	 * Pay variable get function.
	 * @return pay value
	 */
	public Integer getBonus() {
		return bonus;
	}
	
	/**
	 * String value of pay constants.
	 * @return String pay value
	 */
	@Override
	public String toString() {
		return String.valueOf(bonus);
	}
	
	public 
}
