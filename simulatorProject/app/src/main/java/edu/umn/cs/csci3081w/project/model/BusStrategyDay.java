package edu.umn.cs.csci3081w.project.model;

/**
 * Implements GenerationStrategy interface and defines
 * vehicle generation logic for the "day" period (8AM to 6PM).
 */
public class BusStrategyDay implements GenerationStrategy {
  private int counter;

  /**
   * Constructor for BusStrategyDay.
   */
  public BusStrategyDay() {
    this.counter = 0;
  }

  @Override
  public String getTypeOfVehicle(StorageFacility storageFacility) {
    String typeOfVehicle = null;
    if (counter < 2) {
      if (storageFacility.getLargeBusesNum() > 0) {
        typeOfVehicle = LargeBus.LARGE_BUS_VEHICLE;
      }
    } else {
      if (storageFacility.getSmallBusesNum() > 0) {
        typeOfVehicle = SmallBus.SMALL_BUS_VEHICLE;
      }
    }

    if (typeOfVehicle != null) {
      counter++;
      counter = counter % 3;
    }

    return typeOfVehicle;
  }

  /**
   * Gets current value of the counter.
   *
   * @return current value of counter
   */
  public int getCounter() {
    return counter;
  }
}
