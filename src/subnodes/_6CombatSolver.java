package subnodes;

import core.Node;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.ui.EquipmentSlot;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.input.mouse.RectangleDestination;
import org.osbot.rs07.script.MethodProvider;
import utils.util;
import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.script.Script;

public class _6CombatSolver extends Node {

    public _6CombatSolver(Script sA) {
        super(sA);
    }

    @Override
    public String status() { return "Combat Instructor";
    }

    @Override
    public boolean validate() throws InterruptedException {
        return new Area(3095, 9502, 3096, 9503).contains(script.myPlayer()) ; //position should be (3095, 9503, 0) or (3094, 9502, 0)
    }

    @Override
    public boolean execute() throws InterruptedException {

        long startTime = script.getClient().gameClockMs();
        NPC combatInstr;

        //walk near instructor
        util.webWalkEvent(util.routeFinder, new Position(3105 + MethodProvider.random(2),9508 + MethodProvider.random(2), 0), 2, script);

        //talk to instructor
        script.log("Talk to Instructor");
        script.camera.toEntity(script.getNpcs().closest(3307));
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        combatInstr = script.getNpcs().closest(3307);
        combatInstr.interact("Talk-to");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());

        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
//        do {
//            script.log("Dialogue");
//            util.chtc(script);
//        } while (script.getDialogues().isPendingContinuation());
        for(int a = 0;a<4;a++) {
            script.log("Dialogue");
            util.chtc(script);
            MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        }
//        while (script.getDialogues().isPendingContinuation()) {
//            script.log("Dialogue");
//            if (script.getDialogues().clickContinue())
//            {
//                MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
//            }
//            else
//            {
//                script.getDialogues().selectOption("Click here to continue");
//            }
//            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
//        }

        //Open Equipment Interface and equip dagger
        script.log("Open Equipment Tab");
        util.openTab(Tab.EQUIPMENT, script);
        script.getMouse().click(new RectangleDestination(script.getBot(), 572, 416, 33, 31));
        MethodProvider.sleep(MethodProvider.gRandom(3000, 100));
        script.inventory.interact("Wield", "Bronze dagger");
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));

        //Talk to instructor
        combatInstr.interact("Talk-to");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());

//        do {
        for(int a = 0;a<2;++a) {
            script.log("Dialogue");
            util.chtc(script);
        }
//        } while (script.getDialogues().isPendingContinuation());

        script.log("Remove Dagger");
        //Open Equipment Tab
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        util.openTab(Tab.EQUIPMENT, script);
        //Remove Bronze Dagger
        if(EquipmentSlot.WEAPON.name().equals("Bronze dagger")) {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
            script.getEquipment().interact(EquipmentSlot.WEAPON, "Remove");
        }
        MethodProvider.sleep(MethodProvider.gRandom(1000, 100));

        script.log("Open Inventory");
        //Open Inventory
        util.openTab(Tab.INVENTORY, script);
        MethodProvider.sleep(MethodProvider.gRandom(2500, 100));
        //Equip Sword
        script.log("Equip Sword");
        if(!EquipmentSlot.WEAPON.name().equals("Bronze sword"))
            script.getEquipment().equip(EquipmentSlot.WEAPON, "Bronze sword");
        MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        //Equip Shield
        script.log("Equip Shield");
        if(!EquipmentSlot.SHIELD.name().equals("Wooden shield"))
            script.getEquipment().equip(EquipmentSlot.SHIELD, "Wooden shield");
        MethodProvider.sleep(MethodProvider.gRandom(1000, 100));

        //Talk to instructor
        script.log("Talk to instructor");
        combatInstr.interact("Talk-to");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());
        MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
//        do {
        for(int a =0;a<4;++a) {
            script.log("Dialogue");
            util.chtc(script);
        }
