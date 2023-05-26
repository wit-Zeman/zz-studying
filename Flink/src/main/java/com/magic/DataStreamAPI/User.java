package com.magic.DataStreamAPI;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: zhangzhen
 * @Date: 2023-05-08-10:28
 * @Description: 模拟用户访问网页的用户信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    public String name;
    public String url;
    public Integer age;
    public Date date;
}
