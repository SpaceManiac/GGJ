package com.platymuus.ggj15.game;

import com.platymuus.jsc.Hacks;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Joystick;

import java.util.ArrayList;

/**
 * Todo: Javadoc for Player.
 */
public class Player extends Entity {

    private boolean aHeldLast, follow = true;
    private ArrayList<Follower> followers;
    private int hydration;

    private Interactable prevInteractable;

    public Player() {
        location = new Vector2f(0, 40);

        RectangleShape shape = new RectangleShape(new Vector2f(20, 20));
        shape.setFillColor(Color.BLUE);
        shape.setOrigin(10, 10);

        collision = new FloatRect(-10, -10, 20, 20);

        drawable = shape;
        followers = new ArrayList<Follower>();
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
        for (int j = 0; j < Joystick.JOYSTICK_COUNT; ++j) {
            if (!Joystick.isConnected(j)) continue;

            float dx = adjust(Joystick.getAxisPosition(j, Joystick.Axis.X));
            float dy = adjust(Joystick.getAxisPosition(j, Joystick.Axis.Y));
            x += dx;
            y += dy;
            if (dx != 0 || dy != 0) {
                world.controllerMode = true;
            }
        }

        // limit to 1
        float mag = (float) Math.sqrt(x * x + y * y);
        if (mag > 1) {
            x /= mag;
            y /= mag;
        }

        // apply movement
        float spd = 2.f;
        if (Control.GO_FAST.held()) spd *= 5;
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
        if(hydration-- <= 0){
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

    public void clearFollowers() {
        followers = new ArrayList<Follower>();
    }

    public boolean getFollow() {
        return follow;
    }

    public void toggleFollow() {
        follow = !follow;
    }
    public void refill(){
    	hydration = 5400;
    }
}
