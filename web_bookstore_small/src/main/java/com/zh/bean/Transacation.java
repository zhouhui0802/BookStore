package com.zh.bean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * ������Ƶ�ע��
 */
@Retention(RetentionPolicy.RUNTIME) //����ʱ������
@Target(value=ElementType.METHOD) //ֻ�����ڷ�����
public @interface Transacation {

}