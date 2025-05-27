package com.steer.rabbitmq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * 每个RabbitTemplate只能配置一个returnsCallback
 */
@Configuration
public class MQConfirmConfig implements ApplicationContextAware {
    private static final Logger LOG = LoggerFactory.getLogger(MQConfirmConfig.class);


    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        RabbitTemplate template = context.getBean(RabbitTemplate.class);
    }
}
