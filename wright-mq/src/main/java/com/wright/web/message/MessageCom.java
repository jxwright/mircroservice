package com.wright.web.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class MessageCom {

	private Source source;
	
	@Autowired
	public MessageCom(Source source)
	{
		this.source=source;
	}
	
	public void send(String mes)
	{
		this.source.output().send(MessageBuilder.withPayload(mes).build());
	}
}
