package csc366;

import csc366.world.Shape;
import csc366.world.Supporter;
import csc366.world.World;

import java.util.Optional;

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
        if (av.equals(AV.WHAT_IS) && mv.equals(MV.SUPPORTED_BY) && object1.hasColor()) {
            Optional<Supporter> support = world.whatIsSupporting(object1.getShape(), object1.getColor());
            support.ifPresentOrElse(Supporter::print, () -> System.out.println("I don't understand."));
        } else if (av.equals(AV.CAN) && mv.equals(MV.SUPPORT)) {
            String answer = object1.getShape().equals(Shape.Type.BLOCK) ? "Yes." : "No.";
            System.out.println("Answer");
        } else if (av.equals(AV.WHICH) && mv.equals(MV.IS_SITTING_ON)) {
            // TODO: Handle this case
        } else {
            System.out.println("I don't understand.");
        }
    }
}
