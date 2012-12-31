/*
 * (c) Copyright 2005-2012 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-jsf2-spring:src/main/java/conversation/Conversation.p.vm.java
 */
package com.jaxio.web.conversation;

import static com.jaxio.web.conversation.ConversationState.ACTIVE;
import static com.jaxio.web.conversation.ConversationState.ENDED;
import static com.jaxio.web.conversation.ConversationState.NOT_STARTED;
import static com.jaxio.web.conversation.ConversationState.PAUSED;

import java.io.Serializable;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

/**
 * A conversation holds a context stack (1 context per view visited) and the entity manager used 
 * during the entire conversation (spans over several view).
 */
public class Conversation implements Serializable {
    private static AtomicInteger counter = new AtomicInteger();

    private static final long serialVersionUID = 1L;

    private static final Logger log = Logger.getLogger(Conversation.class);

    private Stack<ConversationContext<?>> contextes = new Stack<ConversationContext<?>>();
    private String id;
    private ConversationState state = ConversationState.NOT_STARTED;
    private EntityManager em;

    public Conversation() {
        this.id = String.valueOf(counter.incrementAndGet());
    }

    public String getId() {
        return id;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public void push(ConversationContext<?> newContext) {
        newContext.setConversationId(getId());
        contextes.push(newContext);
    }

    public void pushSub(ConversationContext<?> newContext) {
        newContext.setSub(true);
        push(newContext);
    }

    public void pushSubReadOnly(ConversationContext<?> newContext) {
        newContext.setSub(true);
        newContext.setReadonly(true);
        push(newContext);
    }

    public void pushReadOnly(ConversationContext<?> newContext) {
        newContext.setReadonly(true);
        push(newContext);
    }

    @SuppressWarnings("unchecked")
    public <T extends ConversationContext<?>> T getCurrentContext() {
        return (T) contextes.peek();
    }

    public void popContext() {
        if (contextes.size() > 1) {
            contextes.pop();
        } else {
            log.error("Attention, trying to pop the initial context. Sign of unbalanced push/pop");
        }
    }

    /**
     * @return the label of the last pushed context. 
     */
    public String getLabel() {
        return contextes.peek().getLabel();
    }

    /**
     * @return the menu url of the last pushed context. 
     */
    public String getMenuUrl() {
        return contextes.peek().getMenuUrl();
    }

    /**
     * @return the uri of the last pushed context. 
     */
    public String getUri() {
        return contextes.peek().getUri();
    }

    // ------------------------------------------
    // Conversation state
    // ------------------------------------------

    public ConversationState getState() {
        return state;
    }

    public void start() {
        if (state == NOT_STARTED) {
            state = ACTIVE;
        } else {
            throw new IllegalStateException("You cannot start a Conversation whose state is " + state.name());
        }
    }

    public void pause() {
        if (state == ACTIVE) {
            state = PAUSED;
        } else {
            throw new IllegalStateException("You cannot pause a Conversation whose state is " + state.name());
        }
    }

    public void resume() {
        if (state == PAUSED) {
            state = ACTIVE;
        } else {
            throw new IllegalStateException("You cannot resume a Conversation whose state is " + state.name());
        }
    }

    public void end() {
        if (state == ACTIVE) {
            state = ENDED;
            ConversationManager.getInstance().removeConversation(getId());
        } else {
            throw new IllegalStateException("You cannot end a Conversation whose state is " + state.name());
        }
    }

    // ------------------------------------------
    // Methods below use the last pushed context
    // ------------------------------------------

    public void addBean(String name, Object bean) {
        contextes.peek().addBean(name, bean);
    }

    public Object getBean(String name) {
        return contextes.peek().getBean(name);
    }

    public void setVar(String name, Object var) {
        contextes.peek().setVar(name, var);
    }

    public Object getVar(String name) {
        return contextes.peek().getVar(name);
    }

    public <T> T getVar(String name, Class<T> type) {
        return (T) contextes.peek().getVar(name, type);
    }
}
