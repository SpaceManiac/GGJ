package com.platymuus.ggj15.game;

import com.platymuus.jsc.Hacks;
import com.platymuus.jsc.gui.OutlinedText;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

/**
 * Todo: Javadoc for Interactable.
 */
public abstract class Interactable extends Entity {

    private boolean active;
    private OutlinedText interactText;

    public Interactable() {
        this.interactText = new OutlinedText();
        interactText.setPosition(0, -40);
    }

    public boolean isInteractable() {
        return true;
    }

    public abstract String getInteractText();

    public abstract void interact();

    public void setActive(boolean active) {
        this.active = active;
        if (active) {
            interactText.setString("(A) " + getInteractText());
            Hacks.hCenter(interactText);
        } else {
            interactText.setString("");
        }
    }

    @Override
    public void draw(RenderTarget renderTarget, RenderStates renderStates) {
        super.draw(renderTarget, renderStates);
        interactText.draw(renderTarget, Hacks.offset(renderStates, location));
    }
}
