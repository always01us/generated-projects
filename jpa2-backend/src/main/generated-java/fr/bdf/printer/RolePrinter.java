/*
 * (c) Copyright 2005-2012 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/main/java/project/printer/Printer.e.vm.java
 */
package fr.bdf.printer;

import static org.apache.commons.lang.StringUtils.isBlank;

import javax.inject.Named;
import javax.inject.Singleton;

import java.util.Locale;

import fr.bdf.domain.Role;
import fr.bdf.printer.DiscoverablePrinter;

/**
 * {@link org.springframework.format.Printer} for {@link Role} 
 *
 * @see org.springframework.format.Printer
 * @see DiscoverablePrinter
 * @see TypeAwarePrinter
 */
@Named
@Singleton
public class RolePrinter extends DiscoverablePrinter<Role> {
    public RolePrinter() {
        super(Role.class);
    }

    @Override
    public String print(Role role, Locale locale) {
        if (role == null) {
            return "";
        }
        StringBuilder ret = new StringBuilder(256);
        if (!isBlank(role.getRoleName())) {
            if (ret.length() != 0) {
                ret.append('/');
            }
            ret.append(role.getRoleName().trim());
        }
        return ret.toString();
    }
}
