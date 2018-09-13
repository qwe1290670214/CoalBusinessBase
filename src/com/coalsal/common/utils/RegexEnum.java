package com.coalsal.common.utils;

/**
 * Created by li on 2017/5/2.
 */
public enum RegexEnum {
    /**
     * 是否为整数
     */
    IS_INTEGER("^[-\\+]?[\\d]+$"),
    /**
     * 是否为浮点数
     */
    IS_DOUBLE("^[-\\+]?\\d+\\.\\d+$"),

    /**
     * 是否为中文
     */
    // IS_CHINESE ( "^[\u4e00-\u9fa5]"),
    /**
     * 是否为英文
     */
    IS_ENGLISH("^[A-Za-z]+$"),

    /**
     * 是否为邮箱
     */
    IS_EMAIL("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$"),

    /**
     * 是否为数字和英文
     */
    IS_ENGLISH_NUMBER("^[A-Za-z0-9]+$"),

    /**
     * 是否为字符串
     */
    IS_STRING("^\\w+$"),

    /**
     * 是否为手机号
     */
    IS_PHONE("^1[\\d]{10}$"),

    /**
     * 不包含特殊字符
     */
    NOT_IN_SPECIAL_CHAR("^[a-zA-Z0-9\u4e00-\u9fa5]+$"),
    /**
     * 是否是护照
     */
    IS_PASSPORT("^1[45][0-9]{7}|G[0-9]{8}|P[0-9]{7}|S[0-9]{7,8}|D[0-9]+$");

    RegexEnum(String value) {
        this.value = value;
    }

    private final String value;

    public String getValue() {
        return value;
    }
}
