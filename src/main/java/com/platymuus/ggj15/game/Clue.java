package com.platymuus.ggj15.game;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Vector2f;

import com.platymuus.jsc.Hacks;

public class Clue extends Interactable{
	
	private String clueMsg;
	private RectangleShape shape;
	
	public Clue(String c){
		clueMsg = c;
		
		location = new Vector2f(random(), random());

        RectangleShape shape = new RectangleShape(new Vector2f(30, 30));
        shape.setFillColor(Color.MAGENTA);
        shape.setOrigin(15, 15);
        drawable = shape;
	}
	
	private float random() {
        int DISTRIB = 500;
        return (float) (Math.random() * 2 * DISTRIB) - DISTRIB;
    }

	@Override
	public String getInteractText() {
		return "Examine a shinny";
	}

	@Override
	public void interact() {		
		interactText.setString(clueMsg);
	}
	
	

}
