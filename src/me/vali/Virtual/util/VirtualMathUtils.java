package me.vali.Virtual.util;

import java.util.Random;

public class VirtualMathUtils {

	public Double randomDouble(Double max, Double min) {
		double random = new Random().nextDouble();
		return min + (random * (max - min));
	}
	
	public Float randomFloat(Float max, Float min) {
		float random = new Random().nextFloat();
		return min + (random * (max - min));
	}

	public Integer randomInteger(Integer max, Integer min) {
		int random = new Random().nextInt();
		return min + (random * (max - min));
	}

	public double runden(double wert, int stellen) {
        return  Math.round(wert * Math.pow(10, stellen)) / Math.pow(10, stellen);
    }
	
}
