package com.magic.wc;


import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;


/**
 * @Author: zhangzhen
 * @Date: 2023-05-07-17:24
 * @Description:
 */
public class BoundedStreamCount {

    public static void main(String[] args) throws Exception {
//        // 1、 创建流式处理环境
//        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//
//        // 2、 从文件读取到数据
//        DataStreamSource<String> dataStreamSource = env.readTextFile("D:\\JavaWorkPlace\\zz_studying\\Flink\\src\\main\\resources\\word.txt");
//
//        // 3、 将每行数据进行分词,传入一行数据转换成二元组
//        SingleOutputStreamOperator<Tuple2<String, Long>> wordAndOneTuple = dataStreamSource.flatMap((String line,
//                                                                                                     Collector<Tuple2<String, Long>> out) -> {
//            // 将每一行数据以,分隔
//            String[] words = line.split(",");
//            // 统计每个二元组
//            for (String word : words) {
//                out.collect(Tuple2.of(word, 1L));
//            }
//        }).returns(Types.TUPLE(Types.STRING, Types.LONG));
//
//        // 4、分组
//        KeyedStream<Tuple2<String, Long>, String> wordAndOneKeyedStream = wordAndOneTuple.keyBy(data -> data.f0);
//
//        // 5、求和
//        SingleOutputStreamOperator<Tuple2<String, Long>> sum = wordAndOneKeyedStream.sum(1);
//
//        // 6、打印输出
//        sum.print();
//
//        // 7、启动执行
//        env.execute();
    }
}
