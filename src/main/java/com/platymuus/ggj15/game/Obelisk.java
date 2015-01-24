package com.platymuus.ggj15.game;

import com.platymuus.ggj15.Resources;
import com.platymuus.jsc.BoundsHandler;
import com.platymuus.jsc.Hacks;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

/**
 * Todo: Javadoc for Obelisk.
 */
public class Obelisk extends Interactable {

    private Sprite sprite;

    public Obelisk() {
        location = new Vector2f(0, -50);

        sprite = Resources.getSprite("game/obelisk.png");
        BoundsHandler.of(sprite).position(0.5f, 1f);
        drawable = sprite;

        collision = new FloatRect(-20, -20, 40, 40);
    }

    @Override
    public boolean isInteractable() {
        return world.landmarks == world.totalLandmarks;
    }

    @Override
    public String getInteractText() {
        return "Activate Obelisk";
    }

    @Override
    public String getUninteractableText() {
        return "Discover " + (world.totalLandmarks - world.landmarks) + " more locations...";
    }

    @Override
    public void interact() {
        world.fate = "a world-ending";
    }

    @Override
    public void draw(RenderTarget renderTarget, RenderStates renderStates) {
        sprite.setColor(Hacks.multiply(Color.WHITE, 0.5f + 0.5f * (float) world.landmarks / world.totalLandmarks));
        super.draw(renderTarget, renderStates);
    }
}
