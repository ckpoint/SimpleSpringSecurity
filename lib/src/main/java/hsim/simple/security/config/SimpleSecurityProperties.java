package hsim.simple.security.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class SimpleSecurityProperties {

    @Value("${simple.security.cors.enable:false}")
    private boolean enableCors;

    @Value("${simple.security.csrf.enable:true}")
    private boolean enableCsrf;

    @Value("${simple.security.cors.url:/**}")
    private String corsMappingUrl;

    @Value("${simple.security.cors.origins:*}")
    private String corsAllowedOrigins;

    @Value("${simple.security.cors.methods:*}")
    private String corsAllowedMethods;

    @Value("${simple.security.cors.headers:*}")
    private String corsAllowedHeaders;

    @Value("${simple.security.use.password:true}")
    private boolean usePassword;

    @Value("${simple.security.use.password.encrypt:true}")
    private boolean usePasswordEncrypt;
}
