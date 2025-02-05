package com.paintGame.paintGame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.paintGame.paintGame.Controlers.PlayerController;
import com.paintGame.paintGame.Service.GameService;
import com.paintGame.paintGame.Service.PlayerService;
import com.paintGame.paintGame.models.Player;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

@SpringBootTest
class PaintGameApplicationTests {
	@Autowired
	// For declaration of test subject. "Subject Under Test"
	private PlayerController sut;

	@MockBean
	private PlayerService playerService;

	@Autowired
	private GameService gameService;

	@Test
	// "Given when then". A standard for writing tests
	void givenCorrectLoginDetailsWhenPlayerLogsInThenReturnsPlayerWithBlankPassword() {
		// Create a mock player to be returned by the service
		Player playerUsernameAndPassword = new Player("jake", "jake", null);
		Player player = new Player("66eab2a0fede0825b3f33328", "jake", "jake", null, true);

		// Mock the service method
		when(playerService.loginPlayer(playerUsernameAndPassword)).thenReturn(player);

		// Call the loginPlayer method and check the result
		Player result = sut.loginPlayer(playerUsernameAndPassword);

		// Assert that the username is as expected
		assertEquals("", result.getPassword());
	}

	@Test
	void givenDatabaseIsDownWhenPlayerLogsInThenPropagatesException() {
		Player player = new Player("jake", "jake", null);
		when(playerService.loginPlayer(player)).thenThrow(new RuntimeException("Boom") {
		});
		assertThrows(RuntimeException.class, () -> sut.loginPlayer(player));
	}

	@Test
	void givenWrongPasswordWhenPlayerLogsInThenReturnsPlayerWithNullValues() {
		Player player = new Player("jake", "123", null);
		when(playerService.loginPlayer(player)).thenReturn(null);

		Player result = sut.loginPlayer(player);
		assertTrue(result != null);
		assertEquals(null, result.getPassword());
		assertEquals(null, result.getUsername());
	}

	@Test
	public void givenWebsocketIsConnectedWhenCheckedThenReturnsTrue() throws InterruptedException, ExecutionException {
		List<Transport> transports = Arrays.<Transport>asList(new WebSocketTransport(new StandardWebSocketClient()));
		SockJsClient sockJsClient = new SockJsClient(transports);

		// Create a Stomp client using SockJS
		WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
		stompClient.setMessageConverter(new MappingJackson2MessageConverter());

		// Connect using SockJS endpoint
		String sockJsUrl = "http://localhost:8080/websocket";
		@SuppressWarnings("deprecation")
		StompSession stompSession = stompClient.connect(sockJsUrl, new StompSessionHandlerAdapter() {
		}).get();

		// Assert that the session is connected
		assertTrue(stompSession.isConnected(), "WebSocket should be connected");

	}

	@Test
	void givenSquareIsUnOccupiedWhenCheckedThenReturnsFalse() {

		int squareId = 1;
		gameService.assignSquare(squareId);
		assertFalse(gameService.getOccupiedSquares().isEmpty());

	}

	@Test
	void givenSquareIsOccupiedWhenCheckedThenReturnsTrue() {

		int squareId = 1;
		gameService.assignSquare(squareId);
		assertTrue(gameService.getOccupiedSquares().contains(squareId));

	}

}
