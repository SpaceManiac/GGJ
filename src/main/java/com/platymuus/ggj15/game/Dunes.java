package com.platymuus.ggj15.game;

import com.platymuus.ggj15.Resources;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

/**
 * Simple unlimited scrolling background.
 */
public class Dunes implements Drawable {

    private final Sprite sprite;
    private final Vector2i size;

    public Dunes() {
        sprite = Resources.getSprite("game/sand.png");
        size = sprite.getTexture().getSize();
    }

    @Override
    public void draw(RenderTarget target, RenderStates states) {
        ConstView view = target.getView();
        Vector2f center = new Vector2f(
                view.getCenter().x % size.x,
                view.getCenter().y % size.y
        );

        target.setView(new View(center, view.getSize()));
        doDraw(target, states);
        /*target.setView(new View(center, Vector2f.mul(view.getSize(), 2)));
        doDraw(target, states);*/
        target.setView(view);
    }

    private void doDraw(RenderTarget target, RenderStates states) {
        for (int x = -6; x < 6; ++x) {
            for (int y = -6; y < 6; ++y) {
                sprite.setPosition(x * size.x, y * size.y);
                sprite.draw(target, states);
            }
        }
    }
}
