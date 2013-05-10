//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.12.19 at 06:35:24 PM MEZ 
//


package at.ac.tuwien.ieg.asbruedit.model.asbru;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "deltaLinkOrGmtLink",
    "bibrefOrCommentOrNotYetDefinedOrUrl",
    "arrayRefOrFieldRefOrListRefOrMultiSetRefOrSetRefOrVariableRef"
})
@XmlRootElement(name = "multi-set-ref")
public class MultiSetRef {

    @XmlAttribute(name = "iterator", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String iterator;
    @XmlAttribute(name = "iterator-change", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String iteratorChange;
    @XmlElements({
        @XmlElement(name = "delta-link", type = DeltaLink.class),
        @XmlElement(name = "gmt-link", type = GmtLink.class)
    })
    protected List<Object> deltaLinkOrGmtLink;
    @XmlElements({
        @XmlElement(name = "bibref", type = Bibref.class),
        @XmlElement(name = "comment", type = Comment.class),
        @XmlElement(name = "not-yet-defined", type = NotYetDefined.class),
        @XmlElement(name = "url", type = Url.class)
    })
    protected List<Object> bibrefOrCommentOrNotYetDefinedOrUrl;
    @XmlElements({
        @XmlElement(name = "array-ref", required = true, type = ArrayRef.class),
        @XmlElement(name = "field-ref", required = true, type = FieldRef.class),
        @XmlElement(name = "list-ref", required = true, type = ListRef.class),
        @XmlElement(name = "multi-set-ref", required = true, type = MultiSetRef.class),
        @XmlElement(name = "set-ref", required = true, type = SetRef.class),
        @XmlElement(name = "variable-ref", required = true, type = VariableRef.class)
    })
    protected List<Object> arrayRefOrFieldRefOrListRefOrMultiSetRefOrSetRefOrVariableRef;

    /**
     * Gets the value of the iterator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIterator() {
        return iterator;
    }

    /**
     * Sets the value of the iterator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIterator(String value) {
        this.iterator = value;
    }

    /**
     * Gets the value of the iteratorChange property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIteratorChange() {
        return iteratorChange;
    }

    /**
     * Sets the value of the iteratorChange property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIteratorChange(String value) {
        this.iteratorChange = value;
    }

    /**
     * Gets the value of the deltaLinkOrGmtLink property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deltaLinkOrGmtLink property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeltaLinkOrGmtLink().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DeltaLink }
     * {@link GmtLink }
     * 
     * 
     */
    public List<Object> getDeltaLinkOrGmtLink() {
        if (deltaLinkOrGmtLink == null) {
            deltaLinkOrGmtLink = new ArrayList<Object>();
        }
        return this.deltaLinkOrGmtLink;
    }

    /**
     * Gets the value of the bibrefOrCommentOrNotYetDefinedOrUrl property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bibrefOrCommentOrNotYetDefinedOrUrl property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBibrefOrCommentOrNotYetDefinedOrUrl().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Bibref }
     * {@link Comment }
     * {@link NotYetDefined }
     * {@link Url }
     * 
     * 
     */
    public List<Object> getBibrefOrCommentOrNotYetDefinedOrUrl() {
        if (bibrefOrCommentOrNotYetDefinedOrUrl == null) {
            bibrefOrCommentOrNotYetDefinedOrUrl = new ArrayList<Object>();
        }
        return this.bibrefOrCommentOrNotYetDefinedOrUrl;
    }

    /**
     * Gets the value of the arrayRefOrFieldRefOrListRefOrMultiSetRefOrSetRefOrVariableRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the arrayRefOrFieldRefOrListRefOrMultiSetRefOrSetRefOrVariableRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArrayRefOrFieldRefOrListRefOrMultiSetRefOrSetRefOrVariableRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ArrayRef }
     * {@link FieldRef }
     * {@link ListRef }
     * {@link MultiSetRef }
     * {@link SetRef }
     * {@link VariableRef }
     * 
     * 
     */
    public List<Object> getArrayRefOrFieldRefOrListRefOrMultiSetRefOrSetRefOrVariableRef() {
        if (arrayRefOrFieldRefOrListRefOrMultiSetRefOrSetRefOrVariableRef == null) {
            arrayRefOrFieldRefOrListRefOrMultiSetRefOrSetRefOrVariableRef = new ArrayList<Object>();
        }
        return this.arrayRefOrFieldRefOrListRefOrMultiSetRefOrSetRefOrVariableRef;
    }

}