//        } while (script.getDialogues().isPendingContinuation());

        //Open Combat Skills
        script.log("Open Combat Skills");
        util.openTab(Tab.ATTACK, script);

        //Walk to pit gate
        script.log("Walking to Gate");
        util.webWalkEvent(util.routeFinder, new Position(3111, 9518, 0), 1, script);

        script.log("Put me in coach");
        script.getDoorHandler().handleNextObstacle(new Position(3110, 9518, 0));
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));

        //Ensure inside pit
        while(new Area(3111, 9519, 3112, 9518).contains(script.myPlayer()))
        {
            script.getDoorHandler().handleNextObstacle(new Position(3110, 9518, 0));
            do {
                MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
            }
            while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());
            MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        }

        //Get Target
        script.log("Locking on");
        NPC rat = script.npcs.closest(new Filter<NPC>() {
            @Override
            public boolean match(NPC npc) {
                return (npc.getName().equals("Giant rat") && !npc.isUnderAttack());
            }
        });

        script.camera.toEntity(rat);
        MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        script.log("Attack");
        rat.interact("Attack");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving()|| script.myPlayer().isUnderAttack()||rat.isUnderAttack()||rat.getHealthPercent()!=0);

        //Leave pit
        script.log("Take me out coach");
        script.camera.toPosition(new Position(3111, 9518, 0));
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        script.getDoorHandler().handleNextObstacle(new Position(3111, 9518, 0));
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());

        //Ensure outside the pit
        while(!new Area(3111, 9519, 3112, 9518).contains(script.myPlayer()))
        {
            script.getDoorHandler().handleNextObstacle(new Position(3111, 9518, 0));
            do {
                MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
            }
            while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());
            MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        }

        //Talk to instructor
        script.camera.toEntity(combatInstr);
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        combatInstr.interact("Talk-to");
        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());

        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
//       do {
        for(int a =0;a<4;++a) {
           script.log("Dialogue");
           util.chtc(script);
        }
//       } while (script.getDialogues().isPendingContinuation());
        /*for(int a = 0;a<5;++a) {
            script.log("Dialogue");
            util.chtc(script);
        }*/

        //Equip Bow and Arrows
        script.log("Bow and Arrows");
        util.openTab(Tab.INVENTORY, script);
        //Equip Sword
        script.log("Equip Bow");
        if(!EquipmentSlot.WEAPON.name().equals("Shortbow"))
            script.getEquipment().equip(EquipmentSlot.WEAPON, "Shortbow");
        MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        //Equip Shield
        script.log("Equip Arrows");
        if(!EquipmentSlot.ARROWS.name().equals("Bronze arrow"))
            script.getEquipment().equip(EquipmentSlot.ARROWS, "Bronze arrow");

        //Walk to better ranging spot
        script.camera.toPosition(new Position(3109,9513, 0));
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        util.webWalkEvent(util.routeFinder, new Position(3109,9513, 0), 1, script);


        rat = script.npcs.closest(new Filter<NPC>() {
            @Override
            public boolean match(NPC npc) {
                return npc.getName().equalsIgnoreCase("Giant rat") && !npc.isUnderAttack() && npc.getHealthPercent() > 0;
                }
        });

        script.log("Legolas");
        script.camera.toEntity(rat);
        MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        rat.interact("Attack");
        do {
            script.log("Attacking");
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        } while (rat.getHealthPercent()>0|| script.myPlayer().isInteracting(rat)|| script.getCombat().isFighting());


        script.log("Open Inventory");
        //Open Inventory
        util.openTab(Tab.INVENTORY, script);
        //Equip Sword
        script.log("Equip Sword");
        if(!EquipmentSlot.WEAPON.name().equals("Bronze sword"))
            script.getEquipment().equip(EquipmentSlot.WEAPON, "Bronze sword");
        MethodProvider.sleep(MethodProvider.gRandom(1000, 100));
        //Equip Shield
        script.log("Equip Shield");
        if(!EquipmentSlot.SHIELD.name().equals("Wooden shield"))
            script.getEquipment().equip(EquipmentSlot.SHIELD, "Wooden shield");
        MethodProvider.sleep(MethodProvider.gRandom(1000, 100));

        script.log("Walk to Ladder");
        util.webWalkEvent(util.routeFinder, new Position(3111 + MethodProvider.random(1), 9525 + MethodProvider.random(1), 0), 1, script);

        script.log("Climb Up");
        if(script.getObjects().closest(9727).interact("Climb-up")) {
            MethodProvider.sleep(MethodProvider.gRandom(2000, 100));
        }
        else
        {
            script.log("Failed to climb ladder trying again");
            MethodProvider.sleep(MethodProvider.gRandom(4000, 100));
            util.webWalkEvent(util.routeFinder, new Position(3111 + MethodProvider.random(1), 9525 + MethodProvider.random(1), 0), 1, script);
            script.getObjects().closest(9727).interact("Climb-up");
        }

        do {
            MethodProvider.sleep(MethodProvider.gRandom(1000, 200));
        }
        while (script.myPlayer().isMoving() || script.myPlayer().isAnimating());

        script.log(status() + " completed in: " + (script.getClient().gameClockMs() - startTime));
        MethodProvider.sleep(MethodProvider.gRandom(4000, 100));

        return true;
    }
}
