package hsim.simple.security.resolver;

import hsim.simple.security.account.SimpleUserDetails;
import hsim.simple.security.service.SimpleSecurityService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
public class SimpleUserDetailsResolver implements HandlerMethodArgumentResolver {

    @NonNull
    private final SimpleSecurityService simpleSecurityService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().getSuperclass().equals(SimpleUserDetails.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        return simpleSecurityService.getSession((Class<? extends SimpleUserDetails>) methodParameter.getParameterType());
    }

}