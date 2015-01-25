package com.platymuus.ggj15.game;

import org.jsfml.system.Vector2f;

public class StaticFollower extends Interactable {
    public final String name;

    public StaticFollower(String n) {
        location = new Vector2f(random(), random());
        name = n;
        drawable = Follower.sprite(n);
    }

    private float random() {
        return world.randomDistrib(500);
    }

    @Override
    public String getInteractText() {
        return "Recruit " + name;
    }

    @Override
    public void interact() {
        Follower temp;
        if (world.getPlayer().getFollowers().size() == 0) {
            temp = new Follower(world.getPlayer(), location, name);
        } else {
            temp = new Follower(world.getPlayer().getFollowers().get(world.getPlayer().getFollowers().size() - 1), location, name);
        }
        world.getPlayer().addFollower(temp);
        world.entities.add(temp);
        world.remove(this);
    }

}
