package com.steer.rabbitmq.sendMsg;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SendMsgTest {
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
            template.convertAndSend(queue,msg);
            Thread.sleep(20);
        }

    }
}
