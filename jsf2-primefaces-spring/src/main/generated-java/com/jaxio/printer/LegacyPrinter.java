/*
 * (c) Copyright 2005-2012 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/main/java/project/printer/Printer.e.vm.java
 */
package com.jaxio.printer;

import static org.apache.commons.lang.StringUtils.isBlank;

import javax.inject.Named;
import javax.inject.Singleton;

import java.util.Locale;

import com.jaxio.domain.Legacy;
import com.jaxio.printer.DiscoverablePrinter;

/**
 * {@link org.springframework.format.Printer} for {@link Legacy} 
 *
 * @see org.springframework.format.Printer
 * @see DiscoverablePrinter
 * @see TypeAwarePrinter
 */
@Named
@Singleton
public class LegacyPrinter extends DiscoverablePrinter<Legacy> {
    public LegacyPrinter() {
        super(Legacy.class);
    }

    @Override
    public String print(Legacy legacy, Locale locale) {
        if (legacy == null) {
            return "";
        }
        StringBuilder ret = new StringBuilder(256);
        if (!isBlank(legacy.getName())) {
            if (ret.length() != 0) {
                ret.append('/');
            }
            ret.append(legacy.getName().trim());
        }
        return ret.toString();
    }
}
