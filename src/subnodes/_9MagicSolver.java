package subnodes;

import core.Node;


import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.input.mouse.RectangleDestination;
import org.osbot.rs07.script.MethodProvider;
import utils.util;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.script.Script;

public class _9MagicSolver extends Node {

    public _9MagicSolver(Script sA) {
        super(sA);
    }

    @Override
    public String status() {
        return "Magic Instructor";
    }

    @Override
    public boolean validate() throws InterruptedException {
        return new Area(3121, 3102, 3123, 3101).contains(script.myPlayer()); //position should be (3122, 3102, 0)
    }

    @Override
    public boolean execute() throws InterruptedException {

        long startTime = script.getClient().gameClockMs();

        script.camera.toPosition(new Position(3133+ MethodProvider.random(3), 3088+ MethodProvider.random(3), 0));
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        util.webWalkEvent(util.routeFinder, new Position(3140, 3087, 0), 1, script);

        script.camera.toEntity(script.getNpcs().closest(3309));
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        script.log("Talk to crazy guy with a chicken pen in his house");
        script.getNpcs().closest(3309).interact("Talk-to");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));

        for(int a =0;a<2;++a) {
            script.log("dialogue");
            script.getKeyboard().typeString(" ");
            MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        }

        script.log("Cast spells");
        util.openTab(Tab.MAGIC, script);
        MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        script.getNpcs().closest(3309).interact("Talk-to");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        for(int a =0;a<2;++a) {
            script.log("dialogue");
            script.getKeyboard().typeString(" ");
            MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        }

        script.log("Click on spell");
        script.getMouse().click(new RectangleDestination(script.getBot(), 588, 223, 18, 18));
        MethodProvider.sleep(MethodProvider.gRandom(1000, 100));

        script.camera.toTop();
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        util.webWalkEvent(util.routeFinder, new Position(3139, 3091, 0), 1, script);

        script.log("Find a chicken");
        script.npcs.closest(new Filter<NPC>() {
            @Override
            public boolean match(NPC npc) {
                return (npc.getName().equals("Chicken") && !npc.isUnderAttack());
            }
        }).interact("Cast");
        MethodProvider.sleep(MethodProvider.gRandom(8000, 100));


        script.log("Leave these games for the weak");
        script.getNpcs().closest(3309).interact("Talk-to");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving());


        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        script.log("dialogue");
        script.getKeyboard().typeString(" ");
        MethodProvider.sleep(MethodProvider.gRandom(1000, 100));


        while (script.getDialogues().isPendingOption()) {
            script.log("dialogue");
            script.getKeyboard().typeString("1");
            MethodProvider.sleep(MethodProvider.gRandom(500, 100));
        }

        //script.getMouse().click(new RectangleDestination(script.getBot(), 40, 394, 451, 12));
        //MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        for(int a = 0;a<6;++a) {
            script.log("dialogue");
            script.getKeyboard().typeString(" ");
            MethodProvider.sleep(MethodProvider.gRandom(500, 100));
        }

        script.log(status() + " completed in: " + (script.getClient().gameClockMs() - startTime));

        return true;
    }
}
