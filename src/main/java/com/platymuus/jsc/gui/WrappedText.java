package com.platymuus.jsc.gui;

import org.jsfml.graphics.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Text component which performs word wrapping to a specific width.
 */
public class WrappedText extends TransformDrawable {

    // Properties of wrapped text
    private final List<Text> textList = new LinkedList<>();
    private String sourceText = "";
    private float maxWidth = 400;

    // Properties mirrored onto text elements
    private ConstFont font = null;
    private Color color = Color.WHITE;
    private int style = TextStyle.REGULAR;
    private int characterSize = 30;

    public WrappedText() {
        // nothing exciting
    }

    public void setFont(Font font) {
        this.font = font;
        update();
    }

    public void setColor(Color color) {
        this.color = color;
        update();
    }

    public void setStyle(int style) {
        this.style = style;
        update();
    }

    public void setCharacterSize(int size) {
        this.characterSize = size;
        update();
    }

    public void setMaxWidth(float width) {
        this.maxWidth = width;
        update();
    }

    public void setString(String text) {
        this.sourceText = text;
        update();
    }

    @Override
    public void draw(RenderTarget target, RenderStates states) {
        states = compose(states);
        for (Text elem : textList) {
            elem.draw(target, states);
        }
    }

    private void update() {
        textList.clear();
        if (sourceText.length() == 0) {
            return;
        }
        new Builder().build();
    }

    private class Builder {
        Text currentText = newText();
        StringBuilder currentLine = new StringBuilder();
        StringBuilder currentWord = new StringBuilder();

        void build() {
            for (char ch : sourceText.toCharArray()) {
                if (Character.isWhitespace(ch)) {
                    handleWhitespace(ch);
                } else {
                    currentWord.append(ch);
                }
            }
            // get the last line into the list
            handleWhitespace('\n');
        }

        Text newText() {
            Text text = new Text();
            text.setFont(font);
            text.setColor(color);
            text.setStyle(style);
            text.setCharacterSize(characterSize);
            return text;
        }

        void handleWhitespace(char ch) {
            boolean endLine = false;

            // end of word - see if we can fit it
            if (currentLine.length() == 0) {
                // no words already, so this word definitely fits
                // (even if it doesn't, prefer to add it here)
                currentLine.append(currentWord);
                currentWord.setLength(0);
            } else {
                currentText.setString(currentLine + " " + currentWord);
                if (currentText.getLocalBounds().width > maxWidth) {
                    endLine = true;
                } else {
                    currentLine.append(' ').append(currentWord);
                    currentWord.setLength(0);
                }
            }

            if (ch == '\n' || (currentLine.length() > 0 && endLine)) {
                // finish this line
                currentText.setString(currentLine.toString());
                textList.add(currentText);
                currentText = newText();
                currentLine.setLength(0);
                currentText.setPosition(0, (characterSize + 2) * textList.size());
                handleWhitespace(' ');
            }
        }
    }
}
