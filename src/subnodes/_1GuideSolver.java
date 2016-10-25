package subnodes;

import core.Node;
import utils.util;

import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.input.mouse.RectangleDestination;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.script.Script;

public class _1GuideSolver extends Node {
    private final Area STARTING_AREA = new Area(3092, 3110, 3096, 3105);

    public _1GuideSolver(Script sA) {
        super(sA);
    }

    @Override
    public String status() {
        return "Runescape Guide";
    }

    @Override
    public boolean validate() throws InterruptedException {
        return STARTING_AREA.contains(script.myPlayer());
    }

    @Override
    public boolean execute() throws InterruptedException {

        long startTime = script.getClient().gameClockMs();
        script.getMouse().setDefaultPaintEnabled(true);

        //randomized design
        script.log("Randomizing Character");
        script.log("If character doesn't need to be randomized");
        script.log("It will click 3 times randomly then continue as normal");
        for(int a = 0;a<20;a++) {
            double xrng = Math.random(), yrng = Math.random();

            script.mouse.click((int)(xrng * 44 + 144),(int)(yrng * 245 + 44), false);
            if(script.myPlayer().isAnimating()|| script.myPlayer().isMoving()) {
                a = 21;
                break;
            }
            MethodProvider.sleep(MethodProvider.gRandom(500, 50));
        }

        //randomized color
        for(int a = 0;a<20;a++){
            double x = Math.random(), y = Math.random();

            script.mouse.click((int)(x * 44 + 444),(int)(y * 175 + 78), false);
            if(script.myPlayer().isAnimating()|| script.myPlayer().isMoving()) {
                a = 21;
                break;
            }
            MethodProvider.sleep(MethodProvider.gRandom(500, 50));
         }
        //accept
        MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        script.getMouse().click(new RectangleDestination(script.getBot(), 228, 270, 62, 27));

        NPC RSGuide = script.getNpcs().closest(STARTING_AREA, "Runescape Guide");

        script.log("Talk to Guide");
        script.camera.toEntity(RSGuide);
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        RSGuide.interact("Talk-to");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving());

        MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        while (script.getDialogues().isPendingContinuation()) {
            if (script.getDialogues().clickContinue()) ;
            {
                MethodProvider.sleep(MethodProvider.gRandom(1500, 200));
            }
        }

        script.log("Open Settings");
        util.openTab(Tab.SETTINGS, script);

        script.log("Talk to Guide");
        RSGuide.interact("Talk-to");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving());

        while (script.getDialogues().isPendingContinuation()) {
            if (script.getDialogues().clickContinue()) ; {
                MethodProvider.sleep(MethodProvider.gRandom(1500, 200));
            }
        }

        //turn off music
        script.log("Turn off music");
        MethodProvider.sleep(MethodProvider.gRandom(2000, 200));
        script.getMouse().click(new RectangleDestination(script.getBot(), 604, 211, 30, 31));
        MethodProvider.sleep(MethodProvider.gRandom(2000, 200));
        script.getMouse().click(new RectangleDestination(script.getBot(), 602, 283, 14, 8));
        MethodProvider.sleep(MethodProvider.gRandom(2000, 200));
        script.getMouse().click(new RectangleDestination(script.getBot(), 602, 331, 14, 9));
        MethodProvider.sleep(MethodProvider.gRandom(2000, 200));

        script.log("Exiting through door");
        script.camera.toPosition(new Position(3098, 3107, 0));
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        do {
            script.getDoorHandler().handleNextObstacle(new Position(3098, 3107, 0));
            do {
                MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
            }
            while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());
            MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        }while(!new Area(3098, 3108, 3099, 3106).contains(script.myPlayer()));

        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));

        script.log(status() + " completed in: " + (script.getClient().gameClockMs() - startTime));

        return true;
    }
}
