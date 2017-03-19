package org.yqj.monitor.demo;

import com.jamonapi.JAMonFilter;
import com.jamonapi.MonKeyImp;
import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by yaoqijun on 2017/3/19.
 */
@Component("pageMonFilter")
public class PageMonFilter extends JAMonFilter{

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        Monitor allPages = MonitorFactory.start( new MonKeyImp("jammon.webui.allPages", getURI( request ), "ms." ) );
        Monitor monitor = MonitorFactory.start( getUrl( request ) );

        try
        {
            filterChain.doFilter( request, response );
        }
        finally
        {
            monitor.stop();
            allPages.stop();
        }
    }

    private String getUrl(ServletRequest servletRequest){
        if ( servletRequest instanceof HttpServletRequest)
        {
            return ((HttpServletRequest)servletRequest).getRequestURI();
        } else
        {
            return "Not an HttpServletRequest";
        }
    }
}
