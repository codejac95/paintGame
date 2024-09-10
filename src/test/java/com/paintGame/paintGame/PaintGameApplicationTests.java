package com.paintGame.paintGame;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;


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



	@Autowired
	// Coolt från datavärlden "Subject Under Test"
	private PlayerController sut;

	@MockBean
	private PlayerService playerService;

	@Test
	// Given when then
	void givenCorrectLoginDetailsWhenPlayerLogsInThenReturnsPlayerWithBlankPassword() {
		// Create a mock player to be returned by the service
		Player player = new Player("Georg1", "12345", null);
		when(playerService.getUsername(player.getUsername())).thenReturn(player); // Mock the service method

		// Call the loginPlayer method and check the result
		Player result = sut.loginPlayer(player);

		// Assert that the username is as expected
		assertEquals("", result.getPassword());

	}

	@Test
	void givenDatabaseIsDownWhenPlayerLogsInThenPropagatesException() {
		Player player = new Player("Georg1", "12345", null);
		when(playerService.getUsername(player.getUsername())).thenThrow(new RuntimeException("Boom") {
		});
		assertThrows(RuntimeException.class, () -> sut.loginPlayer(player));
	}

	@Test
	void givenWrongPasswordWhenPlayerLogsInThenReturnsPlayerWithNullValues() {
		Player player = new Player("Georg1", "67890", null);
		when(playerService.getUsername(player.getUsername())).thenReturn(null);

		Player result = sut.loginPlayer(player);
		assertEquals(null, result.getPassword());
		assertEquals(null, result.getUsername());
	}

}

