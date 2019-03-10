package hsim.simple.security.service;

import hsim.simple.security.account.SimpleUserDetails;
import hsim.simple.security.resolver.SimpleUserDetailsResolver;
import hsim.simple.security.spring.config.SecurityAdapterConfig;
import hsim.simple.security.util.ObjectGenerator;
import hsim.simple.security.util.SimplePasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The type Simple security service.
 */
public abstract class SimpleSecurityService extends SecurityContextHolder {

    private SecurityAdapterConfig securityAdapterConfig;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    /**
     * Login simple user details.
     *
     * @param simpleUserDetails the simple user details
     * @param httpSession       the http session
     * @return the simple user details
     */
    public SimpleUserDetails login(SimpleUserDetails simpleUserDetails, HttpSession httpSession) {
        Authentication authentication = getAuthentication(simpleUserDetails.getUsername(), simpleUserDetails.getPassword(), httpSession);
        return simpleUserDetails.updateFromObj(authentication.getPrincipal());
    }

    /**
     * Login t.
     *
     * @param <T>         the type parameter
     * @param userName    the user name
     * @param password    the password
     * @param returnType  the return type
     * @param httpSession the http session
     * @return the t
     */
    public <T> T login(String userName, String password, Class<T> returnType, HttpSession httpSession) {
        Authentication authentication = getAuthentication(userName, password, httpSession);
        return ObjectGenerator.enableFieldModelMapper().map(authentication.getPrincipal(), returnType);
    }


    /**
     * Load user by username simple user details.
     *
     * @param userName the user name
     * @return the simple user details
     */
    public abstract SimpleUserDetails loadUserByUsername(String userName);

    /**
     * Gets session.
     *
     * @param type the type
     * @return the session
     */
    public SimpleUserDetails getSession(Class<? extends SimpleUserDetails> type) {
        Object principal = getContext().getAuthentication().getPrincipal();
        if (principal == null) {
            return null;
        }

        return ObjectGenerator.enableFieldModelMapper().map(principal, type);
    }


    /**
     * User detail service simple user detail service.
     *
     * @return the simple user detail service
     */
    @Bean
    public SimpleUserDetailService userDetailService() {
        return new SimpleUserDetailService(this);
    }

    /**
     * Password encoder password encoder.
     *
     * @return the password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        this.passwordEncoder = createPasswordEncoder();
        SimplePasswordEncoder.initPasswordEncoder(this.passwordEncoder);
        return passwordEncoder;
    }

    /**
     * Spring security config security adapter config.
     *
     * @return the security adapter config
     */
    @Bean
    public SecurityAdapterConfig springSecurityConfig() {
        this.securityAdapterConfig = new SecurityAdapterConfig(this);
        return this.securityAdapterConfig;
    }

    /**
     * Authentication manager authentication manager.
     *
     * @return the authentication manager
     * @throws Exception the exception
     */
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        this.authenticationManager = this.securityAdapterConfig.authenticationManagerBean();
        return this.authenticationManager;
    }

    /**
     * Simple user details resolver simple user details resolver.
     *
     * @return the simple user details resolver
     */
    public SimpleUserDetailsResolver simpleUserDetailsResolver() {
        return new SimpleUserDetailsResolver(this);
    }


    /**
     * Configure.
     *
     * @param http the http
     * @throws Exception the exception
     */
    public abstract void configure(HttpSecurity http) throws Exception;

    /**
     * Add interceptors.
     *
     * @param registry the registry
     */
    public void addInterceptors(InterceptorRegistry registry) {
    }

    /**
     * Add resource handlers.
     *
     * @param registry the registry
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    }

    /**
     * Add cors mappings.
     *
     * @param registry the registry
     */
    public void addCorsMappings(CorsRegistry registry) {
        if (isUseCors()) {
            registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*");
        }
    }

    /**
     * Configure path match.
     *
     * @param configurer the configurer
     */
    public void configurePathMatch(PathMatchConfigurer configurer) {
    }

    /**
     * Configure content negotiation.
     *
     * @param configurer the configurer
     */
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    }

    /**
     * Configure async support.
     *
     * @param configurer the configurer
     */
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
    }

    /**
     * Configure default servlet handling.
     *
     * @param configurer the configurer
     */
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    }

    /**
     * Add formatters.
     *
     * @param registry the registry
     */
    public void addFormatters(FormatterRegistry registry) {
    }

    /**
     * Add view controllers.
     *
     * @param registry the registry
     */
    public void addViewControllers(ViewControllerRegistry registry) {
    }

    /**
     * Configure view resolvers.
     *
     * @param registry the registry
     */
    public void configureViewResolvers(ViewResolverRegistry registry) {
    }

    /**
     * Add argument resolvers.
     *
     * @param resolvers the resolvers
     */
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    }

    /**
     * Add return value handlers.
     *
     * @param handlers the handlers
     */
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
    }

    /**
     * Configure message converters.
     *
     * @param converters the converters
     */
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    }

    /**
     * Extend message converters.
     *
     * @param converters the converters
     */
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    }

    /**
     * Configure handler exception resolvers.
     *
     * @param resolvers the resolvers
     */
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
    }

    /**
     * Extend handler exception resolvers.
     *
     * @param resolvers the resolvers
     */
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
    }

    /**
     * Is use csrf boolean.
     *
     * @return the boolean
     */
    public abstract boolean isUseCsrf();

    /**
     * Is use cors boolean.
     *
     * @return the boolean
     */
    public abstract boolean isUseCors();

    /**
     * Create password encoder password encoder.
     *
     * @return the password encoder
     */
    protected PasswordEncoder createPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    private Authentication getAuthentication(String userName, String password, HttpSession httpSession) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName, password);
        Authentication authentication = this.authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        return authentication;
    }


}
