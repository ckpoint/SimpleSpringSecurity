package hsim.simple.security.event;

import hsim.simple.security.type.SecurityConfigRefreshType;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class SecurityConfigRefreshEvent extends ApplicationEvent {

    @Getter
    private final SecurityConfigRefreshType eventType;

    public SecurityConfigRefreshEvent(Object source, SecurityConfigRefreshType eventType) {
        super(source);
        this.eventType = eventType;
    }
}
