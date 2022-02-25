package org.usfirst.frc7913.Main;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc7913.Main.commands.*;
import org.usfirst.frc7913.Main.subsystems.*;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in
 * the project.
 */
public class Robot extends TimedRobot {

    Command autonomousCommand;
    SendableChooser<Command> chooser = new SendableChooser<>();

    public static IO io;
    public static DriveTrain DriveTrain;
    public static Intake Intake = new Intake();
    public static Conveyor Conveyor = new Conveyor();
    public static Shooter Shooter = new Shooter();
    private JoystickButton startShoot;
    private JoystickButton stopShoot;
    private JoystickButton conveyorHold;
    private JoystickButton shooterHold;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        DriveTrain = new DriveTrain();
        io = new IO();
        startShoot = new JoystickButton(io.getXboxController(), 1);
        stopShoot = new JoystickButton(io.getXboxController(), 2);
        conveyorHold = new JoystickButton(io.getXboxController(), 5);
        shooterHold = new JoystickButton(io.getXboxController(), 6);

        chooser.setDefaultOption("Autonomous Command", new AutonomousCommand());

        SmartDashboard.putData("Auto mode", chooser);

        startShoot.whenPressed(new StartIntake());
        stopShoot.whenPressed(new StopIntake());
        conveyorHold.whileHeld(new MoveConveyor());
        shooterHold.whileHeld(new RunShooter());
    }

    @Override
    public void robotPeriodic() {

    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    @Override
    public void disabledInit() {

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        autonomousCommand = chooser.getSelected();
        // schedule the autonomous command (example)
        if (autonomousCommand != null)
            autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null)
            autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
}
