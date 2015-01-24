package com.platymuus.ggj15.game;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

import java.util.LinkedList;
import java.util.List;

/**
 * Todo: Javadoc for World.
 */
public class World implements Drawable {

    public final List<Entity> entities = new LinkedList<>();

    public void update() {
        for (Entity entity : entities) {
            entity.update();
        }
    }

    @Override
    public void draw(RenderTarget renderTarget, RenderStates renderStates) {
        for (Entity entity : entities) {
            entity.draw(renderTarget, renderStates);
        }
    }
}
