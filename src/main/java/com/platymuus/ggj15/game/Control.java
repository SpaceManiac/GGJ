package com.platymuus.ggj15.game;

import org.jsfml.window.Joystick;
import org.jsfml.window.Keyboard;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Enum of controls in the game.
 */
public enum Control {
    // normal
    ACTION(Keyboard.Key.SPACE, XboxButton.A),
    ALTERNATE(Keyboard.Key.TAB, XboxButton.B),
    //
    PAUSE(Keyboard.Key.ESCAPE, XboxButton.START),
    // cheats
    ZOOM_IN(Keyboard.Key.ADD, XboxButton.LB),
    ZOOM_OUT(Keyboard.Key.SUBTRACT, XboxButton.RB),
    RESTART(Keyboard.Key.F1, XboxButton.BACK),
    GO_FAST(Keyboard.Key.LSHIFT, XboxButton.X),
    // movement
    UP(Keyboard.Key.UP, null),
    DOWN(Keyboard.Key.DOWN, null),
    LEFT(Keyboard.Key.LEFT, null),
    RIGHT(Keyboard.Key.RIGHT, null);

    private static final Map<Keyboard.Key, Control> keys = new EnumMap<>(Keyboard.Key.class);
    private static final Map<Integer, Control> xbox = new HashMap<>();

    private final Keyboard.Key key;
    private final XboxButton button;

    Control(Keyboard.Key key, XboxButton button) {
        this.key = key;
        this.button = button;
    }

    public boolean held() {
        if (key != null && Keyboard.isKeyPressed(key)) {
            return true;
        }
        if (button != null) {
            for (int j = 0; j < Joystick.JOYSTICK_COUNT; ++j) {
                if (Joystick.isConnected(j) && Joystick.isButtonPressed(j, button.ordinal())) {
                    return true;
                }
            }
        }
        return false;
    }

    public String describe(boolean controller) {
        if (controller && button != null) {
            return button.describe();
        } else if (key != null) {
            return key.name();
        } else {
            return "null";
        }
    }

    public static Control get(Keyboard.Key key) {
        return keys.get(key);
    }

    public static Control get(int button) {
        return xbox.get(button);
    }

    static {
        for (Control control : values()) {
            if (control.key != null) {
                keys.put(control.key, control);
            }
            if (control.button != null) {
                xbox.put(control.button.ordinal(), control);
            }
        }
    }
}
