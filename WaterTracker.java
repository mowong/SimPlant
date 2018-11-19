//WaterTracker.java

import java.util.*;
import java.text.*;

public class WaterTracker implements Tracker {

	private final double THRESHOLD = 100;
	private final double STEP_AMOUNT = 10;
	private final double GOLDEN_ZONE = 25;
	private final double DEATH_ZONE = 50;

	private double level;
	private String status;
	private String causeOfDeath;

	DecimalFormat df = new DecimalFormat("#.##");

	// Decreases the amount of water by a step plus a random amount
	public void step() {
		// level is decreased by a STEP_AMOUNT + a random amount .
		level -= STEP_AMOUNT + Math.random()*5;
	}

	// Applies water * 7 plus a random amount because it should be watered once a week
	public void apply() { 
		level += (STEP_AMOUNT * 7) + Math.random()*5;
	}

	// returns a String containing the cussrent status based on the water level
	public String getStatus() {
	
		if (level < (THRESHOLD - GOLDEN_ZONE)) {
			status = "Dehydrated!";
		} 
		else if (level > (THRESHOLD + GOLDEN_ZONE)) {
			status = "Drowning!";
		}
		else if (level <= THRESHOLD - DEATH_ZONE || level >= THRESHOLD + DEATH_ZONE) {
			status = "Dead!";
		}
		else {
			status = "Perfectly hydrated!";
		}
	
		return status;
	}

	// if water level is within the "golden zone" (threshold +/- 25), return true
	// Plant can be not dead and not healthy
	public boolean isHealthy() {
			if( level <= (THRESHOLD + GOLDEN_ZONE) && level >= (THRESHOLD - GOLDEN_ZONE)) {
				return true;
			}
			else {
				return false;
			}
	}

	// if water level is above or below threshold +/- 50, plant is considered dead
	public boolean isDead() {
		if (level <= THRESHOLD - DEATH_ZONE || level >= THRESHOLD + DEATH_ZONE) {
			return true;
		} 
		else {
			return false;
		}
	}

	// returns the cause of death: either too much water or not enough
	public String whyDead() {
		
			if (level <= THRESHOLD - DEATH_ZONE) {
				causeOfDeath = "Dead. Dried out";
			}
			else if (level >= THRESHOLD + DEATH_ZONE) {
				causeOfDeath = "Dead. Drowned";
			}
		

		return causeOfDeath;
	}


	public String toString() {
		return ("Water level: " + df.format(level) + "\n");

	}
}