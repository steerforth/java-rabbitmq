package com.steer.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 测试acknowledge-mode
 * none;manul;auto
 *
 * TODO 没生效
 */
@Configuration
public class AckQueueConfig {
    @Bean
    public Queue ackQueue(){
        return QueueBuilder.durable("ack.queue").build();
    }
}
