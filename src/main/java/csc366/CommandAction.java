package csc366;

import csc366.world.World;

public class CommandAction extends BlockAction {

    public enum Action {
        PICK_UP, PUT_DOWN
    }

    private Action action;
    private ObjectNode object;
    private BlockAction command;
    private boolean hasCommand = false;

    public void setAction(Action action) {
        this.action = action;
    }

    public void setObject(ObjectNode object) {
        this.object = object;
    }

    public void setCommand(BlockAction command) {
        this.command = command;
        this.hasCommand = true;
    }

    /**
     * Execute the action represented by this object.
     * @param world The world representation to operate on
     */
    public void execute(World world) {
        if (action.equals(Action.PICK_UP)) {
            if (object.hasColor()) {
                world.liftObject(object.getShape(), object.getColor());
            } else {
                world.liftObject(object.getShape());
            }
        } else {
            if (object.hasColor()) {
                world.dropObject(object.getShape(), object.getColor());
            }
            // TODO: Do we allow to place shape on an arbitrary block? No for now.
        }
        if (hasCommand) {
            command.execute(world);
        }
    }
}
