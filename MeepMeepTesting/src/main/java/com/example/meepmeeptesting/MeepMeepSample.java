package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepSample {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                //blue backdrop left
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, 60, Math.toRadians(270)))
                                .forward(30)
                                //intaker
                                .waitSeconds(0.3)
                                .back(27)
                                .waitSeconds(0.3)
                                .turn(Math.toRadians(90))
                                .waitSeconds(0.3)
                                .waitSeconds(10)
                                .forward(36)
                                .waitSeconds(0.5)
                                .splineToSplineHeading(new Pose2d(51, 42, Math.toRadians(180)), Math.toRadians(0))
                                .waitSeconds(0.5)
                                //servo
                                .addDisplacementMarker(() -> {
                                })
                                .waitSeconds(0.5)
                                .forward(20)
                                .splineTo(new Vector2d(40, 60), Math.toRadians(0))
                                .splineTo(new Vector2d(60, 60), Math.toRadians(0))
                                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}