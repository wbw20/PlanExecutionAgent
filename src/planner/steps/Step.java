package planner.steps;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import planner.State;
import planner.Planner.Square;
import planner.Unit;

import edu.cwru.sepia.action.Action;
import edu.cwru.sepia.environment.model.state.State.StateView;
import edu.cwru.sepia.environment.model.state.Unit.UnitView;

public abstract class Step {
    public static Integer BIG_VALUE = 10;
    public static Integer MEDIUM_VALUE = 5;
    public static Integer SMALL_VALUE = 1;

    public Integer unitID;
    public State state;

    protected Step(Integer unitID, State state) {
        this.unitID = unitID;
        this.state = state;
    }

    abstract Map<Integer, Action> getActions();

    public abstract Boolean arePrerequisitesMet();

    public abstract Integer heuristicValue(State goal);

    @Override
    public abstract String toString();

    protected Unit getTownHall() {
        for (Unit u : state.getUnits()) {
            if (u.getType().equals(Unit.TOWN_HALL)) {
                return u;
            }
        }

        return null;
    }

    protected List<Unit> getPeasants() {
        List<Unit> peasantLocations = new ArrayList<Unit>();

        for (Unit u : state.getUnits()) {
            if (u.getType().equals(Unit.PEASANT)) {
                peasantLocations.add(u);
            }
        }

        return peasantLocations;
    }
}
