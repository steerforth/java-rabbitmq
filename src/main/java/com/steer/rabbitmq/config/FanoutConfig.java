package com.steer.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {
    @Bean
    public FanoutExchange fanoutExchange(){
//        ExchangeBuilder.fanoutExchange("").build();
        return new FanoutExchange("steer.fanout");
    }

    @Bean
    public Queue fanoutQueue1(){
        return new Queue("fanout.queue1");
    }

    @Bean
    public Queue fanoutQueue2(){
        return new Queue("fanout.queue2");
    }


    @Bean
    public Binding bindingFQueue1(Queue fanoutQueue1,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }

    @Bean
    public Binding bindingFQueue2(Queue fanoutQueue2,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
    }
}
