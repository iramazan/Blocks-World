package csc366.world;

public abstract class Shape {

    public enum Type {
        BLOCK, PYRAMID, IT
    }
    public enum Color {
        RED, GREEN, BLUE
    }

    /**
     * Returns true if this shape can support the specified block.
     * @param block block object to query with
     * @return True if the shape can support the block
     */
    public abstract boolean canSupport(Block block);

    /**
     * Returns true if this shape can support the specified pyramid.
     * @param pyramid pyramid object to query with
     * @return True if the shape can support the pyramid
     */
    public abstract boolean canSupport(Pyramid pyramid);

}
