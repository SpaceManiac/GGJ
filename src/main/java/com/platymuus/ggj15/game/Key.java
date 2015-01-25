package com.platymuus.ggj15.game;

import com.platymuus.ggj15.Resources;
import com.platymuus.jsc.BoundsHandler;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

public class Key extends Interactable {

    public Key() {
        location = new Vector2f(random(), random());

        Sprite sprite = Resources.getSprite("game/key.png");
        BoundsHandler.of(sprite).position(0.5f, 1);
        drawable = sprite;
    }

    private float random() {
        return world.randomDistrib(500);
    }

    @Override
    public String getInteractText() {
        return "Pick up";
    }

    @Override
    public void interact() {
        world.getPlayer().setKey();
        world.remove(this);
    }

}
