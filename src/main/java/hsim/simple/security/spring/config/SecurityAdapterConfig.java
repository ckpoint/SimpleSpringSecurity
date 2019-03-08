package hsim.simple.security.spring.config;

import hsim.simple.security.service.SimpleSecurityService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 *
 */
@Slf4j
@RequiredArgsConstructor
public class SecurityAdapterConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    @NonNull
    private final SimpleSecurityService simpleSecurityService;

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        this.simpleSecurityService.configure(http);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        this.simpleSecurityService.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        this.simpleSecurityService.addResourceHandlers(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        this.simpleSecurityService.addCorsMappings(registry);
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        this.simpleSecurityService.configurePathMatch(configurer);

    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        this.simpleSecurityService.configureContentNegotiation(configurer);
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        this.simpleSecurityService.configureAsyncSupport(configurer);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        this.simpleSecurityService.configureDefaultServletHandling(configurer);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        this.simpleSecurityService.addFormatters(registry);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        this.simpleSecurityService.addViewControllers(registry);
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        this.simpleSecurityService.configureViewResolvers(registry);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        this.simpleSecurityService.addArgumentResolvers(resolvers);
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        this.simpleSecurityService.addReturnValueHandlers(handlers);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        this.simpleSecurityService.configureMessageConverters(converters);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        this.simpleSecurityService.extendMessageConverters(converters);
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        this.simpleSecurityService.configureHandlerExceptionResolvers(resolvers);
    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        this.simpleSecurityService.extendHandlerExceptionResolvers(resolvers);
    }

}
