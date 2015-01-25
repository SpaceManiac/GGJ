package com.platymuus.ggj15.game;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;

public class Key extends Interactable {

    public Key(Vector2f l) {
        location = new Vector2f(random(), random());

        RectangleShape shape = new RectangleShape(new Vector2f(30, 30));
        shape.setFillColor(Color.WHITE);
        shape.setOrigin(15, 15);
        drawable = shape;

        collision = new FloatRect(-15, -15, 30, 30);
    }

    private float random() {
        return world.randomDistrib(500);
    }

    @Override
    public String getInteractText() {
        return "Pick up";
    }

    @Override
    public void interact() {
        world.getPlayer().setKey();
        world.remove(this);
    }

}
