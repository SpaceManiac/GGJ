package com.platymuus.ggj15.game;

import org.jsfml.system.Vector2f;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * info on the entities contained on the game map.
 */
public class GameMap {

    public static Collection<? extends Entity> entities() {
        LinkedList<Entity> L = new LinkedList<>();

        add(L, new Path(), 600, 600);
        add(L, new Path(), 1200, 1100);
        add(L, new StaticFollower("Prof"), 1200, 1200);

        for (int j = 1; j <= 5; ++j) {
            add(L, new Path(), 550 * j, -300 * j);
        }
        add(L, new Dunes(), 550 * 8, -300 * 13);
        add(L, new Oasis(), 550 * 8 - 20, -300 * 13 - 50 + 1200);
        add(L, new StaticFollower("Joe"), 550 * 8, -1300);

        add(L, new ObeliskRune(), 450, -200);
        add(L, new ObeliskRune(), 550 * 8 + 100, -300 * 12);

        return L;
    }

    private static void add(List<Entity> L, Entity e, float x, float y) {
        e.location = new Vector2f(x, y);
        L.add(e);
    }
}
