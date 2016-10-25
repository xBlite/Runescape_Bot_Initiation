package subnodes;

import core.Node;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.script.MethodProvider;
import utils.util;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.script.Script;


public class _2SurvivalSolver extends Node {

    public _2SurvivalSolver(Script sA) {
        super(sA);
    }

    @Override
    public String status() {
        return "Survival Instructor";
    }

    @Override
    public boolean validate() throws InterruptedException {
        return new Area(3098, 3108, 3099, 3106).contains(script.myPlayer());
    }

    @Override
    public boolean execute() throws InterruptedException {

        long startTime = script.getClient().gameClockMs();
        NPC SurvExpert;

        script.log("Walk to Survival Expert");
        util.webWalkEvent(util.routeFinder, new Position(3103 + MethodProvider.random(1), 3098 + MethodProvider.random(1), 0), 2, script);

        script.log("Talk to Survivalist");
        SurvExpert = script.getNpcs().closest(3306);
        script.camera.toEntity(SurvExpert);
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        SurvExpert.interact("Talk-to");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());

        script.log("Dialogue");
        while (!script.getDialogues().inDialogue()) {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        }
        while (script.getDialogues().isPendingContinuation()) {
            script.getKeyboard().typeString(" ");
            MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        }

        script.log("Open Inventory");
        util.openTab(Tab.INVENTORY, script);

        script.log("Hit a Tree");
        Entity tree = script.getObjects().closest(9730);
        script.camera.toEntity(tree);
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        tree.interact("Chop down");
        do
        {
            MethodProvider.sleep(MethodProvider.gRandom(3000, 100));
        }
        while(!script.inventory.contains("Logs")|| script.myPlayer().isAnimating());

        script.log("Dialogue");
        while (!script.getDialogues().inDialogue()) {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        }
        while (script.getDialogues().isPendingContinuation()) {
            script.getKeyboard().typeString(" ");
            MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        }

        script.log("Light a fire");
        script.inventory.interact("Use", "TinderBox");
        MethodProvider.sleep(MethodProvider.gRandom(4000, 600));
        script.inventory.interact("Use", "Logs");
        MethodProvider.sleep(MethodProvider.gRandom(4000, 600));

        script.log("Open Skills Tab");
        util.openTab(Tab.SKILLS, script);

        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        script.log("Talk to Instructor");
        SurvExpert.interact("Talk-to");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving());

        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        while (!script.getDialogues().inDialogue()) {
            script.log("waiting for dialogue");
            MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        }
        while (script.getDialogues().isPendingContinuation()) {
            script.log("dialogue");
            script.getKeyboard().typeString(" ");
            MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        }

        script.log("Open Inventory");
        util.openTab(Tab.INVENTORY, script);

        script.log("Fishing");
        Entity shrimpPool = script.getNpcs().closest("Fishing spot");
        script.camera.toEntity(shrimpPool);
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        shrimpPool.interact("Net");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());

        script.log("Fishing some more");
        shrimpPool.interact("Net");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        }
        while (script.myPlayer().isAnimating());

        script.log("Open Inventory");
        util.openTab(Tab.INVENTORY, script);

        script.log("Get Fire");
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        Entity fire = script.getObjects().closest(26185);
        while(fire.getDefinition() == null)
        {
            tree = script.getObjects().closest(9730);
            tree.interact("Chop");
            do {
                MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
            }
            while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());

            //check if standing on an existing fire
            //move to another location and repeat
            MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
            script.inventory.interact("Use", "TinderBox");
            do {
                MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
            }
            while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());

            script.inventory.interact("Use", "Logs");
            do {
                MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
            }
            while (script.myPlayer().isAnimating());

            MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
            fire = script.getObjects().closest(26185);
            MethodProvider.sleep(MethodProvider.gRandom(2000, 250));
        }

        script.log("Cook Shrimps");
        script.getInventory().interact("Use", "Raw shrimps");
        MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        fire.interact("Use");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(2000, 200));
        }
        while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));

        while(fire.getDefinition() == null)
        {
            script.log("Searching for Fire");
            tree = script.getObjects().closest(9730);
            tree.interact("Chop");
            do {
                MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
            }
            while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());

            //check if standing on an existing fire
            //move to another location and repeat
            MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
            script.inventory.interact("Use", "TinderBox");
            do {
                MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
            }
            while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());

            script.inventory.interact("Use", "Logs");
            do {
                MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
            }
            while (script.myPlayer().isAnimating());

            MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
            fire = script.getObjects().closest(26185);
            MethodProvider.sleep(MethodProvider.gRandom(2000, 250));
        }

        script.log("Cook Shrimps");
        script.getInventory().interact("Use", "Raw shrimps");
        fire.interact("Use");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(2000, 200));
        }
        while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));

        script.log("Walk Away");
        script.camera.toPosition(new Position(3090 + MethodProvider.random(1), 3092 + MethodProvider.random(1), 0));
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        util.webWalkEvent(util.routeFinder, new Position(3090 + MethodProvider.random(1), 3092 + MethodProvider.random(1), 0), 1, script);

        script.log("Leave Area");
        script.getDoorHandler().handleNextObstacle(new Position(3089, 3092, 0));
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());

        script.log(status() + " completed in: " + (script.getClient().gameClockMs() - startTime));

        return true;
    }
}
