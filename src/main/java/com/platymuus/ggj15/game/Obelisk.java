package com.platymuus.ggj15.game;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;

/**
 * Todo: Javadoc for Obelisk.
 */
public class Obelisk extends Entity {

    public Obelisk() {
        RectangleShape shape = new RectangleShape(new Vector2f(30, 100));
        shape.setFillColor(Color.GREEN);
        shape.setOrigin(15, 90);
        drawable = shape;
    }
}
