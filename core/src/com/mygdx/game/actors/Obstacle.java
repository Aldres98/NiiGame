package com.mygdx.game.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.box2d.ObstacleUserData;
import com.mygdx.game.box2d.UserData;

/**
 * Created by Aldres on 11.12.2017.
 */

public class Obstacle extends GameActor {

    public Obstacle(Body body) {
        super(body);
    }

    @Override
    public UserData getUserData() {
        return (ObstacleUserData) userData;
    }
}
