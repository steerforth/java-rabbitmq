package com.steer.rabbitmq.consumer.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
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

    /**
     * bind RoutingKey blue,red
     * @param msg
     */
    @RabbitListener(queues = "direct.queue1")
    public void listenerDirectQueue1(String msg){
        LOG.info("direct.queue1 receive msg:{}",msg);
    }
    /**
     * bind RoutingKey red,yellow
     * @param msg
     */
    @RabbitListener(queues = "direct.queue2")
    public void listenerDirectQueue2(String msg){
        LOG.error("direct.queue2 >>>>>>> receive msg:{}",msg);
    }

    /**
     * bind RoutingKey china.#
     * #代表0或多个代词
     * *代表一个单词
     *
     * 方式二:除了基于Config的方式配置，还可以基于下列方式
     * @param msg
     */
//    @RabbitListener(queues = "topic.queue1")
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue1"),
            exchange = @Exchange(name = "steer.topic",type = ExchangeTypes.TOPIC),
            key = {"china.#"}
    ))
    public void listenerTopicQueue1(String msg){
        LOG.info("topic.queue1 receive msg:{}",msg);
    }
    /**
     * bind RoutingKey #.news
     * #代表0或多个代词
     * *代表一个单词
     * @param msg
     */
//    @RabbitListener(queues = "topic.queue2")
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue2"),
            exchange = @Exchange(name = "steer.topic",type = ExchangeTypes.TOPIC),
            key = {"#.news"}
    ))
    public void listenerTopicQueue2(String msg){
        LOG.error("topic.queue2 >>>>>>> receive msg:{}",msg);
    }
}
