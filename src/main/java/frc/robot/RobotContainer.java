// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.List;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
//import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
//import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.DriveSubsystem;

/*
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems
  //private final DriveSubsystem m_robotDrive = new DriveSubsystem();

  // The driver's controller
  XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);
  XboxController m_otherController = new XboxController(OIConstants.kOtherControllerPort);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
    DriveSubsystem.getInstance().setDefaultCommand(
        // The left stick controls translation of the robot.
        // Turning is controlled by the X axis of the right stick.
        new RunCommand(
            () -> DriveSubsystem.getInstance().drive(
                -MathUtil.applyDeadband(m_driverController.getLeftY(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getLeftX(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getRightX(), OIConstants.kDriveDeadband),
                true, true),
            DriveSubsystem.getInstance()));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its
   * subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then calling
   * passing it to a
   * {@link JoystickButton}.
   */
  private void configureButtonBindings() {
    // Set the wheels to a X
    new JoystickButton(m_driverController, 1)
        .whileTrue(new RunCommand(
            () -> DriveSubsystem.getInstance().setX(),
            DriveSubsystem.getInstance()));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
//   public Command getAutonomousCommand() {
//     // Create config for trajectory
//     TrajectoryConfig config = new TrajectoryConfig(
//         AutoConstants.kMaxSpeedMetersPerSecond,
//         AutoConstants.kMaxAccelerationMetersPerSecondSquared)
//         // Add kinematics to ensure max speed is actually obeyed
//         .setKinematics(DriveConstants.kDriveKinematics);

//     // An example trajectory to follow. All units in meters.
//     Trajectory centerRingBackTrajectory = TrajectoryGenerator.generateTrajectory(
//         // Start at the origin facing the +X direction
//         new Pose2d(0, 0, new Rotation2d(0)),
//         // Pass through these two interior waypoints, making an 's' curve path
//         List.of(/*new Translation2d(1, 1), new Translation2d(2, -1)*/),
//         // End 3 meters straight ahead of where we started, facing forward
//         new Pose2d(0.6, 0, new Rotation2d(0)),
//         config);

//     Trajectory centerRingForwardTrajectory = TrajectoryGenerator.generateTrajectory(
//         // Start at the origin facing the +X direction
//         new Pose2d(0, 0, new Rotation2d(0)),
//         // Pass through these two interior waypoints, making an 's' curve path
//         List.of(/*new Translation2d(1, 1), new Translation2d(2, -1)*/),
//         // End 3 meters straight ahead of where we started, facing forward
//         new Pose2d(-1.1, 0, new Rotation2d(0)),
//         config);

//     Trajectory intakeTrajectory = TrajectoryGenerator.generateTrajectory (
//         new Pose2d(0, 0, new Rotation2d(0)),
//         List.of(),
//         new Pose2d(.5, 0, new Rotation2d(0)),
//         config);

//     var thetaController = new ProfiledPIDController(
//         AutoConstants.kPThetaController, 0, 0, AutoConstants.kThetaControllerConstraints);
//         thetaController.enableContinuousInput(-Math.PI, Math.PI);

//     SwerveControllerCommand centerRingBackCommand = new SwerveControllerCommand(
//         centerRingBackTrajectory,
//         DriveSubsystem.getInstance()::getPose, // Functional interface to feed supplier
//         DriveConstants.kDriveKinematics,

//         // Position controllers
//         new PIDController(AutoConstants.kPXController, 0, 0),
//         new PIDController(AutoConstants.kPYController, 0, 0),
//         thetaController,
//         DriveSubsystem.getInstance()::setModuleStates,
//         DriveSubsystem.getInstance());

//     SwerveControllerCommand centerRingForwardCommand = new SwerveControllerCommand(
//         centerRingForwardTrajectory,
//         DriveSubsystem.getInstance()::getPose, // Functional interface to feed supplier
//         DriveConstants.kDriveKinematics,

//         // Position controllers
//         new PIDController(AutoConstants.kPXController, 0, 0),
//         new PIDController(AutoConstants.kPYController, 0, 0),
//         thetaController,
//         DriveSubsystem.getInstance()::setModuleStates,
//         DriveSubsystem.getInstance());

//     SwerveControllerCommand intakeMovementCommand = new SwerveControllerCommand(
//         intakeTrajectory,
//         DriveSubsystem.getInstance()::getPose,
//         DriveConstants.kDriveKinematics,
        
//         new PIDController(AutoConstants.kPXController, 0, 0),
//         new PIDController(AutoConstants.kPYController, 0, 0),
//         thetaController,
//         DriveSubsystem.getInstance()::setModuleStates,
//         DriveSubsystem.getInstance());

//     Command intakeCommand = new Command() {
//         public void initialize() {
//             DriveSubsystem.getInstance().moveShooter(0.15);
//             DriveSubsystem.getInstance().intake(1);
//             Timer.delay(0.5);
//             DriveSubsystem.getInstance().moveShooter(0);
//         }
//     };

//     Command intakeStopCommand = new Command() {
//         public void initialize() {
//             DriveSubsystem.getInstance().moveShooter(-.4);
//             Timer.delay(0.5);
//             DriveSubsystem.getInstance().moveShooter(0);
//             DriveSubsystem.getInstance().intake(0);
//         }
//     };

//     Command shootCommand = new Command() {
//         public void initialize() {
//             DriveSubsystem.getInstance().shoot(-1);
//             Timer.delay(0.25);
//             DriveSubsystem.getInstance().intake(1);
//             Timer.delay(.25);
//             DriveSubsystem.getInstance().shoot(0);
//             DriveSubsystem.getInstance().intake(0);
//         }
//     };

//     // Reset odometry to the starting pose of the trajectory.
//     DriveSubsystem.getInstance().resetOdometry(centerRingBackTrajectory.getInitialPose());

//     // Run path following command, then stop at the end.
//     return shootCommand.andThen(centerRingBackCommand);//.andThen(intakeCommand).andThen(intakeMovementCommand).andThen(intakeStopCommand).andThen(centerRingForwardCommand).andThen(shootCommand);
//   }
// }

  public Command getAutonomousCommand() {
    DriveSubsystem.getInstance().zeroHeading();
    // Create config for trajectory
    TrajectoryConfig config =
        new TrajectoryConfig(
                1,
                AutoConstants.kMaxAccelerationMetersPerSecondSquared)
            // Add kinematics to ensure max speed is actually obeyed
            .setKinematics(DriveConstants.kDriveKinematics);

    // An example trajectory to follow. All units in meters.
    Trajectory exampleTrajectory =
        TrajectoryGenerator.generateTrajectory(
            // Start at the origin facing the +X direction
            new Pose2d(0, 0, new Rotation2d(0)),
            // Pass through these two interior waypoints, making an 's' curve path
            List.of(new Translation2d(1, 1), new Translation2d(2, -1), new Translation2d(3, 0)),
            // End 3 meters straight ahead of where we started, facing forward
            new Pose2d(0, 0, new Rotation2d(0)),
            config);

    var thetaController =
        new ProfiledPIDController(
            AutoConstants.kPThetaController, 0, 0, AutoConstants.kThetaControllerConstraints);
    thetaController.enableContinuousInput(-Math.PI, Math.PI);

    SwerveControllerCommand swerveControllerCommand =
        new SwerveControllerCommand(
            exampleTrajectory,
            DriveSubsystem.getInstance()::getPose, // Functional interface to feed supplier
            DriveConstants.kDriveKinematics,

            // Position controllers
            new PIDController(AutoConstants.kPXController, 0, 0),
            new PIDController(AutoConstants.kPYController, 0, 0),
            thetaController,
            DriveSubsystem.getInstance()::setModuleStates,
            DriveSubsystem.getInstance());

    // Reset odometry to the starting pose of the trajectory.
    DriveSubsystem.getInstance().resetOdometry(exampleTrajectory.getInitialPose());

    // Run path following command, then stop at the end.
    return swerveControllerCommand.andThen(() -> DriveSubsystem.getInstance().drive(0, 0, 0, true, false));
  }
}