package com.mygdx.game.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Constants;
import com.mygdx.game.box2d.GroundUserData;
import com.mygdx.game.box2d.ObstacleUserData;
import com.mygdx.game.box2d.RunnerUserData;

/**
 * Created by Aldres on 01.12.2017.
 */

public class WorldUtils {
    public static World createWorld(){
        return new World(Constants.WORLD_GRAVITY, true);
    }

    public static Body createGround(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(Constants.GROUND_X, Constants.GROUND_Y));
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.GROUND_WIDTH, Constants.GROUND_HEIGHT / 2);
        body.createFixture(shape, Constants.GROUND_DENSITY);
        body.setUserData(new GroundUserData());
        shape.dispose();
        return body;
    }

    public static Body createRunner(World world){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(Constants.RUNNER_X, Constants.RUNNER_Y));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.RUNNER_WIDTH / 2, Constants.RUNNER_HEIGHT / 2);
        Body body = world.createBody(bodyDef);
        body.setGravityScale(Constants.RUNNER_GRAVITY_SCALE);
        body.setUserData(new RunnerUserData());
        body.createFixture(shape, Constants.RUNNER_DENSITY);
        body.resetMassData();
        shape.dispose();
        return body;

    }

    public static Body createObstacle(World world, float sizeX, float sizeY, float positionX, float positionY){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(positionX, positionY));
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sizeX, sizeY);
        body.createFixture(shape, Constants.GROUND_DENSITY);
        body.setUserData(new ObstacleUserData());
        shape.dispose();
        return body;
    }
}
