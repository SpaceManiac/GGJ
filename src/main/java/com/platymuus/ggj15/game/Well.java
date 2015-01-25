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
        collision = new FloatRect(-size.x / 2, -size.y * 3 / 4, size.x, size.y * 3 / 4);
    }

    private float random() {
        int DISTRIB = 500;
        return (float) (Math.random() * 2 * DISTRIB) - DISTRIB;
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
