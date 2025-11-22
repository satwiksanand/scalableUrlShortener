package com.github.satwiksanand.scalableUrlShortener.config;

import com.github.satwiksanand.scalableUrlShortener.filter.IpRateLimitFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<IpRateLimitFilter> rateFilter(IpRateLimitFilter filter) {
        FilterRegistrationBean<IpRateLimitFilter> reg = new FilterRegistrationBean<>();
        reg.setFilter(filter);
        reg.addUrlPatterns("/*"); // apply to all routes
        reg.setOrder(1);
        return reg;
    }
}
