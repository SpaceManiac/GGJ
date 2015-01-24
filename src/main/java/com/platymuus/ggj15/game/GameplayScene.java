package com.platymuus.ggj15.game;

import com.platymuus.jsc.Hacks;
import com.platymuus.jsc.Scene;
import com.platymuus.jsc.gui.BlackFade;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.View;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.event.Event;

/**
 * The main scene for gameplay.
 */
public class GameplayScene extends Scene {

    private BlackFade fade;

    private int turn;

    @Override
    public void initialize() throws Exception {
        runner.window.setKeyRepeatEnabled(false);

        fade = new BlackFade();
        fade.fadeIn();
    }

    @Override
    public void handleEvent(Event event) {
        switch (event.type) {
            case KEY_PRESSED:
                done = true;
                break;
        }
    }

    private Vector2f localize(View view, Vector2i screenPos) {
        Vector2f onScreen = Hacks.rectTransform(
                new Vector2f(screenPos),
                new FloatRect(Vector2f.ZERO, new Vector2f(runner.screenSize)),
                new FloatRect(0, 0, 1, 1));
        Vector2f size = view.getSize();
        FloatRect viewRect = new FloatRect(Vector2f.sub(view.getCenter(), Vector2f.div(size, 2)), size);
        return Hacks.rectTransform(onScreen, view.getViewport(), viewRect);
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
        /*target.setView(worldView);
        target.draw(world);
        target.draw(selector);
        target.draw(intface.worldDraw);

        // user interface
        target.setView(uiView);
        target.draw(intface);*/

        // overlay
        target.setView(Hacks.getDefaultView(target));
        target.draw(fade);
    }
}
