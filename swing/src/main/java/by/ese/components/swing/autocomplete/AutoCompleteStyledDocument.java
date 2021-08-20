/*
 * $Id$
 *
 * Copyright 2008 Sun Microsystems, Inc., 4150 Network Circle,
 * Santa Clara, California 95054, U.S.A. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package by.ese.components.swing.autocomplete;

import by.ese.components.swing.autocomplete.adaptor.AutoCompleteAdaptor;
import by.ese.components.swing.autocomplete.converter.ObjectToStringConverter;

import javax.swing.text.*;
import java.awt.*;

/**
 * @author Karl George Schaefer
 */
public class AutoCompleteStyledDocument extends AutoCompleteDocument implements
        StyledDocument {
    /**
     * @param adaptor AutoCompleteAdaptor
     * @param strictMatching strict match input text (allow or disallow input of chars not in list)
     * @param stringConverter Object to string converter
     * @param delegate Original document
     */
    public AutoCompleteStyledDocument(AutoCompleteAdaptor adaptor,
                                      boolean strictMatching, ObjectToStringConverter stringConverter,
                                      StyledDocument delegate) {
        super(adaptor, strictMatching, stringConverter, delegate);
    }

    /**
     * @param adaptor AutoCompleteAdaptor
     * @param strictMatching strict match input text (allow or disallow input of chars not in list)
     * @param stringConverter Object to string converter
     */
    public AutoCompleteStyledDocument(AutoCompleteAdaptor adaptor,
                                      boolean strictMatching, ObjectToStringConverter stringConverter) {
        super(adaptor, strictMatching, stringConverter);
    }

    /**
     * @param adaptor AutoCompleteAdaptor
     * @param strictMatching strict match input text (allow or disallow input of chars not in list)
     */
    public AutoCompleteStyledDocument(AutoCompleteAdaptor adaptor,
                                      boolean strictMatching) {
        super(adaptor, strictMatching);
    }

    @Override
    protected Document createDefaultDocument() {
        return new DefaultStyledDocument();
    }

    @Override
    public Style addStyle(String nm, Style parent) {
        return ((StyledDocument) delegate).addStyle(nm, parent);
    }

    @Override
    public Color getBackground(AttributeSet attr) {
        return ((StyledDocument) delegate).getBackground(attr);
    }

    @Override
    public Element getCharacterElement(int pos) {
        return ((StyledDocument) delegate).getCharacterElement(pos);
    }

    @Override
    public Font getFont(AttributeSet attr) {
        return ((StyledDocument) delegate).getFont(attr);
    }

    @Override
    public Color getForeground(AttributeSet attr) {
        return ((StyledDocument) delegate).getForeground(attr);
    }

    @Override
    public Style getLogicalStyle(int p) {
        return ((StyledDocument) delegate).getLogicalStyle(p);
    }

    @Override
    public Element getParagraphElement(int pos) {
        return ((StyledDocument) delegate).getParagraphElement(pos);
    }

    @Override
    public Style getStyle(String nm) {
        return ((StyledDocument) delegate).getStyle(nm);
    }

    @Override
    public void removeStyle(String nm) {
        ((StyledDocument) delegate).removeStyle(nm);
    }

    @Override
    public void setCharacterAttributes(int offset, int length,
                                       AttributeSet s, boolean replace) {
        ((StyledDocument) delegate).setCharacterAttributes(offset, length, s, replace);
    }

    @Override
    public void setLogicalStyle(int pos, Style s) {
        ((StyledDocument) delegate).setLogicalStyle(pos, s);
    }

    @Override
    public void setParagraphAttributes(int offset, int length,
                                       AttributeSet s, boolean replace) {
        ((StyledDocument) delegate).setParagraphAttributes(offset, length, s, replace);
    }
}
