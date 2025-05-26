package com.steer.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectConfig {
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("steer.direct");
    }

    @Bean
    public Queue directQueue1(){
        return new Queue("direct.queue1");
    }

    @Bean
    public Queue directQueue2(){
        return new Queue("direct.queue2");
    }

    @Bean
    public Binding dQueue1BindingRed(Queue directQueue1, DirectExchange directExchange){
        return BindingBuilder.bind(directQueue1).to(directExchange).with("red");
    }

    @Bean
    public Binding dQueue1BindingBlue(Queue directQueue1, DirectExchange directExchange){
        return BindingBuilder.bind(directQueue1).to(directExchange).with("blue");
    }

    @Bean
    public Binding dQueue2BindingRed(Queue directQueue2, DirectExchange directExchange){
        return BindingBuilder.bind(directQueue2).to(directExchange).with("red");
    }

    @Bean
    public Binding dQueue2BindingYellow(Queue directQueue2, DirectExchange directExchange){
        return BindingBuilder.bind(directQueue2).to(directExchange).with("yellow");
    }

}
