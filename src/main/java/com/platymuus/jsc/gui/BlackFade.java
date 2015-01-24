package com.platymuus.jsc.gui;

import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

/**
 * A simple fade to/from black transition.
 */
public class BlackFade implements Drawable {

    private final RectangleShape shape;
    private final int length;

    private int time = 0;

    /**
     * Create a new fade lasting a default time.
     */
    public BlackFade() {
        this(40);
    }

    /**
     * Create a new fade lasting the given time.
     * @param length The fade time.
     */
    public BlackFade(int length) {
        shape = new RectangleShape();
        this.length = length;
    }

    /**
     * Update the fade, returning whether a fade has finished.
     * @return True if a fade out has finished.
     */
    public boolean update() {
        return (time != 0) && (++time >= length);
    }

    /**
     * Reset the fade so it has no effect.
     */
    public void reset() {
        time = 0;
    }

    /**
     * Begin a fade out if one is not already in progress.
     */
    public void fadeOut() {
        if (time <= 0) {
            time = -time + 1;
        }
    }

    /**
     * Begin a fade in.
     */
    public void fadeIn() {
        time = -length;
    }

    /**
     * Get the fade's current transparency, from 0 (opaque) to 1 (invisible).
     * @return The fade's transparency.
     */
    public float getTransparency() {
        return 1.f - (float)time / length;
    }

    @Override
    public void draw(RenderTarget target, RenderStates states) {
        Vector2f size = target.getView().getSize();
        shape.setSize(size);
        shape.setPosition(target.getView().getCenter());
        shape.setOrigin(Vector2f.div(size, 2));
        shape.setFillColor(new Color(0, 0, 0, 255 * Math.abs(time) / (length - 5)));
        shape.draw(target, states);
    }
}
