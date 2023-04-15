package com.multipart.client.multipartclient;

import org.springframework.core.MethodParameter;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.util.MethodInvoker;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.service.invoker.AbstractNamedValueArgumentResolver;
import org.springframework.web.service.invoker.HttpRequestValues;
import org.springframework.web.service.invoker.RequestPartArgumentResolver;

import java.lang.reflect.InvocationTargetException;

public class CustomMultipartResolver extends AbstractNamedValueArgumentResolver {
    private RequestPartArgumentResolver defaultRequestpartResolver;

    public CustomMultipartResolver(ReactiveAdapterRegistry reactiveAdapterRegistry) {
        defaultRequestpartResolver = new RequestPartArgumentResolver(reactiveAdapterRegistry);
    }


    @Override
    protected NamedValueInfo createNamedValueInfo(MethodParameter parameter) {
        var methodInvoker = new MethodInvoker();
        methodInvoker.setArguments(parameter);
        methodInvoker.setTargetMethod("createNamedValueInfo");
        methodInvoker.setTargetObject(defaultRequestpartResolver);
        try {
            methodInvoker.prepare();
            return (NamedValueInfo) methodInvoker.invoke();
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void addRequestValue(String name, Object value, MethodParameter parameter, HttpRequestValues.Builder requestValues) {
        var methodInvoker = new MethodInvoker();
        methodInvoker.setArguments(name, getActualValue(value), parameter, requestValues);
        methodInvoker.setTargetMethod("addRequestValue");
        methodInvoker.setTargetObject(defaultRequestpartResolver);
        try {
            methodInvoker.prepare();
            methodInvoker.invoke();
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static Object getActualValue(Object value) {
        if (value instanceof MultipartFile)
            return ((MultipartFile) value).getResource();
        return value;
    }
}
