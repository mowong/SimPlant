/**
 * tracks some level of the plant characteristic
 */
public interface TrackerInterface {

  /**
   * Process one time-unit (day).
   * Decreases the level by an amount.
   */
  void step();

  /**
   * Applies the action (water/feed/spray/whatever)
   * appropriate for the tracked characteristic.
   * Increases the level by an amount.
   */
  void apply();

  /**
   * Returns a message regarding the status tracked characteristic.
   * It should be a complete sentence and have a trailing space.
   * It will form part of the complete plant status message.
   * <p>
   * Example: “There are yellow tips on the upper leaves. ”
   * </p>
   *
   * @return sentence representing the status of the tracked thing
   */
  String getStatus();

  /**
   * Indicates if the tracked level is in the healthy range
   *
   * @return true if the level is in a healthy zone.
   */
  boolean isHealthy();

  /**
   * Indicates if the tracked level is in a deadly range
   * When dead, isHealthy() is irrelevant.
   * This will cause the plant to die.
   *
   * @return true if the level is in a death zone.
   */
  boolean isDead();

  /**
   * Returns a message regarding the manner of death
   * caused by tracked characteristic.
   * The messages should be a complete sentence and have a trailing space.
   * <p>
   * Example: “The soil was so waterlogged that a gnarly fungus took over.”
   *
   * @return null if there is no cause of death,
   *     otherwise a status message illustrating cause.
   */
  String getCauseOfDeath();

}