/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Robot;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 * <p>
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 * <p>
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name = "Main", group = "LinearOpmode")
public class Main extends Robot {


    @Override
    public void runOpMode() {
        super.runOpMode();

        waitForStart();


        while (opModeIsActive()) {
            armMotor.setDirection(DcMotor.Direction.FORWARD);
            telemetry.addData("frontLeftMotor", this.frontLeftMotor.getPower());
            telemetry.addData("backLeftMotor", this.backLeftMotor.getPower());
            telemetry.addData("frontRightMotor", this.frontRightMotor.getPower());
            telemetry.addData("backRightMotor", this.backRightMotor.getPower());
            telemetry.addData("armMotor", this.armMotor.getPower());
            telemetry.addData("armWobble", this.armWobble.getPower());
            telemetry.addData("servo", this.wobbleServo.getPosition());
            telemetry.update();


            //forward
            if ((gamepad1.left_stick_y >= gamepad1.left_stick_x) && (gamepad1.left_stick_y >= -gamepad1.left_stick_x)) {
                this.goForward(gamepad1.left_stick_y);
            }

            if ((gamepad1.left_stick_y <= gamepad1.left_stick_x) && (gamepad1.left_stick_y <= -gamepad1.left_stick_x)) {
                this.goBackward(gamepad1.left_stick_y);
            }

            if ((gamepad1.right_stick_y >= gamepad1.right_stick_x) && (gamepad1.right_stick_y <= -gamepad1.right_stick_x)) {
                this.turnLeft(Math.abs(gamepad1.right_stick_x));
            }

            if ((gamepad1.right_stick_y <= gamepad1.right_stick_x) && (gamepad1.right_stick_y >= -gamepad1.right_stick_x)) {
                this.turnRight(Math.abs(gamepad1.right_stick_x));
            }

            if (gamepad1.left_trigger != 0) {
                this.strafeLeft(gamepad1.left_trigger);
            }

            if (gamepad1.right_trigger != 0) {
                this.strafeRight(gamepad1.right_trigger);
            }

//            if(gamepad1.b) {
//                this.turnAround();
//            }


            if (gamepad2.dpad_left) {
                this.arm(0.2);
            }
            if (gamepad2.dpad_right) {
                this.arm(-0.2);
            }else {
                this.arm(0.0);
            }

            this.moveVerticalLift(gamepad2.right_stick_y);

            if (this.gamepad2.dpad_up) {
                this.moveRingGrabberArm(0.33);
            } else if (this.gamepad2.dpad_down) {
                this.moveRingGrabberArm(-0.33);
            } else {
                this.moveRingGrabberArm(0);
            }

            if (gamepad2.a) {
                this.closeRingGrabber();
            }

            if (gamepad2.b) {
                this.openRingGrabber();
            }

            if (gamepad2.x) {
                this.ringGrabberArmMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }

            if (gamepad2.left_stick_x <= -.05) {
                this.arm(gamepad2.left_stick_x);
            }

            if (gamepad2.left_stick_x >= .05) {
                this.arm(gamepad2.left_stick_x);
            }

            this.armWobble(gamepad2.right_trigger);
            this.armWobble(-gamepad2.left_trigger);

            if(gamepad2.right_bumper) {
                this.toggleServoLock();
            }

            if(gamepad1.left_bumper) {
                this.armLift(0.5);
            }

            if(gamepad1.right_bumper) {
                this.armLift(-0.5);
            }


        }

    }

}
