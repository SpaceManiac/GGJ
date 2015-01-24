package com.platymuus.ggj15.game;

import com.platymuus.ggj15.Resources;
import org.jsfml.graphics.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Todo: Javadoc for World.
 */
public class World implements Drawable {

    public final List<Entity> entities = new LinkedList<>();


    private Sprite sprite;
    private Player player = new Player();

    public World() {
        sprite = Resources.getSprite("game/sand.png");
        entities.add(player);
    }

    public Player getPlayer() {
        return player;
    }

    public void update() {
        for (Entity entity : entities) {
            entity.world = this;
            entity.update();
        }
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
        for (Entity entity : entities) {
            entity.draw(renderTarget, renderStates);
        }
    }
}
