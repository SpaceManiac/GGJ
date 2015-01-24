package com.platymuus.ggj15.game;

import com.platymuus.jsc.Hacks;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Joystick;
import org.jsfml.window.Keyboard;

/**
 * Todo: Javadoc for Player.
 */
public class Player extends Entity {

    private boolean aHeldLast;

    private Interactable prevInteractable;

    public Player() {
        RectangleShape shape = new RectangleShape(new Vector2f(20, 20));
        shape.setFillColor(Color.BLUE);
        shape.setOrigin(10, 10);

        drawable = shape;
    }

    @Override
    public void update() {
        boolean left = Keyboard.isKeyPressed(Keyboard.Key.LEFT);
        boolean right = Keyboard.isKeyPressed(Keyboard.Key.RIGHT);
        boolean up = Keyboard.isKeyPressed(Keyboard.Key.UP);
        boolean down = Keyboard.isKeyPressed(Keyboard.Key.DOWN);
        float x = left && !right ? -1 : right && !left ? 1 : 0;
        float y = up && !down ? -1 : down && !up ? 1 : 0;

        boolean aHeld = false;
        Joystick.update();
        for (int j = 0; j < Joystick.JOYSTICK_COUNT; ++j) {
            if (!Joystick.isConnected(j)) continue;

            float jx = Joystick.getAxisPosition(j, Joystick.Axis.X);
            float jy = Joystick.getAxisPosition(j, Joystick.Axis.Y);
            x += adjust(jx);
            y += adjust(jy);

            aHeld |= Joystick.isButtonPressed(j, XboxButtons.A);
        }

        // move
        final float spd = 2.f;
        location = new Vector2f(location.x + spd * x, location.y + spd * y);

        // search for interactable thing
        Interactable interactable = null;
        for (Entity entity : world.entities) {
            if (entity instanceof Interactable) {
                Vector2f delta = Vector2f.sub(entity.location, location);
                Interactable inter = ((Interactable) entity);
                if (Hacks.dist(delta) < 50 && inter.isInteractable()) {
                    interactable = inter;
                    break;
                }
            }
        }
        if (interactable != prevInteractable) {
            if (prevInteractable != null) prevInteractable.setActive(false);
            if (interactable != null) interactable.setActive(true);
        }
        if (aHeld && !aHeldLast && interactable != null) {
            interactable.interact();
        }

        // interact
        aHeldLast = aHeld;
        prevInteractable = interactable;
    }

    private float adjust(float v) {
        v /= 100;
        if (Math.abs(v) < .3) {
            return 0;
        } else {
            return Math.signum(v) * (1 / .7f) * (Math.abs(v) - 0.3f);
        }
    }
}
