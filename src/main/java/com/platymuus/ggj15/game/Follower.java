package com.platymuus.ggj15.game;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;

/**
 * Todo: Javadoc for Player.
 */
public class Follower extends Entity {

    private RectangleShape shape;

    public Follower() {
        location = new Vector2f((float) (Math.random() * 200), (float) (Math.random() * 200));

        shape = new RectangleShape(new Vector2f(20, 20));
        shape.setFillColor(Color.RED);
        child = shape;
    }

    @Override
    public void update() {
        //
    }
}
