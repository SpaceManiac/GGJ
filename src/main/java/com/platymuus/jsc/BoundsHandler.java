package com.platymuus.jsc;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Transformable;

/**
 * Hacky wrapper thing to more easily apply origins to bounded objects.
 */
public abstract class BoundsHandler {

    public abstract Transformable getTransformable();

    public abstract FloatRect getGlobalBounds();

    public abstract FloatRect getLocalBounds();

    public void centerHorizontally() {
        position(0.5f, 0);
    }

    public void centerVertically() {
        position(0, 0.5f);
    }

    public void center() {
        position(0.5f, 0.5f);
    }

    private void position(float x, float y) {
        FloatRect bounds = getLocalBounds();
        getTransformable().setOrigin(bounds.left + x * bounds.width, bounds.top + y * bounds.height);
    }

    public static BoundsHandler of(Transformable value) {
        if (value instanceof Text) {
            return of((Text) value);
        } else if (value instanceof Sprite) {
            return of((Sprite) value);
        } else {
            throw new IllegalArgumentException("" + value.getClass());
        }
    }

    public static BoundsHandler of(final Text value) {
        return new BoundsHandler() {
            @Override
            public Transformable getTransformable() {
                return value;
            }

            @Override
            public FloatRect getGlobalBounds() {
                return value.getGlobalBounds();
            }

            @Override
            public FloatRect getLocalBounds() {
                return value.getLocalBounds();
            }
        };
    }

    public static BoundsHandler of(final Sprite value) {
        return new BoundsHandler() {
            @Override
            public Transformable getTransformable() {
                return value;
            }

            @Override
            public FloatRect getGlobalBounds() {
                return value.getGlobalBounds();
            }

            @Override
            public FloatRect getLocalBounds() {
                return value.getLocalBounds();
            }
        };
    }

}
