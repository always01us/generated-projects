/*
 * (c) Copyright 2005-2012 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/main/java/project/dao/support/OrderBy.p.vm.java
 */
package fr.bdf.dao.support;

import java.io.Serializable;

import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.lang.Validate;

import static fr.bdf.dao.support.OrderByDirection.ASC;
import static fr.bdf.dao.support.OrderByDirection.DESC;
import fr.bdf.domain.Identifiable;

/**
 * Holder class for search ordering used by the {@link SearchParameters}.
 * When used with {@link NamedQueryUtil}, you pass column name. For other usage, pass the property name.
 */
public class OrderBy implements Serializable {
    private static final long serialVersionUID = 1L;
    private String columnOrProperty;
    private OrderByDirection direction = ASC;

    public OrderBy(String columnOrProperty, OrderByDirection direction) {
        Validate.notNull(columnOrProperty);
        Validate.notNull(direction);
        this.columnOrProperty = columnOrProperty;
        this.direction = direction;
    }

    public OrderBy(String columnOrProperty) {
        this(columnOrProperty, ASC);
    }

    public OrderBy(SingularAttribute<? extends Identifiable<? extends Serializable>, ? extends Serializable> attribute, OrderByDirection direction) {
        Validate.notNull(attribute);
        Validate.notNull(direction);
        this.columnOrProperty = attribute.getName();
        this.direction = direction;
    }

    public OrderBy(SingularAttribute<? extends Identifiable<? extends Serializable>, ? extends Serializable> attribute) {
        this(attribute, ASC);
    }

    public String getColumn() {
        return columnOrProperty;
    }

    public String getProperty() {
        return columnOrProperty;
    }

    public OrderByDirection getDirection() {
        return direction;
    }

    public boolean isOrderDesc() {
        return DESC == direction;
    }
}