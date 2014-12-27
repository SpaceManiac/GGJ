package com.platymuus.jsc.gui;

import com.platymuus.colonize.Resources;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Vector2f;

/**
 * Textbox container for visual novel-style text conversations.
 */
public class NovelTextbox extends TransformDrawable {

    private RectangleShape outline;
    private WrappedText text;

    public NovelTextbox() {
        outline = new RectangleShape(new Vector2f(600, 120));
        outline.setFillColor(new Color(20, 20, 196, 128));
        outline.setOutlineColor(new Color(20, 20, 128, 255));
        outline.setOutlineThickness(5.0f);

        text = new WrappedText();
        text.setFont(Resources.FONT_TITLE);
        text.setCharacterSize(20);
        text.setMaxWidth(600 - 10);
        text.setPosition(5, 2);
        text.setString("Lorem ipsum dolor sit amet, consectetur adipiscing " +
                "elit. Donec a diam lectus. Sed sit amet ipsum mauris. Maecenas " +
                "congue ligula ac quam viverra nec consectetur ante hendrerit. " +
                "Donec et mollis dolor. Praesent et diam eget libero egestas " +
                "mattis sit amet vitae augue. Nam tincidunt congue enim, ut " +
                "porta lorem lacinia consectetur. Donec ut libero sed arcu " +
                "vehicula ultricies a non tortor. Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Aenean ut gravida lorem. Ut " +
                "turpis felis, pulvinar a semper sed, adipiscing id dolor. " +
                "Pellentesque auctor nisi id magna consequat sagittis. Curabitur " +
                "dapibus enim sit amet elit pharetra tincidunt feugiat nisl imperdiet. " +
                "Ut convallis libero in urna ultrices accumsan. Donec sed odio " +
                "eros. Donec viverra mi quis quam pulvinar at malesuada arcu " +
                "rhoncus. Cum sociis natoque penatibus et magnis dis parturient " +
                "montes, nascetur ridiculus mus. In rutrum accumsan ultricies. " +
                "Mauris vitae nisi at sem facilisis semper ac in est.");
    }

    @Override
    public void draw(RenderTarget target, RenderStates states) {
        states = compose(states);
        outline.draw(target, states);
        text.draw(target, states);
    }
}
