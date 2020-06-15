package com.vdd.v1;

import com.alipay.mobile.common.rpc.RpcException;
import com.alipay.mobile.common.rpc.RpcInterceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

public class CommonInterceptor implements RpcInterceptor {
    /**
     * 前置拦截：发送 RPC 之前回调。
     * @param proxy  RPC 代理对象。
     * @param clazz  rpcface 模型类，通过 clazz 参数可以判断当前调用的是哪个 RPC 模型类
     * @param method 当前 RPC 调用的方法。
     * @throws RpcException
     * @return true 表示继续向下执行，false 表示中断当前请求，抛出 RpcException，错误码：9。
     */
    @Override
    public boolean preHandle(Object proxy,
                             ThreadLocal<Object> retValue,
                             byte[] retRawValue,
                             Class<?> clazz,
                             Method method,
                             Object[] args,
                             Annotation annotation,
                             ThreadLocal<Map<String, Object>> extParams)
            throws RpcException {
        //Do something...
        return true;
    }
    /**后置拦截：发起 RPC 成功之后回调。
     *@return true 表示继续向下执行，false 表示中断当前请求，抛出 RpcException，错误码：9。
     */
    @Override
    public boolean postHandle(Object proxy,
                              ThreadLocal<Object> retValue,
                              byte[] retRawValue,
                              Class<?> clazz,
                              Method method,
                              Object[] args,
                              Annotation annotation) throws RpcException {
        //Do something...
        return true;
    }
    /**
     * 异常拦截：发起 RPC 失败之后回调。
     * @param exception 表示当前 RPC 出错异常。
     * @return true 表示将当前异常继续向上抛出，false 表示不要抛出异常，正常返回，没有特殊需求，切勿返回 false。
     */
    @Override
    public boolean exceptionHandle(Object proxy,
                                   ThreadLocal<Object> retValue,
                                   byte[] retRawValue,
                                   Class<?> clazz,
                                   Method method,
                                   Object[] args,
                                   RpcException exception,
                                   Annotation annotation) throws RpcException {
        //Do something...
        return true;
    }
}
