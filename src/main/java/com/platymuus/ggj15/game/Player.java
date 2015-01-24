package com.platymuus.ggj15.game;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Joystick;
import org.jsfml.window.Keyboard;

/**
 * Todo: Javadoc for Player.
 */
public class Player extends Entity {

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

        Joystick.update();
        for (int j = 0; j < Joystick.JOYSTICK_COUNT; ++j) {
            if (!Joystick.isConnected(j)) continue;

            float jx = Joystick.getAxisPosition(j, Joystick.Axis.X);
            float jy = Joystick.getAxisPosition(j, Joystick.Axis.Y);
            x += adjust(jx);
            y += adjust(jy);
        }

        final int spd = 4;
        location = new Vector2f(location.x + spd * x, location.y + spd * y);
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
