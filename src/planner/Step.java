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
    	protected HarvestGold(Integer unitID, StateView state) {
    		super(unitID, state);
    	}

		@Override
		Map<Integer, Action> getActions() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return null;
		}
    }

    public class HarvestWood extends Step {
    	protected HarvestWood(Integer unitID, StateView state) {
    		super(unitID, state);
    	}

		@Override
		Map<Integer, Action> getActions() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return null;
		}
    }

    public class MoveTo extends Step {
    	protected MoveTo(Integer unitID, StateView state) {
    		super(unitID, state);
    	}

		@Override
		Map<Integer, Action> getActions() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return null;
		}
    }
}
