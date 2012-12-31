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

import com.jaxio.domain.Book;
import com.jaxio.printer.DiscoverablePrinter;

/**
 * {@link org.springframework.format.Printer} for {@link Book} 
 *
 * @see org.springframework.format.Printer
 * @see DiscoverablePrinter
 * @see TypeAwarePrinter
 */
@Named
@Singleton
public class BookPrinter extends DiscoverablePrinter<Book> {
    public BookPrinter() {
        super(Book.class);
    }

    @Override
    public String print(Book book, Locale locale) {
        if (book == null) {
            return "";
        }
        StringBuilder ret = new StringBuilder(256);
        if (!isBlank(book.getTitle())) {
            if (ret.length() != 0) {
                ret.append('/');
            }
            ret.append(book.getTitle().trim());
        }
        return ret.toString();
    }
}
