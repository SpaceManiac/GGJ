package com.platymuus.ggj15.game;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

import com.platymuus.ggj15.Resources;
import com.platymuus.jsc.BoundsHandler;
import com.platymuus.jsc.Hacks;

public class Well extends Interactable{
	
	public Well() {
		 location = new Vector2f(random(), random());

	    RectangleShape shape = new RectangleShape(new Vector2f(30, 30));
	    shape.setFillColor(Color.MAGENTA);
	    shape.setOrigin(15, 15);
	    drawable = shape;
	    
	    collision = new FloatRect(-15, -15, 30, 30);
    }

    private float random() {
        int DISTRIB = 500;
        return (float) (Math.random() * 2 * DISTRIB) - DISTRIB;
    }


	@Override
	public String getInteractText() {
		return "Draw water";
	}

	@Override
	public void interact() {
		world.getPlayer().refill();
	}

}
