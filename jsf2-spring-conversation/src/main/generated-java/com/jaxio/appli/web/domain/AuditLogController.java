/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to purchase Celerio ? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Template pack-jsf2-spring-conversation:src/main/java/domain/Controller.e.vm.java
 */
package com.jaxio.appli.web.domain;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.jaxio.appli.domain.AuditLog;
import com.jaxio.appli.printer.AuditLogPrinter;
import com.jaxio.appli.repository.AuditLogRepository;
import com.jaxio.appli.web.domain.support.GenericController;
import com.jaxio.appli.web.permission.AuditLogPermission;

/**
 * Stateless controller for {@link AuditLog} conversation start.
 */
@Named
@Singleton
public class AuditLogController extends GenericController<AuditLog, Integer> {
    public static final String AUDITLOG_EDIT_URI = "/domain/auditLogEdit.faces";
    public static final String AUDITLOG_SELECT_URI = "/domain/auditLogSelect.faces";

    @Inject
    public AuditLogController(AuditLogRepository auditLogRepository, AuditLogPermission auditLogPermission, AuditLogPrinter auditLogPrinter) {
        super(auditLogRepository, auditLogPermission, auditLogPrinter, AUDITLOG_SELECT_URI, AUDITLOG_EDIT_URI);
    }
}