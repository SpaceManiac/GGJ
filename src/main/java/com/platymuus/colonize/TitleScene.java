package com.platymuus.colonize;

import com.platymuus.jsc.Scene;
import com.platymuus.colonize.game.GameplayScene;
import com.platymuus.jsc.gui.BlackFade;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.Event;

/**
 * Simple title screen.
 */
public class TitleScene extends Scene {

    public static final String TITLE = "Team of Amazing Scientists";

    private Text titleText;
    private Text beginText;
    private BlackFade fade;

    private Sprite background;

    private boolean doneOnFade;

    @Override
    public void initialize() throws Exception {
        background = Resources.getSprite("intface/background.png");

        titleText = new Text(TITLE, Resources.FONT_TITLE, 60);
        center(titleText);

        beginText = new Text("Press any key or click to begin.", Resources.FONT_TEXT, 20);
        beginText.setPosition(runner.screenCenter.x, runner.screenCenter.y - 60);
        center(beginText);

        fade = new BlackFade(runner.screenSize);
        fade.fadeIn();
    }

    @Override
    public void handleEvent(Event event) {
        switch (event.type) {
            case KEY_PRESSED:
                fade.fadeOut();
                if (event.asKeyEvent().key == Keyboard.Key.ESCAPE) {
                    doneOnFade = true;
                }
                break;
            case MOUSE_BUTTON_PRESSED:
                if (event.asMouseButtonEvent().button == Mouse.Button.LEFT) {
                    fade.fadeOut();
                }
                break;
        }
    }

    @Override
    public void update() {
        // if we're undergoing a transition, keep adding to the value
        if (fade.update()) {
            if (doneOnFade) {
                done = true;
            } else {
                runner.play(new GameplayScene());
                fade.fadeIn();
            }
        }

        // update text position
        titleText.setPosition(runner.screenCenter.x, 120 + 5 * (float) Math.sin(time * 3));
    }

    @Override
    public void render(RenderTarget target) {
        target.draw(background);
        target.draw(titleText);
        target.draw(beginText);
        target.draw(fade);
    }
}
