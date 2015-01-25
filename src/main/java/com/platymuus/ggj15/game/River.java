package com.platymuus.ggj15.game;

import com.platymuus.ggj15.Resources;
import com.platymuus.jsc.BoundsHandler;
import com.platymuus.jsc.Hacks;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2i;

/**
 * deadly river styx of doom
 */
public class River extends Entity {
    private FloatRect myRect;

    // if the player ends up inside us - DEATH!

    public River() {
        Sprite sprite = Resources.getSprite("game/river.png");
        Vector2i size = sprite.getTexture().getSize();
        BoundsHandler.of(sprite).centerHorizontally();
        drawable = sprite;
        myRect = new FloatRect(-size.x / 2, 180, size.x, size.y - 2 * 180);
    }

    @Override
    public void update() {
        FloatRect modified = Hacks.translateRect(myRect, location);
        if (modified.intersection(Hacks.translateRect(world.getPlayer().collision, world.getPlayer().location)) != null) {
            collision = new FloatRect(myRect.left - 20, myRect.top - 20, myRect.width + 40, myRect.height + 40);
            world.fate = "a watery";
        }
    }
}
