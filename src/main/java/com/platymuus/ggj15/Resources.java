package com.platymuus.ggj15;

import org.jsfml.audio.Music;
import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Texture;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Helpers for loading resources.
 */
public final class Resources {

    private Resources() {
    }

    private static final LogWrapper log = new LogWrapper(Resources.class);

    ///////////////////////////////////////////////////////////////////////////
    // Statically accessible resources

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
            loadFont(FONT_TEXT, "VeraSerif");
        } catch (IOException e) {
            log.error("failed to load fonts", e);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Internals

    /**
     * Get the URL corresponding to the specified resource path. First the path
     * is checked as a file, and if that is not found it is checked internally.
     * If the resource is found nowhere, null is returned.
     *
     * @param path the resource path
     * @return the URL, or null
     */
    public static URL getUrl(String path) {
        File file = new File(path);
        if (file.exists()) {
            try {
                URL url = file.toURI().toURL();
                log.info("external resource: " + path);
                return url;
            } catch (MalformedURLException e) {
                log.warning("malformed external resource: " + path, e);
            }
        }
        return Resources.class.getResource("/" + path);
    }

    /**
     * Open an InputStream from the given resource path, found according to
     * {@link #getUrl}. If the path is not found or an error occurs, null is
     * returned.
     *
     * @param path the resource path
     * @return the InputStream, or null
     */
    public static InputStream open(String path) {
        URL url = getUrl(path);
        if (url == null) {
            log.warning("resource does not exist: " + path);
            return null;
        }
        try {
            return url.openStream();
        } catch (IOException e) {
            log.error("failed to open URL: " + url, e);
            return null;
        }
    }

    private static void loadFont(Font font, String path) throws IOException {
        try (InputStream in = open("fonts/" + path + ".ttf")) {
            font.loadFromStream(in);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Resource access

    /**
     * Get a texture with the given filename, relative to "resources".
     *
     * @param path The path to the texture.
     * @return The loaded texture.
     */
    public static Texture getTexture(String path) {
        if (textures.containsKey(path)) {
            return textures.get(path);
        }
        Texture tex = new Texture();
        try (InputStream in = open(path)) {
            tex.loadFromStream(in);
            textures.put(path, tex);
        } catch (IOException e) {
            log.error("failed to load texture: " + path, e);
        }
        return tex;
    }

    /**
     * Get a sprite from the texture with the given filename.
     *
     * @param path The path to the texture.
     * @return A sprite with the loaded texture.
     */
    public static Sprite getSprite(String path) {
        return new Sprite(getTexture(path));
    }

    /**
     * Begin streaming music from the given filename, relative to the "sound"
     * directory and excluding the ".ogg" extension.
     *
     * @param path The music filename.
     * @return The loaded Music.
     */
    public static Music getMusic(String path) {
        Music mus = new Music();
        try (InputStream in = open("sound/" + path + ".ogg")) {
            mus.openFromStream(in);
        } catch (IOException e) {
            log.error("failed to load music: " + path, e);
        }
        return mus;
    }

    /**
     * Get a sound buffer with the given filename, relative to the "sound"
     * directory and excluding the ".wav" extension.
     *
     * @param path The sound filename.
     * @return The loaded SoundBuffer.
     */
    public static SoundBuffer getSoundBuffer(String path) {
        if (sounds.containsKey(path)) {
            return sounds.get(path);
        }
        SoundBuffer buffer = new SoundBuffer();
        try (InputStream in = open("sound/" + path + ".wav")) {
            buffer.loadFromStream(in);
            sounds.put(path, buffer);
        } catch (IOException e) {
            log.error("failed to load sound buffer: " + path, e);
        }
        return buffer;
    }

    /**
     * Get a sound from the sound buffer with the given filename.
     *
     * @param path The sound filename.
     * @return A sound with the loaded buffer.
     */
    public static Sound getSound(String path) {
        return new Sound(getSoundBuffer(path));
    }

    /**
     * Shorthand for creating a new Text for general use. The new Text will
     * have a blank string, the {@link #FONT_TEXT} font, and the given size.
     *
     * @param sz The size to use for the text.
     * @return The newly created Text.
     */
    public static Text text(int sz) {
        return new Text("", FONT_TEXT, sz);
    }

}
