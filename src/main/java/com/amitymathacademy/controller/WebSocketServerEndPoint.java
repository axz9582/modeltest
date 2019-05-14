package com.amitymathacademy.controller;

import java.io.StringWriter;
import java.util.*;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.*;
@ServerEndpoint("/modeltestEndpoint")

public class WebSocketServerEndPoint {
	
	static Set<Session> chatroomUsers= Collections.synchronizedSet(new HashSet<Session>());
	
	@OnOpen
	
	public void handleOpen(Session userSession ){
		System.out.println("on open ........");
		chatroomUsers.add(userSession);
	}
	
	@OnClose
	public void handleClose(Session userSession){
		System.out.println("on close ........");
		chatroomUsers.remove(userSession);
	}
	
	@OnMessage
	public void handleMessage(String message, Session userSession) throws Exception{

		System.out.println("on message ........");
		String username = (String) userSession.getUserProperties().get("username");
		if(username==null){
			userSession.getUserProperties().put("username", message);
			userSession.getBasicRemote().sendText(buildJsonData("system", "you are now connected"));
		}
		else{
			Iterator<Session> iterator = chatroomUsers.iterator();
			while(iterator.hasNext()){
				iterator.next().getBasicRemote().sendText(buildJsonData(username, message));
			}
		}
	}
	
	String buildJsonData(String username, String message){
		
		JsonObject js = Json.createObjectBuilder().add("message", username+":"+message).build();
	 
		StringWriter stringWriter = new StringWriter();
		try{JsonWriter jswriter = Json.createWriter(stringWriter);
			jswriter.write(js);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return stringWriter.toString();
	}

}
