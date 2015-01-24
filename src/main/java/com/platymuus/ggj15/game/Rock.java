package com.platymuus.ggj15.game;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;

/**
 * Todo: Javadoc for Rock.
 */
public class Rock extends Entity {

    public Rock() {
        location = new Vector2f(random(), random());

        RectangleShape shape = new RectangleShape(new Vector2f(30, 30));
        shape.setFillColor(Math.random() > 0.5 ? Color.BLACK : new Color(128, 128, 128));
        shape.setOrigin(15, 15);
        drawable = shape;

        collision = new FloatRect(-15, -15, 30, 30);
    }

    private float random() {
        int DISTRIB = 3000;
        return (float) (Math.random() * 2 * DISTRIB) - DISTRIB;
    }
}
