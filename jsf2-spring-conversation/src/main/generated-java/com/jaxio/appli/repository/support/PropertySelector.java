/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to purchase Celerio ? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Template pack-backend-jpa:src/main/java/repository/support/PropertySelector.p.vm.java
 */
package com.jaxio.appli.repository.support;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;

import java.io.Serializable;
import java.util.List;

import javax.persistence.metamodel.Attribute;

import com.jaxio.appli.domain.LabelizedEnum;

/**
 * Used to construct OR predicate for a property value. In other words you can search
 * all entities E having a given property set to one of the selected values.
 */
public class PropertySelector<E, F> implements Serializable {
    /**
     * {@link PropertySelector} builder
     */
    public static <E, F> PropertySelector<E, F> newPropertySelector(Attribute<?, ?>... fields) {
        return new PropertySelector<E, F>(checkNotNull(fields));
    }

    /**
     * {@link PropertySelector} builder
     */
    public static <E, F> PropertySelector<E, F> newPropertySelector(boolean orMode, Attribute<?, ?>... fields) {
        PropertySelector<E, F> ps = new PropertySelector<E, F>(checkNotNull(fields));
        return ps.orMode(orMode);
    }

    private static final long serialVersionUID = 1L;

    private final List<Attribute<?, ?>> attributes;
    private List<F> selected = newArrayList();
    private SearchMode searchMode; // for string property only.
    private Boolean notIncludingNull;
    private boolean orMode = true;

    public PropertySelector(Attribute<?, ?>... attributes) {
        this.attributes = newArrayList(checkNotNull(attributes));
        JpaUtil.getInstance().verifyPath(attributes);
    }

    public List<Attribute<?, ?>> getAttributes() {
        return attributes;
    }

    public boolean isNotIncludingNullSet() {
        return notIncludingNull != null;
    }

    public Boolean isNotIncludingNull() {
        return notIncludingNull;
    }

    public PropertySelector<E, F> withoutNull() {
        this.notIncludingNull = true;
        return this;
    }

    /**
     * Get the possible candidates for property.
     */
    public List<F> getSelected() {
        return selected;
    }

    @SuppressWarnings("unchecked")
    public void setSelected(F selected) {
        this.selected = newArrayList(selected);
    }

    public PropertySelector<E, F> add(F object) {
        this.selected.add(object);
        return this;
    }

    /**
     * Set the possible candidates for property.
     */
    public void setSelected(List<F> selected) {
        this.selected = selected;
    }

    public PropertySelector<E, F> selected(F... selected) {
        setSelected(newArrayList(selected));
        return this;
    }

    public boolean isNotEmpty() {
        return selected != null && !selected.isEmpty();
    }

    public void clearSelected() {
        if (selected != null) {
            selected.clear();
        }
    }

    public boolean isBoolean() {
        return isType(Boolean.class);
    }

    public boolean isLabelizedEnum() {
        return isType(LabelizedEnum.class);
    }

    public boolean isString() {
        return isType(String.class);
    }

    public boolean isNumber() {
        return isType(Number.class);
    }

    public boolean isType(Class<?> type) {
        return type.isAssignableFrom(attributes.get(attributes.size() - 1).getJavaType());
    }

    public SearchMode getSearchMode() {
        return searchMode;
    }

    /**
     * In case, the field's type is a String, you can set a searchMode to use. It is null by default.
     */
    public void setSearchMode(SearchMode searchMode) {
        this.searchMode = searchMode;
    }

    public PropertySelector<E, F> searchMode(SearchMode searchMode) {
        setSearchMode(searchMode);
        return this;
    }

    public String getPath() {
        return JpaUtil.getInstance().getPath(getAttributes());
    }

    public boolean isOrMode() {
        return orMode;
    }

    public void setOrMode(boolean orMode) {
        this.orMode = orMode;
    }

    public PropertySelector<E, F> orMode(boolean orMode) {
        setOrMode(orMode);
        return this;
    }
}