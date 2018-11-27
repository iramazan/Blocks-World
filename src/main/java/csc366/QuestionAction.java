package csc366;

import csc366.world.World;

public class QuestionAction extends BlockAction {

    public enum AV {
        WHAT_IS, CAN, WHICH
    }
    public enum MV {
        SUPPORTED_BY, SUPPORT, IS_SITTING_ON
    }

    private AV av;
    private MV mv;
    private ObjectNode object1;
    private ObjectNode object2;
    private boolean hasObject2 = false;

    public void setObject1(ObjectNode object) {
        this.object1 = object;
    }

    public void setObject2(ObjectNode object) {
        this.object2 = object;
        this.hasObject2 = true;
    }

    public void setAv(AV av) {
        this.av = av;
    }

    public void setMv(MV mv) {
        this.mv = mv;
    }

    /**
     * Execute the action represented by this object.
     * @param world The world representation to operate on
     */
    public void execute(World world) {
    }
}
