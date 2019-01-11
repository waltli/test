package com.sbolo.syk;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import oth.common.anotation.Test;

@Configuration
public class WebSocketConfig {
	@Bean
	@Test
    public ServerEndpointExporter z() {
        return new ServerEndpointExporter();
    }
}
