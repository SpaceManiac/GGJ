package com.platymuus.ggj15.game;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Vector2f;

/**
 * Todo: Javadoc for Obelisk.
 */
public class Obelisk extends Interactable {

    public Obelisk() {
        RectangleShape shape = new RectangleShape(new Vector2f(30, 100));
        shape.setFillColor(Color.GREEN);
        shape.setOrigin(15, 90);
        drawable = shape;
    }

    @Override
    public String getInteractText() {
        return "Do Obelisk Things";
    }

    @Override
    public void interact() {
        System.out.println("Interacted.");
    }

    @Override
    public void draw(RenderTarget renderTarget, RenderStates renderStates) {
        super.draw(renderTarget, renderStates);
    }
}
