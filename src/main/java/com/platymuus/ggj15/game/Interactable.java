package com.platymuus.ggj15.game;

import com.platymuus.jsc.BoundsHandler;
import com.platymuus.jsc.Hacks;
import com.platymuus.jsc.gui.OutlinedText;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

/**
 * An entity the player can interact with.
 */
public abstract class Interactable extends Entity {

    private boolean active;
    protected OutlinedText interactText;

    public Interactable() {
        this.interactText = new OutlinedText();
        interactText.setPosition(0, -40);
    }

    public boolean isInteractable() {
        return true;
    }

    public abstract String getInteractText();

    public abstract void interact();

    public String getUninteractableText() {
        return "";
    }

    public float getInteractZone() {
        return 50;
    }

    public void setActive(boolean active) {
        this.active = active;
        if (active) {
            if (isInteractable()) {
                interactText.setString("(" + Control.ACTION.describe(world.controllerMode) + ") " + getInteractText());
            } else {
                interactText.setString(getUninteractableText());
            }
        } else {
            interactText.setString("");
        }
    }

    @Override
    public void draw(RenderTarget renderTarget, RenderStates renderStates) {
        super.draw(renderTarget, renderStates);
        BoundsHandler.of(interactText).centerHorizontally();
        interactText.draw(renderTarget, Hacks.offset(renderStates, location));
    }
}
