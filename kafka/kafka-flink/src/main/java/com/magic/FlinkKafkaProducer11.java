package com.magic;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.ArrayList;
import java.util.Properties;

/**
 * @Author: zhangzhen
 * @Date: 2023-05-26-10:44
 * @Description: 个人理解:flink获取外部数据输入到kafka生产者，Flinkkafka生产者将数据搬运到kafka集群，FlinKKafka消费者进行消费
 */
public class FlinkKafkaProducer11 {
    public static void main(String[] args) throws Exception {

        // 1 获取环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        // 2 准备数据源
        ArrayList<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        DataStreamSource<String> stream = env.fromCollection(list);

        // 创建kafka生产者
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop102:9092,hadoop103:9092");
        FlinkKafkaProducer<String> kafkaProducer = new FlinkKafkaProducer<>("first", new SimpleStringSchema(), properties);

        // 3 添加数据
        stream.addSink(kafkaProducer);

        // 4 执行代码
        env.execute();
    }
}
