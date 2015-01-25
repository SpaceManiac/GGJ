package com.platymuus.ggj15.game;

import com.platymuus.ggj15.Resources;
import com.platymuus.jsc.BoundsHandler;
import com.platymuus.jsc.Hacks;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

/**
 * deadly dunes of death
 */
public class Dunes extends Entity {
    private FloatRect myRect;

    // if the player ends up inside us - DEATH!

    public Dunes() {
        Sprite sprite = Resources.getSprite("game/dunes.png");
        Vector2i size = sprite.getTexture().getSize();
        BoundsHandler.of(sprite).centerHorizontally();
        drawable = sprite;
        location = new Vector2f(2000, -size.y / 2);

        myRect = new FloatRect(-size.x / 2, 0, size.x, size.y);
    }

    @Override
    public void update() {
        boolean successJ = false;
        for (Follower f : world.getPlayer().getFollowers()) {
            if (f.getName().equals("Joe")) {
                successJ = true;
            }
        }
        FloatRect modified = Hacks.translateRect(myRect, location);
        if (modified.intersection(Hacks.translateRect(world.getPlayer().collision, world.getPlayer().location)) != null) {
            if (successJ) {
                if (world.sand < 30) {
                    ++world.sand;
                }
            } else {
                if (++world.sand >= 200) {
                    world.fate = "a dusty";
                }
            }
        } else {
            if (world.sand > 128) {
                world.sand = 128;
            } else if (world.sand > 0) {
                --world.sand;
            }
        }
    }
}
