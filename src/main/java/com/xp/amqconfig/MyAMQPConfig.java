package com.xp.amqconfig;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author xp
 * @CreateTime 2019/03/12  23:32
 * @Function 自定义messageConverter将消息对象转化为json发送给消息队列
 */
@Configuration
public class MyAMQPConfig {

    @Bean
    public MessageConverter JsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
