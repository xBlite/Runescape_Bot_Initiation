package subnodes;

import core.Node;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.input.mouse.RectangleDestination;
import org.osbot.rs07.script.MethodProvider;
import utils.util;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.script.Script;

public class _7FinancialSolver extends Node {

    public _7FinancialSolver(Script sA) {
        super(sA);
    }

    @Override
    public String status() {
        return "Financial Instructor";
    }

    @Override
    public boolean validate() throws InterruptedException {
        return new Area(3110, 3127, 3112, 3125).contains(script.myPlayer()); //position should be (3111, 3125, 0)
    }

    @Override
    public boolean execute() throws InterruptedException {

        long startTime = script.getClient().gameClockMs();
        //Walk to bank
        script.log("Walk to bank entrance");
        util.webWalkEvent(util.routeFinder, new Position(3121+ MethodProvider.random(2), 3118+ MethodProvider.random(2), 0), 2, script);

        MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        script.log("Check Doors");
        if(script.objects.closest("Large door").hasAction("Open")){
            script.log("Open Large Doors");
            script.getDoorHandler().handleNextObstacle(new Position(3121, 3119, 0));
            do {
                MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
            }
            while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());
        }

        //Access Bank Account
        //script.camera.toEntity(script.getObjects().closest(10083));
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        script.log("Bank");
        script.getObjects().closest("Bank booth").interact("Use");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(3000, 200));
        }
        while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());

        //Talk to Banker
        MethodProvider.sleep(MethodProvider.gRandom(3000, 100));

        script.log("Dialogue");
        MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        script.log("clickToContinue");
        util.chtc(script);
        MethodProvider.sleep(MethodProvider.gRandom(2000, 200));

        script.log("Click Yes");
        script.getDialogues().selectOption("Yes");
        MethodProvider.sleep(MethodProvider.gRandom(2000, 200));

        //Exit Tab
        script.log("Exit Bank Tab");
        do {
            script.getBank().close();
            MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        } while(script.getBank().isOpen());
        //script.getMouse().click(new RectangleDestination(script.getBot(), 479, 15, 12, 13));

        //Poll Booth
        script.log("Open Poll Booth");
        script.camera.toEntity(script.getObjects().closest(26815));
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        script.getObjects().closest("Poll booth").interact("Use");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());

        MethodProvider.sleep(MethodProvider.gRandom(4000, 100));
//        while (script.getDialogues().isPendingContinuation()) {
//            script.log("Dialogue");
//        if (script.getDialogues().clickContinue());
//        else
//            util.chtc(script);
//        MethodProvider.sleep(MethodProvider.gRandom(1500, 200));
//        }
        for(int a =0;a<3;++a) {
            script.log("Dialogue");
            if (script.getDialogues().clickContinue());
            else
                util.chtc(script);
            MethodProvider.sleep(MethodProvider.gRandom(1500, 200));
        }


        //Exit pool booth
        script.log("Exiting Poll Booth");
        script.getMouse().click(new RectangleDestination(script.getBot(), 484, 19, 16, 13));
        MethodProvider.sleep(MethodProvider.gRandom(1000, 100));

        util.webWalkEvent(util.routeFinder, new Position(3124, 3124, 0), 1, script);

        script.log("Open Door");
        do {
        script.getDoorHandler().handleNextObstacle(new Position(3125, 3124, 0));
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());

        util.webWalkEvent(util.routeFinder, new Position(3127, 3123, 0), 0, script);

        script.log("Talk to Grandpa");
        script.camera.toTop();
        MethodProvider.sleep(MethodProvider.gRandom(4000, 100));
        script.getNpcs().closest(3310).interact("Talk-to");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());

        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
//        while (script.getDialogues().isPendingContinuation()) {
//            script.log("Dialogue");
//            if (script.getDialogues().clickContinue()) ;
//            {
//                MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
//            }
//        }

        for(int a = 0;a<10;++a)
        {
            script.log("Dialogue");
            util.chtc(script);
        }
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));

        util.webWalkEvent(util.routeFinder, new Position( 3129, 3124, 0), 1, script);

        script.log("Exit Door");
        while(!new Area(3130, 3125, 3131, 3123).contains(script.myPlayer())) {
            //script.getDoorHandler().handleNextObstacle(new Position(3130, 3124, 0));
            script.log("Exit door");
            script.getDoorHandler().getNextObstacle(new Position(3130, 3124, 0)).interact("Open");
            do {
                MethodProvider.sleep(MethodProvider.gRandom(2000, 200));
            }
            while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());
            MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        }

        script.log(status() + " completed in: " + (script.getClient().gameClockMs() - startTime));

        return true;
    }
}
