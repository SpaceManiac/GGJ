package com.platymuus.ggj15.game;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;

public class StaticFollower extends Interactable{
	
	public StaticFollower() {
		location = new Vector2f(random(), random());

        RectangleShape shape = new RectangleShape(new Vector2f(20, 20));
        shape.setFillColor(Color.RED);
        shape.setOrigin(10, 10);
        drawable = shape;
    }
    
    private float random() {
        int DISTRIB = 500;
        return (float) (Math.random() * 2 * DISTRIB) - DISTRIB;
    }

	@Override
	public String getInteractText() {
		return "Recruit";
	}

	@Override
	public void interact() {
		Follower temp;
		if(world.getPlayer().getFollowers().size() == 0){
			temp = new Follower(world.getPlayer(), location);
		}else{
			temp = new Follower(world.getPlayer().getFollowers().get(world.getPlayer().getFollowers().size()-1), location);
		}
		world.getPlayer().addFollower(temp);
		world.addEntity(temp);
		world.remove(this);
		
		
	}

}
