//package cn.segema.learn.springboot2.controller;
//
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import cn.segema.cloud.demo.processor.MyMessagePostProcessor;
//import cn.segema.cloud.demo.vo.DemoStudentVO;
//
//@Controller
//@RequestMapping(value = "/demo/rabbitmq")
//public class RabbitmqController {
//
//    @Autowired
//    RabbitMessagingTemplate rabbitMessagingTemplate;
//
//    @Autowired
//    private AmqpTemplate amqpTemplate;
//
//    @RequestMapping(value = "/send", method = RequestMethod.GET)
//    @ResponseBody
//    public void send() {
//        DemoStudentVO student = new DemoStudentVO();
//        student.setName("zhangsan");
//        student.setAddress("wuhan");
//        student.setAge(20);
//        // rabbitSendTemplate.convertAndSend("default.topic", "test2.send", student);
//        // rabbitSendTemplate.convertAndSend("myFanoutExhange", "",student);
//        // rabbitSendTemplate.convertAndSend("myFanoutExhange", "", "这是FanoutExhange消息内容");
//        // rabbitMessagingTemplate.convertAndSend("myDirectExhange",
//        // "myDirectExhange-myDirectQueue","这是DirectExhange消息");
//        amqpTemplate.convertSendAndReceive("myDirectExhange", "myDirectExhange-myDirectQueue", "这是确认消息内容",
//            new MyMessagePostProcessor());
//
//    }
//}
