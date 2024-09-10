package com.paintGame.paintGame;



import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.paintGame.paintGame.Controlers.PlayerController;
import com.paintGame.paintGame.Service.PlayerService;
import com.paintGame.paintGame.models.Player;

@SpringBootTest
class PaintGameApplicationTests {
    @Autowired
    private PlayerController playerController;

    @MockBean
    private PlayerService playerService;

    @Test
    void playerLoginTest(){
        // Create a mock player to be returned by the service
        Player player = new Player("Georg1", "12345", null);
        when(playerService.getUsername(player.getUsername())).thenReturn(player); // Mock the service method

        // Call the loginPlayer method and check the result
        Player result = playerController.loginPlayer(player);

        // Assert that the username is as expected
        assertEquals("", result.getPassword());
    }




   }