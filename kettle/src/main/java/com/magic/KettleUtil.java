package com.magic;

import lombok.extern.slf4j.Slf4j;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.logging.LogLevel;

import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;



/**
 * @Author: zhangzhen
 * @Date: 2023-05-06-16:11
 * @Description:
 */
@Slf4j
public class KettleUtil {

    /**
     * 执行trans任务
     *
     * @param transFilePath
     * @throws KettleException
     */
    public static void runTrans(String transFilePath) throws KettleException {
        //1、初始化
        KettleEnvironment.init();
        //2、读取文件名
        TransMeta transMeta = new TransMeta(transFilePath);
        //3、调用trans
        Trans trans = new Trans(transMeta);
        //4、执行
        trans.setLogLevel(LogLevel.ERROR);
        log.info("kettle任务正在执行");

        trans.execute(null);
        //5、等待执行结束
        trans.waitUntilFinished();
        log.info("kettle任务执行结束");

    }

    /**
     * 执行job任务
     * @param JobFilePath
     * @throws KettleException
     */
    public static void runJob(String JobFilePath) throws KettleException {

        KettleEnvironment.init();


        JobMeta jobMeta = new JobMeta(JobFilePath,null);

        Job job = new Job(null,jobMeta);

        job.setLogLevel(LogLevel.ERROR);

        job.start();

        job.waitUntilFinished();
    }
}
