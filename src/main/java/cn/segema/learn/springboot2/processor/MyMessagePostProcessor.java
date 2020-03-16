//package cn.segema.cloud.demo.processor;
//
//import org.springframework.amqp.AmqpException;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessagePostProcessor;
//
//public class MyMessagePostProcessor implements MessagePostProcessor {
//
//	@Override
//	public Message postProcessMessage(Message message) throws AmqpException {
//		
//		byte[] messageBytes = message.getBody();
//		
//		System.out.println(messageBytes);
//		return message;
//	}
//}
