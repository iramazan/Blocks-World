package csc366;

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
     */
    public void execute() {

    }
}
