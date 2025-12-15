package com.ctw.workstation.learning.simple;

import com.ctw.workstation.dto.TeamDTO;

public interface ExternalMessageService {

    String sayHelloFromOuterSpace(String name);

    String sayHelloFromOuterSpace();

    void doSomething();

    void doSomething(TeamDTO teamDTO);
}
