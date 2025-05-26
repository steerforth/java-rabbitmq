package com.steer.rabbitmq.sendMsg;

import com.steer.rabbitmq.consumer.listeners.MQListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SendMsgTest {
    private static final Logger LOG = LoggerFactory.getLogger(SendMsgTest.class);

    @Autowired
    private RabbitTemplate template;

    @Test
    public void testSendMsg(){
        String queue = "simple.queue";
        String msg = "hello world!";
        template.convertAndSend(queue,msg);
    }

    /**
     * 多个consumer对应一个queue
     */
    @Test
    public void testWorkQueue() throws InterruptedException {
        String queue = "work.queue";
        for (int i = 0; i < 100; i++) {
            String msg = "hello "+i;
            LOG.info("send msg:{}",msg);
            template.convertAndSend(queue,msg);
            Thread.sleep(20);
        }

    }

    @Test
    public void testFanoutExchange(){
        String exchange = "steer.fanout";
        String msg = "hello world!";
        template.convertAndSend(exchange,null,msg);
    }

    @Test
    public void testDirectExchange(){
        String exchange = "steer.direct";
        String msg = "hello world!";
        template.convertAndSend(exchange,"red",msg);
    }

    @Test
    public void testDirectExchange2(){
        String exchange = "steer.direct";
        String msg = "hello world!";
        template.convertAndSend(exchange,"blue",msg);
    }

    @Test
    public void testTopicExchange(){
        String exchange = "steer.topic";
        String msg = "hello world!";
        template.convertAndSend(exchange,"china.news",msg);
    }

    @Test
    public void testTopicExchange2(){
        String exchange = "steer.topic";
        String msg = "hello world!";
        template.convertAndSend(exchange,"english.news",msg);
    }

    /**
     * 需要配置Jackson2JsonMessageConverter
     */
    @Test
    public void testSendObject(){
        String exchange = "simple.queue";
        Map<String,String> obj = new HashMap<>();
        obj.put("name","张三");
        obj.put("age","12");
        template.convertAndSend(exchange,obj);
    }
}
