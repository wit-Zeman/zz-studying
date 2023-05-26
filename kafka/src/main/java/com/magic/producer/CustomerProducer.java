package com.magic.producer;

import com.magic.partition.MyPartition;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @Author: zhangzhen
 * @Date: 2023-05-22-16:07
 * @Description: 创建kafka生产者并发送数据到kafka集群
 */
public class CustomerProducer {

    // 定义全局唯一的事务id
    public static final Long TRANS_ID = 999L;

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        // 0 配置kafka
        Properties properties = new Properties();

        // 连接集群 boostrap.servers
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop102:9092,hadoop103:9092");

        // 指定key和value的序列化类型
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 自定义分区
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, MyPartition.class.getName());

        // 提高producer吞吐量
        // 缓冲区 默认32M（33554432）
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        // 批次大小 默认16k（16348）
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16348);
        // linger.ms 默认0ms
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        // 压缩 snappy
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");

        // 设置 acks保证数据可靠性
        properties.put(ProducerConfig.ACKS_CONFIG, "1");

        //-----------1 kafka事务 数据去重 需要事务id持久化到硬盘
        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, TRANS_ID);

        // 重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG, 3);

        // 1 创建kafka生产者
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);

        //-----------2 事务操作步骤
        kafkaProducer.initTransactions();
        kafkaProducer.beginTransaction();
        try {
            // 发送数据
            kafkaProducer.send(new ProducerRecord<>("TOPIC_FIRST", "hello,kafka"));
            kafkaProducer.commitTransaction();
        } catch (Exception e) {
            // 遇到异常 回滚事务
            kafkaProducer.abortTransaction();
        }

        // 2.1 发送数据 基础发送方式 异步
        kafkaProducer.send(new ProducerRecord<>("TOPIC_FIRST", "hello,kafka"));
        // 2.2 回调发送
        kafkaProducer.send(new ProducerRecord<>("TOPIC_FIRST", "hello,kafka"), new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e != null) {
                    System.out.println("topic: " + recordMetadata.topic() + "partition" + recordMetadata.partition());
                }
            }
        });
        // 2.3 同步发送 只需要在异步发送方式后面添加get方法即可
        kafkaProducer.send(new ProducerRecord<>("TOPIC_FIRST", "hello,kafka")).get();
        // 2.4 自定义分区发送数据
        kafkaProducer.send(new ProducerRecord<>("TOPIC_FIRST", "指定消息", "hello,kafka"));

        // 3 关闭资源
        kafkaProducer.close();
    }
}
