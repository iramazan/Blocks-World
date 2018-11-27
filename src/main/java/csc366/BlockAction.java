package csc366;

import csc366.world.World;

public abstract class BlockAction {
    /**
     * Execute the action represented by this object.
     * @param world The world representation to operate on
     */
    public abstract void execute(World world);
}
