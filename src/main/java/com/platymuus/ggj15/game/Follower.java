package com.platymuus.ggj15.game;

import com.platymuus.jsc.Hacks;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;

public class Follower extends Entity {

    private Entity target;
    private final String name;

    public Follower(Entity t, Vector2f l, String n) {
        target = t;
        location = l;
        name = n;

        RectangleShape shape = new RectangleShape(new Vector2f(20, 20));
        shape.setFillColor(Color.RED);
        shape.setOrigin(10, 10);
        drawable = shape;
    }

    @Override
    public void update() {
        if (target != null) {
            Vector2f delta = Vector2f.sub(location, target.location);
            float mag = Hacks.dist(delta);
            if (world.getPlayer().getFollow() && mag > 30) {
                delta = Vector2f.mul(delta, 30 / mag);
                location = Vector2f.add(target.location, delta);
            }
        }
    }
    public String getName(){
    	return name;
    }
}
