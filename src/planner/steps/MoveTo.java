package planner.steps;

import java.util.HashMap;
import java.util.Map;

import planner.Planner;
import planner.State;
import planner.Planner.Square;
import edu.cwru.sepia.action.Action;

public class MoveTo extends Step {
    private Square destination;

    public MoveTo(Integer unitID, Square destination, State state) {
        super(unitID, state);

        this.destination = destination;
    }

    @Override
    Map<Integer, Action> getActions() {
        Map<Integer, Action> toReturn = new HashMap<Integer, Action>();

        toReturn.put(unitID, Action.createCompoundMove(unitID, destination.x, destination.y));

        return toReturn;
    }

    @Override
    public String toString() {
        return "The peasant with ID " + unitID + " moves from " + 
                state.getUnitBy(unitID).getLocation().toString() +
                 " to " + destination.toString() + ".";
    }

    /**
     * The prerequisites for moving somewhere is that there is nothing there at 
     * the moment.
     */
    @Override
    public Boolean arePrerequisitesMet() {
        return state.getUnitIn(destination) == null;
    }
}
