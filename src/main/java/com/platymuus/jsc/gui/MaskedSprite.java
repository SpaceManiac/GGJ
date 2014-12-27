package com.platymuus.jsc.gui;

import com.platymuus.colonize.Resources;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;

/**
 * Simple container pairing a static sprite and a colorable mask.
 */
public class MaskedSprite extends Group {

    private final Sprite sprite, mask;

    public MaskedSprite(String fname) {
        sprite = Resources.getSprite(fname + ".png");
        mask = Resources.getSprite(fname + "_mask.png");
        add(sprite);
        add(mask);
    }

    public void setMaskColor(Color color) {
        mask.setColor(color);
    }

    public FloatRect getGlobalBounds() {
        return getTransform().transformRect(sprite.getLocalBounds());
    }

}
