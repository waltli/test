package com.sbolo.syk.common.inner;
 
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
 
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sbolo.syk.Message;

import oth.common.exception.BusinessException;

public class WebSocket1 {
	private static final Logger log = LoggerFactory.getLogger(WebSocket1.class);

    private static int onlineCount = 0;
    private static Map<String, Message> clients = new ConcurrentHashMap<String, Message>();

    public static void onOpen(String username, Message webSocket) throws IOException {
        addOnlineCount();
        clients.put(username, webSocket);
    }

    public static void onClose(String username) throws IOException {
        clients.remove(username); 
        subOnlineCount(); 
    }

    public static void onMessage(String message, String username, Session session) throws IOException {
    	sendMessageTo("Affirmative! + "+username, username);
    }

    public static void onError(Session session, Throwable error) {
    	log.error("", error);
    }

    public static void sendMessageTo(String message, String to) throws IOException {
    	Message client = clients.get(to);
    	if(client == null) {
    		throw new BusinessException(to+" doesn't link。");
    	}
    	client.getSession().getAsyncRemote().sendText(message);
    }

    public static void sendMessageAll(String message) throws IOException {
        for (Message item : clients.values()) {
            item.getSession().getAsyncRemote().sendText(message);
        }
    }
    
    public static boolean isOnline(String username) {
    	Message client = clients.get(username);
    	if(client == null) {
    		return false;
    	}
    	return true;
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocket1.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocket1.onlineCount--;
    }
}