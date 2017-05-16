package com.tgb.SpringActivemq.mq.consumer.topic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

@Component("top")
public class TopicReceiver3 implements MessageListener{

	@Override
	public void onMessage(Message message) {
			try {
				System.out.println("TopicReceiver3接收消息: "+((TextMessage)message).getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
	}

}
