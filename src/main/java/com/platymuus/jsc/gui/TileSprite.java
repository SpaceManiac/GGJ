package com.platymuus.jsc.gui;

import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;

/**
 * Sprite with attached tile information used for displaying tile sheets.
 */
public class TileSprite extends Sprite {

    private final int tileSize;
    private int tilesPerRow;

    public TileSprite(ConstTexture texture, int tileSize) {
        this(texture, tileSize, 0);
    }

    public TileSprite(ConstTexture texture, int tileSize, int tile) {
        super();
        this.tileSize = tileSize;
        setTexture(texture);
        setTile(tile);
    }

    @Override
    public void setTexture(ConstTexture texture, boolean resetRect) {
        super.setTexture(texture, resetRect);
        tilesPerRow = texture.getSize().x / tileSize;
    }

    public void setTile(int tile) {
        int x = tileSize * (tile % tilesPerRow), y = tileSize * (tile / tilesPerRow);
        setTextureRect(new IntRect(x, y, tileSize, tileSize));
    }
}
