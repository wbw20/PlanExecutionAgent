package planner.steps;

import java.util.HashMap;
import java.util.Map;

import planner.State;

import edu.cwru.sepia.action.Action;

public class HarvestGold extends Step {
    public Integer goldMineID;

    public HarvestGold(Integer unitID, Integer goldMineID, State state) {
        super(unitID, state);
        
        this.goldMineID = goldMineID;
    }

    @Override
    Map<Integer, Action> getActions() {
        Map<Integer, Action> toReturn = new HashMap<Integer, Action>();

        toReturn.put(unitID, Action.createCompoundGather(unitID, goldMineID));

        return toReturn;
    }

    @Override
    public String toString() {
        return "The peasant with ID " + unitID + 
                " gathers gold from the gold mine with ID " + goldMineID + ".";
    }

    /**
     * The prerequisites for a HarvestGold operation are that the peasant who will
     * be harvesting is next to a gold mine and that gold mine has some gold.
     */
    @Override
    public Boolean arePrerequisitesMet() {
        return state.getUnitBy(unitID).getLocation().isAdjacent(state.getUnitBy(goldMineID).getLocation()) &&
                state.getUnitBy(goldMineID).getGold() > 0;
    }

    @Override
    public Integer heuristicValue(State goal) {
        Integer toReturn = 0;

        if (goal.GOLD_AMOUNT > state.GOLD_AMOUNT) {
            toReturn += MEDIUM_VALUE;
        }

        return toReturn;
    }
}
