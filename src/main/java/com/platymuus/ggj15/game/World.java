package com.platymuus.ggj15.game;

import com.platymuus.jsc.Hacks;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Vector2f;

import java.util.*;

/**
 * Container for entities & other game-world info.
 */
public class World implements Drawable {

    public final Random random = new Random();

    public final List<Entity> entities = new LinkedList<>();
    private final List<Entity> removeEntities = new LinkedList<>();

    private Player player = new Player();

    public int runes = 0, totalRunes = 6;

    public boolean controllerMode;

    public String fate;
    public boolean goodEnd = false;

    public int sand; // sand on screen level

    public World() {
        Entity.world = this;

        entities.add(player);
        entities.add(new Obelisk());

        entities.addAll(GameMap.entities());

        for (int i = 0; i < 100; ++i) {
            entities.add(new Rock());
        }

        entities.add(new Boat(null, null));
        entities.add(new Bunker(null));
        entities.add(new Key());
    }

    public Player getPlayer() {
        return player;
    }

    public void update() {
        for (Entity entity : new ArrayList<>(entities)) {
            entity.update();
        }

        entities.removeAll(removeEntities);
        removeEntities.clear();
    }

    @Override
    public void draw(RenderTarget target, RenderStates states) {
        List<Entity> local = new ArrayList<>(entities);
        Collections.sort(local, EntityComparator.instance);
        for (Entity entity : local) {
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

    public float randomDistrib(float distrib) {
        return random.nextFloat() * 2 * distrib - distrib;
    }

    private static class EntityComparator implements Comparator<Entity> {
        private static final EntityComparator instance = new EntityComparator();

        @Override
        public int compare(Entity o1, Entity o2) {
            return Float.compare(o1.location.y, o2.location.y);
        }
    }
}
