package com.zr.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Feign拦截器
 * 主要是微服务之间调用，传递header、cookie信息，传递jwt令牌的
 * zuul到微服务是可以传递令牌的，但是微服务和微服务之间是不走网关的，所以需要单独设置feign拦截器，加上相关配置
 * @author Administrator
 * @version 1.0
 **/
public class FeignClientInterceptor implements RequestInterceptor {

    /**
     * 每次feign远程调用都会经过此拦截器
     * @param requestTemplate
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(requestAttributes!=null){
            HttpServletRequest request = requestAttributes.getRequest();
            //取出当前请求的header，找到jwt令牌
            Enumeration<String> headerNames = request.getHeaderNames();
            if(headerNames!=null){
                while (headerNames.hasMoreElements()){
                    String headerName = headerNames.nextElement();
                    String headerValue = request.getHeader(headerName);
                    // 将header向下传递
                    requestTemplate.header(headerName,headerValue);
                }
            }
        }
    }
}
