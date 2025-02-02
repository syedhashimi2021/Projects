package edu.umn.cs.csci3081w.project.webserver;

import com.google.gson.JsonObject;

/**
 * Command that pauses or resumes the simulation.
 */
public class PauseCommand extends SimulatorCommand {
  private VisualTransitSimulator visSim;

  /**
   * Constructor for PauseCommand.
   *
   * @param visSim the simulator instance that will be paused or resumed
   */
  public PauseCommand(VisualTransitSimulator visSim) {
    this.visSim = visSim;
  }

  /**
   * Tells the simulator to pause the simulation.
   *
   * @param session object representing the simulation session
   * @param command object containing the original command
   */
  @Override
  public void execute(WebServerSession session, JsonObject command) {
    visSim.togglePause();
  }
}
