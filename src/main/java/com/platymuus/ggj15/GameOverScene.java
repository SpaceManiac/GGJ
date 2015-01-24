package com.platymuus.ggj15;

import com.platymuus.jsc.Hacks;
import com.platymuus.jsc.Scene;
import com.platymuus.jsc.gui.BlackFade;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Text;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.Event;

/**
 * Game-over scene.
 */
public class GameOverScene extends Scene {

    private String fate;

    private Text titleText;
    private Text beginText;
    private BlackFade fade;

    public GameOverScene(String fate) {
        this.fate = fate;
    }

    @Override
    public void initialize() throws Exception {
        titleText = new Text("You've met " + fate + " fate...", Resources.FONT_TITLE, 60);
        center(titleText);

        beginText = new Text("but maybe there was something else you could have done?", Resources.FONT_TEXT, 20);
        beginText.setPosition(0, -60);
        center(beginText);

        fade = new BlackFade();
        fade.fadeIn();
    }

    @Override
    public void handleEvent(Event event) {
        switch (event.type) {
            case KEY_PRESSED:
                fade.fadeOut();
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
            done = true;
        }

        // update text position
        titleText.setPosition(0, -150 + 5 * (float) Math.sin(time * 3));
    }

    @Override
    public void render(RenderTarget target) {
        target.setView(Hacks.getCenteredView(target));
        //target.draw(background);
        target.draw(titleText);
        target.draw(beginText);
        target.draw(fade);
        target.setView(Hacks.getDefaultView(target));
    }
}
