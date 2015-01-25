package com.platymuus.ggj15;

import com.platymuus.ggj15.game.Background;
import com.platymuus.ggj15.game.GameplayScene;
import com.platymuus.jsc.Hacks;
import com.platymuus.jsc.Scene;
import com.platymuus.jsc.gui.BlackFade;
import com.platymuus.jsc.gui.OutlinedText;
import org.jsfml.graphics.ConstView;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.View;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.Event;

/**
 * Simple title screen.
 */
public class TitleScene extends Scene {

    public static final String TITLE = "Get Out?";

    private Text titleText;
    private Text beginText;
    private BlackFade fade;

    private boolean doneOnFade;

    private Background background;

    @Override
    public void initialize() throws Exception {
        titleText = new OutlinedText(TITLE, 60);
        center(titleText);

        beginText = new OutlinedText("Press any key or click to begin.", 20);
        beginText.setPosition(0, -60);
        center(beginText);

        background = new Background("game/blendground.png");

        fade = new BlackFade();
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
            case JOYSTICK_BUTTON_PRESSED:
                fade.fadeOut();
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
        titleText.setPosition(0, -150 + 5 * (float) Math.sin(time * 3));
    }

    @Override
    public void render(RenderTarget target) {
        ConstView centered = Hacks.getCenteredView(target);

        View moving = new View(Vector2f.add(centered.getCenter(), new Vector2f((float) (50 * time), (float) (10 * time))), centered.getSize());
        target.setView(moving);
        target.draw(background);

        target.setView(centered);
        target.draw(titleText);
        target.draw(beginText);
        target.draw(fade);
        target.setView(Hacks.getDefaultView(target));
    }
}
