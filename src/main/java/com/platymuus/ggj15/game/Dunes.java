package com.platymuus.ggj15.game;

import com.platymuus.ggj15.Resources;
import com.platymuus.jsc.BoundsHandler;
import com.platymuus.jsc.Hacks;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

/**
 * deadly dunes of death
 */
public class Dunes extends Entity {
    private FloatRect myRect;
    private boolean successJ = false, successP = false;

    // if the player ends up inside us - DEATH!

    public Dunes() {
        Sprite sprite = Resources.getSprite("game/sand2.png");
        Vector2i size = sprite.getTexture().getSize();
        BoundsHandler.of(sprite).centerHorizontally();
        drawable = sprite;
        location = new Vector2f(2000, -size.y / 2);

        myRect = new FloatRect(-size.x / 2, 0, size.x, size.y);
    }

    @Override
    public void update() {
    	if(!successJ||!successP){
    		for(Follower f: world.getPlayer().getFollowers()){
    			if(f.getName().equals("Joe")){
    				successJ = true;
    			}
    			if(f.getName().equals("Prof")){
    				successP = true;
    			}
    		}
    	}
        FloatRect modified = Hacks.translateRect(myRect, location);
        if (modified.intersection(Hacks.translateRect(world.getPlayer().collision, world.getPlayer().location)) != null) {
            if (++world.sand >= 200) {
            	if(successJ && successP){
            		world.fate = "a prosperous";
            		world.goodEnd = true;
            	}else{
            		world.fate = "a dusty";
            	}
            }
        } else {
            if (world.sand > 128) {
                world.sand = 128;
            } else if (world.sand > 0) {
                --world.sand;
            }
        }
    }
}
