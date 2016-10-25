package core;
/*
*TODO:
* - Implement Configs to check where we are in tutorial island.  Use main ones to find which subnode
*       then a switch to determine which subroutine of that part of the quest.
*  - Implement Configs using onConfig for subnodes.  Then subnodes checking the config for subroutines.
*
*/

//import OSBot files
import org.osbot.rs07.api.webwalk.INodeRouteFinder;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

//import nodes that you use
import nodes.ChickenFarming;
import nodes.CowFarming;
import nodes.Tutorialisland;
import utils.util;

//import Java utils
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ScriptManifest (name = "BotInitiation", author = "xBlite", version = 0.1, info = "BotInitiation", logo = "BotInitiation")
public class BotInitiation extends Script {

    private String status = "";
    private List<Node> nodes = new ArrayList<>();

    @Override
    public void onStart() {

        util.routeFinder = INodeRouteFinder.createAdvanced();

        Collections.addAll(nodes,
                new Tutorialisland(this),
                new ChickenFarming(this),
                new CowFarming(this)
        );

    }

    @Override
    public int onLoop() throws InterruptedException {
     //things to check while looping
     //ensure game is in static size mode
     //smart breaks / antiban (length 2 - 10 minutes)

    for(Node n : nodes) {
        if(n.validate()) {
            status = n.status();
            if(n.execute()){
                return random(200,400);
            }
        }
    }

    return random(500, 700);
}
}