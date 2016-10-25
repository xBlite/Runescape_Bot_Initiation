package subnodes;

import core.Node;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.input.mouse.RectangleDestination;
import org.osbot.rs07.script.MethodProvider;
import utils.util;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.script.Script;

public class _3ChefSolver extends Node {

    public _3ChefSolver(Script sA) {
        super(sA);
    }

    @Override
    public String status() {
        return "Cooking Instructor";
    }

    @Override
    public boolean validate() throws InterruptedException {
        return new Area(3088, 3093, 3089, 3090).contains(script.myPlayer());
    }


    @Override
    public boolean execute() throws InterruptedException {

        long startTime = script.getClient().gameClockMs();
        NPC Chef;

        script.camera.toPosition(new Position(3079 + MethodProvider.random(1), 3084 + MethodProvider.gRandom(3,2), 0));
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        util.webWalkEvent(util.routeFinder, new Position(3079 + MethodProvider.random(1), 3084 + MethodProvider.gRandom(3,2), 0), 1, script);

        script.log("Open Door");
        script.getDoorHandler().handleNextObstacle(new Position(3078, 3084, 0));
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving()|| script.myPlayer().isAnimating());

        Chef = script.getNpcs().closest(3305);
        script.camera.toEntity(Chef);
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        Chef.interact("talk-to");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());

        while (!script.getDialogues().inDialogue()) {
            script.log("waiting for dialogue");
            MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        }
        while (script.getDialogues().isPendingContinuation()) {
            script.log("dialogue");
            script.getKeyboard().typeString(" ");
            MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        }

        script.log("Make Dough");
        script.getInventory().interact("Use", "Bucket of water");
        script.getInventory().interact("Use", "Pot of flour");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        }
        while (script.myPlayer().isAnimating());

        script.log("Make Bread");
        script.getInventory().interact("Use", "Bread dough");
        script.camera.toEntity(script.getObjects().closest(9736));
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        script.getObjects().closest(9736).interact("Use");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        }
        while (script.myPlayer().isAnimating());



        script.log("Open Music Tab");
        util.openTab(Tab.MUSIC, script);

        script.camera.toPosition(new Position(3073, 3090, 0));
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        util.webWalkEvent(util.routeFinder, new Position(3073, 3090, 0), 1, script);

        script.log("Exit door");
        script.getDoorHandler().handleNextObstacle(new Position(3072, 3090,0));
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());


        script.log("Open Emotes Tab");
        util.openTab(Tab.EMOTES, script);
        script.log("Emote");
        script.getMouse().click(new RectangleDestination(script.getBot(),562, 217, 145, 36));
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        }
        while (script.myPlayer().isAnimating());

        MethodProvider.sleep(MethodProvider.gRandom(4000, 100));
        script.log("Open Settings and Run");
        util.openTab(Tab.SETTINGS, script);
        script.settings.setRunning(false);
        MethodProvider.sleep(MethodProvider.gRandom(1500, 100));
        script.settings.setRunning(true);

        script.camera.toPosition(new Position(3086, 3126, 0));
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        util.webWalkEvent(util.routeFinder, new Position(3086, 3126, 0), 1, script);

        script.getObjects().closest(9716).interact("Open");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(2000, 200));
        }
        while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());

        script.log(status() + " completed in: " + (script.getClient().gameClockMs() - startTime));

        return true;
    }
}
