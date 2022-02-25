package org.usfirst.frc7913.Main.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc7913.Main.Robot;

public class StartIntake extends Command {

    
    public StartIntake() {
        requires(Robot.DriveTrain);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.Intake.start();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.Intake.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
