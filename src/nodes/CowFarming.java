package nodes;

import core.Node;
import org.osbot.rs07.script.Script;
//import data.Constants;

public class CowFarming extends Node {
    //private Constants c = new Constants();

    public CowFarming(Script sA) {
        super(sA);
    }

    @Override
    public String status() {
        return "Farming Cows";
    }

    @Override
    public boolean validate() throws InterruptedException {
        return false;
    }

    @Override
    public boolean execute() throws InterruptedException {

        /*Outline:
        * Make sure all combat stats are 15 before cows
        * Option to collect cowhides and bank them
        * Option to pick up and bury bones (buries bones once inventory is full)
        * Ensure we continuously train each stat in stages of 5
        *   - Check if on correct combat stance
        *   - Deathwalk (optional)
        */

        return true;
    }
}
