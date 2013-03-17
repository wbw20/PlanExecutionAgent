package planner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import planner.Planner.Square;

import edu.cwru.sepia.action.Action;
import edu.cwru.sepia.environment.model.state.State.StateView;
import edu.cwru.sepia.environment.model.state.Unit.UnitView;

public abstract class Step {
    protected Integer unitID;
    protected StateView state;

    protected Step(Integer unitID, StateView state) {
        this.unitID = unitID;
        this.state = state;
    }

    abstract Map<Integer, Action> getActions();

    abstract Boolean arePrerequisitesMet(State state);

    @Override
    public abstract String toString();

    protected UnitView getTownHall() {
        for (UnitView u : state.getAllUnits()) {
            if (u.getTemplateView().getName().equals("TownHall")) {
                return u;
            }
        }

        return null;
    }

    protected List<UnitView> getPeasants() {
        List<UnitView> peasantLocations = new ArrayList<UnitView>();

        for (UnitView u : state.getAllUnits()) {
            if (u.getTemplateView().getName().equals("Peasant")) {
                peasantLocations.add(u);
            }
        }

        return peasantLocations;
    }

    public class Deposit extends Step {
        protected Deposit(Integer unitID, StateView state) {
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
        Boolean arePrerequisitesMet(State state) {
            return state.getUnitBy(unitID).getLocation().isAdjacent(new Square(getTownHall()));
        }
    }

    public class HarvestGold extends Step {
        private Integer goldMineID;

        protected HarvestGold(Integer unitID, Integer goldMineID, StateView state) {
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
        Boolean arePrerequisitesMet(State state) {
            return state.getUnitBy(unitID).getLocation().isAdjacent(state.getUnitBy(goldMineID).getLocation()) &&
                    state.getUnitBy(goldMineID).getGold() > 0;
        }
    }

    public class HarvestWood extends Step {
        private Integer forestID;

        protected HarvestWood(Integer unitID, Integer forrestID, StateView state) {
            super(unitID, state);

            this.forestID = forrestID;
        }

        @Override
        Map<Integer, Action> getActions() {
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
        Boolean arePrerequisitesMet(State state) {
            return state.getUnitBy(unitID).getLocation().isAdjacent(state.getUnitBy(forestID).getLocation()) &&
                    state.getUnitBy(forestID).getWood() > 0;
        }
    }

    public class MoveTo extends Step {
        private Square destination;

        protected MoveTo(Integer unitID, Square destination, StateView state) {
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
                    new Square(state.getUnit(unitID)).toString() +
                     " to " + destination.toString() + ".";
        }

        /**
         * The prerequisites for moving somewhere is that there is nothing there at 
         * the moment.
         */
        @Override
        Boolean arePrerequisitesMet(State state) {
            return state.getUnitIn(destination) == null;
        }
    }
}
