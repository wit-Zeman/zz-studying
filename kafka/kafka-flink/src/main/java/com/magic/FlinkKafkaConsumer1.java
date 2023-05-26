package com.magic;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;

/**
 * @Author: zhangzhen
 * @Date: 2023-05-26-10:57
 * @Description:
 */
public class FlinkKafkaConsumer1 {
    public static void main(String[] args) throws Exception {
        // 1 获取环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        // 2 创建一个消费者
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop102:9092,hadoop103:9092");
        FlinkKafkaConsumer kafkaConsumer = new FlinkKafkaConsumer<>("first",new SimpleStringSchema(),properties);
        // 3 关联 消费者 和 flink流
        env.addSource(kafkaConsumer).print();
        // 4 执行
        env.execute();
    }
}
