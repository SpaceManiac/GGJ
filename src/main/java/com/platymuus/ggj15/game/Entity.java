package com.platymuus.ggj15.game;

import com.platymuus.jsc.Hacks;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

/**
 * Todo: Javadoc for Entity.
 */
public class Entity implements Drawable {

    public Vector2f location = Vector2f.ZERO;
    protected World world;
    protected Drawable drawable;

    public FloatRect collision;

    public void update() {

    }

    @Override
    public void draw(RenderTarget renderTarget, RenderStates renderStates) {
        if (drawable != null) {
            drawable.draw(renderTarget, Hacks.offset(renderStates, location));
        }
        /*if (collision != null) {
            RectangleShape shape = new RectangleShape(new Vector2f(collision.width, collision.height));
            shape.setPosition(collision.left, collision.top);
            shape.setOutlineColor(Color.GREEN);
            shape.setOutlineThickness(1);
            shape.draw(renderTarget, Hacks.offset(renderStates, location));
        }*/
    }
}
