package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Constants;
import com.mygdx.game.box2d.RunnerUserData;
import com.mygdx.game.box2d.UserData;

public class Runner extends GameActor {

    private boolean jumping;



    public Runner(Body body) {
        super(body);
    }

    @Override
    public RunnerUserData getUserData() {
        return (RunnerUserData) userData;
    }

    public void jump() {
        if (!jumping) {
            body.applyLinearImpulse(getUserData().getJumpingLinearImpulse(), body.getWorldCenter(), true);
            jumping = true;
        }
    }

    public void move() {
        if (body.getLinearVelocity().x > Constants.MAX_SPEED.x) {
            body.setLinearVelocity(Constants.MAX_SPEED);
            Gdx.app.log("Speed", "Runner speed is: " + body.getLinearVelocity().x);
        }
        body.applyLinearImpulse(getUserData().getRunningLinearImpulse(), body.getWorldCenter(), true);
    }


    public void  landed(){
        jumping = false;
    }
}

