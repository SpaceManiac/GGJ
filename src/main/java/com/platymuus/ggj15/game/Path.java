package com.platymuus.ggj15.game;

import com.platymuus.ggj15.Resources;
import com.platymuus.jsc.BoundsHandler;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

/**
 * Like Rock, but you can walk over it.
 */
public class Path extends Entity {

    public Path() {
        Sprite sprite = Resources.getSprite("game/path.png");
        drawable = sprite;

        Vector2i size = sprite.getTexture().getSize();
        BoundsHandler.of(sprite).centerHorizontally();
        location = new Vector2f(2000, -size.y / 2);
    }
}
