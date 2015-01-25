package com.platymuus.jsc;

import com.platymuus.ggj15.LogWrapper;
import org.jsfml.audio.Music;

import java.io.IOException;
import java.io.InputStream;

/**
 * Music with a persistent attached InputStream which can later be closed.
 */
public class MusicStream extends Music implements AutoCloseable {

    private static final LogWrapper log = new LogWrapper(MusicStream.class);

    private InputStream stream;

    public MusicStream(InputStream stream) throws IOException {
        this.stream = stream;
        openFromStream(stream);
    }

    @Override
    public void close() {
        stop();
        try {
            stream.close();
        } catch (IOException e) {
            log.error("failed to close music", e);
        }
    }

}
