package com.platymuus.ggj15.game;

import com.platymuus.jsc.BoundsHandler;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;

public class Clue extends Interactable {

    private String clueMsg;

    public Clue(String message) {
        clueMsg = message;

        location = new Vector2f(random(), random());

        RectangleShape shape = new RectangleShape(new Vector2f(30, 30));
        shape.setFillColor(Color.MAGENTA);
        shape.setOrigin(15, 15);
        drawable = shape;
    }

    private float random() {
        int DISTRIB = 500;
        return (float) (Math.random() * 2 * DISTRIB) - DISTRIB;
    }

    @Override
    public String getInteractText() {
        return "Examine shiny object";
    }

    @Override
    public void interact() {
        interactText.setString(clueMsg);
        BoundsHandler.of(interactText).centerHorizontally();
    }

}
