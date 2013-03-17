package planner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import planner.Planner.Square;

public class State {

    /* Static Keys */
    private final String ID = "id";
    private final String WOOD = "wood";
    private final String GOLD = "gold";
    private final String LOCATION = "location";

    private Set<Unit> units;

    protected State() {
        units = new HashSet<Unit>();
    }

    protected void add(Unit unit) {
        units.add(unit);
    }

    protected Set<Unit> getUnits() {
        return units;
    }

    protected Unit getUnitBy(Integer id) {
        for(Unit unit : units) {
            if (unit.dynamicValues.get(ID).equals(id)) {
                return unit;
            }
        }

        return null;
    }

    protected Unit getUnitIn(Square square) {
        for (Unit unit : units) {
            if (unit.getLocation().equals(square)) {
                return unit;
            }
        }

        return null;
    }

    protected class Unit {
        protected Map<String, Object> dynamicValues;

        protected Unit() {
            dynamicValues = new HashMap<String, Object>();
        }

        protected Square getLocation() {
            return (Square)dynamicValues.get(LOCATION);
        }

        protected Integer getGold() {
            return (Integer)dynamicValues.get(GOLD);
        }

        protected Integer getWood() {
            return (Integer)dynamicValues.get(WOOD);
        }
    }
}
