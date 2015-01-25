package com.platymuus.ggj15.game;

import com.platymuus.ggj15.Resources;
import com.platymuus.jsc.BoundsHandler;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

/**
 * Evil rune for Obelisk purposes.
 */
public class ObeliskRune extends Interactable {

    private String name;
    private Sprite sprite;
    private boolean used;

    public ObeliskRune(String name) {
        this.name = name;
        location = new Vector2f(random(), random());

        sprite = Resources.getSprite("game/heiro-" + name + ".png");
        BoundsHandler.of(sprite).position(0.5f, 1);
        drawable = sprite;
    }

    private float random() {
        int DISTRIB = 500;
        return (float) (Math.random() * 2 * DISTRIB) - DISTRIB;
    }

    @Override
    public String getInteractText() {
        return "Activate Rune";
    }

    @Override
    public boolean isInteractable() {
        return !used;
    }

    @Override
    public void interact() {
        world.runes++;
        sprite.setTexture(Resources.getTexture("game/heiro-" + name + "-glow.png"));
        used = true;
        interactText.setString("");
    }
}
