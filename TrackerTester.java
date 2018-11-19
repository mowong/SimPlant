//TrackerTester.java

public class TrackerTester {

	public static void main(String[] args) {

		WaterTracker waterTracker = new WaterTracker();
		FeedTracker feedTracker = new FeedTracker();

		// Apply #1
		waterTracker.apply();
		feedTracker.apply();
		System.out.println(waterTracker.toString());
		System.out.println(feedTracker.toString());

		System.out.println(waterTracker.getStatus());
		System.out.println(feedTracker.getStatus());

		if(waterTracker.isHealthy()) {
			System.out.println("Water level: Healthy");
		}
		else {
			System.out.println("Water level: Not healthy");
		}
		if(waterTracker.isDead()) {
			System.out.println(waterTracker.whyDead());
		}

		if (feedTracker.isHealthy()) {
			System.out.println("Fertilizer level: Healthy");
		}
		else {
			System.out.println("Fertilizer level: Not Healthy");
		}
		if(feedTracker.isDead()) {
			System.out.println(feedTracker.whyDead());
		}


		// Apply #2
		waterTracker.apply();
		feedTracker.apply();
		System.out.println(waterTracker.toString());
		System.out.println(feedTracker.toString());

		System.out.println(waterTracker.getStatus());
		System.out.println(feedTracker.getStatus());

		if(waterTracker.isHealthy()) {
			System.out.println("Water level: Healthy");
		}
		else {
			System.out.println("Water level: Not healthy");
		}
		if(waterTracker.isDead()) {
			System.out.println(waterTracker.whyDead());
		}

		if (feedTracker.isHealthy()) {
			System.out.println("Fertilizer level: Healthy");
		}
		else {
			System.out.println("Fertilizer level: Not Healthy");
		}
		if(feedTracker.isDead()) {
			System.out.println(feedTracker.whyDead());
		}

		// Step #1
		waterTracker.step();
		feedTracker.step();
		System.out.println(waterTracker.toString());
		System.out.println(feedTracker.toString());

		System.out.println(waterTracker.getStatus());
		System.out.println(feedTracker.getStatus());

		if(waterTracker.isHealthy()) {
			System.out.println("Water level: Healthy");
		}
		else {
			System.out.println("Water level: Not healthy");
		}
		if(waterTracker.isDead()) {
			System.out.println(waterTracker.whyDead());
		}

		if (feedTracker.isHealthy()) {
			System.out.println("Fertilizer level: Healthy");
		}
		else {
			System.out.println("Fertilizer level: Not Healthy");
		}
		if(feedTracker.isDead()) {
			System.out.println(feedTracker.whyDead());
		}

		// Step #2
		waterTracker.step();
		feedTracker.step();
		System.out.println(waterTracker.toString());
		System.out.println(feedTracker.toString());

		System.out.println(waterTracker.getStatus());
		System.out.println(feedTracker.getStatus());

		if(waterTracker.isHealthy()) {
			System.out.println("Water level: Healthy");
		}
		else {
			System.out.println("Water level: Not healthy");
		}
		if(waterTracker.isDead()) {
			System.out.println(waterTracker.whyDead());
		}

		if (feedTracker.isHealthy()) {
			System.out.println("Fertilizer level: Healthy");
		}
		else {
			System.out.println("Fertilizer level: Not Healthy");
		}
		if(feedTracker.isDead()) {
			System.out.println(feedTracker.whyDead());
		}

		// Step #3
		waterTracker.step();
		feedTracker.step();
		System.out.println(waterTracker.toString());
		System.out.println(feedTracker.toString());

		System.out.println(waterTracker.getStatus());
		System.out.println(feedTracker.getStatus());

		if(waterTracker.isHealthy()) {
			System.out.println("Water level: Healthy");
		}
		else {
			System.out.println("Water level: Not healthy");
		}
		if(waterTracker.isDead()) {
			System.out.println(waterTracker.whyDead());
		}

		if (feedTracker.isHealthy()) {
			System.out.println("Fertilizer level: Healthy");
		}
		else {
			System.out.println("Fertilizer level: Not Healthy");
		}
		if(feedTracker.isDead()) {
			System.out.println(feedTracker.whyDead());
		}

		// Step #4
		waterTracker.step();
		feedTracker.step();
		System.out.println(waterTracker.toString());
		System.out.println(feedTracker.toString());

		System.out.println(waterTracker.getStatus());
		System.out.println(feedTracker.getStatus());

		if(waterTracker.isHealthy()) {
			System.out.println("Water level: Healthy");
		}
		else {
			System.out.println("Water level: Not healthy");
		}
		if(waterTracker.isDead()) {
			System.out.println(waterTracker.whyDead());
		}

		if (feedTracker.isHealthy()) {
			System.out.println("Fertilizer level: Healthy");
		}
		else {
			System.out.println("Fertilizer level: Not Healthy");
		}
		if(feedTracker.isDead()) {
			System.out.println(feedTracker.whyDead());
		}

		// Step #5
		waterTracker.step();
		feedTracker.step();
		System.out.println(waterTracker.toString());
		System.out.println(feedTracker.toString());

		System.out.println(waterTracker.getStatus());
		System.out.println(feedTracker.getStatus());

		if(waterTracker.isHealthy()) {
			System.out.println("Water level: Healthy");
		}
		else {
			System.out.println("Water level: Not healthy");
		}
		if(waterTracker.isDead()) {
			System.out.println(waterTracker.whyDead());
		}

		if (feedTracker.isHealthy()) {
			System.out.println("Fertilizer level: Healthy");
		}
		else {
			System.out.println("Fertilizer level: Not Healthy");
		}
		if(feedTracker.isDead()) {
			System.out.println(feedTracker.whyDead());
		}

		// Step #6
		waterTracker.step();
		feedTracker.step();
		System.out.println(waterTracker.toString());
		System.out.println(feedTracker.toString());

		System.out.println(waterTracker.getStatus());
		System.out.println(feedTracker.getStatus());

		if(waterTracker.isHealthy()) {
			System.out.println("Water level: Healthy");
		}
		else {
			System.out.println("Water level: Not healthy");
		}
		if(waterTracker.isDead()) {
			System.out.println(waterTracker.whyDead());
		}

		if (feedTracker.isHealthy()) {
			System.out.println("Fertilizer level: Healthy");
		}
		else {
			System.out.println("Fertilizer level: Not Healthy");
		}
		if(feedTracker.isDead()) {
			System.out.println(feedTracker.whyDead());
		}

		// Step #7
		waterTracker.step();
		feedTracker.step();
		System.out.println(waterTracker.toString());
		System.out.println(feedTracker.toString());

		System.out.println(waterTracker.getStatus());
		System.out.println(feedTracker.getStatus());

		if(waterTracker.isHealthy()) {
			System.out.println("Water level: Healthy");
		}
		else {
			System.out.println("Water level: Not healthy");
		}
		if(waterTracker.isDead()) {
			System.out.println(waterTracker.whyDead());
		}

		if (feedTracker.isHealthy()) {
			System.out.println("Fertilizer level: Healthy");
		}
		else {
			System.out.println("Fertilizer level: Not Healthy");
		}
		if(feedTracker.isDead()) {
			System.out.println(feedTracker.whyDead());
		}

		// Apply #3
		waterTracker.apply();
		feedTracker.apply();
		System.out.println(waterTracker.toString());
		System.out.println(feedTracker.toString());

		System.out.println(waterTracker.getStatus());
		System.out.println(feedTracker.getStatus());

		if(waterTracker.isHealthy()) {
			System.out.println("Water level: Healthy");
		}
		else {
			System.out.println("Water level: Not healthy");
		}
		if(waterTracker.isDead()) {
			System.out.println(waterTracker.whyDead());
		}

		if (feedTracker.isHealthy()) {
			System.out.println("Fertilizer level: Healthy");
		}
		else {
			System.out.println("Fertilizer level: Not Healthy");
		}
		if(feedTracker.isDead()) {
			System.out.println(feedTracker.whyDead());
		}
		
		// Apply #4
		waterTracker.apply();
		feedTracker.apply();
		System.out.println(waterTracker.toString());
		System.out.println(feedTracker.toString());

		System.out.println(waterTracker.getStatus());
		System.out.println(feedTracker.getStatus());

		if(waterTracker.isHealthy()) {
			System.out.println("Water level: Healthy");
		}
		else {
			System.out.println("Water level: Not healthy");
		}
		if(waterTracker.isDead()) {
			System.out.println(waterTracker.whyDead());
		}

		if (feedTracker.isHealthy()) {
			System.out.println("Fertilizer level: Healthy");
		}
		else {
			System.out.println("Fertilizer level: Not Healthy");
		}
		if(feedTracker.isDead()) {
			System.out.println(feedTracker.whyDead());
		}

	
	}
}