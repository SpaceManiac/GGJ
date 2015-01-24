package com.platymuus.ggj15.game;

import com.platymuus.ggj15.Resources;
import org.jsfml.graphics.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Todo: Javadoc for World.
 */
public class World implements Drawable {

    public final List<Entity> entities = new LinkedList<>();
    private final List<Entity> removeEntities = new LinkedList<>();

    private Sprite sprite;
    private Player player = new Player();

    public int landmarks, totalLandmarks;

    public World() {
        sprite = Resources.getSprite("game/sand.png");

        entities.add(player);
        entities.add(new Obelisk());

        for (int i = 0; i < 10; ++i) {
            entities.add(new Follower());
        }

        for (int i = 0; i < 100; ++i) {
            entities.add(new Rock());
        }

        landmarks = 0;
        totalLandmarks = 10;
        for (int i = 0; i < totalLandmarks; ++i) {
            entities.add(new Landmark());
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void update() {
        for (Entity entity : entities) {
            entity.world = this;
            entity.update();
        }

        entities.removeAll(removeEntities);
        removeEntities.clear();
    }

    @Override
    public void draw(RenderTarget renderTarget, RenderStates renderStates) {
        ConstTexture tex = sprite.getTexture();
        for (int x = -5; x < 5; ++x) {
            for (int y = -5; y < 5; ++y) {
                sprite.setPosition(x * tex.getSize().x, y * tex.getSize().y);
                sprite.draw(renderTarget, renderStates);
            }
        }
        Collections.sort(entities, EntityComparator.instance);
        for (Entity entity : entities) {
            entity.draw(renderTarget, renderStates);
        }
    }

    public void remove(Entity entity) {
        removeEntities.add(entity);
    }

    private static class EntityComparator implements Comparator<Entity> {
        private static final EntityComparator instance = new EntityComparator();

        @Override
        public int compare(Entity o1, Entity o2) {
            return Float.compare(o1.location.y, o2.location.y);
        }
    }
}
