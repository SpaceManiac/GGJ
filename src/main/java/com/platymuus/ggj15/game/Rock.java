package com.platymuus.ggj15.game;

import com.platymuus.ggj15.Resources;
import com.platymuus.jsc.BoundsHandler;
import com.platymuus.jsc.Hacks;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

/**
 * Generic rock thing which does nothing.
 */
public class Rock extends Entity {

    private static final String[] NAMES = {
            "rock-1",
            "rock-2",
            "rock-3",
            "rock-4",
            "rock-1",
            "rock-2",
            "rock-3",
            "rock-4",
            "rock-1",
            "rock-2",
            "rock-3",
            "rock-4",
            "pyramid"
    };

    public Rock() {
        do {
            location = new Vector2f(random(), random());
        } while (Hacks.dist(location) < 700);

        Sprite sprite = Resources.getSprite("game/" + NAMES[world.random.nextInt(NAMES.length)] + ".png");
        BoundsHandler.of(sprite).position(0.5f, 1);
        drawable = sprite;

        Vector2i size = sprite.getTexture().getSize();

        collision = new FloatRect(-size.x / 2, -size.y * 3 / 4, size.x, size.y * 3 / 4);
    }

    private float random() {
        return world.randomDistrib(6000);
    }
}
