package edu.umn.cs.csci3081w.project.webserver;

import com.google.gson.JsonObject;

/**
 * Command to update the state of the visual transit simulation.
 */
public class UpdateCommand extends SimulatorCommand {

  /**
   * Constructor for UpdateCommand with specified simulator.
   *
   * @param simulator the visual transit simulator to be updated
   */
  private VisualTransitSimulator simulator;

  /**
   * Constructor for UpdateCommand with specified simulator.
   *
   * @param simulator the visual transit simulator to be updated
   */
  public UpdateCommand(VisualTransitSimulator simulator) {
    this.simulator = simulator;
  }

  /**
   * Updates the state of the simulation.
   *
   * @param session current simulation session
   * @param command the update simulation command content
   */
  @Override
  public void execute(WebServerSession session, JsonObject command) {
    simulator.update();
  }

}
