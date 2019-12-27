package com.oven.multitenant;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import static com.oven.multitenant.MultiTenantConstants.CURRENT_TENANT_IDENTIFIER;
import static com.oven.multitenant.MultiTenantConstants.DEFAULT_TENANT_ID;

@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {

    @Override
    public String resolveCurrentTenantIdentifier() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            String tenantId = (String) requestAttributes.getAttribute(CURRENT_TENANT_IDENTIFIER, RequestAttributes.SCOPE_REQUEST);
            if (tenantId != null) {
                return tenantId;
            }
        }
        return DEFAULT_TENANT_ID;
    }

    public void forceCurrentTenantIndetifier(String tenant) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            requestAttributes.setAttribute(CURRENT_TENANT_IDENTIFIER, tenant, RequestAttributes.SCOPE_REQUEST);
        }
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }

}
