package com.platymuus.jsc.gui;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Simple grouping of multiple Drawables which is also transformable.
 */
public class Group extends TransformDrawable {
    public final List<Drawable> children = new ArrayList<>();

    public Group() {
    }

    public Group(Drawable... initialElems) {
        children.addAll(Arrays.asList(initialElems));
    }

    public void add(Drawable elem) {
        children.add(elem);
    }

    @Override
    public void draw(RenderTarget target, RenderStates states) {
        states = compose(states);
        for (Drawable child : children) {
            child.draw(target, states);
        }
    }
}
