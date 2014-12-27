package com.platymuus.colonize;

import com.platymuus.jsc.SceneRunner;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.ContextSettings;
import org.jsfml.window.VideoMode;

/**
 * Main class.
 */
public class Main {

    public static void main(String[] args) {
        // Desire OpenGL 3.0
        ContextSettings settings = new ContextSettings(3, 0);

        // Create window
        RenderWindow window = new RenderWindow(
                new VideoMode(1024, 768),
                TitleScene.TITLE,
                RenderWindow.TITLEBAR | RenderWindow.CLOSE,
                settings);

        // Apply icon

        // Launch application
        new SceneRunner(window).play(new TitleScene());

        // Clean up & exit
        window.close();
    }

}
