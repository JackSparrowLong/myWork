package com.example.rabbitdemo.mq.fanout;

import com.example.rabbitdemo.mq.config.RabbitMQConfig;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class RabbitMqFanoutServiceImpl implements IRabbitMqFanoutService{

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    private static RabbitTemplate rabbitTemplate;

    @Resource
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate){
        RabbitMqFanoutServiceImpl.rabbitTemplate =rabbitTemplate;
    }

    @Override
    public String sendMsgByFanoutExchange(String msg) throws Exception {
        try {
            Map<String, Object> message = getMessage(msg);
            rabbitTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE_DEMO_NAME,"", message);
            return "ok";
        } catch (AmqpException e) {
            e.printStackTrace();
            return "error";
        }
    }


    //组装消息体
    private Map<String,Object> getMessage(String msg){
        String msgId = UUID.randomUUID().toString().replace("-", "").substring(0, 32);
        String sendTime = sdf.format(new Date());
        Map<String, Object> map = new HashMap<>();
        map.put("msgId", msgId);
        map.put("sendTime", sendTime);
        map.put("msg", msg);
        return map;
    }

}
