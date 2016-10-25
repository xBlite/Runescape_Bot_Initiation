package subnodes;

import core.Node;


import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.input.mouse.RectangleDestination;
import org.osbot.rs07.script.MethodProvider;
import utils.util;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.script.Script;

public class _5MiningSolver extends Node {

    public _5MiningSolver(Script sA) {
        super(sA);
    }

    @Override
    public String status() {
        return "Mining Instructor";
    }

    @Override
    public boolean validate() throws InterruptedException {
        return new Area(3089, 9521, 3087, 9519).contains(script.myPlayer()); //position should be (3088, 9520, 0)
    }

    @Override
    public boolean execute() throws InterruptedException {

        long startTime = script.getClient().gameClockMs();
        NPC miningInstr;

        script.camera.toPosition(new Position(3081 + MethodProvider.random(3), 9507 +  MethodProvider.random(3) , 0));
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        util.webWalkEvent(util.routeFinder, new Position(3081 + MethodProvider.random(2), 9507 +  MethodProvider.random(2) , 0), 2, script);

        script.log("Talk to Mining Instructor");
        miningInstr = script.getNpcs().closest(3311);
        script.camera.toEntity(miningInstr);
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        miningInstr.interact("Talk-to");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());

        while(!script.getDialogues().inDialogue())
        {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        }
        while (script.getDialogues().isPendingContinuation()) {
            script.getKeyboard().typeString(" ");
            MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        }

        Entity TinRock = script.getObjects().closest(10080);
        script.log("Prospect Tin");
        script.camera.toEntity(TinRock);
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        TinRock.interact("Prospect");
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

        script.log("Walk near copper");
        util.webWalkEvent(util.routeFinder, new Position(3086, 9502, 0), 1, script);

        script.log("Prospect Copper");
        Entity CopperRock = script.getObjects().closest(10079);
        script.camera.toEntity(CopperRock);
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        CopperRock.interact("Prospect");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(6000, 200));
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

        script.log("Talk to Mining Instructor");
        script.camera.toEntity(miningInstr);
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        miningInstr.interact("Talk-to");
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

        script.log("Open inventory");
        util.openTab(Tab.INVENTORY, script);

        TinRock = script.getObjects().closest(10080);
        CopperRock = script.getObjects().closest(10079);

        script.log("Mine Tin");
        script.camera.toEntity(TinRock);
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        TinRock.interact("Mine");
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

        script.log("Walk near copper");
        util.webWalkEvent(util.routeFinder, new Position(3086, 9502, 0), 1, script);
        script.log("Mine Copper");
        script.camera.toEntity(CopperRock);
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        CopperRock = script.getObjects().closest(10079);
        MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        CopperRock.interact("Mine");
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

        util.openTab(Tab.INVENTORY, script);
        script.log("Walk to Furnace");
        util.webWalkEvent(util.routeFinder, new Position(3079, 9497, 0), 1, script);

        script.log("Smelt Tin");
        script.inventory.interact("Use", "Tin ore");
        script.camera.toEntity(script.getObjects().closest(10082));
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        script.getObjects().closest(10082).interact("Use");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());

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

        script.log("Attempt to get rid of bronze bar");
        util.chtc(script);
        util.chtc(script);

        script.log("Talk to Mining Instructor");
        script.camera.toEntity(miningInstr);
        MethodProvider.sleep(MethodProvider.gRandom(4000, 100));
        miningInstr = script.getNpcs().closest(3311);
        if(miningInstr.getDefinition() ==  null)
        {
            script.log("Can't find mining instructor");
            util.webWalkEvent(util.routeFinder, new Position(3081 + MethodProvider.random(1), 9507 +  MethodProvider.random(1) , 0), 2, script);
            miningInstr = script.getNpcs().closest(3311);
            MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        }
        miningInstr.interact("Talk-to");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
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
        MethodProvider.sleep(MethodProvider.gRandom(1000, 100));

        script.log("Smith a Dagger");
        script.inventory.interact("Use", "Bronze bar");
        script.camera.toEntity(script.getObjects().closest(2097));
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        script.getObjects().closest(2097).interact("Use");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());

        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        script.getMouse().click(new RectangleDestination(script.getBot(), 26, 61, 29, 26));
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

        script.camera.toPosition(new Position(3095, 9503, 0));
        MethodProvider.sleep(MethodProvider.gRandom(3000, 100));
        util.webWalkEvent(util.routeFinder, new Position(3093+ MethodProvider.random(2), 9503+ MethodProvider.random(2), 0), 2, script);

        script.log("Leave through gate");
        script.getDoorHandler().handleNextObstacle(new Position( 3095, 9503, 0));
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));

        while(new Area(3094, 9503, 3074, 9496).contains(script.myPlayer())||new Area(3074, 9504, 3091, 9508).contains(script.myPlayer()))
        {
            util.webWalkEvent(util.routeFinder, new Position(3082+MethodProvider.random(2), 9504+MethodProvider.random(2), 0), 2, script);

            util.webWalkEvent(util.routeFinder, new Position(3088+MethodProvider.random(3), 9504+MethodProvider.random(3), 0), 3, script);

            util.webWalkEvent(util.routeFinder, new Position(3093+MethodProvider.random(3), 9503+MethodProvider.random(3), 0), 2, script);

            script.getDoorHandler().handleNextObstacle(new Position( 3095, 9503, 0));
            do {
                MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
            }
            while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());
            MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        }

        script.log(status() + " completed in: " + (script.getClient().gameClockMs() - startTime));

        return true;
    }
}
