package com.platymuus.jsc.gui;

import org.jsfml.graphics.BasicTransformable;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.Transform;

/**
 * Base for transformable/drawable components.
 */
public abstract class TransformDrawable extends BasicTransformable implements Drawable {

    protected RenderStates compose(RenderStates states) {
        return new RenderStates(states, Transform.combine(states.transform, getTransform()));
    }

}
