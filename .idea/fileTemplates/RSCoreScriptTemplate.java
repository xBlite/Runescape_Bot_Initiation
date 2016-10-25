package ${PACKAGE_NAME};

//import OSBot files
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

//import nodes that you use
import nodes.Banking;

//import Java utils
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ScriptManifest (name = "${NAME}", author = "xBlite", version = 0.1, info = "${NAME}", logo = "${NAME}")
public class ${NAME} extends Script {
    private String status = "";
    private List<Node> nodes = new ArrayList<>();
    

    @Override
    public void onStart() {
        Collections.addAll(nodes,
                new Banking(this)
        );
    }


    @Override
    public int onLoop() throws InterruptedException {
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