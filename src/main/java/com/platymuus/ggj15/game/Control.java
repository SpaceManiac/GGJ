package com.platymuus.ggj15.game;

import org.jsfml.window.Keyboard;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Todo: Javadoc for Control.
 */
public enum Control {
    // normal
    ACTION,    // xA, kX, kEnter
    ALTERNATE, // xB, kZ
    //
    PAUSE,     // xStart, kEscape
    //
    ZOOM_IN,
    ZOOM_OUT,
    RESTART;

    private static final Map<Keyboard.Key, Control> keys = new EnumMap<>(Keyboard.Key.class);
    private static final Map<Integer, Control> xbox = new HashMap<>();

    public static Control get(Keyboard.Key key) {
        return keys.get(key);
    }

    public static Control get(int button) {
        return xbox.get(button);
    }

    static {
        keys.put(Keyboard.Key.X, ACTION);
        keys.put(Keyboard.Key.Z, ALTERNATE);
        keys.put(Keyboard.Key.ESCAPE, PAUSE);
        keys.put(Keyboard.Key.PAUSE, PAUSE);

        keys.put(Keyboard.Key.ADD, ZOOM_IN);
        keys.put(Keyboard.Key.SUBTRACT, ZOOM_OUT);
        keys.put(Keyboard.Key.F1, RESTART);

        xbox.put(XboxButtons.A, ACTION);
        xbox.put(XboxButtons.B, ALTERNATE);
        xbox.put(XboxButtons.START, PAUSE);

        xbox.put(XboxButtons.BACK, RESTART);
        xbox.put(XboxButtons.LB, ZOOM_IN);
        xbox.put(XboxButtons.RB, ZOOM_OUT);
    }
}
