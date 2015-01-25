package com.platymuus.ggj15.game;

import com.platymuus.ggj15.Resources;
import com.platymuus.jsc.BoundsHandler;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2i;

public class Bunker extends Interactable {

    public Bunker() {
        Sprite sprite = Resources.getSprite("game/bunker.png");
        BoundsHandler.of(sprite).position(0.5f, 1);
        drawable = sprite;

        Vector2i size = sprite.getTexture().getSize();
        collision = new FloatRect(-size.x / 2, -size.y / 2, size.x, size.y / 2);
    }

    @Override
    public String getInteractText() {
        return "Venture into the depths";
    }

    @Override
    public void interact() {
        if (!world.getPlayer().getKey()) {
            world.fate = "an explosive";
        } else if (world.getPlayer().getFollowers().size() > 1) {
            world.fate = "a hungry";
        } else {
            world.fate = "a safe";
        }
    }

}
