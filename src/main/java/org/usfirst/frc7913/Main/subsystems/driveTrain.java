package org.usfirst.frc7913.Main.subsystems;

import org.usfirst.frc7913.Main.Robot;
import org.usfirst.frc7913.Main.commands.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class driveTrain extends Subsystem {
    private PWMVictorSPX leftLead;
    private PWMVictorSPX leftFollow;
    private MotorControllerGroup leftSide;
    private PWMVictorSPX rightLead;
    private PWMVictorSPX rightFollow;
    private MotorControllerGroup rightSide;

    private DifferentialDrive driveTrain;

    public driveTrain() {
        // Left motors looking from the back of the robot
        // Front-Left motor
        leftLead = new PWMVictorSPX(0);
        addChild("leftLead", leftLead);
        leftLead.setInverted(false);
        // Rear-Left motor
        leftFollow = new PWMVictorSPX(2);
        addChild("leftFollow", leftFollow);
        leftFollow.setInverted(false);

        // Assigning left motors to the same controller group
        leftSide = new MotorControllerGroup(leftLead, leftFollow);

        // Right motors looking from the back of the robot
        // Front-Right motor
        rightLead = new PWMVictorSPX(1);
        addChild("rightLead", rightLead);
        rightLead.setInverted(false);

        // Rear-Right motor
        rightFollow = new PWMVictorSPX(3);
        addChild("rightFollow", rightFollow);
        rightFollow.setInverted(false);

        // Assigning right motors to the same controller group
        rightSide = new MotorControllerGroup(rightLead, rightFollow);

        // Using differential drive which is similar to a tank or skid steer
        // If using mechanum drive, this must be changed to creating a MecanumDrive
        // object instead
        driveTrain = new DifferentialDrive(leftSide, rightSide);

        // Drivetrain config
        driveTrain.setSafetyEnabled(true);
        driveTrain.setExpiration(0.1);
        driveTrain.setMaxOutput(1.0);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new drive());
    }

    @Override
    public void periodic() {
        // Gets X & Y axes from the joystick controller
        // Arcade drive combines the two motor controller groups, first arg is forward/reverse, second is rotation.
        // Unlike tank drive which has the two motor controller groups separate where the first arg is forward/reverse for the left side and the second is the same for the right
        driveTrain.arcadeDrive(Robot.oi.joystickx30.getY(), Robot.oi.joystickx30.getX(), true);
    }
}