package com.mygdx.game.utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.box2d.UserData;
import com.mygdx.game.enums.UserDataType;

/**
 * Created by Aldres on 01.12.2017.
 */

public class BodyUtils {
    public static boolean bodyIsRunner(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.RUNNER;
    }

    public static boolean bodyIsGround(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.GROUND;
    }

}
