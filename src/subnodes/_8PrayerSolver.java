package subnodes;

import core.Node;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.script.MethodProvider;
import utils.util;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.script.Script;

public class _8PrayerSolver extends Node {

    public _8PrayerSolver(Script sA) {
        super(sA);
    }

    @Override
    public String status() {
        return "Prayer Instructor";
    }

    @Override
    public boolean validate() throws InterruptedException {
        return new Area(3129, 3123, 3131, 3125).contains(script.myPlayer()); //position should be (3130, 3124, 0)
    }

    @Override
    public boolean execute() throws InterruptedException {

        long startTime = script.getClient().gameClockMs();

        util.webWalkEvent(util.routeFinder, new Position(3129, 3106, 0), 2, script);

        script.log("Walk to Large Doors");
        if(script.objects.closest("Large door").hasAction("Open")){
            script.log("Open Large Doors");
            script.getDoorHandler().handleNextObstacle(new Position(3128, 3107, 0));
            do {
                MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
            }
            while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());
        }

        script.log("Talk to Buddha");
        script.camera.toTop();
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        script.getNpcs().closest(3319).interact("Talk-to");
        if(script.objects.closest("Large door").hasAction("Open")){
            script.log("Open Large Doors");
            script.getDoorHandler().handleNextObstacle(new Position(3128, 3107, 0));
            do {
                MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
            }
            while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());
            script.getNpcs().closest(3319).interact("Talk-to");
        }
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving());
        MethodProvider.sleep(MethodProvider.gRandom(1000, 100));

        for(int a =0;a<2;++a) {
            script.log("dialogue");
            script.getKeyboard().typeString(" ");
            MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        }

        MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        script.log("Open Prayer Tab");
        util.openTab(Tab.PRAYER, script);

        script.getNpcs().closest(3319).interact("Talk-to");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving());

        for(int a =0;a<4;++a) {
            script.log("dialogue");
            script.getKeyboard().typeString(" ");
            MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        }

        script.log("View my lack of friends");
        util.openTab(Tab.FRIENDS, script);
        util.openTab(Tab.IGNORES, script);

        script.getNpcs().closest(3319).interact("Talk-to");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving());

        for(int a =0;a<9;++a) {
            script.log("dialogue");
            script.getKeyboard().typeString(" ");
            MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        }

        script.log("Exit through door");
        util.webWalkEvent(util.routeFinder, new Position(3122, 3103, 0), 1, script);

        script.log("Open Door");
        script.getDoorHandler().handleNextObstacle(new Position( 3122, 3102, 0));
        do {
            script.log("Opening");
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());

        script.log(status() + " completed in: " + (script.getClient().gameClockMs() - startTime));
        MethodProvider.sleep(MethodProvider.gRandom(4000, 100));

        return true;
    }
}
