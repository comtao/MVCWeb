package com.cn.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cn.model.User;
import com.cn.util.StringUtil;

public class RepeatSubmissionInterceptor  extends HandlerInterceptorAdapter{
	 private static final Logger LOG = Logger.getLogger(RepeatSubmissionInterceptor.class);
	 
	    @Override
	    public boolean preHandle(HttpServletRequest request,
	            HttpServletResponse response, Object handler) throws Exception {
	        User user = new User();
	        if (user != null) {
	            HandlerMethod handlerMethod = (HandlerMethod) handler;
	            Method method = handlerMethod.getMethod();
	 
	            RepeatSubmission annotation = method.getAnnotation(RepeatSubmission.class);
	            if (annotation != null) {
	                boolean needSaveSession = annotation.needSaveToken();
	                if (needSaveSession) {
	                    request.getSession(false).setAttribute("token", StringUtil.getCode());
	                }
	 
	                boolean needRemoveSession = annotation.needRemoveToken();
	                if (needRemoveSession) {
	                    if (isRepeatSubmit(request)) {
	                        LOG.warn("please don't repeat submit,[user:" + user.getUserName() + ",url:"
	                                + request.getServletPath() + "]");
	                        return false;
	                    }
	                    request.getSession(false).removeAttribute("token");
	                }
	            }
	        }
	        return true;
	    }
	 
	    private boolean isRepeatSubmit(HttpServletRequest request) {
	        String serverToken = (String) request.getSession(false).getAttribute("token");
	        if (serverToken == null) {
	            return true;
	        }
	        String clinetToken = request.getParameter("token");
	        if (clinetToken == null) {
	            return true;
	        }
	        if (!serverToken.equals(clinetToken)) {
	            return true;
	        }
	        return false;
	    }
	 
}
