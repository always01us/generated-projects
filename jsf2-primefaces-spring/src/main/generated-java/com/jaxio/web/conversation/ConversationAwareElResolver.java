/*
 * (c) Copyright 2005-2012 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-jsf2-spring:src/main/java/conversation/ConversationAwareElResolver.p.vm.java
 */
package com.jaxio.web.conversation;

import javax.el.ELContext;
import javax.el.ELException;

import org.springframework.web.jsf.el.SpringBeanFacesELResolver;

public class ConversationAwareElResolver extends SpringBeanFacesELResolver {

    @Override
    public Object getValue(ELContext elContext, Object base, Object property) throws ELException {
        if (base == null && property != null) {
            Conversation currentConversation = ConversationManager.getInstance().getCurrentConversation();
            if (currentConversation != null) {
                Object result = currentConversation.getVar(property.toString());
                if (result != null) {
                    elContext.setPropertyResolved(true);
                    return result;
                }
            }
        }

        return super.getValue(elContext, base, property);
    }

    @Override
    public Class<?> getType(ELContext elContext, Object base, Object property) throws ELException {
        if (base == null && property != null) {
            Conversation currentConversation = ConversationManager.getInstance().getCurrentConversation();
            if (currentConversation != null) {
                Object value = currentConversation.getVar(property.toString());
                if (value != null) {
                    elContext.setPropertyResolved(true);
                    return value.getClass();
                }
            }
        }

        return super.getType(elContext, base, property);
    }
}
