package planner;
import java.util.List;
import java.util.Map;

import planner.Planner.Square;

import edu.cwru.sepia.action.Action;
import edu.cwru.sepia.environment.model.state.State.StateView;

public abstract class Step {
	protected Integer unitID;
	protected StateView state;
	
	protected Step(Integer unitID, StateView state) {
        this.unitID = unitID;
        this.state = state;
	}

	abstract Map<Integer, Action> getActions();
	
	protected Square getTownHall() {
		return null;
	}
	
	protected List<Square> getPeasants() {
		return null;
	}

    public class Deposit extends Step {
    	protected Deposit(Integer unitID, StateView state) {
    		super(unitID, state);
    	}

		@Override
		Map<Integer, Action> getActions() {
			// TODO Auto-generated method stub
			return null;
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
    }
}
