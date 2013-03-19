package planner.steps;

import java.util.HashMap;
import java.util.Map;

import planner.Planner;
import planner.State;
import planner.Planner.Square;
import planner.Unit;
import edu.cwru.sepia.action.Action;

public class MoveTo extends Step {
    public Square destination;

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

    /**
     * A MoveTo is only worth something if the destination is next to a resource that
     * we need, or our peasant is carrying something and our destination is next to
     * the Town Hall
     */
    @Override
    public Integer heuristicValue(State goal) {
        Integer toReturn = 0;

        //The peasant is carrying something
        if (state.getUnitBy(unitID).getPayloadSize() > 0) {
            for (Unit townhall : state.getAllOf(Unit.TOWN_HALL)) {
                if (destination.isAdjacent(townhall.getLocation())) {
                    toReturn += BIG_VALUE;
                }
            }
        } else { //The peasant is empty-handed
            if (goal.WOOD_AMOUNT > state.WOOD_AMOUNT) { //If we need more wood
                for (Unit forest : state.getAllOf(Unit.FOREST)) {
                    if (forest.getLocation().isAdjacent(destination)) {
                        toReturn += SMALL_VALUE;
                    }
                }
            }

            if (goal.GOLD_AMOUNT > state.GOLD_AMOUNT) { //If we need more gold
                for (Unit mine : state.getAllOf(Unit.GOLD_MINE)) {
                    if (mine.getLocation().isAdjacent(destination)) {
                        toReturn += SMALL_VALUE;
                    }
                }
            }
        }

        return toReturn;
    }
}
