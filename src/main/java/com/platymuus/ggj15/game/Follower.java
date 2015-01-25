package com.platymuus.ggj15.game;

import com.platymuus.ggj15.Resources;
import com.platymuus.jsc.BoundsHandler;
import com.platymuus.jsc.Hacks;
import org.jsfml.graphics.*;
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
            if (world.getPlayer().getFollow() && mag > 55) {
                delta = Vector2f.mul(delta, 55 / mag);
                location = Vector2f.add(target.location, delta);
            }

            if (name.equals("Prof")) {
                double angle = Math.toDegrees(Math.atan2(-delta.y, -delta.x));
                angle = (360 + angle) % 360;
                int facing = 1;
                if (angle < 45) {
                    facing = 1;
                } else if (angle < 135) {
                    facing = 2;
                } else if (angle < 225) {
                    facing = 3;
                } else if (angle < 315) {
                    facing = 0;
                }
                ((Sprite) drawable).setTextureRect(new IntRect(81 * facing, 0, 81, 121));
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
            case "Prof":
                sprite = Resources.getSprite("game/char-prof.png");
                sprite.setTextureRect(new IntRect(2 * 81, 0, 81, 121));
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
