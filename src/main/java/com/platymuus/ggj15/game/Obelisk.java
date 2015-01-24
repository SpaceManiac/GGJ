package com.platymuus.ggj15.game;

import com.platymuus.jsc.Hacks;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

/**
 * Todo: Javadoc for Obelisk.
 */
public class Obelisk extends Interactable {

    private RectangleShape shape;

    public Obelisk() {
        shape = new RectangleShape(new Vector2f(30, 100));
        shape.setFillColor(Color.GREEN);
        shape.setOrigin(15, 85);
        drawable = shape;

        collision = new FloatRect(-15, -15, 30, 30);
    }

    @Override
    public boolean isInteractable() {
        return world != null && world.landmarks == world.totalLandmarks;
    }

    @Override
    public String getInteractText() {
        return "Do Obelisk Things";
    }

    @Override
    public void interact() {
        for (Entity entity : world.entities) {
            world.remove(entity);
        }
    }

    @Override
    public void draw(RenderTarget renderTarget, RenderStates renderStates) {
        shape.setFillColor(Hacks.multiply(Color.GREEN, (float) world.landmarks / world.totalLandmarks));
        super.draw(renderTarget, renderStates);
    }
}
