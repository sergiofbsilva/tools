package pt.utl.ist.fenix.tools.file.dspace;

import java.io.Serializable;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.tree.BaseElement;

import pt.utl.ist.fenix.tools.file.XMLSerializable;

public class RemoveFileFromItemResponse implements Serializable, XMLSerializable {

    String error;

    public RemoveFileFromItemResponse() {
        super();
    }

    public RemoveFileFromItemResponse(String error) {
        this();
        this.error = error;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toXMLString() {
        return toXML().asXML();

    }

    public Element toXML() {
        Element rootElement = new BaseElement("removeFileFromItemResponse");

        if (getError() != null) {
            rootElement.addElement("error").setText(getError());
        }

        return rootElement;
    }

    public static RemoveFileFromItemResponse createFromXml(String xml) {
        RemoveFileFromItemResponse retVal = new RemoveFileFromItemResponse();
        retVal.fromXMLString(xml);
        return retVal;
    }

    @Override
    public void fromXMLString(String xml) {
        try {
            fromXML(DocumentHelper.parseText(xml).getRootElement());
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    public void fromXML(Element xmlElement) {

        Element errorElement = xmlElement.element("error");

        if (errorElement != null) {
            this.error = errorElement.getText();
        }
    }
}
