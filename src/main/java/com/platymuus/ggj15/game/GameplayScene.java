package com.platymuus.ggj15.game;

import com.platymuus.jsc.Hacks;
import com.platymuus.jsc.Scene;
import com.platymuus.jsc.gui.BlackFade;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.View;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard;
import org.jsfml.window.event.Event;

/**
 * The main scene for gameplay.
 */
public class GameplayScene extends Scene {

    private BlackFade fade;

    private World world;
    private Player player;

    private View worldView;

    @Override
    public void initialize() throws Exception {
        runner.window.setKeyRepeatEnabled(false);

        player = new Player();
        world = new World();
        world.entities.add(player);

        worldView = new View(Vector2f.ZERO, new Vector2f(runner.screenSize));

        fade = new BlackFade();
        fade.fadeIn();

        for (int i = 0; i < 10; ++i) {
            world.entities.add(new Follower());
        }
    }

    @Override
    public void handleEvent(Event event) {
        switch (event.type) {
            case KEY_PRESSED:
                if (event.asKeyEvent().key == Keyboard.Key.ESCAPE) {
                    done = true;
                }
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

        world.update();
        worldView.setCenter(player.location);
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

        target.setView(worldView);
        target.draw(world);

        // overlay
        target.setView(Hacks.getDefaultView(target));
        target.draw(fade);
    }
}
