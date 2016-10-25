package utils;

import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.api.webwalk.INodeRouteFinder;
import org.osbot.rs07.input.mouse.MouseDestination;
import org.osbot.rs07.input.mouse.RectangleDestination;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.event.WebWalkEvent;
import org.osbot.rs07.utility.Condition;


import static org.osbot.rs07.script.MethodProvider.gRandom;

public class util {

    public static INodeRouteFinder routeFinder;

    public util() {

    }

    public static void chtc(final Script sA) throws InterruptedException {

        final MouseDestination clickHereToContinue = new RectangleDestination(sA.getBot(),117, 443, 375, 12);

        MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        sA.getMouse().click(clickHereToContinue);
        MethodProvider.sleep(gRandom(1000, 100));
    }

    public static void webWalkEvent(final INodeRouteFinder routeFinder, final Position position, final int threshold, final Script script) throws InterruptedException
    {

        WebWalkEvent event = new WebWalkEvent(routeFinder, position);
        event.setBreakCondition(new Condition()
        {
            @Override
            public boolean evaluate()
            {
                return script.getMap().distance(position) <= threshold;
            }
        });

        script.execute(event);

        do {
            MethodProvider.sleep(MethodProvider.gRandom(1500, 200));
        }
        while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());
    }

    public static void openTab(final Tab tab, final Script sA) throws InterruptedException
    {
        do {
            MethodProvider.sleep(MethodProvider.gRandom(800, 200));
            sA.getTabs().open(tab);
            MethodProvider.sleep(MethodProvider.gRandom(1750, 100));
        } while(sA.getTabs().getOpen()!= tab);
    }

    public static void AntiBan(final Script sA) throws InterruptedException {
        Tab openBeforeAntiBan = sA.tabs.getOpen();

        switch (MethodProvider.random(1, 30)) {
            case 1:
                sA.camera.moveYaw(50 + (MethodProvider.random(1, 30)));
                MethodProvider.sleep(gRandom(600, 1300));
                break;
            case 2:
                sA.camera.movePitch(100 + (MethodProvider.random(1, 70)));
                MethodProvider.sleep(gRandom(600, 1300));
                break;
            case 3:
                sA.tabs.open(Tab.SKILLS);
                MethodProvider.sleep(gRandom(600, 1300));
                break;
            case 4:
                sA.tabs.open(Tab.ATTACK);
                MethodProvider.sleep(gRandom(600, 1300));
                break;
            case 5:
                sA.tabs.open(Tab.QUEST);
                MethodProvider.sleep(gRandom(600, 1300));
                break;
            case 6:
                sA.camera.toTop();
                MethodProvider.sleep(gRandom(600, 1300));
                break;
            case 7:
                sA.camera.toBottom();
                MethodProvider.sleep(gRandom(600, 1300));
                break;
            case 8:
                sA.tabs.open(Tab.CLANCHAT);
                MethodProvider.sleep(gRandom(600, 1300));
                break;
            case 9:
                sA.tabs.open(Tab.FRIENDS);
                MethodProvider.sleep(gRandom(600, 1300));
                break;
            case 10:
                sA.log("AntiBan sleep");
                MethodProvider.sleep(MethodProvider.random(5000, 12000));
                break;
        }

        MethodProvider.sleep(MethodProvider.random(700, 1300));
        openTab(openBeforeAntiBan, sA); //RETURNS TO THE  TAB AFTER EVERY ANTIBAN INSTANCE
    }

}
