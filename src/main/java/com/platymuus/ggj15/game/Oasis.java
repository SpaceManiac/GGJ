package com.platymuus.ggj15.game;

import org.jsfml.system.Vector2f;

/**
 * oasis of rescue
 */
public class Oasis extends Interactable {

    public Oasis() {
        location = new Vector2f(2000 - 20, -50);
    }

    @Override
    public boolean isInteractable() {
        for (Follower f : world.getPlayer().getFollowers()) {
            if (f.getName().equals("Prof")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getInteractText() {
        return "Follow map to civilization";
    }

    @Override
    public String getUninteractableText() {
        return "Need map to civilization";
    }

    @Override
    public void interact() {
        world.fate = "a civilized";
    }

    @Override
    public float getInteractZone() {
        return 120;
    }

    @Override
    public void update() {
        /*boolean successJ = false, successP = false;
        for (Follower f : world.getPlayer().getFollowers()) {
            if (f.getName().equals("Joe")) {
                successJ = true;
            } else if (f.getName().equals("Prof")) {
                successP = true;
            }
        }

        FloatRect modified = Hacks.translateRect(myRect, location);
        if (modified.intersection(Hacks.translateRect(world.getPlayer().collision, world.getPlayer().location)) != null) {
            if (successJ && successP) {
                world.fate = "a civilized";
            }
        }*/
    }
}
