package com.platymuus.ggj15.game;

import com.platymuus.ggj15.Resources;
import com.platymuus.jsc.BoundsHandler;
import com.platymuus.jsc.Hacks;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

/**
 * Todo: Javadoc for Rock.
 */
public class Rock extends Entity {

    private static final String[] NAMES = {
            "rock-1",
            "rock-2",
            "rock-3",
            "rock-4",
    };

    public Rock() {
        do {
            location = new Vector2f(random(), random());
        } while (Hacks.dist(location) < 700);

        int random = (int) (Math.random() * NAMES.length);
        Sprite sprite = Resources.getSprite("game/" + NAMES[random] + ".png");
        BoundsHandler.of(sprite).position(0.5f, 1);
        drawable = sprite;

        Vector2i size = sprite.getTexture().getSize();

        collision = new FloatRect(-size.x / 2, -size.y * 3 / 4, size.x, size.y * 3 / 4);
    }

    private float random() {
        int DISTRIB = 6000;
        return (float) (Math.random() * 2 * DISTRIB) - DISTRIB;
    }
}
