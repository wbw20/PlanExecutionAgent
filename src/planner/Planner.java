package planner;

import java.util.HashSet;
import java.util.Set;

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

	static class Square {
		Integer x;
		Integer y;
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
		
		boolean isAdjacent(Square square) {
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
	
	private static boolean isOccupied(Square square, StateView state) {
		boolean temp = state.isResourceAt(square.x, square.y);
		return temp;
	}
	
}
