package com.platymuus.ggj15.game;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;

public class FollowerDropOff extends Interactable {

    boolean fPresent = false;

    public FollowerDropOff() {
        location = new Vector2f(random(), random());

        RectangleShape shape = new RectangleShape(new Vector2f(30, 30));
        shape.setFillColor(Color.YELLOW);
        shape.setOrigin(15, 15);
        drawable = shape;
    }

    private float random() {
        int DISTRIB = 500;
        return (float) (Math.random() * 2 * DISTRIB) - DISTRIB;
    }

    @Override
    public String getInteractText() {
        if (world.getPlayer().getFollow()) {
            return "Leave followers";
        }
        return "Pick up followers";
    }

    @Override
    public void interact() {
        if (fPresent && !world.getPlayer().getFollow() || !fPresent && world.getPlayer().getFollow()) {
            world.getPlayer().toggleFollow();
            fPresent = !fPresent;
            interactText.setString("(" + Control.ACTION.describe(world.controllerMode) + ") " + getInteractText());
        } else {
            interactText.setString("No followers here");
        }
    }

}
