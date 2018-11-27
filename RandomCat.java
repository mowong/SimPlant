import java.util.Random;

public class RandomCat implements TrackerInterface {
//	chance of random cat appearing at each step
	private static final Double CAT_PEES = 0.05;
	private static final Double PLANT_KNOCKED_DOWN = 0.10;
	private static final Double CAT_NIBBLES = 0.15;
	
	private boolean peed;
	private boolean knockedDown;
	private boolean nibbled;
	
	private final Plant plant;
	private Random random = new Random();

  RandomCat (Plant plant) {
    this.plant = plant;
    peed = false;
    knockedDown = false;
    nibbled = false;
	}	
  
  @Override
  public void step() {
//	would like to call water increment (peed), decrement (knocked down), bloom (-1 days)
//	right now it's ~15% chance of something happening
//	there's gotta be a better way to write this :P
	  Double number = random.nextDouble();
	  if (number < CAT_PEES)
		  peed = true;
	  else if (number > CAT_PEES && number < PLANT_KNOCKED_DOWN)
		  knockedDown = true;
	  else if (number > PLANT_KNOCKED_DOWN && number < CAT_NIBBLES)
		  nibbled = true;
  }
  
  @Override
  public void apply(){
	  throw new UnsupportedOperationException("Cannot apply RandomCat.");
  	}

  @Override
  public String getStatus(){
	  if (peed){
		  peed = false;
		 return "Your cat peed in your plant! ";
	  }
	  else if (knockedDown){
		  knockedDown = false;
		  return "Your cat knocked down your plant! ";
	  }
	  else if (nibbled){
		  nibbled = false;
		  return "Your cat has nibbled on your plant! ";
	  }
	  return " ";
  	}
  @Override
  public boolean isHealthy() {
	  return true; // will never make plant unhealthy
  	}
  @Override
  public boolean isDead() {
	  return false; // will never kill plant
  	}
  @Override
  public String getCauseOfDeath(){
	  return null; // will never kill plant
  	}
  
  @Override
  public String getLevelCode(){
	  return "No cat code";
	}
  
}
