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

    private RectangleShape shape;

    public Player() {
        shape = new RectangleShape(new Vector2f(20, 20));
        shape.setFillColor(Color.BLUE);
        child = shape;
    }

    @Override
    public void update() {
        boolean left = Keyboard.isKeyPressed(Keyboard.Key.LEFT);
        boolean right = Keyboard.isKeyPressed(Keyboard.Key.RIGHT);
        boolean up = Keyboard.isKeyPressed(Keyboard.Key.UP);
        boolean down = Keyboard.isKeyPressed(Keyboard.Key.DOWN);

        Joystick.update();
        for (int j = 0; j < Joystick.JOYSTICK_COUNT; ++j) {
            if (!Joystick.isConnected(j)) continue;

            float x = Joystick.getAxisPosition(j, Joystick.Axis.X);
            float y = Joystick.getAxisPosition(j, Joystick.Axis.Y);
            left |= x < -50;
            right |= x > 50;
            up |= y < -50;
            down |= y > 50;
        }

        final int spd = 3;
        if (left && !right) {
            location = new Vector2f(location.x - spd, location.y);
        } else if (right && !left) {
            location = new Vector2f(location.x + spd, location.y);
        }
        if (up && !down) {
            location = new Vector2f(location.x, location.y - spd);
        } else if (down && !up) {
            location = new Vector2f(location.x, location.y + spd);
        }
    }
}
