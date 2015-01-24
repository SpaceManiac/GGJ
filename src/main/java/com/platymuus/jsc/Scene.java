package com.platymuus.jsc;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Text;
import org.jsfml.window.event.Event;

/**
 * Basic interface for a scene.
 */
public abstract class Scene {

    protected SceneRunner runner;
    protected boolean done = false;
    protected double time = 0;

    /**
     * Perform pre-initialization tasks internal to the scene system.
     * @param runner The runner to use for this scene.
     */
    void preInit(SceneRunner runner) {
        this.runner = runner;
    }

    /**
     * Determines whether the scene is done and should be ended.
     * @return <tt>true</tt> if the scene should be ended, <tt>false</tt> to continue.
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Initializes the scene by loading resources and creating initial entities.
     * @throws Exception In case an error occurs while initializing the scene.
     */
    public abstract void initialize() throws Exception;

    /**
     * Handles a user event.
     * @param event The event to handle.
     */
    public abstract void handleEvent(Event event);

    /**
     * Updates the scene for one tick.
     */
    public abstract void update();

    /**
     * Renders the scene to the specified target.
     * @param target The render target.
     */
    public abstract void render(RenderTarget target);

    /**
     * Tidies up the scene by freeing resources if needed.
     */
    public void finish() {}

    // Utility stuff

    /**
     * Horizontally centers a Text object by setting its origin.
     * @param text The Text to center.
     */
    protected static void center(Text text) {
        BoundsHandler.of(text).centerHorizontally();
    }

}