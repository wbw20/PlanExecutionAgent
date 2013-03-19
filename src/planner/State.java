package planner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import planner.Planner.Square;

public class State {

    /* State Attributes */
    public static Integer BOARD_DIMENSION_X;
    public static Integer BOARD_DIMENSION_Y;

    public Integer WOOD_AMOUNT;
    public Integer GOLD_AMOUNT;

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
            if (unit.dynamicValues.get(Unit.ID).equals(id)) {
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

    public Set<Square> getEmptyTiles() {
        Set<Square> occupiedSquares = new HashSet<Square>();
        Set<Square> unnoccupiedSquares = new HashSet<Square>();

        for (Unit unit : getUnits()) {
            occupiedSquares.add(unit.getLocation());
        }

        for (int x = 0; x < BOARD_DIMENSION_X; x++) {
            for (int y = 0; y < BOARD_DIMENSION_Y; y++) {
                Square square = new Square(x, y);

                if (!occupiedSquares.contains(square)) {
                    unnoccupiedSquares.add(square);
                }
            }
        }

        return unnoccupiedSquares;
    }
}
