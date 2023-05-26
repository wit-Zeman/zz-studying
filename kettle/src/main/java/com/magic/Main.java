package com.magic;
import lombok.extern.slf4j.Slf4j;
import org.pentaho.di.core.exception.KettleException;

/**
 * @Author: zhangzhen
 * @Date: ${YEAR}-${MONTH}-${DAY}-${TIME}
 * @Description:
 */
@Slf4j
public class Main {
    public static void main(String[] args) throws KettleException {
        log.info("主函数调用kettle任务-------->");
        KettleUtil.runTrans("D:\\JavaWorkPlace\\zz_studying\\kettle\\src\\main\\resources\\kettle\\files\\empOutput.ktr");

    }
}