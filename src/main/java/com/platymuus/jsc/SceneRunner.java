package com.platymuus.jsc;

import com.platymuus.colonize.Resources;
import org.jsfml.graphics.*;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.event.Event;

/**
 * Container/runner for scenes.
 */
public class SceneRunner {
    // Constants regarding framerate
    public static final int FPS = 60;
    public static final double FRAME_TIME = 1.0 / FPS;
    private static final int UPDATE_CAP = 5;

    // Properties accessible by scenes
    public final RenderWindow window;
    public final Vector2i screenSize;
    public final Vector2i screenCenter;

    // Internal properties
    private boolean windowClosed = false;
    private boolean windowFocused = true;
    private final Clock frameClock = new Clock();
    private double accumulator = 0;

    // Shade and warning to refocus window
    private final RectangleShape shade;
    private final Text warning;

    // Debug text and highlight
    private final Text debug;
    private final RectangleShape debugRect;

    /**
     * Instantiates the application.
     * @param window The window to use.
     */
    public SceneRunner(RenderWindow window) {
        this.window = window;
        window.setFramerateLimit(FPS);
        screenSize = window.getSize();
        screenCenter = Vector2i.div(screenSize, 2);

        shade = new RectangleShape(new Vector2f(screenSize));
        shade.setFillColor(new Color(255, 255, 255, 196));
        warning = new Text("Click the window border to focus", Resources.FONT_TITLE);
        warning.setColor(new Color(0, 0, 0));
        Scene.center(warning);
        warning.setPosition(screenCenter.x, 50);

        debug = new Text();
        debug.setFont(Resources.FONT_TEXT);
        debug.setCharacterSize(20);
        debug.setColor(new Color(255, 255, 255));
        debugRect = new RectangleShape();
        debugRect.setFillColor(new Color(0, 0, 0, 196));
    }

    /**
     * Set a piece of debug text to display in the top-left corner.
     * @param string The text to display.
     */
    public void debug(String string) {
        debug.setString(string);
        FloatRect localBounds = debug.getLocalBounds();
        debugRect.setSize(new Vector2f(localBounds.width, 2 * localBounds.height));
    }

    /**
     * Plays a scene in the application and returns when done.
     * @param scene The scene to play.
     */
    public void play(Scene scene) {
        // Attempt to initialize the scene
        scene.preInit(this);
        try {
            scene.initialize();
        } catch (Exception ex) {
            // Scene initialization failed, exit
            ex.printStackTrace();
            return;
        }

        // Update the scene once, so update logic happens before the first frame
        scene.update();

        // Enter main loop
        while (!scene.isDone() && !windowClosed) {
            // Delegate events to the scene
            for (Event event = window.pollEvent(); event != null; event = window.pollEvent()) {
                handleEvent(event);
                if (windowFocused) {
                    scene.handleEvent(event);
                }
            }

            // Update the scene as needed
            int updatesThisTime = 0;
            accumulator += frameClock.restart().asSeconds();
            while (accumulator >= FRAME_TIME) {
                // Perform a single update tick
                accumulator -= FRAME_TIME;
                if (windowFocused) {
                    // freeze time if the window is unfocused
                    scene.time += FRAME_TIME;
                    scene.update();
                }

                // Don't perform too many updates
                if (++updatesThisTime >= UPDATE_CAP) {
                    accumulator = 0;
                    break;
                }
            }

            // Render the scene
            window.clear();
            scene.render(window);
            if (!windowFocused) {
                window.draw(shade);
                window.draw(warning);
            }

            window.draw(debugRect);
            window.draw(debug);
            window.display();
        }

        scene.finish();
    }

    /**
     * Handle events that apply to the application as a whole.
     * @param event The event to handle.
     */
    private void handleEvent(Event event) {
        switch (event.type) {
            case CLOSED:
                windowClosed = true;
                break;
            case LOST_FOCUS:
                windowFocused = false;
                break;
            case GAINED_FOCUS:
                windowFocused = true;
                break;
        }
    }
}