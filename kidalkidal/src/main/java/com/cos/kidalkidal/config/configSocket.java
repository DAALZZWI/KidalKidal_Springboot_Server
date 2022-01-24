package com.cos.kidalkidal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
import com.cos.kidalkidal.service.serviceSocketChat;
import com.cos.kidalkidal.service.serviceSocketOrder;

@Configuration
@EnableWebSocket
public class configSocket implements WebSocketConfigurer {
	
	@Override
	public void registerWebSocketHandlers( WebSocketHandlerRegistry registry ) {

		registry.addHandler( new serviceSocketOrder() , "/order/customer/{userEmail}/{companyCode}"  );
		registry.addHandler( new serviceSocketOrder() , "/order/manager/{userEmail}/{companyCode}" );
		registry.addHandler( new serviceSocketChat() , "/chat/{userEmail}/{userName}/{roomName}" );
	}
	
	@Bean
	public ServletServerContainerFactoryBean createWebSocketContainer() { 
		
		ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean(); 
		container.setMaxTextMessageBufferSize( 160 * 64 * 1024 );
		container.setMaxBinaryMessageBufferSize( 160 * 64 * 1024 ); 
		return container;
	}
}