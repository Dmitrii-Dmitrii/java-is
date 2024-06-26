package ru.Onshin.SecutiryGateway;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityService {
    public String getCurrentOwnerName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
