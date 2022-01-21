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
        //Left motors
        leftLead = new PWMVictorSPX(0);
        addChild("leftLead",leftLead);
        leftLead.setInverted(false);
        leftFollow = new PWMVictorSPX(2);
        addChild("leftFollow",leftFollow);
        leftFollow.setInverted(false);
        leftSide = new MotorControllerGroup(leftLead, leftFollow);

        //Right motors
        rightLead = new PWMVictorSPX(1);
        addChild("rightLead",rightLead);
        rightLead.setInverted(false);
        rightFollow = new PWMVictorSPX(3);
        addChild("rightFollow",rightFollow);
        rightFollow.setInverted(false);
        rightSide = new MotorControllerGroup(rightLead, rightFollow);
        
        driveTrain = new DifferentialDrive(leftSide, rightSide);
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
        //Line that sets speed of motors
        driveTrain.arcadeDrive(Robot.oi.joystickx30.getY(), Robot.oi.joystickx30.getX());
    }
}