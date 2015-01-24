package com.platymuus.ggj15.game;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;

public class Follower extends Entity {

    public Follower() {
        location = new Vector2f((float) (Math.random() * 200), (float) (Math.random() * 200));

        RectangleShape shape = new RectangleShape(new Vector2f(20, 20));
        shape.setFillColor(Color.RED);
        shape.setOrigin(10, 10);
        drawable = shape;
    }

    @Override
    public void update() {
        float velX = 0, velY = 0;

        for (Entity entity : world.entities) {
            if (entity == this) continue;

            Vector2f offset = Vector2f.sub(entity.location, location);
            double dist = Math.sqrt(offset.x * offset.x + offset.y * offset.y);

            double speed = speedFunc(dist);
            double angle = Math.atan2(offset.y, offset.x);
            velX += speed * Math.cos(angle);
            velY += speed * Math.sin(angle);
        }

        location = new Vector2f(location.x + velX, location.y + velY);

        /*
        double mag = Math.sqrt(offset.x * offset.x + offset.y * offset.y);
        float factor = 1;
        if (mag > 1) {
            factor = (float) (1 / mag);
        }
        if (mag < 50) {
            factor = 0;
        }
        location = Vector2f.add(location, Vector2f.mul(offset, factor));
        */
    }

    private double speedFunc(double dist) {
        double direction = (dist < 50) ? -1 : 1;
        double fac = Math.abs(dist - 50) / 50;
        if (fac > 8) {
            fac = 0;
        } else if (fac > 4) {
            fac = 4;
        }
        return fac * direction;
    }
}
