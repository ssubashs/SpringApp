//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.01.27 at 11:53:13 AM PST 
//


package com.saml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EvidenceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EvidenceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}AssertionIDRef"/>
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}AssertionURIRef"/>
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}Assertion"/>
 *         &lt;element ref="{urn:oasis:names:tc:SAML:2.0:assertion}EncryptedAssertion"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EvidenceType", propOrder = {
    "assertionIDRefOrAssertionURIRefOrAssertion"
})
public class EvidenceType {

    @XmlElementRefs({
        @XmlElementRef(name = "AssertionURIRef", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "EncryptedAssertion", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Assertion", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "AssertionIDRef", namespace = "urn:oasis:names:tc:SAML:2.0:assertion", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> assertionIDRefOrAssertionURIRefOrAssertion;

    /**
     * Gets the value of the assertionIDRefOrAssertionURIRefOrAssertion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the assertionIDRefOrAssertionURIRefOrAssertion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssertionIDRefOrAssertionURIRefOrAssertion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link EncryptedElementType }{@code >}
     * {@link JAXBElement }{@code <}{@link AssertionType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getAssertionIDRefOrAssertionURIRefOrAssertion() {
        if (assertionIDRefOrAssertionURIRefOrAssertion == null) {
            assertionIDRefOrAssertionURIRefOrAssertion = new ArrayList<JAXBElement<?>>();
        }
        return this.assertionIDRefOrAssertionURIRefOrAssertion;
    }

}
