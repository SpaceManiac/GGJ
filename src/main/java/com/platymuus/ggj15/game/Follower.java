package com.platymuus.ggj15.game;

import com.platymuus.ggj15.Resources;
import com.platymuus.jsc.BoundsHandler;
import com.platymuus.jsc.Hacks;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

public class Follower extends Entity {

    private Entity target;
    private final String name;

    public Follower(Entity t, Vector2f l, String n) {
        target = t;
        location = l;
        name = n;
        drawable = sprite(n);
    }

    @Override
    public void update() {
        if (target != null) {
            Vector2f delta = Vector2f.sub(location, target.location);
            float mag = Hacks.dist(delta);
            if (world.getPlayer().getFollow() && mag > 50) {
                delta = Vector2f.mul(delta, 50 / mag);
                location = Vector2f.add(target.location, delta);
            }
        }
    }

    public String getName() {
        return name;
    }

    public static Drawable sprite(String name) {
        Sprite sprite;
        switch (name) {
            case "Joe":
                sprite = Resources.getSprite("game/char-joe.png");
                BoundsHandler.of(sprite).position(0.5f, 0.9f);
                return sprite;
            default:
                RectangleShape shape = new RectangleShape(new Vector2f(20, 20));
                shape.setFillColor(Color.RED);
                shape.setOrigin(10, 10);
                return shape;
        }
    }
}
