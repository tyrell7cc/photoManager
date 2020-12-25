package com.backgulf.system.secure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@WebFilter
public class RequestFilter implements Filter {
    @Autowired
    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter init");
    }

    @Override
    public void destroy() {
        System.out.println("filter destroy");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        String s = request.getRequestURI();
        System.out.println(s);
        if (userService.whetherLogged(request)){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        if (s.startsWith("/login")||s.startsWith("/hello")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        response.setStatus(401);
        response.sendRedirect("/login");
        return;
    }
}
