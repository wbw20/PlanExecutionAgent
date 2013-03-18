package planner.steps;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import planner.State;
import planner.Planner.Square;
import planner.State.Unit;

import edu.cwru.sepia.action.Action;
import edu.cwru.sepia.environment.model.state.State.StateView;
import edu.cwru.sepia.environment.model.state.Unit.UnitView;

public abstract class Step {
    protected Integer unitID;
    protected State state;

    protected Step(Integer unitID, State state) {
        this.unitID = unitID;
        this.state = state;
    }

    abstract Map<Integer, Action> getActions();

    public abstract Boolean arePrerequisitesMet();

    @Override
    public abstract String toString();

    protected Unit getTownHall() {
        for (Unit u : state.getUnits()) {
            if (u.getType().equals(State.TOWN_HALL)) {
                return u;
            }
        }

        return null;
    }

    protected List<Unit> getPeasants() {
        List<Unit> peasantLocations = new ArrayList<Unit>();

        for (Unit u : state.getUnits()) {
            if (u.getType().equals(State.PEASANT)) {
                peasantLocations.add(u);
            }
        }

        return peasantLocations;
    }
}
