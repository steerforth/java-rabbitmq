package com.steer.rabbitmq.consumer.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MQListener {
    private static final Logger LOG = LoggerFactory.getLogger(MQListener.class);

//    @RabbitListener(queues = "simple.queue")
//    public void listenerSimpleQueue(String msg){
//        LOG.info("simple.queue receive msg:{}",msg);
//    }

    @RabbitListener(queues = "work.queue")
    public void listenerWorkQueue1(String msg){
        LOG.info("work.queue receive msg:{}",msg);
    }

    @RabbitListener(queues = "work.queue")
    public void listenerWorkQueue2(String msg){
        LOG.error("work.queue >>>>>>> receive msg:{}",msg);
    }

    @RabbitListener(queues = "fanout.queue1")
    public void listenerFanoutQueue1(String msg){
        LOG.info("fanout.queue1 receive msg:{}",msg);
    }

    @RabbitListener(queues = "fanout.queue2")
    public void listenerFanoutQueue2(String msg){
        LOG.error("fanout.queue2 >>>>>>> receive msg:{}",msg);
    }
}
