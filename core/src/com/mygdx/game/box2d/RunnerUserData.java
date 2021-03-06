package com.mygdx.game.box2d;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Constants;
import com.mygdx.game.enums.UserDataType;

/**
 * Created by Aldres on 01.12.2017.
 */

public class RunnerUserData extends UserData {

    private Vector2 jumpingLinearImpulse;
    private Vector2 runningLinearImpulse;
    private Vector2 runningLeftLinearImpulse;

    public RunnerUserData(){
        super();
        jumpingLinearImpulse = Constants.RUNNER_JUMPING_LINEAR_IMPULSE;
        runningLinearImpulse = Constants.RUNNER_RUNNING_LINEAR_IMPULSE;
        runningLeftLinearImpulse = Constants.RUNNER_RUNNING_LEFT_LINEAR_IMPULSE;
        userDataType = UserDataType.RUNNER;
    }

    public Vector2 getJumpingLinearImpulse() {
        return jumpingLinearImpulse;
    }

    public void setJumpingLinearImpulse(Vector2 jumpingLinearImpulse) {
        this.jumpingLinearImpulse = jumpingLinearImpulse;
    }

    public Vector2 getRunningLeftLinearImpulse() {
        return runningLeftLinearImpulse;
    }

    public Vector2 getRunningLinearImpulse() {
        return runningLinearImpulse;
    }

    public void setRunningLinearImpulse(Vector2 runningLinearImpulse) {
        this.runningLinearImpulse = runningLinearImpulse;
    }
}
