/**
 *
 */
package by.ese.components.swing.autocomplete;

import javax.swing.event.DocumentEvent;
import javax.swing.text.Document;
import javax.swing.text.Element;

/**
 * @author Karl George Schaefer
 *
 */
final class DelegatingDocumentEvent implements DocumentEvent {
    private final Document resourcedDocument;
    private final DocumentEvent sourceEvent;

    public DelegatingDocumentEvent(Document resourcedDocument, DocumentEvent sourceEvent) {
        this.resourcedDocument = resourcedDocument;
        this.sourceEvent = sourceEvent;
    }


    @Override
    public ElementChange getChange(Element elem) {
        return sourceEvent.getChange(elem);
    }


    @Override
    public Document getDocument() {
        return resourcedDocument;
    }


    @Override
    public int getLength() {
        return sourceEvent.getLength();
    }


    @Override
    public int getOffset() {
        return sourceEvent.getOffset();
    }


    @Override
    public EventType getType() {
        return sourceEvent.getType();
    }

}
