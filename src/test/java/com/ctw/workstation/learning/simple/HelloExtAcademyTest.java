package com.ctw.workstation.learning.simple;

import com.ctw.workstation.dto.TeamDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HelloExtAcademyTest {

    @Mock
    ExternalMessageService externalMessageService;

    @InjectMocks
    HelloExtAcademy helloExtAcademy;

//    @Test
//    @DisplayName("When providing a valid name, a greeting message from outer space is returned")
//    void when_providing_a_valid_name_greeting_message_from_outer_space_is_returned() {
//        // given
//        ExternalMessageService externalMessageServiceMock = mock(ExternalMessageService.class);
//        // stub
//        String name = "Requim";
//        when(externalMessageServiceMock.sayHelloFromOuterSpace(name))
//                .thenReturn("Hello %s from outer space".formatted(name));
//
//        ArgumentCaptor<TeamDTO> captor = ArgumentCaptor.forClass(TeamDTO.class);
//        HelloExtAcademy helloExtAcademy = new HelloExtAcademy(externalMessageServiceMock);
//
//        // when
//        String actualMessage = helloExtAcademy.sayHello(name);
//        // then
//        assertThat(actualMessage)
//                .as("When %s name is provided, a greeting message from outer space is returned", name)
//                .isEqualTo("Hello %s from outer space".formatted(name));
//
//        verify(externalMessageServiceMock, times(2)).doSomething();
//        verify(externalMessageServiceMock, times(1)).doSomething(captor.capture());
//
//        TeamDTO dto = captor.getValue();
//        assertThat(dto)
//                .hasNoNullFieldsOrProperties();
//
//    }


    @Test
    @DisplayName("When providing a valid name, a greeting message from outer space is returned")
    void when_providing_a_valid_name_greeting_message_from_outer_space_is_returned() {
        // given
        String name = "Requim";

        when(externalMessageService.sayHelloFromOuterSpace(name))
                .thenReturn("Hello from outer space %s".formatted(name));

//        doNothing().when(externalMessageServiceSpy).doSomething();

        // when
        String actualMessage = helloExtAcademy.sayHello(name);
        // then
        assertThat(actualMessage)
                .as("When %s name is provided, a greeting message from outer space is returned", name)
                .isEqualTo("Hello from outer space %s".formatted(name));

    }

    @Test
    void exploreSpy() {
        List<String> strings = new ArrayList<>();
        List<String> stringsSpy = spy(strings);
//        when(stringsSpy.get(0)).thenReturn("Requim");
        // inverter o stub porque tentamos aceder ao get(0) antes de estar populado. o stub simula a lógica real
        doReturn("Requim").when(stringsSpy).get(0);

        System.out.println(stringsSpy.get(0));
    }

}