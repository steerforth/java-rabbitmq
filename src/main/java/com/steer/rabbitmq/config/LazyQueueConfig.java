package com.steer.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 建议用延迟队列，性能高
 */
@Configuration
public class LazyQueueConfig {
    @Bean
    public Queue lazyQueue(){
        return QueueBuilder.durable("lazy.queue").lazy().build();
    }
}
