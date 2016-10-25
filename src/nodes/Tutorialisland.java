package nodes;
import core.Node;

//osbot
import org.osbot.rs07.script.Script;

//subnodes
import subnodes.*;

//Java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Tutorialisland extends Node {
    private List<Node> subnodes = new ArrayList<>();
    private String status = "Tutorial Island: ";

    public Tutorialisland(Script sA) {
        super(sA);
    }

    @Override
    public String status() {
        return status;
    }

    @Override
    public boolean validate() throws InterruptedException {
        return new _1GuideSolver(script).validate()||
                new _2SurvivalSolver(script).validate()||
                new _3ChefSolver(script).validate()||
                new _4QuestSolver(script).validate()||
                new _5MiningSolver(script).validate()||
                new _6CombatSolver(script).validate()||
                new _7FinancialSolver(script).validate()||
                new _8PrayerSolver(script).validate()||
                new _9MagicSolver(script).validate();

    }

    @Override
    public boolean execute() throws InterruptedException {
        script.log("Executing Tutorial Island");
        long startTime = script.getClient().gameClockMs();

        //import the solver for each instructor
        Collections.addAll(subnodes,
                new _1GuideSolver(script),
                new _2SurvivalSolver(script),
                new _3ChefSolver(script),
                new _4QuestSolver(script),
                new _5MiningSolver(script),
                new _6CombatSolver(script),
                new _7FinancialSolver(script),
                new _8PrayerSolver(script),
                new _9MagicSolver(script)
        );


        for(Node n : subnodes) {
            if(n.validate()) {
                status += n.status();
                script.log(status);
                if(n.execute()){
                    status = "Tutorial Island: ";
                    continue;
                }
            }
        }

        script.log(status() + " completed in: " + (script.getClient().gameClockMs() - startTime));

        return true;
    }
}
