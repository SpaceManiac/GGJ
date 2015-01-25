package com.platymuus.ggj15.game;

import com.platymuus.jsc.Hacks;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Vector2f;

/**
 * Root for entities in the world.
 */
public class Entity implements Drawable {

    public Vector2f location = Vector2f.ZERO;
    protected static World world;
    protected Drawable drawable;

    public FloatRect collision;

    public void update() {

    }

    @Override
    public void draw(RenderTarget renderTarget, RenderStates renderStates) {
        /*if (collision != null) {
            RectangleShape shape = new RectangleShape(new Vector2f(collision.width, collision.height));
            shape.setPosition(collision.left, collision.top);
            shape.setOutlineColor(Color.GREEN);
            shape.setOutlineThickness(1);
            shape.setFillColor(Color.TRANSPARENT);
            shape.draw(renderTarget, Hacks.offset(renderStates, location));
        }*/
        if (drawable != null) {
            drawable.draw(renderTarget, Hacks.offset(renderStates, location));
        }
    }
}
