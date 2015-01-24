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

    private Dunes stars;
    private World world;
    private View worldView;

    private float zoom = 1;

    private boolean paused;

    @Override
    public void initialize() throws Exception {
        runner.window.setKeyRepeatEnabled(false);

        stars = new Dunes();
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
                doControl(Control.get(event.asKeyEvent().key));
                break;
            case JOYSTICK_BUTTON_PRESSED:
                doControl(Control.get(event.asJoystickButtonEvent().button));
                break;
        }
    }

    private void doControl(Control control) {
        if (control == null) return;
        switch (control) {
            case ACTION:
                break;
            case ALTERNATE:
                break;
            case PAUSE:
                paused = !paused;
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
        worldView.setSize(Vector2f.mul(new Vector2f(runner.screenSize), zoom));
        worldView.setCenter(world.getPlayer().location);

        runner.debug("zoom: " + 1 / zoom);
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
        target.draw(stars);
        target.draw(world);

        // overlay
        target.setView(Hacks.getDefaultView(target));
        target.draw(fade);
    }
}
