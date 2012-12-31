/*
 * (c) Copyright 2005-2012 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-jsf2-spring:src/main/java/domain/Controller.e.vm.java
 */
package com.jaxio.web.domain;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import com.jaxio.domain.Address;
import com.jaxio.repository.AddressRepository;
import com.jaxio.web.conversation.Conversation;
import com.jaxio.web.conversation.ConversationFactory;
import com.jaxio.web.domain.support.GenericController;

/**
 * Stateless controller for {@link Address}.
 */
@Named
@Singleton
public class AddressController extends GenericController<Address, Integer> implements ConversationFactory {

    @Inject
    public AddressController(AddressRepository addressRepository) {
        super(addressRepository);
    }

    // --------------------------------------------
    // ConversationFactory impl
    // --------------------------------------------

    @Override
    public boolean canCreate(HttpServletRequest request) {
        return AddressContext.selectUri.equals(request.getServletPath());
    }

    @Override
    public Conversation create(HttpServletRequest request) {
        String uri = request.getServletPath();
        if (AddressContext.selectUri.equals(uri)) {
            Conversation conversation = new Conversation();
            AddressContext ctx = new AddressContext();
            ctx.setLabelWithKey("address");
            ctx.setUri(uri);
            conversation.push(ctx);
            return conversation;
        }

        throw new IllegalStateException("Unexpected conversation creation demand");
    }

    protected final AddressContext addressContext() {
        return conversation().getCurrentContext();
    }
}