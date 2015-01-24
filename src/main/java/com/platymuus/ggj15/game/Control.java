package com.platymuus.ggj15.game;

import org.jsfml.window.Joystick;
import org.jsfml.window.Keyboard;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Todo: Javadoc for Control.
 */
public enum Control {
    // normal
    ACTION(Keyboard.Key.SPACE, XboxButtons.A),
    ALTERNATE(Keyboard.Key.TAB, XboxButtons.B),
    //
    PAUSE(Keyboard.Key.ESCAPE, XboxButtons.START),
    // cheats
    ZOOM_IN(Keyboard.Key.ADD, XboxButtons.LB),
    ZOOM_OUT(Keyboard.Key.SUBTRACT, XboxButtons.RB),
    RESTART(Keyboard.Key.F1, XboxButtons.BACK),
    GO_FAST(Keyboard.Key.LSHIFT, XboxButtons.X),
    // movement
    UP(Keyboard.Key.UP, -1),
    DOWN(Keyboard.Key.DOWN, -1),
    LEFT(Keyboard.Key.LEFT, -1),
    RIGHT(Keyboard.Key.RIGHT, -1);

    private static final Map<Keyboard.Key, Control> keys = new EnumMap<>(Keyboard.Key.class);
    private static final Map<Integer, Control> xbox = new HashMap<>();

    private final Keyboard.Key key;
    private final int button;

    Control(Keyboard.Key key, int button) {
        this.key = key;
        this.button = button;
    }

    public boolean held() {
        if (key != null && Keyboard.isKeyPressed(key)) {
            return true;
        }
        if (button < 0) {
            return false;
        }
        for (int j = 0; j < Joystick.JOYSTICK_COUNT; ++j) {
            if (Joystick.isConnected(j) && Joystick.isButtonPressed(j, button)) {
                return true;
            }
        }
        return false;
    }

    public static Control get(Keyboard.Key key) {
        return keys.get(key);
    }

    public static Control get(int button) {
        return xbox.get(button);
    }

    static {
        for (Control control : values()) {
            keys.put(control.key, control);
            xbox.put(control.button, control);
        }
    }
}
