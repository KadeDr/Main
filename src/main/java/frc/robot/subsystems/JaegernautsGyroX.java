package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS; // Giving an error. Attempting to locate a fix. Supposed fix is clearing cache. I have attempted redownloading the library.

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.SPI;

/**
 * An extended class for the NavX gyro that adds better
 * support for fused headings, a more accurate heading
 * reading.
 *
 * Why is it in degrees... I dont know... I guess because
 * fused heading does... sorry?
 *
 * Also represents the NavX as a singleton object.
 *
 * @author Justus Languell
 * @author Jack Pittenger
 */
public final class JaegernautsGyroX extends AHRS {
    private static volatile JaegernautsGyroX instance;

    public static final synchronized JaegernautsGyroX getInstance() {
        return instance == null ? instance = new JaegernautsGyroX() : instance;
    }

    private double angleOffset = 0;

    public JaegernautsGyroX() {
        super(SPI.Port.kMXP);
        getFusedHeading();
    }

    public final void setOffsetCW(final Rotation2d offset) {
        this.angleOffset = calculateOffsetCW(offset.getDegrees());
    }

    public final void setOffsetCCW(final Rotation2d offset) {
        this.angleOffset = 360 - calculateOffsetCW(offset.getDegrees());
    }

    public final Rotation2d getAngleOffsetCW() { return Rotation2d.fromDegrees(angleOffset); }

    public final Rotation2d getAngleOffsetCCW() { return Rotation2d.fromDegrees(angleOffset).times(-1); }

    public final Rotation2d getHeadingCW() { return Rotation2d.fromDegrees(getDegreesClockwise()); }

    public final Rotation2d getHeadingCCW() { return Rotation2d.fromDegrees(getDegreesCounterClockwise()); }

    public final Rotation2d getAngularVelocity() { return Rotation2d.fromDegrees(getRate()); }

    private final double calculateOffsetCW(final double offset) {
        // Why is the +360 here bruh?
        return (offset - getFusedHeading() + 360) % 360;
    }

    private final double getDegreesClockwise() { return (getFusedHeading() + angleOffset) % 360; }


    private final double getDegreesCounterClockwise() { return 360 - getDegreesClockwise(); }

}
