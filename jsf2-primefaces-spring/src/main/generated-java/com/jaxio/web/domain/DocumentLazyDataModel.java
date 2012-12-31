/*
 * (c) Copyright 2005-2012 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-jsf2-spring:src/main/java/domain/LazyDataModel.e.vm.java
 */
package com.jaxio.web.domain;

import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;
import com.jaxio.domain.Document;
import com.jaxio.repository.DocumentRepository;
import com.jaxio.repository.support.Repository;
import com.jaxio.web.conversation.ConversationContext;
import com.jaxio.web.converter.DocumentJsfConverter;
import com.jaxio.web.domain.support.GenericLazyDataModel;
import com.jaxio.web.domain.support.GenericSearchForm;

/**
 * Provides server-side pagination for search.
 * TODO: dependencies wiring after deserialization (get inspiration from http://jira.springframework.org/browse/SWF-1224 )
 */
@Named
@Scope("conversation")
public class DocumentLazyDataModel extends GenericLazyDataModel<Document, String, DocumentSearchForm> {
    private static final long serialVersionUID = 1L;

    @Inject
    transient private DocumentRepository documentRepository;
    @Inject
    transient private DocumentJsfConverter documentConverter;
    @Inject
    transient private DocumentSearchForm documentSearchForm;

    @Override
    protected Repository<Document, String> getRepository() {
        return documentRepository;
    }

    @Override
    protected Converter getConverter() {
        return documentConverter;
    }

    @Override
    protected GenericSearchForm<Document, DocumentSearchForm> getSearchForm() {
        return documentSearchForm;
    }

    @Override
    protected ConversationContext<Document> getSelectedContext(Document selected) {
        DocumentContext documentContext = new DocumentContext(selected);
        documentContext.setUri(DocumentContext.editUri);
        return documentContext;
    }
}