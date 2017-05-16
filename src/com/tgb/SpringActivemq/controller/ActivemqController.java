package com.tgb.SpringActivemq.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tgb.SpringActivemq.mq.producer.queue.QueueSender;
import com.tgb.SpringActivemq.mq.producer.topic.TopicSender;

/**
 * 
 * @author liang
 * @description controller测试
 */
@Controller
@RequestMapping("/activemq")
public class ActivemqController {
	
	@Resource 
	QueueSender queueSender;
	@Resource 
	TopicSender topicSender;
	
	/**
	 * 发送消息到队列
	 * Queue队列：仅有一个订阅者会收到消息，消息一旦被处理就不会存在队列中
	 * @param message
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("queueSender")
	public String queueSender(@RequestParam("message")String message){
		String opt="";
		try {
			queueSender.send("test.queue", message);			//发送的队列的名称，要和配置文件中定义的监听器监听的队列名称相同
			opt = "suc";
		} catch (Exception e) {
			opt = e.getCause().toString();
		}
		return opt;
	}
	
	/**
	 * 发送消息到主题
	 * Topic主题 ：放入一个消息，所有订阅者都会收到 
	 * 这个是主题目的地是一对多的
	 * @param message
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("topicSender")
	public String topicSender(@RequestParam("message")String message){
		String opt = "";
		try {
			topicSender.send("test.topic", message);				//发送的主题的名称，要和配置文件中定义的监听器监听的主题名称相同
			opt = "suc";
		} catch (Exception e) {
			opt = e.getCause().toString();
		}
		
		return opt;
	}
	
}
