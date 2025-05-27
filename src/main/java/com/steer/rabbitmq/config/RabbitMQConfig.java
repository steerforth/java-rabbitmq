package com.steer.rabbitmq.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    private static final Logger LOG = LoggerFactory.getLogger(RabbitMQConfig.class);

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        //设置为true,如果没有找到一个合适的queue存储消息，那么broker会调用basic.return方法将消息返回给生产者
        //设置为false,直接将消息丢失，回调方法也不会执行
        template.setMandatory(true);
        template.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            //TODO callback无消息产生
            @Override
            public void returnedMessage(ReturnedMessage returnedMessage) {
                LOG.info("exchange:{} routkey:{} 收到return callback: replyCode:{} replyMsg:{} msg:{}",returnedMessage.getExchange(),returnedMessage.getRoutingKey(),returnedMessage.getReplyCode(),returnedMessage.getReplyText(),returnedMessage.getMessage());
                //可根据实际情况，进行业务数据重发消息
            }
        });
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }
}
