package com.ctw.workstation.learning.simple;

import com.ctw.workstation.dto.TeamDTO;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

public class HelloExtAcademy {

    ExternalMessageService externalMessageService;

    public HelloExtAcademy(ExternalMessageService externalMessageService) {
        this.externalMessageService = externalMessageService;
    }

    public HelloExtAcademy() {}

    public String sayHello(String name) {
        if (name != null) {
            System.out.println("Vai chamar o externalMessageService.sayHelloFromOuterSpace");
            try {
                return externalMessageService.sayHelloFromOuterSpace(name);
            } catch (Exception e) {
                return "Hello from outer space, but there was an error";
            }
        }

        return externalMessageService.sayHelloFromOuterSpace();
    }
}
