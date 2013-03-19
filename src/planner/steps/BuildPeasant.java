package planner.steps;

import java.util.HashMap;
import java.util.Map;

import planner.State;
import planner.Unit;
import edu.cwru.sepia.action.Action;

public class BuildPeasant extends Step {

    private Integer townHallID;
    private Integer templateID;

    protected BuildPeasant(Integer unitID, State state, Integer townHallID, Integer templateID) {
        super(unitID, state);
        
        this.townHallID = townHallID;
        this.templateID = templateID;
    }

    @Override
    public Map<Integer, Action> getActions() {
        Map<Integer, Action> toReturn = new HashMap<Integer, Action>();

        toReturn.put(unitID, Action.createCompoundBuild(townHallID, templateID, 0, 0));

        return toReturn;
    }

    @Override
    public Boolean arePrerequisitesMet() {
        return state.getAllOf(Unit.PEASANT).size() < 3 && state.GOLD_AMOUNT > 400;
    }

    @Override
    public Integer heuristicValue(State goal) {
        return Step.BIG_VALUE;
    }

    @Override
    public String toString() {
        return "The Town Hall produces a new peasant";
    }

}
