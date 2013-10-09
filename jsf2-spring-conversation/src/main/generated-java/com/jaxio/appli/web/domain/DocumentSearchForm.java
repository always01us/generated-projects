/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to purchase Celerio ? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Template pack-jsf2-spring-conversation:src/main/java/domain/SearchForm.e.vm.java
 */
package com.jaxio.appli.web.domain;

import static com.jaxio.appli.repository.support.PropertySelector.newPropertySelector;

import javax.inject.Named;

import com.jaxio.appli.domain.Account;
import com.jaxio.appli.domain.Document;
import com.jaxio.appli.domain.Document_;
import com.jaxio.appli.repository.support.PropertySelector;
import com.jaxio.appli.repository.support.SearchParameters;
import com.jaxio.appli.repository.support.TermSelector;
import com.jaxio.appli.web.domain.support.GenericSearchForm;
import com.jaxio.appli.web.faces.ConversationContextScoped;

/**
 * View Helper to search {@link Document}.
 * It exposes a {@link Document} instance so it can be used in search by-example-query.
 */
@Named
@ConversationContextScoped
public class DocumentSearchForm extends GenericSearchForm<Document, String, DocumentSearchForm> {
    private static final long serialVersionUID = 1L;

    // full text search (applied first)
    protected TermSelector documentBinaryTermSelector = new TermSelector(Document_.documentBinary);
    protected TermSelector documentFileNameTermSelector = new TermSelector(Document_.documentFileName);

    // classic search
    protected Document document = new Document();
    protected PropertySelector<Document, Account> ownerSelector = newPropertySelector(Document_.owner);

    public Document getDocument() {
        return document;
    }

    @Override
    protected Document getEntity() {
        return getDocument();
    }

    @Override
    public DocumentSearchForm newInstance() {
        return new DocumentSearchForm();
    }

    @Override
    public SearchParameters toSearchParameters() {
        SearchParameters sp = searchParameters();
        sp.term(termsOnAll, documentBinaryTermSelector, documentFileNameTermSelector);
        sp.fetch(Document_.owner);
        sp.property(ownerSelector);
        return sp;
    }

    @Override
    public void resetWithOther(DocumentSearchForm other) {
        this.document = other.getDocument();
        this.termsOnAll = other.getTermsOnAll();
        this.documentBinaryTermSelector = other.getDocumentBinaryTermSelector();
        this.documentFileNameTermSelector = other.getDocumentFileNameTermSelector();
        this.ownerSelector = other.getOwnerSelector();
    }

    // Term selectors    
    public TermSelector getDocumentBinaryTermSelector() {
        return documentBinaryTermSelector;
    }

    public TermSelector getDocumentFileNameTermSelector() {
        return documentFileNameTermSelector;
    }

    // Relation selectors
    public PropertySelector<Document, Account> getOwnerSelector() {
        return ownerSelector;
    }
}