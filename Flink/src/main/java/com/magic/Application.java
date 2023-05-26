package com.magic;


import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;


import java.util.Properties;

/**
 * @Author: zhangzhen
 * @Date: 2023-05-09-13:39
 * @Description:
 */
public class Application {

    /**
     * 程序的主启动函数
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        // 1、创建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        // 2、配置kafka信息
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "10.100.51.107:9092");

        // 3、读取kafka数据
        DataStreamSource<String> kafkaStream = env.addSource(new FlinkKafkaConsumer<String>("order_v2", new SimpleStringSchema(), properties));

        // 4、打印
        kafkaStream.print();

        // 5、执行
        env.execute();
    }
}
