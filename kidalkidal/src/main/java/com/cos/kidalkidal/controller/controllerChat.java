package com.cos.kidalkidal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.kidalkidal.model.modelChatPayload;
import com.cos.kidalkidal.service.serviceSocketChat;

@RestController
public class controllerChat {

	@Autowired
	private serviceSocketChat serviceSocketChat;
	
	@PostMapping ( "/chat/select" )
	public List< String > chatSelect () {
		
		return serviceSocketChat.functionChatRoomSelect();
	}
	
	@PostMapping( "/chat/image/select" )
	public modelChatPayload chatImageSelect ( @RequestBody modelChatPayload modelChatPayload ) {
		
		return serviceSocketChat.functionChatImageSelect( modelChatPayload );
	}
	
	@PostMapping( "/chat/image/add" )
	public modelChatPayload chatImageAdd ( @RequestBody String email ) {
		
		return serviceSocketChat.functionChatImageAdd( email );
	}
}
