package com.example.rabbitdemo.mq.fanout;

public interface IRabbitMqFanoutService {

    /**
     * 发送消息
     * @param msg 消息
     * @return 字符串
     * @throws Exception 异常
     */
    String sendMsgByFanoutExchange(String msg) throws Exception;
}
