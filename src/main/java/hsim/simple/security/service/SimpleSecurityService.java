package hsim.simple.security.service;

import hsim.simple.security.account.SimpleUserDetails;
import hsim.simple.security.spring.config.SecurityAdapterConfig;
import hsim.simple.security.util.ObjectGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

public abstract class SimpleSecurityService {

    private SecurityAdapterConfig securityAdapterConfig;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    public SimpleUserDetails login(SimpleUserDetails simpleUserDetails, HttpSession httpSession) {
        Authentication authentication = getAuthentication(simpleUserDetails.getUsername(), simpleUserDetails.getPassword(), httpSession);
        return simpleUserDetails.updateFromObj(authentication.getPrincipal());
    }

    public <T> T login(String userName, String password, Class<T> returnType, HttpSession httpSession) {
        Authentication authentication = getAuthentication(userName, password, httpSession);
        return ObjectGenerator.enableFieldModelMapper().map(authentication.getPrincipal(), returnType);
    }

    public String getEncodePassword(String plainPassword) {
        return this.passwordEncoder.encode(plainPassword);
    }

    public abstract UserDetails loadUserByUsername(String userName);

    @Bean
    public UserDetailService userDetailService() {
        return new UserDetailService(this);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        this.passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }

    @Bean
    public SecurityAdapterConfig springSecurityConfig() {
        this.securityAdapterConfig = new SecurityAdapterConfig(this);
        return this.securityAdapterConfig;
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        this.authenticationManager = this.securityAdapterConfig.authenticationManagerBean();
        return this.authenticationManager;
    }


    public abstract void configure(HttpSecurity http) throws Exception;

    public void addInterceptors(InterceptorRegistry registry) {
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    }

    public void addCorsMappings(CorsRegistry registry) {
    }

    public void configurePathMatch(PathMatchConfigurer configurer) {
    }

    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    }

    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
    }

    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    }

    public void addFormatters(FormatterRegistry registry) {
    }

    public void addViewControllers(ViewControllerRegistry registry) {
    }

    public void configureViewResolvers(ViewResolverRegistry registry) {
    }

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    }

    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
    }

    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    }

    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    }

    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
    }

    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
    }

    private Authentication getAuthentication(String userName, String password, HttpSession httpSession) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName, password);
        Authentication authentication = this.authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        return authentication;
    }


}
