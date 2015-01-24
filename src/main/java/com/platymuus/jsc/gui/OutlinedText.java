package com.platymuus.jsc.gui;

import com.platymuus.ggj15.Resources;
import com.platymuus.jsc.Hacks;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

/**
 * Todo: Javadoc for OutlinedText.
 */
public class OutlinedText extends Text {

    private final int SIZE = 2;

    public OutlinedText() {
        this("");
    }

    public OutlinedText(String string) {
        this(string, 25);
    }

    public OutlinedText(String string, int characterSize) {
        super(string, Resources.FONT_TEXT, characterSize);
    }

    @Override
    public void draw(RenderTarget target, RenderStates states) {
        setColor(Color.BLACK);
        for (int x = -SIZE; x <= SIZE; ++x) {
            for (int y = -SIZE; y <= SIZE; ++y) {
                super.draw(target, Hacks.offset(states, new Vector2f(x, y)));
            }
        }
        setColor(Color.WHITE);
        super.draw(target, states);
    }
}
