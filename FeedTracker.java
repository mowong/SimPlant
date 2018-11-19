//FeedTracker.java

import java.util.*;
import java.text.*;

public class FeedTracker implements Tracker {

	private final double THRESHOLD = 100.0;
	private final double STEP_AMOUNT = 1;
	private final double GOLDEN_ZONE = 25;
	private final double DEATH_ZONE = 50;

	private double level;
	private String status;
	private String causeOfDeath;

	DecimalFormat df = new DecimalFormat("#.##");

	public void step() {
		// level is decreased by a STEP_AMOUNT + a random amount .
		level -= STEP_AMOUNT + Math.random()*5;
	}

	// fertilizer level is increased by the amount * 30 because plant should be fertilized once a month
	public void apply() { 
		level += (STEP_AMOUNT * 30) + Math.random()*5;
	}

	// returns a string containing status based on the current fertilizer level
	public String getStatus() {
	
		if (level < (THRESHOLD - GOLDEN_ZONE)) {
			status = "Not enough fertilizer!";
		} 
		else if (level > (THRESHOLD + GOLDEN_ZONE)) {
			status = "Too much fertilizer!";
		}
		else if (level <= THRESHOLD - DEATH_ZONE || level >= THRESHOLD + DEATH_ZONE) {
			status = "Dead!";
		}
		else {
			status = "Perfectly fertilized!";
		}
	
		return status;
	}

	// returns true if fertilizer level is within the golden zone
	public boolean isHealthy() {
			if( level <= (THRESHOLD + GOLDEN_ZONE) && level >= (THRESHOLD - GOLDEN_ZONE)) {
				return true;
			}
			else {
				return false;
			}
	}

	// returns false id fertiliwer level is above or below the death zone
	public boolean isDead() {
		if (level <= THRESHOLD - DEATH_ZONE || level >= THRESHOLD + DEATH_ZONE) {
			return true;
		} 
		else {
			return false;
		}
	}

	// returns a string containing the cause of death.
	public String whyDead() {
		
		if (level <= THRESHOLD - DEATH_ZONE) {
			causeOfDeath = "Dead. Succumbed to infertile soil";
		}
		else if (level >= THRESHOLD + DEATH_ZONE) {
			causeOfDeath = "Dead. Choked on all the fertilizer";
		}

		return causeOfDeath;
	}


	public String toString() {
		return ("Fertilizer level: " + df.format(level) + "\n");

	}
}