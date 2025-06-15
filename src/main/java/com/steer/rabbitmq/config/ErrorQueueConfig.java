package com.steer.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.ImmediateRequeueMessageRecoverer;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息异常处理测试
 */
@Configuration
public class ErrorQueueConfig {
    @Bean
    public Queue errorQueue(){
        return QueueBuilder.durable("error.queue").build();
    }

    @Bean
    public DirectExchange errorExchange(){
        return new DirectExchange("error.direct");
    }

    @Bean
    public Binding errorBindinds(Queue errorQueue,DirectExchange errorExchange){
        return BindingBuilder.bind(errorQueue).to(errorExchange).with("errorkey");
    }

    /**
     * RejectAndDontRequeueRecoverer 默认。重试耗尽后，直接reject
     * ImmediateRequeueMessageRecoverer 重试耗尽后，返回nack，消息重新入对
     * RepublishMessageRecoverer 重试耗尽后，将失败消息投到指定的交换机
     * @param template
     * @return
     */
    @Bean
    public MessageRecoverer messageRecoverer(RabbitTemplate template){
        return new RejectAndDontRequeueRecoverer();
//        return new RepublishMessageRecoverer(template,"error.direct","errorkey");
    }
}
