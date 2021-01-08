package com.imooc.common.enums;

/**
 * @Description 性别枚举类
 * @author zhaocan
 * @date 2021年1月8日17:45:45
 */
public enum Sex {
    //性别枚举值
    woman(0, "女"),
    man(1, "男"),
    secret(2, "保密");

    public final Integer type;
    public final String value;

    Sex(Integer type, String value) {
        this.type = type;
        this.value = value;
    }


}
