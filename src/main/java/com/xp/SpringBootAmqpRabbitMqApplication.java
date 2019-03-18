package com.xp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * RabbitMQ自动配置类（RabbitAutoConfiguration）:
 * 1.ConnectionFactory：RabbitMQ连接工厂
 * 2.RabbitProperties：封装了RabbitMQ的所有配置
 * 4.RabbitTemplate：给RabbitMQ发送和接收消息
 * 5.AmqpAdmin：RabbitMQ系统管理功能组件,可以生成队列,创建交换器
 *   可以创建和删除Queue，Exchange,Binding
 * 6. @EnableRabbit + @RabbitListener(queues = "atguigu.news")监听消息队列的内容
 *
 * */
@SpringBootApplication
@EnableRabbit   //开启基于注解的RabbitMQ
public class SpringBootAmqpRabbitMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAmqpRabbitMqApplication.class, args);
    }

}
