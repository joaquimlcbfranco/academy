package com.ctw.workstation.learning.simple;

import com.ctw.workstation.dto.TeamDTO;
import io.quarkus.logging.Log;

public class ExternalMessageServiceImpl implements ExternalMessageService {

    @Override
    public String sayHelloFromOuterSpace(String name) {
            doSomething();
            return "Hello from outer space " + name;
    }

    @Override
    public String sayHelloFromOuterSpace() {
//        Log.info("Hello from outer space");
        return "Hello from outer space";
    }

    @Override
    public void doSomething() {
        System.out.println("do something");
//        throw new IllegalAccessException();
    }

    @Override
    public void doSomething(TeamDTO teamDTO) {

    }
}
