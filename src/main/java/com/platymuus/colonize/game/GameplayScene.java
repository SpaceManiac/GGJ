package com.platymuus.colonize.game;

import com.platymuus.jsc.Scene;
import com.platymuus.jsc.gui.BlackFade;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.View;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;
import org.jsfml.window.event.Event;

/**
 * The main scene for gameplay.
 */
public class GameplayScene extends Scene {

    private BlackFade fade;
    private Vector2f pos;

    @Override
    public void initialize() throws Exception {
        fade = new BlackFade(runner.screenSize);
        fade.fadeIn();

        pos = new Vector2f(runner.screenCenter.x, runner.screenCenter.y);

        runner.window.setKeyRepeatEnabled(false);
    }

    @Override
    public void handleEvent(Event event) {
        switch (event.type) {
            case KEY_PRESSED:
                Keyboard.Key key = event.asKeyEvent().key;
                if (key == Keyboard.Key.ESCAPE) {
                    fade.fadeOut();
                }
                break;
        }
    }

    @Override
    public void update() {
        if (fade.update()) {
            done = true;
        }
    }

    @Override
    public void render(RenderTarget target) {
        // world
        target.setView(new View(pos, new Vector2f(target.getSize())));

        // overlay
        target.setView(target.getDefaultView());
        target.draw(fade);
    }

}
