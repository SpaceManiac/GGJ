package com.platymuus.ggj15.game;

import com.platymuus.ggj15.Resources;
import com.platymuus.jsc.BoundsHandler;
import com.platymuus.jsc.Hacks;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

import java.util.*;

/**
 * Todo: Javadoc for World.
 */
public class World implements Drawable {

    public final List<Entity> entities = new LinkedList<>();
    private final List<Entity> removeEntities = new LinkedList<>();

    private Player player = new Player();

    public int landmarks, totalLandmarks;

    private Sprite island;

    public boolean controllerMode;

    public String fate;
    public boolean goodEnd = false;

    public int sand; // sand on screen level

    public World() {
        entities.add(player);
        entities.add(new Obelisk());

        entities.add(new StaticFollower("Joe"));
        entities.add(new StaticFollower("Prof"));
        entities.add(new StaticFollower("Dorky"));
        entities.add(new StaticFollower("Soarin "));


        for (int i = 0; i < 100; ++i) {
            entities.add(new Rock());
        }

        landmarks = 0;
        totalLandmarks = 10;
        for (int i = 0; i < totalLandmarks; ++i) {
            entities.add(new Landmark());
        }
        for (int i = 0; i < 5; ++i) {
            entities.add(new Clue("This is clue #" + (i + 1)));
        }

        for (int i = 0; i < 2; ++i) {
            entities.add(new FollowerDropOff());
        }

        entities.add(new Dunes());

        island = Resources.getSprite("game/island-half.png");
        BoundsHandler.of(island).center();
    }

    public Player getPlayer() {
        return player;
    }

    public void update() {
        for (Entity entity : entities) {
            entity.world = this;
        }
        for (Entity entity : new ArrayList<>(entities)) {
            entity.update();
        }

        entities.removeAll(removeEntities);
        removeEntities.clear();
    }

    @Override
    public void draw(RenderTarget target, RenderStates states) {
        target.draw(island);

        List<Entity> local = new ArrayList<>(entities);
        Collections.sort(local, EntityComparator.instance);
        for (Entity entity : local) {
            entity.world = this;
            entity.draw(target, states);
        }
    }

    public void remove(Entity entity) {
        removeEntities.add(entity);
    }

    public boolean canOccupy(Entity entity, Vector2f newLoc) {
        if (entity.collision == null) {
            return true;
        }

        FloatRect actual = Hacks.translateRect(entity.collision, newLoc);
        for (Entity entity2 : entities) {
            if (entity2 == entity || entity2.collision == null) continue;

            FloatRect rect2 = Hacks.translateRect(entity2.collision, entity2.location);
            if (actual.intersection(rect2) != null) {
                return false;
            }
        }

        return true;
    }

    public void collideTranslate(Entity entity, Vector2f delta) {
        float sx = Math.signum(delta.x), sy = Math.signum(delta.y);
        float tx = Math.abs(delta.x), ty = Math.abs(delta.y);

        do {
            Vector2f newLoc = new Vector2f(entity.location.x + sx * tx, entity.location.y);
            if (canOccupy(entity, newLoc)) {
                break;
            }
            tx -= 0.1;
        } while (tx > 0);

        do {
            Vector2f newLoc = new Vector2f(entity.location.x + sx * tx, entity.location.y + sy * ty);
            if (canOccupy(entity, newLoc)) {
                break;
            }
            ty -= 0.1;
        } while (ty > 0);

        entity.location = new Vector2f(entity.location.x + sx * tx, entity.location.y + sy * ty);
    }

    public void addEntity(Entity e) {
        entities.add(e);
    }

    private static class EntityComparator implements Comparator<Entity> {
        private static final EntityComparator instance = new EntityComparator();

        @Override
        public int compare(Entity o1, Entity o2) {
            return Float.compare(o1.location.y, o2.location.y);
        }
    }
}
