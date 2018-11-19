import java.util.*;

public class SprayTracker implements TrackerInterface {
		private static final int MIN_SPRAY_LEVEL = 10;
		private static final int SPRAY_AMOUNT = 15;
		private static final int NUM_OF_BUGS = 5;
		private static final int MAX_MULTIPLIER = 2;
		private static final int BUGGY_STILL_HEALTHY = 10;
		private static final int DEAD_FROM_BUGS = 100;
		
		boolean hasBugs = false;
		int sprayLevel;
		int numberOfBugs;
		
//	  how much is decremented every day, calls bugs if spray level is low
	@Override
  public void step() {
		sprayLevel -=1;
		if (sprayLevel < MIN_SPRAY_LEVEL && hasBugs == false){
			bugCaller();
		}
		if (hasBugs = true){ 
			bugMultiplier();
		}
  }
	
//	  how much is added when called, kill bugs
  @Override
  public void apply() {
	  sprayLevel += SPRAY_AMOUNT;
	  bugKiller();
  }

//string of what's going on - ??? need spray amount?
  @Override
  public String getStatus() {
    return null;

  }
  
//if spray number is below a certain point, call bugs
  private int bugCaller() {
	  Random bugNum = new Random();
	  if (sprayLevel < MIN_SPRAY_LEVEL && hasBugs == false){
		  if (Math.random() < 0.1){
			  	numberOfBugs = bugNum.nxtInt(NUM_OF_BUGS)+1;
			  	hasBugs = true;
		  }
	  }
  }
  
//should only be called weekly, once a week? - put in random chance
  private int bugMultiplier(){
	  Random multiplier = new Random();
	  if (hasBugs == true){
		  if (Math.random() < 0.15){
			  numberOfBugs = numberOfBugs * (multiplier.nxtFloat(MAX_MULTIPLIER) +1);
		  }
	  }
  }
  
  private int bugKiller(){
	  Random killBugs = new Random();
	  if (hasBugs == true){
		  numberOfBugs = numberOfBugs (killBugs.nxtFloat(1));
	  }
	  if (numberOfBugs == 0){
		  hasBugs = false;
	  }
  }

  @Override
  public boolean isHealthy() {
	  boolean health;
	  if (numberOfBugs < BUGGY_STILL_HEALTHY){
		  health = true;
	  }else
		  health = false;
    return health;
  }

//if bugs go over a certain number, plant is dead
  @Override
  public boolean isDead() {
	  boolean dead;
	  if (numberOfBugs > DEAD_FROM_BUGS){
		  dead = true;
	  }else
		  dead = false;
    return dead;
  }

//if isDead is True, return plant died from bugs
  @Override
  public String getCauseOfDeath() {
    return "The plant was eaten up by bugs.";
  }
}
