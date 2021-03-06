package com.mygdx.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Constants;
import com.mygdx.game.actors.Ground;
import com.mygdx.game.actors.Obstacle;
import com.mygdx.game.actors.Runner;
import com.mygdx.game.contols.DoubleTapDetector;
import com.mygdx.game.utils.BodyUtils;
import com.mygdx.game.utils.WorldUtils;

/**
 * Created by Aldres on 01.12.2017.
 */

public class GameStage extends Stage implements ContactListener {
    private static final int VIEWPORT_WIDTH = 20;
    private static final int VIEWPORT_HEIGHT = 13;

    private World world;
    private Ground ground;
    private Runner runner;

    private final float TIME_STEP = 1 / 60f;
    private float accumulator = 0f;

    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer;

    private Touchpad touchpad;
    private Touchpad.TouchpadStyle touchpadStyle;
    private Skin touchpadSkin;
    private Drawable touchBackground;
    private Drawable touchKnob;
    ClickListener listener;





    public GameStage() {
        setUpWorld();
        renderer = new Box2DDebugRenderer();
        setupCamera();
    }

    private void setUpWorld() {
        world = WorldUtils.createWorld();
        world.setContactListener(this);
        setUpGround();
        setUpObstacle();
        setUpRunner();
        setupTouchControlAreas();
        setUpTouchpad();

        listener = new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Tap count is:  " + getTapCount());
                if (listener.getTapCount() == 2){
                    runner.jump();
                    listener.setTapCount(0);
                }
                return super.touchDown(event, x, y, pointer, button);
            }

        };

        addListener(listener);

        }


    private void setUpGround() {
        ground = new Ground(WorldUtils.createGround(world));
        addActor(ground);
    }

    private void setUpRunner() {
        runner = new Runner(WorldUtils.createRunner(world));
        addActor(runner);
    }

    private void setUpObstacle() {
        Obstacle obstacle = new Obstacle(WorldUtils.createObstacle(world, 1f, 1.5f, 5f, Constants.GROUND_HEIGHT + Constants.GROUND_Y));
        addActor(obstacle);

    }

    private void setUpTouchpad() {
        touchpadSkin = new Skin();
        touchpadSkin.add("touchBackground", new Texture("touchBackground.png"));
        touchpadSkin.add("touchKnob", new Texture("touchKnob.png"));
        touchpadStyle = new Touchpad.TouchpadStyle();
        touchBackground = touchpadSkin.getDrawable("touchBackground");
        touchKnob = touchpadSkin.getDrawable("touchKnob");
        touchpadStyle.background = touchBackground;
        touchpadStyle.knob = touchKnob;
        touchpad = new Touchpad(10, touchpadStyle);
        touchpad.setBounds(Gdx.graphics.getWidth() - 150, Gdx.graphics.getHeight() / 2 - 150, 150, 150);
        addActor(touchpad);

        touchpad.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (touchpad.isTouched() && !runner.isJumping()) {
                    runner.move(touchpad.getKnobPercentX(), touchpad.getKnobPercentY());
                }
            }
        });
    }


    private void setupCamera() {
        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }

    private void setupTouchControlAreas() {
//        touchPoint = new Vector3();
//        screenRightSide = new Rectangle(getCamera().viewportWidth / 2, 0, getCamera().viewportWidth / 2,
//                getCamera().viewportHeight);
        Gdx.input.setInputProcessor(this);
    }


    //Old touch controls
//    @Override
//    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//        lastTouchTime = TimeUtils.nanoTime();
//        if (lastUpTime - lastTouchTime <= 100){
//            runner.jump();
//        }
//        System.out.println("Last upTime: " + lastUpTime + ", last touch time: " + lastTouchTime);
//        return super.touchDown(screenX, screenY, pointer, button);
//
//    }

//
//    private boolean rightSideTouched(float x, float y) {
//        return screenRightSide.contains(x, y);
//    }
//
//
//    private void translateScreenToWorldCoordinates(int x, int y) {
//        getCamera().unproject(touchPoint.set(x, y, 0));
//    }


    @Override
    public void act(float delta) {
        super.act(delta);

        accumulator += delta;

        while (accumulator >= delta) {
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
        }
    }

    @Override
    public void draw() {
        super.draw();
        renderer.render(world, camera.combined);
    }

    @Override
    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();


        if ((BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsGround(b)) ||
                (BodyUtils.bodyIsGround(a) && BodyUtils.bodyIsRunner(b) || (BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsObstacle(b)) || BodyUtils.bodyIsRunner(b) && BodyUtils.bodyIsObstacle(a))) {
            runner.landed();
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
