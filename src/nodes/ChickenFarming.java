package nodes;

import core.Node;

import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.MethodProvider;
//import data.Constants;

public class ChickenFarming extends Node {

    public ChickenFarming(Script script) {
        super(script);
    }

    @Override
    public String status() {
        return "Farming Chickens";
    }

    @Override
    public boolean validate() throws InterruptedException {
        return false;
    }

    @Override
    public boolean execute() throws InterruptedException {

        /*Outline:
        * Option to pickup feathers to sell for starter money
        * Option to pick up and bury bones (buries bones once inventory is full)
        * Ensure we train each stat to 20 (attack to 20, str to 20, def to 20 in order)
        *   - Check if on correct combat stance
        *   - Deathwalk (optional)
        */


        while (script.getTabs().getOpen() != Tab.IGNORES)
        {

            MethodProvider.sleep(MethodProvider.gRandom(2000, 100));

            if(script.getTabs().getOpen() == Tab.ATTACK) {
                script.log("Fighting variables");
                script.log(script.getClient().gameClockMs()/1000);
                script.log("Combat Time: " + script.myPlayer().getCombatTime());
                if(script.myPlayer().getInteracting()!=null) {
                    script.log("Interacting with: " + script.myPlayer().getInteracting().toString());
                    script.log("Attacker Animation:" + script.myPlayer().getInteracting().getAnimation());
                    script.log("EnemyIsUnderAttack:" + script.myPlayer().getInteracting().isUnderAttack());
                    script.log("EnemyHealthPercent:" + script.myPlayer().getInteracting().getHealthPercent());
                }
                script.log("isFighting(): " + script.getCombat().isFighting());
            }

            if(script.getTabs().getOpen() == Tab.LOGOUT) {
                script.log("Dialogue variables");
                script.log(script.getClient().gameClockMs()/1000);
                script.log("isPendingContinuation: " + script.getDialogues().isPendingContinuation());
                script.log("isPendingOption: " + script.getDialogues().isPendingOption());
                script.log("inDialogue: " + script.getDialogues().inDialogue());
            }

        }

        return true;
    }
}
