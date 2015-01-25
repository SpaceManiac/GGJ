package com.platymuus.ggj15.game;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;

public class Boat extends Interactable {
    private Vector2f location1, location2;
    private boolean traveled = false;

    public Boat(Vector2f L1, Vector2f L2) {
        location1 = location = new Vector2f(random(), random());
        location2 = new Vector2f(random(), random());

        RectangleShape shape = new RectangleShape(new Vector2f(30, 30));
        shape.setFillColor(Color.YELLOW);
        shape.setOrigin(15, 15);
        drawable = shape;
    }

    private float random() {
        return world.randomDistrib(500);
    }

    @Override
    public String getInteractText() {
        return "Travel across the river";
    }

    @Override
    public void interact() {
        location = traveled ? location1 : location2;
        world.getPlayer().location = traveled ? location1 : location2;
        for (Follower entities : world.getPlayer().getFollowers()) {
            entities.location = traveled ? location1 : location2;
        }
        traveled = !traveled;
    }
}
