package com.magic.partition;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * @Author: zhangzhen
 * @Date: 2023-05-22-16:52
 * @Description:
 */
public class MyPartition implements Partitioner{
    @Override
    public int partition(String s, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {

        // 获取数据
        String msg = o.toString();

        int partition;

        if (msg.contains("指定消息")){
            partition = 0;
        }else {
            partition = 1;
        }
        return partition;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
