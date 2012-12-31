/*
 * (c) Copyright 2005-2012 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-jsf2-spring:src/main/java/domain/SearchForm.e.vm.java
 */
package com.jaxio.web.domain;

import javax.inject.Named;
import org.springframework.context.annotation.Scope;
import com.jaxio.dao.support.SearchParameters;
import com.jaxio.domain.Role;
import com.jaxio.web.domain.support.GenericSearchForm;

/**
 * Bean used to back the search page.
 * It exposes a {@link Role} instance so it can be used in search by Example query.
 */
@Named
@Scope("conversation")
public class RoleSearchForm extends GenericSearchForm<Role, RoleSearchForm> {
    private static final long serialVersionUID = 1L;

    private Role role = new Role();

    public Role getRole() {
        return role;
    }

    @Override
    protected Role getEntity() {
        return role;
    }

    public SearchParameters toSearchParameters() {
        return new SearchParameters().anywhere();
    }

    @Override
    public RoleSearchForm newInstance() {
        return new RoleSearchForm();
    }

    @Override
    public void resetWithOther(RoleSearchForm other) {
        this.role = other.getRole();
    }
}