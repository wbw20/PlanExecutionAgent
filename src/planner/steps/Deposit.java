package planner.steps;

import java.util.HashMap;
import java.util.Map;

import planner.State;

import edu.cwru.sepia.action.Action;

public class Deposit extends Step {
    public Deposit(Integer unitID, State state) {
        super(unitID, state);
    }

    @Override
    Map<Integer, Action> getActions() {
        Map<Integer, Action> toReturn = new HashMap<Integer, Action>();
        
        toReturn.put(unitID, Action.createCompoundDeposit(unitID, getTownHall().getID()));
        
        return toReturn;
    }

    @Override
    public String toString() {
        return "The peasant with ID " + unitID + " deposits to the Town Hall.";
    }

    /**
     * The prerequisites for a Deposit operation are that the peasant who will
     * be making the deposit must be next to the Town Hall.
     */
    @Override
    public Boolean arePrerequisitesMet() {
        return state.getUnitBy(unitID).getLocation().isAdjacent(getTownHall().getLocation());
    }
}
