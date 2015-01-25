package com.platymuus.ggj15.game;

import com.platymuus.ggj15.Resources;
import com.platymuus.jsc.BoundsHandler;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

public class Well extends Interactable {

    public Well(int i) {
        location = new Vector2f(random(), random());

        Sprite sprite = Resources.getSprite("game/well-" + i + ".png");
        BoundsHandler.of(sprite).position(0.5f, 1);
        drawable = sprite;

        Vector2i size = sprite.getTexture().getSize();
        collision = new FloatRect(-size.x / 2, -size.y * 1 / 2, size.x, size.y * 1 / 2);
    }

    private float random() {
        return world.randomDistrib(500);
    }

    @Override
    public String getInteractText() {
        return "Draw water";
    }

    @Override
    public void interact() {
        world.getPlayer().refill();
    }

}
