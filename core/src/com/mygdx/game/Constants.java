package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Aldres on 01.12.2017.
 */

public class Constants {
    // System
    public static final int APP_WIDTH = 800;
    public static final int APP_HEIGHT = 480;

    public static final Vector2 WORLD_GRAVITY = new Vector2(0, -10);

    // Ground
    public static final float GROUND_X = 0;
    public static final float GROUND_Y = 0;
    public static final float GROUND_WIDTH = 25f;
    public static final float GROUND_HEIGHT = 2f;
    public static final float GROUND_DENSITY = 0f;

    // Player
    public static final float RUNNER_X = 2;
    public static final float RUNNER_Y = GROUND_Y + GROUND_HEIGHT;
    public static final float RUNNER_WIDTH = 1f;
    public static final float RUNNER_HEIGHT = 2f;
    public static final float RUNNER_GRAVITY_SCALE = 3f;
    public static float RUNNER_DENSITY = 0.5f;
    public static final Vector2 RUNNER_JUMPING_LINEAR_IMPULSE = new Vector2(0, 13f);

    public static final Vector2 RUNNER_RUNNING_LINEAR_IMPULSE = new Vector2(0.5f, 0f);
    public static final Vector2 RUNNER_RUNNING_LEFT_LINEAR_IMPULSE = new Vector2(-0.5f, 0f);
    public static final Vector2 MAX_SPEED = new Vector2(4f, 0);




}
