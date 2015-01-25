package com.platymuus.ggj15.game;

import com.platymuus.ggj15.GameOverScene;
import com.platymuus.jsc.Hacks;
import com.platymuus.jsc.Scene;
import com.platymuus.jsc.gui.BlackFade;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderStates;
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

    private Background background, overlay;
    private World world;
    private View worldView;

    private float zoom = 1;

    private boolean paused;

    @Override
    public void initialize() throws Exception {
        runner.window.setKeyRepeatEnabled(false);

        background = new Background("game/blendground.png");
        overlay = new Background("game/sand.png");
        world = new World();
        worldView = new View(Vector2f.ZERO, new Vector2f(runner.screenSize));
        paused = false;

        fade = new BlackFade();
        fade.fadeIn();
    }

    @Override
    public void handleEvent(Event event) {
        switch (event.type) {
            case KEY_PRESSED:
                world.controllerMode = false;
                doControl(Control.get(event.asKeyEvent().key));
                break;
            case JOYSTICK_BUTTON_PRESSED:
                world.controllerMode = true;
                doControl(Control.get(event.asJoystickButtonEvent().button));
                break;
        }
    }

    private void doControl(Control control) {
        if (control == null) return;
        switch (control) {
            case PAUSE:
                //paused = !paused;
                fade.fadeOut();
                break;
            case ZOOM_IN:
                zoom /= 2;
                break;
            case ZOOM_OUT:
                zoom *= 2;
                break;
            case RESTART:
                world = new World();
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

        if (!paused) {
            world.update();
        }
        if (world.fate != null) {
            fade.fadeOut();
        }
        worldView.setSize(Vector2f.mul(new Vector2f(runner.screenSize), zoom));
        worldView.setCenter(world.getPlayer().location);

        runner.debug("zoom: " + 1 / zoom);
    }

    @Override
    public void finish() {
        if (world.fate != null) {
            runner.play(new GameOverScene(world.fate, world.goodEnd));
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

        target.setView(worldView);
        target.draw(background);
        target.draw(world);
        if (world.sand > 0) {
            overlay.drawObscuring(time, 2 * world.sand, target, RenderStates.DEFAULT);
        }

        // overlay
        target.setView(Hacks.getDefaultView(target));
        target.draw(fade);
    }
}
