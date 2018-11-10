package csc366.world;

import java.util.Optional;

public abstract class Shape {

    public enum Type {
        BLOCK, PYRAMID, IT
    }
    public enum Color {
        RED, GREEN, BLUE
    }

    public abstract boolean canSupport();

    public abstract Optional<Shape> getBehind();

    public abstract Optional<Shape> getInFront();

    public abstract Optional<Shape> getRight();

    public abstract Optional<Shape> getLeft();

    public abstract void setBehind(Shape shape);

    public abstract void setInFront(Shape shape);

    public abstract void setLeft(Shape shape);

    public abstract void setRight(Shape shape);

}
