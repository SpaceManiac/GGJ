package com.platymuus.ggj15.game;

import com.platymuus.ggj15.Resources;
import com.platymuus.jsc.BoundsHandler;
import com.platymuus.jsc.Hacks;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Joystick;

import java.util.ArrayList;

/**
 * Our protagonist.
 */
public class Player extends Entity {

    private boolean aHeldLast, follow = true;
    private ArrayList<Follower> followers;
    private int hydration;

    private Interactable prevInteractable;

    private Sprite sprite;
    private int moveTimer;

    public Player() {
        location = new Vector2f(0, 40);

        sprite = Resources.getSprite("game/protag.png");
        sprite.setTextureRect(new IntRect(100 * 2, 0, 100, 102));
        BoundsHandler.of(sprite).position(0.5f, 0.9f);
        collision = new FloatRect(-10, -10, 20, 20);

        drawable = sprite;
        followers = new ArrayList<>();
        hydration = 5400;
    }

    @Override
    public void update() {
        // key-movement handling
        boolean left = Control.LEFT.held();
        boolean right = Control.RIGHT.held();
        boolean up = Control.UP.held();
        boolean down = Control.DOWN.held();
        float x = left && !right ? -1 : right && !left ? 1 : 0;
        float y = up && !down ? -1 : down && !up ? 1 : 0;

        // joy-movement handling
        boolean goFast = false;
        for (int j = 0; j < Joystick.JOYSTICK_COUNT; ++j) {
            if (!Joystick.isConnected(j)) continue;

            float dx = adjust(Joystick.getAxisPosition(j, Joystick.Axis.X));
            float dy = adjust(Joystick.getAxisPosition(j, Joystick.Axis.Y));
            x += dx;
            y += dy;
            if (dx != 0 || dy != 0) {
                world.controllerMode = true;
            }

            goFast |= Joystick.getAxisPosition(j, Joystick.Axis.Z) > 50;
        }

        // limit to 1
        float mag = (float) Math.sqrt(x * x + y * y);
        if (mag > 1) {
            x /= mag;
            y /= mag;
        }

        if (mag > 0) {
            double angle = Math.toDegrees(Math.atan2(y, x));
            angle = (360 + angle) % 360;
            int facing = 1;
            if (angle < 45) {
                facing = 1;
            } else if (angle < 135) {
                facing = 2;
            } else if (angle < 225) {
                facing = 3;
            } else if (angle < 315) {
                facing = 0;
            }
            sprite.setTextureRect(new IntRect(100 * facing, 0, 100, 102));

            moveTimer = (moveTimer + 1) % 120;
            if (moveTimer < 60) {
                sprite.setPosition(0, -5);
            } else {
                sprite.setPosition(0, 0);
            }
        } else {
            moveTimer = 0;
            sprite.setPosition(0, 0);
        }

        // apply movement
        float spd = 2.f;
        if (Control.GO_FAST.held() || goFast) spd *= 5;
        world.collideTranslate(this, new Vector2f(spd * x, spd * y));

        // search for interactable thing
        Interactable interactable = null;
        float dist = 0;
        for (Entity entity : world.entities) {
            if (entity instanceof Interactable) {
                Interactable inter = (Interactable) entity;
                Vector2f delta = Vector2f.sub(entity.location, location);
                float thisDist = Hacks.dist(delta);
                if (thisDist < inter.getInteractZone() && (thisDist < dist || interactable == null)) {
                    interactable = inter;
                    dist = thisDist;
                }
            }
        }
        // update its state
        if (interactable != prevInteractable) {
            if (prevInteractable != null) prevInteractable.setActive(false);
            if (interactable != null) interactable.setActive(true);
        }
        // interact with it if needed
        boolean action = Control.ACTION.held();
        if (action && !aHeldLast && interactable != null && interactable.isInteractable()) {
            interactable.interact();
        }

        // bookkeeping
        aHeldLast = action;
        prevInteractable = interactable;
        if (hydration-- <= 0) {
            world.fate = "a thirsty";
        }
    }

    private float adjust(float v) {
        v /= 100;
        if (Math.abs(v) < .3) {
            return 0;
        } else {
            return Math.signum(v) * (1 / .7f) * (Math.abs(v) - 0.3f);
        }
    }

    public void addFollower(Follower f) {
        followers.add(f);
    }

    public ArrayList<Follower> getFollowers() {
        return followers;
    }

    public boolean getFollow() {
        return follow;
    }

    public void toggleFollow() {
        follow = !follow;
    }

    public void refill() {
        hydration = 5400;
    }

    public int getHydration() {
        return hydration;
    }
}
