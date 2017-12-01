package com.mygdx.game.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;


abstract class GameActor extends Actor {
    protected Body body;

    public GameActor(Body body){
        this.body = body;
    }
}
