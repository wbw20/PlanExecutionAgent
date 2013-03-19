package planner;

import java.util.HashMap;
import java.util.Map;

import planner.Planner.Square;

public class Unit {

    /* Static Keys */
    public final static String ID = "id";
    public final static String WOOD = "wood";
    public final static String GOLD = "gold";
    public final static String LOCATION = "location";
    public final static String PAYLOAD_SIZE = "payload size";
    public final static String PAYLOAD_TYPE = "payload type";
    public final static String TYPE = "type";

    /* Static Values */
    public final static String TOWN_HALL = "TownHall";
    public final static String PEASANT = "Peasant";
    public final static String FOREST = "TREE";
    public final static String GOLD_MINE = "GOLD_MINE";

    public Map<String, Object> dynamicValues;

    public Unit(Integer id) {
        dynamicValues = new HashMap<String, Object>();
        dynamicValues.put(ID, id);
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

    public Integer getPayloadSize() {
        return (Integer)dynamicValues.get(PAYLOAD_SIZE);
    }

    public String getPayloadType() {
        return (String)dynamicValues.get(PAYLOAD_TYPE);
    }

    public String getType() {
        return (String)dynamicValues.get(TYPE);
    }

    public void setLocation(Square square) {
        dynamicValues.put(LOCATION, square);
    }

    public void setGold(Integer gold) {
        dynamicValues.put(GOLD, gold);
    }

    public void setWood(Integer wood) {
        dynamicValues.put(WOOD, wood);
    }

    public void setPayloadSize(Integer size) {
        dynamicValues.put(PAYLOAD_SIZE, size);
    }

    public void setPayloadType(String type) {
        dynamicValues.put(PAYLOAD_TYPE, type);
    }

    public void setType(String type) {
        dynamicValues.put(TYPE, type);
    }
}
