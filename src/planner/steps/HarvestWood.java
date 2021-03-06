package planner.steps;

import java.util.HashMap;
import java.util.Map;

import planner.State;

import edu.cwru.sepia.action.Action;

public class HarvestWood extends Step {
    public Integer forestID;

    public HarvestWood(Integer unitID, Integer forrestID, State state) {
        super(unitID, state);

        this.forestID = forrestID;
    }

    @Override
    public Map<Integer, Action> getActions() {
        Map<Integer, Action> toReturn = new HashMap<Integer, Action>();

        toReturn.put(unitID, Action.createCompoundGather(unitID, forestID));

        return toReturn;
    }

    @Override
    public String toString() {
        return "The peasant with ID " + unitID + 
                " gathers wood from the forest with ID " + forestID + ".";
    }

    /**
     * The prerequisites for a HarvestWood operation are that the peasant who will
     * be harvesting is next to a forest and that forest has some wood.
     */
    @Override
    public Boolean arePrerequisitesMet() {
        return state.getUnitBy(unitID).getLocation().isAdjacent(state.getUnitBy(forestID).getLocation()) &&
                state.getUnitBy(forestID).getPayloadSize() > 0;
    }

    @Override
    public Integer heuristicValue(State goal) {
        Integer toReturn = 0;

        if (goal.WOOD_AMOUNT > state.WOOD_AMOUNT) {
            toReturn += MEDIUM_VALUE;
        }

        return toReturn;
    }
}
