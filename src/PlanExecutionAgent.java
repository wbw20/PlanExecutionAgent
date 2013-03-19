import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import planner.Planner;
import planner.Planner.Square;
import planner.steps.Step;
import planner.State;
import planner.Unit;

import edu.cwru.sepia.action.Action;
import edu.cwru.sepia.agent.Agent;
import edu.cwru.sepia.environment.model.history.History.HistoryView;
import edu.cwru.sepia.environment.model.state.ResourceNode.ResourceView;
import edu.cwru.sepia.environment.model.state.State.StateView;
import edu.cwru.sepia.environment.model.state.Unit.UnitView;


public class PlanExecutionAgent extends Agent {

    private static List<Set<Step>> pathToGoalState;
    private static Square destination;

    public PlanExecutionAgent(int playernum) {
        super(playernum);
    }

    @Override
    public Map<Integer, Action> initialStep(StateView stateView, HistoryView arg1) {

        State initial = new State();
        State goal = new State();

        initial.GOLD_AMOUNT = 0;
        initial.WOOD_AMOUNT = 0;

        initial.BOARD_DIMENSION_X = stateView.getXExtent();
        initial.BOARD_DIMENSION_Y = stateView.getYExtent();

        /* Populate our simulated State with Unit clones */
        for(UnitView view : stateView.getAllUnits()) {
            Unit clone = new Unit(view.getID());
            clone.setLocation(new Square(view));
            clone.setType(view.getTemplateView().getName());
            clone.setPayloadSize(0);

            initial.add(clone);
        }

        /* Populate our simulated State with Resource clones */
        for(ResourceView view : stateView.getAllResourceNodes()) {
            Unit clone = new Unit(view.getID());
            clone.setLocation(new Square(view));
            clone.setType(view.getType().toString());
            clone.setPayloadSize(view.getAmountRemaining());
            initial.add(clone);
        }

        goal.GOLD_AMOUNT = 1000;
        goal.WOOD_AMOUNT = 1000;

        Planner planner = new Planner(initial, goal);
        pathToGoalState = planner.findPathToGoal();

        if (pathToGoalState.isEmpty()) {
            System.out.println("No solution exists.");
            System.exit(-1);
        }

//        Map<Integer, Action> moves = new HashMap<Integer, Action>();
//        return moves;
        return null;
    }

    @Override
    public void loadPlayerData(InputStream arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public Map<Integer, Action> middleStep(StateView arg0, HistoryView arg1) {
        Map<Integer, Action> sepiaActions = new HashMap<Integer, Action>();

        System.out.println(pathToGoalState.size());

        if (!pathToGoalState.isEmpty()) {
            Set<Step> stepsToExecute = pathToGoalState.get(0);

            for (Step step : stepsToExecute) {
                for (Integer id : step.getActions().keySet()) {
                    sepiaActions.put(id, step.getActions().get(id));
                }
            }

            pathToGoalState.remove(0);
        } else {
            System.exit(0);
        }
        
        System.out.println(sepiaActions);

        return sepiaActions;
    }

    @Override
    public void savePlayerData(OutputStream arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void terminalStep(StateView arg0, HistoryView arg1) {
        // TODO Auto-generated method stub

    }

}
