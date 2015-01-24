package com.platymuus.jsc;

import com.platymuus.ggj15.LogWrapper;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Window;

import java.lang.reflect.Method;

/**
 * Collection of invocation or mathematical hacks for JSFML.
 */
public final class Hacks {

    private static final LogWrapper log = new LogWrapper(Hacks.class);

    private Hacks() {
    }

    private static Method window_setSize;

    static {
        try {
            window_setSize = Window.class.getDeclaredMethod("nativeSetSize", int.class, int.class);
            window_setSize.setAccessible(true);
        } catch (ReflectiveOperationException e) {
            log.error("Failed to initialize reflection information", e);
        }
    }

    /**
     * Set the window's size to the given amount, as a workaround to the broken
     * {@link Window#setSize}.
     * @param window the window
     * @param size the desired size
     */
    public static void setSize(Window window, Vector2i size) {
        try {
            window_setSize.invoke(window, size.x, size.y);
        } catch (ReflectiveOperationException e) {
            log.error("Failed to invoke nativeSetSize", e);
        }
    }

    /**
     * Get a pixel-perfect view for the render target, as if by the equivalent
     * of {@link RenderTarget#getDefaultView} adjusted for the target's current
     * size rather than its initial size.
     * @param target the render target
     * @return the default view
     */
    public static ConstView getDefaultView(RenderTarget target) {
        View view = new View();
        Vector2i size = target.getSize();
        view.setCenter(size.x / 2, size.y / 2);
        view.setSize(size.x, size.y);
        return view;
    }

    /**
     * Get a pixel-perfect view with its origin at the center of the targe
     * rather than its top-left corner.
     * @param target the render target
     * @return the centered view
     */
    public static ConstView getCenteredView(RenderTarget target) {
        return new View(Vector2f.ZERO, new Vector2f(target.getSize()));
    }

    /**
     * Transform a point within one rectangle to the equivalent point in another.
     * @param vec the point to transform, relative to src
     * @param src the source rectangle
     * @param dest the target rectangle
     * @return the transformed point, relative to dest
     */
    public static Vector2f rectTransform(Vector2f vec, FloatRect src, FloatRect dest) {
        float x = (vec.x - src.left) / src.width;
        float y = (vec.y - src.top) / src.height;
        return new Vector2f(
                dest.left + x * dest.width,
                dest.top + y * dest.height
        );
    }

    /**
     * Perform piecewise floor division on a vector.
     * @param vec the vector to divide
     * @param factor the divisor
     * @return the result vector
     */
    public static Vector2i floorDiv(Vector2i vec, int factor) {
        return new Vector2i(
                (int) Math.floor((double)vec.x / factor),
                (int) Math.floor((double)vec.y / factor));
    }

    public static RenderStates offset(RenderStates states, Vector2f offset) {
        return new RenderStates(states, Transform.translate(states.transform, offset));
    }

    public static float dist(Vector2f vec) {
        return (float) Math.sqrt(vec.x * vec.x + vec.y * vec.y);
    }

    public static void hCenter(Text text) {
        FloatRect localBounds = text.getLocalBounds();
        text.setOrigin(localBounds.width / 2, 0);
    }

    public static Color multiply(Color color, float f) {
        return new Color(
                (int) (color.r * f),
                (int) (color.g * f),
                (int) (color.b * f),
                color.a);
    }

    public static void center(Sprite sprite) {
        FloatRect localBounds = sprite.getLocalBounds();
        sprite.setOrigin(localBounds.width / 2, localBounds.height / 2);
    }
}
