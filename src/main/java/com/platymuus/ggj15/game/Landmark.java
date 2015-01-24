package com.platymuus.ggj15.game;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;

/**
 * Todo: Javadoc for Item.
 */
public class Landmark extends Interactable {

    public Landmark() {
        location = new Vector2f(random(), random());

        RectangleShape shape = new RectangleShape(new Vector2f(30, 30));
        shape.setFillColor(Color.CYAN);
        shape.setOrigin(15, 15);
        drawable = shape;
    }

    private float random() {
        int DISTRIB = 500;
        return (float) (Math.random() * 2 * DISTRIB) - DISTRIB;
    }

    @Override
    public String getInteractText() {
        return "Discover location";
    }

    @Override
    public void interact() {
        world.landmarks++;
        world.remove(this);
    }
}
