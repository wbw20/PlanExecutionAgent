package planner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import planner.State.Unit;
import planner.steps.Deposit;
import planner.steps.HarvestGold;
import planner.steps.HarvestWood;
import planner.steps.MoveTo;
import planner.steps.Step;

import edu.cwru.sepia.environment.model.state.State.StateView;
import edu.cwru.sepia.environment.model.state.Unit.UnitView;
import edu.cwru.sepia.util.Direction;

public class Planner {
    private State initial;
    private State goal;

    public State getInitial() {
        return initial;
    }
    public void setInitial(State initial) {
        this.initial = initial;
    }
    public State getGoal() {
        return goal;
    }
    public void setGoal(State goal) {
        this.goal = goal;
    }

    public List<Step> findPathToGoal() {
        List<Step> steps = new ArrayList<Step>();
        
        
        
        return steps;
    }

    public static class Square {
    	public Integer x;
        public Integer y;
        Integer distance;

        Square(UnitView unit) {
            this.x = unit.getXPosition();
            this.y = unit.getYPosition();
        }

        Square(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }

        Integer costTo(Square square) {
            return Math.abs(square.x - this.x) + Math.abs(square.y - this.y);
        }

        @Override
        public boolean equals(Object square) {
            if (square instanceof Square) {
                return ((Square)square).x == this.x && ((Square)square).y == this.y;
            }
            
            return false;
        }

        @Override
        public String toString() {
            return "[" + x + ", " + y + "]";
        }

        public boolean isAdjacent(Square square) {
            return Math.abs(this.x - square.x) < 2 && Math.abs(this.y - square.y) < 2;
        }

        Direction directionTo(Square square) {
            if (square.x < this.x) {
                if (square.y < this.y) {
                    return Direction.NORTHWEST;
                } else if (square.y > this.y) {
                    return Direction.SOUTHWEST;
                } else {
                    return Direction.WEST;
                }
            } else if (square.x > this.x) {
                if (square.y < this.y) {
                    return Direction.NORTHEAST;
                } else if (square.y > this.y) {
                    return Direction.SOUTHEAST;
                } else {
                    return Direction.EAST;
                }
            } else {
                if (square.y < this.y) {
                    return Direction.NORTH;
                } else if (square.y > this.y) {
                    return Direction.SOUTH;
                } else {
                    return null;
                }
            }
        }

        Set<Square> getAdjacent(StateView state) {
            Set<Square> adjacent = new HashSet<Square>();

            for (int i = -1; i < 2; i++) {
                int newX = this.x + i;

                if (!(newX < 0 || newX > state.getXExtent() || (newX == this.x)) && newX != this.x) {
                    Square adjacentSquare = new Square(newX, this.y);
                    if (!isOccupied(adjacentSquare, state)) {
                        adjacent.add(adjacentSquare);
                    }
                }
            }

            for (int i = -1; i < 2; i++) {
                int newY = this.y + i;

                if (!(newY < 0 || newY > state.getXExtent() || (newY == this.x)) && newY != this.y) {
                    Square adjacentSquare = new Square(this.x, newY);
                    if (!isOccupied(adjacentSquare, state)) {
                        adjacent.add(adjacentSquare);
                    }
                }
            }

            adjacent.add(this);
            return adjacent;
        }
    }

    private static Set<Map<Unit, Step>> getPossibleMoves(State state) {
        Set<Map<Unit, Step>> possibleMoves = new HashSet<Map<Unit, Step>>();

        Set<Unit> peasants = state.getAllOf(State.PEASANT);
        Set<Unit> townhalls = state.getAllOf(State.TOWN_HALL);
        Set<Unit> goldMines = state.getAllOf(State.GOLD_MINE);
        Set<Unit> forests = state.getAllOf(State.FOREST);

        for (Unit peasant : peasants) {
            Set<Step> stepsforPeasant = new HashSet<Step>();

            /* Deposit */
            for (Unit townhall : townhalls) {
                Step deposit = new Deposit(peasant.getID(), state);
                if (deposit.arePrerequisitesMet()) {
                    stepsforPeasant.add(deposit);
                }
            }

            /* Harvest Wood */
            for (Unit forest: forests) {
                Step harvest = new HarvestWood(peasant.getID(), forest.getID(), state);
                if (harvest.arePrerequisitesMet()) {
                    stepsforPeasant.add(harvest);
                }
            }

            /* Harvest Gold */
            for (Unit mine : goldMines) {
                Step harvest = new HarvestGold(peasant.getID(), mine.getID(), state);
                if (harvest.arePrerequisitesMet()) {
                    stepsforPeasant.add(harvest);
                }
            }

            /* Move To */
            for (Square unnoccupied : state.getEmptyTiles()) {
                Step moveTo = new MoveTo(peasant.getID(), unnoccupied, state);
                stepsforPeasant.add(moveTo);
            }
        }

        return possibleMoves;
    }

    private static boolean isOccupied(Square square, StateView state) {
        boolean temp = state.isResourceAt(square.x, square.y);
        return temp;
    }
}
