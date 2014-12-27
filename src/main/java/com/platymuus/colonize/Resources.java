package com.platymuus.colonize;

import org.jsfml.audio.Music;
import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

/**
 * Helpers for loading resources.
 */
public class Resources {

    private Resources() {
    }

    ///////////////////////////////////////////////////////////////////////////
    // Statically accessible resources

    private static final String ROOT = "/";

    private static final HashMap<String, Texture> textures = new HashMap<>();
    private static final HashMap<String, SoundBuffer> sounds = new HashMap<>();

    /**
     * Font to use for titles.
     */
    public static final Font FONT_TITLE = new Font();
    /**
     * Font to use for prose.
     */
    public static final Font FONT_TEXT = new Font();

    static {
        try {
            loadFont(FONT_TITLE, "VeraSerif");
            loadFont(FONT_TEXT, "DayRoman");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Internals

    public static URL getUrl(String path) {
        return Resources.class.getResource(ROOT + path);
    }

    public static InputStream open(String path) {
        return Resources.class.getResourceAsStream(ROOT + path);
    }

    private static void loadFont(Font font, String path) throws IOException {
        font.loadFromStream(open("fonts/" + path + ".ttf"));
    }

    private static Texture loadTexture(Texture texture, String path) throws IOException {
        try {
            texture.loadFromStream(open(path));
            return texture;
        } catch (Exception e) {
            throw new IOException("Error loading texture from: " + path, e);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Resource access

    /**
     * Get a texture with the given filename, relative to "resources".
     * @param path The path to the texture.
     * @return The loaded texture.
     */
    public static Texture getTexture(String path) {
        if (textures.containsKey(path)) {
            return textures.get(path);
        }
        Texture tex = new Texture();
        tex.setSmooth(true);
        try {
            loadTexture(tex, path);
            textures.put(path, tex);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tex;
    }

    /**
     * Get a sprite from the texture with the given filename.
     * @param path The path to the texture.
     * @return A sprite with the loaded texture.
     */
    public static Sprite getSprite(String path) {
        return new Sprite(getTexture(path));
    }

    /**
     * Begin streaming music from the given filename, relative to the "sound"
     * directory and excluding the ".ogg" extension.
     * @param path The music filename.
     * @return The loaded Music.
     */
    public static Music getMusic(String path) {
        Music mus = new Music();
        try {
            mus.openFromStream(open("sound/" + path + ".ogg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mus;
    }

    /**
     * Get a sound buffer with the given filename, relative to the "sound"
     * directory and excluding the ".wav" extension.
     * @param path The sound filename.
     * @return The loaded SoundBuffer.
     */
    public static SoundBuffer getSoundBuffer(String path) {
        if (sounds.containsKey(path)) {
            return sounds.get(path);
        }
        SoundBuffer buffer = new SoundBuffer();
        try {
            buffer.loadFromStream(open("sound/" + path + ".wav"));
            sounds.put(path, buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * Get a sound from the sound buffer with the given filename.
     * @param path The sound filename.
     * @return A sound with the loaded buffer.
     */
    public static Sound getSound(String path) {
        return new Sound(getSoundBuffer(path));
    }

}
