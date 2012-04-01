/*
 * (c) Copyright 2005-2012 JAXIO, www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-sd:src/main/java/project/util/WithoutTrailingImplBeanNameGenerator.p.vm.java
 */
package com.jaxio.demo.util;

import static org.apache.commons.lang.StringUtils.substringBeforeLast;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

/**
 * {@link AnnotationBeanNameGenerator} to remove the trailing <code>Impl</code> of the spring bean names that are picked by spring using the annotation scanning feature.
 * <p>
 * To enable in your applicationContext:
 * <pre>
 *  &lt;context:component-scan base-package="com.jaxio.demo" name-generator="com.jaxio.demo.util.WithoutTrailingImplBeanNameGenerator"&gt;
 * </pre>
 * @see AnnotationBeanNameGenerator
 */
public class WithoutTrailingImplBeanNameGenerator extends AnnotationBeanNameGenerator {
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        String beanName = super.generateBeanName(definition, registry);
        return beanName.endsWith("Impl") ? substringBeforeLast(beanName, "Impl") : beanName;
    }
}
