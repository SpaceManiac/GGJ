package com.platymuus.ggj15;

import com.platymuus.ggj15.game.GameplayScene;
import com.platymuus.jsc.SceneRunner;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.ContextSettings;
import org.jsfml.window.VideoMode;
import org.jsfml.window.WindowStyle;

/**
 * Main class.
 */
public class Main {

    private static final int[] widths = {1920, 1600, 1024, 800, 640};
    private static final int[] heights = {1080, 900, 768, 600, 480};

    public static void main(String[] args) {
        // Desire OpenGL 3.0
        ContextSettings settings = new ContextSettings(3, 0);

        // Detect what resolution to use
        VideoMode current = VideoMode.getDesktopMode();
        int width = 0, height = 0;
        for (int w : widths) {
            if (current.width > w) {
                width = w;
                break;
            }
        }
        for (int h : heights) {
            if (current.height > h) {
                height = h;
                break;
            }
        }
        if (width == 0 || height == 0) {
            return;
        }

        // Create window
        RenderWindow window = new RenderWindow(
                new VideoMode(width, height),
                TitleScene.TITLE,
                WindowStyle.DEFAULT,
                settings);

        // Apply icon

        // Launch application
        new SceneRunner(window).play(new GameplayScene());

        // Clean up & exit
        window.close();
    }

}
