package com.platymuus.ggj15.game;

import com.platymuus.ggj15.Resources;
import com.platymuus.jsc.BoundsHandler;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

public class Boat extends Interactable {
    private Vector2f location1, location2;
    private boolean traveled = false;

    public Boat(Vector2f L1, Vector2f L2) {
        location1 = location = new Vector2f(random(), random());
        location2 = new Vector2f(random(), random());

        Sprite sprite = Resources.getSprite("game/boat.png");
        BoundsHandler.of(sprite).position(0.5f, 1);
        drawable = sprite;

        Vector2i size = sprite.getTexture().getSize();
        collision = new FloatRect(-size.x * 3 / 8, -size.y * 3 / 4, size.x * 3 / 4, size.y * 3 / 4);
    }

    private float random() {
        return world.randomDistrib(500);
    }

    @Override
    public String getInteractText() {
        return "Travel across the river";
    }

    @Override
    public void interact() {
        location = traveled ? Vector2f.sub(location1, new Vector2f(0, 10)) : Vector2f.sub(location2, new Vector2f(0, 10));
        world.getPlayer().location = traveled ? location1 : location2;
        for (Follower entities : world.getPlayer().getFollowers()) {
            entities.location = traveled ? location1 : location2;
        }
        traveled = !traveled;
    }
}
