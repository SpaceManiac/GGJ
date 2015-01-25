package com.platymuus.ggj15.game;

import com.platymuus.ggj15.Resources;
import com.platymuus.jsc.BoundsHandler;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2i;

/**
 * Evil rune for Obelisk purposes.
 */
public class ObeliskRune extends Interactable {

    private String name;
    private Sprite sprite;
    private boolean used;

    public ObeliskRune(String name) {
        this.name = name;

        sprite = Resources.getSprite("game/heiro-" + name + ".png");
        BoundsHandler.of(sprite).position(0.5f, 1);
        drawable = sprite;

        Vector2i size = sprite.getTexture().getSize();
        collision = new FloatRect(-size.x / 2, -size.y * 1 / 2, size.x, size.y * 1 / 2);
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
