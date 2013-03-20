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

/**
 * Steps are the nodes in the search tree.  The subclasses of this class represent
 * the STRIPS actions in this planning problem.
 *
 */
public abstract class Step {
    public static Integer BIG_VALUE = 1000;
    public static Integer MEDIUM_LARGE_VALUE = 750;
    public static Integer MEDIUM_VALUE = 500;
    public static Integer SMALL_VALUE = 100;

    public Integer unitID;
    public State state;

    protected Step(Integer unitID, State state) {
        this.unitID = unitID;
        this.state = state;
    }

    public abstract Map<Integer, Action> getActions();

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
