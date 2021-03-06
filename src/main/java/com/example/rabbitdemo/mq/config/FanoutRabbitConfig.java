package com.example.rabbitdemo.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class FanoutRabbitConfig implements BeanPostProcessor {


    private static RabbitAdmin rabbitAdmin;

    @Resource
    public void setRabbitAdmin(RabbitAdmin rabbitAdmin){
        FanoutRabbitConfig.rabbitAdmin = rabbitAdmin;
    }


    public Queue fanoutExchangeQueueA() {
        //队列A
        return new Queue(RabbitMQConfig.FANOUT_EXCHANGE_QUEUE_TOPIC_A, true, false, false);
    }


    public Queue fanoutExchangeQueueB() {
        //队列B
        return new Queue(RabbitMQConfig.FANOUT_EXCHANGE_QUEUE_TOPIC_B, true, false, false);

    }


    public FanoutExchange rabbitMqDemoFanoutExchange() {
        //创建FanoutExchange类型交换机
        return new FanoutExchange(RabbitMQConfig.FANOUT_EXCHANGE_DEMO_NAME, true, false);
    }

    @Bean
    public Binding bindFanoutA() {
        //队列A绑定到FanoutExchange类型交换机
        return BindingBuilder.bind(fanoutExchangeQueueA()).to(rabbitMqDemoFanoutExchange());
    }

    @Bean
    public Binding bindFanoutB() {
        //队列B绑定到FanoutExchange类型交换机
        return BindingBuilder.bind(fanoutExchangeQueueB()).to(rabbitMqDemoFanoutExchange());
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //启动项目即创建交换机和队列
        rabbitAdmin.declareExchange(rabbitMqDemoFanoutExchange());
        rabbitAdmin.declareQueue(fanoutExchangeQueueA());
        rabbitAdmin.declareQueue(fanoutExchangeQueueB());
        return null;
    }
}
