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
    }

    public class HarvestWood extends Step {
        private Integer forrestID;

        protected HarvestWood(Integer unitID, Integer forrestID, StateView state) {
            super(unitID, state);

            this.forrestID = forrestID;
        }

        @Override
        Map<Integer, Action> getActions() {
            Map<Integer, Action> toReturn = new HashMap<Integer, Action>();

            toReturn.put(unitID, Action.createCompoundGather(unitID, forrestID));

            return toReturn;
        }

        @Override
        public String toString() {
            return "The peasant with ID " + unitID + 
                    " gathers wood from the forrest with ID " + forrestID + ".";
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
    }
}
