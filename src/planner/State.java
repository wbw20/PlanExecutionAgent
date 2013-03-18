package planner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import planner.Planner.Square;

public class State {

    /* Static Keys */
    public final static String ID = "id";
    public final static String WOOD = "wood";
    public final static String GOLD = "gold";
    public final static String LOCATION = "location";
    public final static String TYPE = "type";

    /* Static Values */
    public final static String TOWN_HALL = "townhall";
    public final static String PEASANT = "peasant";
    public final static String FOREST = "forest";
    public final static String GOLD_MINE = "gold mine";

    private Set<Unit> units;

    public State() {
        units = new HashSet<Unit>();
    }

    public void add(Unit unit) {
        units.add(unit);
    }

    public Set<Unit> getUnits() {
        return units;
    }

    public Unit getUnitBy(Integer id) {
        for(Unit unit : units) {
            if (unit.dynamicValues.get(ID).equals(id)) {
                return unit;
            }
        }

        return null;
    }

    public Unit getUnitIn(Square square) {
        for (Unit unit : units) {
            if (unit.getLocation().equals(square)) {
                return unit;
            }
        }

        return null;
    }

    public Set<Unit> getAllOf(String type) {
        Set<Unit> toReturn = new HashSet<Unit>();

        for (Unit unit : units) {
            if (unit.getType().equals(type)) {
                toReturn.add(unit);
            }
        }

        return toReturn;
    }

    public class Unit {
        public Map<String, Object> dynamicValues;

        public Unit() {
            dynamicValues = new HashMap<String, Object>();
        }

        public Integer getID() {
            return (Integer)dynamicValues.get(ID);
        }

        public Square getLocation() {
            return (Square)dynamicValues.get(LOCATION);
        }

        public Integer getGold() {
            return (Integer)dynamicValues.get(GOLD);
        }

        public Integer getWood() {
            return (Integer)dynamicValues.get(WOOD);
        }

        public String getType() {
            return (String)dynamicValues.get(TYPE);
        }
    }
}
