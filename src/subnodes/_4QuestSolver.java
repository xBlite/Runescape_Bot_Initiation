package subnodes;

import core.Node;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.script.Script;
import utils.util;

public class _4QuestSolver extends Node {

    public _4QuestSolver(Script sA) {
        super(sA);
    }

    @Override
    public String status() {
        return "Quest Instructor";
    }

    @Override
    public boolean validate() throws InterruptedException {
        return new Area(3088, 3125, 3084, 3124).contains(script.myPlayer()); //position should be (3086, 3126, 0)
    }

    @Override
    public boolean execute() throws InterruptedException {

        long startTime = script.getClient().gameClockMs();
        NPC questGuide = script.getNpcs().closest(3312);

        script.camera.toEntity(questGuide);
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        questGuide.interact("Talk-to");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving());

        while (!script.getDialogues().inDialogue()) {
            script.log("waiting for dialogue");
            MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        }
        while (script.getDialogues().isPendingContinuation()) {
            script.log("dialogue");
            script.getKeyboard().typeString(" ");
            MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        }

        script.log("Open Quest Tab");
        util.openTab(Tab.QUEST, script);

        questGuide.interact("Talk-to");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving());

        while (!script.getDialogues().inDialogue()) {
            script.log("waiting for dialogue");
            MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        }
        while (script.getDialogues().isPendingContinuation()) {
            script.log("dialogue");
            script.getKeyboard().typeString(" ");
            MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        }

        script.log("Climb Ladder");
        script.camera.toTop();
        MethodProvider.sleep(MethodProvider.gRandom(4000, 100));
        script.getObjects().closest(9726).interact("Climb-down");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());

        script.log(status() + " completed in: " + (script.getClient().gameClockMs() - startTime));
        MethodProvider.sleep(MethodProvider.gRandom(6000, 100));

        return true;
    }
}
