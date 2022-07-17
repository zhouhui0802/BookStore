package com.zh.bean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * 事务控制的注解
 */
@Retention(RetentionPolicy.RUNTIME) //运行时起作用
@Target(value=ElementType.METHOD) //只能用在方法上
public @interface Transacation {

}