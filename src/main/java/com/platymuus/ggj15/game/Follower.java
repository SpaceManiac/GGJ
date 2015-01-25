package com.platymuus.ggj15.game;

import com.platymuus.jsc.BoundsHandler;
import com.platymuus.jsc.Hacks;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;

public class Follower extends Entity {

    private Entity target;

    public Follower(Entity t, Vector2f l) {
    	target = t;
    	
    	location = l;

        RectangleShape shape = new RectangleShape(new Vector2f(20, 20));
        shape.setFillColor(Color.RED);
        shape.setOrigin(10, 10);
        drawable = shape;
    }

    @Override
    public void update() {
    	if(target != null){
	        Vector2f delta = Vector2f.sub(location, target.location);
	        float mag = Hacks.dist(delta);
	        if (world.getPlayer().getFollow() && mag > 30) {
	            delta = Vector2f.mul(delta, 30 / mag);
	            location = Vector2f.add(target.location, delta);
	        }
    	}
    }
<<<<<<< HEAD

    private double speedFunc(double dist) {
        final double D = 100;
        double direction = (dist < D) ? -1 : 1;
        double fac = Math.abs(dist - D) / D;
        if (fac > 8) {
            fac = 0;
        } else if (fac > 5) {
            fac = 5;
        }
        return fac * direction;
    }
    
	
=======
>>>>>>> origin/master
}
