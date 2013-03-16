package planner;

import java.util.HashMap;
import java.util.Map;

import planner.Planner.Square;

public class State {
	
	/* Static Keys */
	private final String ID = "id";
	private final String WOOD = "wood";
	
	private Map<Unit, Square> units;
	
	protected State() {
	}
	
	protected class Unit {
		protected Map<String, Object> dymanicValues;
		
		protected Unit() {
			dymanicValues = new HashMap<String, Object>();
		}
	}
}
