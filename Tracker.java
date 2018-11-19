//Tracker.java

public interface Tracker {
	void step();
	void apply();
	String getStatus();
	boolean isHealthy();
	boolean isDead();
	String whyDead();
}