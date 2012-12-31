/*
 * (c) Copyright 2005-2012 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-jsf2-spring:src/main/java/util/UserContextUtil.p.vm.java
 */
package com.jaxio.web.util;

import java.util.List;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;

import com.jaxio.context.UserContext;
import com.jaxio.web.conversation.Conversation;
import com.jaxio.web.conversation.ConversationManager;

/**
 * Simple pass over to access static 'userContext'from EL.
 * Keeps also the user's preferred locale.
 *
 * @see org.springframework.security.web.authentication.AnonymousAuthenticationFilter
 */
@Named("userContext")
@Scope("session")
public class UserContextUtil {
    private Locale locale;

    /**
     * It is invoked from the f:view tag. 
     * It defaults to the default JSF local.
     * 
     * @return the current user's locale.
     */
    public String getLocale() {
        if (locale == null) {
            locale = FacesContext.getCurrentInstance().getApplication().getDefaultLocale();
        }
        LocaleContextHolder.setLocale(locale);
        return locale.getLanguage();
    }

    public String switchToFrench() {
        return switchToLocale(Locale.FRENCH);
    }

    public String switchToEnglish() {
        return switchToLocale(Locale.ENGLISH);
    }

    private String switchToLocale(Locale locale) {
        this.locale = locale;
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
        LocaleContextHolder.setLocale(locale);
        Conversation currentConversation = ConversationManager.getInstance().getCurrentConversation();

        if (currentConversation != null) {
            return FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true&_cid_=" + currentConversation.getId();
        } else {
            return FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true";
        }
    }

    public String getUsername() {
        return UserContext.getUsername();
    }

    public boolean isAnonymousUser() {
        return UserContext.ANONYMOUS_USER.equalsIgnoreCase(getUsername());
    }

    public boolean isLoggedIn() {
        return !isAnonymousUser();
    }

    public boolean isFrench() {
        if (locale != null) {
            return "fr".equals(locale.getLanguage());
        } else {
            // just in case, but should not happen
            return "fr".equals(LocaleContextHolder.getLocale().getLanguage());
        }
    }

    public List<String> getRoles() {
        return UserContext.getRoles();
    }

    public boolean hasRole(String role) {
        return UserContext.getRoles().contains(role);
    }
}