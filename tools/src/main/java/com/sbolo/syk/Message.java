package com.sbolo.syk;
 
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
 
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sbolo.syk.common.inner.WebSocket1;

import oth.common.anotation.Test;
import oth.common.exception.BusinessException;

@ServerEndpoint("/webSocket/{username}/connect")
@Component
@Test
public class Message {
	private static final Logger log = LoggerFactory.getLogger(Message.class);

    private Session session;
    private String username;

    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) throws IOException {
        this.username = username;
        this.session = session;
        WebSocket1.onOpen(username, this);
    }

    @OnClose
    public void onClose() throws IOException {
    	WebSocket1.onClose(username);
    }

    @OnMessage
    public void onMessage(String message) throws IOException {
    	WebSocket1.onMessage(message, this.username, this.session);
    }

    @OnError
    public void onError(Session session, Throwable error) {
    	WebSocket1.onError(session, error);
    }

    @Test
    public void s(String message, String to) throws IOException {
    	WebSocket1.sendMessageTo(message, to);
    }

    public void sendMessageAll(String message) throws IOException {
    	WebSocket1.sendMessageAll(message);
    }
    
    public boolean isOnline(String username) {
    	return WebSocket1.isOnline(username);
    }

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}