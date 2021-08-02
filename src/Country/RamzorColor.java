package Country;

import java.awt.*;

//Noa Fadida, 209507680|| Adi Godosi , 316413780//

public enum RamzorColor {
	GREEN(0.4, Color.GREEN,1),
	YELLOW(0.6,Color.YELLOW,0.8),
	ORANGE(0.8,Color.ORANGE,0.6),
	RED(1,Color.RED,0.4);


	/**
	 * Color field of RamzorColor
	 */
	private Color color;


	/**
	 * transition  field of RamzorColor - the probability to move to settlement according to color's settlement
	 */
	private double transition;


	/**
	 * Contractor
	 * @param i (double) - the contagiousPercent of settlement
	 * @param  color (Color)
	 * @param d (double)  - the probability to move to settlement according to color's settlement
	 */
	RamzorColor(double i,Color color,double d) {
		RamzorColor r= getRamzorColor(i);
	}


	/**
	* a method that returns the RamzorColor 
	* @param contagiousPercent (double)
	* @return RamzorColor
    */
	public static RamzorColor getRamzorColor(double contagiousPercent) {
		if (contagiousPercent <= 0.4) {
			return GREEN;
		}
		if (0.4 <contagiousPercent && contagiousPercent <= 0.6) {
			return YELLOW;
		}
		if ( 0.6 < contagiousPercent && contagiousPercent<=0.8) {
			return ORANGE;
		}
		if (contagiousPercent<=1) {
			return RED;
		}
		return null;	
	}

	public Color getColor() {
		if (this==GREEN) {
			return Color.GREEN;
		}

	 	 else if (this == YELLOW) {
			return Color.YELLOW;
		}
		else if (this==ORANGE) {
			return Color.ORANGE;

		} else if ((this == YELLOW)) {
			return Color.YELLOW;
		}
		else if (this==RED){
			return Color.RED;
		}
		return Color.GREEN;
	}


	/**
	 * a method that returns the transition
	 * @param r (RamzorColor)
	 * @return transition (double)
	 */
	public static double getTransition(RamzorColor r) {
		return r.transition;
	}
}
