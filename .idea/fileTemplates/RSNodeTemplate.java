package ${PACKAGE_NAME};

import core.Node;
import org.osbot.rs07.script.Script;

public class ${NAME} extends Node {
    
    public ${NAME}(Script sA) {
        super(sA);
    }

    @Override
    public String status() {
        return "${NAME}";
    }

    @Override
    public boolean validate() throws InterruptedException {
        return false;
    }

    @Override
    public boolean execute() throws InterruptedException {
        return true;
    }
}
