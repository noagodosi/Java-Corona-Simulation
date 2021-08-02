package Simulation;

//Noa Fadida, 209507680|| Adi Godosi , 316413780//


public final class Clock {
	
	// Attributes
	/**
	* time field of the Clock
	*/
	private static long time=0;

	/**
	 * day_per_ticks - count of tick per one day
	 */
	private static int ticks_per_day =1;



	// Methods
	public Clock(long time) {Clock.time = time;}
	
	/**
	* method that return the current time 
	* @return current tick(time)
	*/
	public static long now() {return Clock.time;}

	/**
	 * method that return the numberOfDaysPassed
	 * @return res (long)
	 */
	public static long numberOfDaysPassed(long startTime) {
		long res;
		try {
			res = (Clock.now()-startTime)/(long)Clock.ticks_per_day;
		}catch (ArithmeticException e){
			res=1;
		}
		return res;
	}
	
	/**
	* method that add 1 to current tick
	*/
	public static void nextTick() { Clock.time=Clock.time+1;}

	public static void setTicks_per_day(int ticks_per_day) {
		if(ticks_per_day<=0){ Clock.ticks_per_day=1; }
		Clock.ticks_per_day = ticks_per_day;
	}

	public static long getTicksPerDay(){return Clock.ticks_per_day;}
}
