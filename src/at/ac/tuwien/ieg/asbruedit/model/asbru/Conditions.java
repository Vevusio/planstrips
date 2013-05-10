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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "deltaLinkOrGmtLink",
    "bibrefOrCommentOrNotYetDefinedOrUrl",
    "filterPrecondition",
    "setupPrecondition",
    "suspendCondition",
    "reactivateCondition",
    "completeCondition",
    "abortCondition"
})
@XmlRootElement(name = "conditions")
public class Conditions {

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
    @XmlElement(name = "filter-precondition")
    protected FilterPrecondition filterPrecondition;
    @XmlElement(name = "setup-precondition")
    protected SetupPrecondition setupPrecondition;
    @XmlElement(name = "suspend-condition")
    protected SuspendCondition suspendCondition;
    @XmlElement(name = "reactivate-condition")
    protected ReactivateCondition reactivateCondition;
    @XmlElement(name = "complete-condition")
    protected CompleteCondition completeCondition;
    @XmlElement(name = "abort-condition")
    protected AbortCondition abortCondition;

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
     * Gets the value of the filterPrecondition property.
     * 
     * @return
     *     possible object is
     *     {@link FilterPrecondition }
     *     
     */
    public FilterPrecondition getFilterPrecondition() {
        return filterPrecondition;
    }

    /**
     * Sets the value of the filterPrecondition property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterPrecondition }
     *     
     */
    public void setFilterPrecondition(FilterPrecondition value) {
        this.filterPrecondition = value;
    }

    /**
     * Gets the value of the setupPrecondition property.
     * 
     * @return
     *     possible object is
     *     {@link SetupPrecondition }
     *     
     */
    public SetupPrecondition getSetupPrecondition() {
        return setupPrecondition;
    }

    /**
     * Sets the value of the setupPrecondition property.
     * 
     * @param value
     *     allowed object is
     *     {@link SetupPrecondition }
     *     
     */
    public void setSetupPrecondition(SetupPrecondition value) {
        this.setupPrecondition = value;
    }

    /**
     * Gets the value of the suspendCondition property.
     * 
     * @return
     *     possible object is
     *     {@link SuspendCondition }
     *     
     */
    public SuspendCondition getSuspendCondition() {
        return suspendCondition;
    }

    /**
     * Sets the value of the suspendCondition property.
     * 
     * @param value
     *     allowed object is
     *     {@link SuspendCondition }
     *     
     */
    public void setSuspendCondition(SuspendCondition value) {
        this.suspendCondition = value;
    }

    /**
     * Gets the value of the reactivateCondition property.
     * 
     * @return
     *     possible object is
     *     {@link ReactivateCondition }
     *     
     */
    public ReactivateCondition getReactivateCondition() {
        return reactivateCondition;
    }

    /**
     * Sets the value of the reactivateCondition property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReactivateCondition }
     *     
     */
    public void setReactivateCondition(ReactivateCondition value) {
        this.reactivateCondition = value;
    }

    /**
     * Gets the value of the completeCondition property.
     * 
     * @return
     *     possible object is
     *     {@link CompleteCondition }
     *     
     */
    public CompleteCondition getCompleteCondition() {
        return completeCondition;
    }

    /**
     * Sets the value of the completeCondition property.
     * 
     * @param value
     *     allowed object is
     *     {@link CompleteCondition }
     *     
     */
    public void setCompleteCondition(CompleteCondition value) {
        this.completeCondition = value;
    }

    /**
     * Gets the value of the abortCondition property.
     * 
     * @return
     *     possible object is
     *     {@link AbortCondition }
     *     
     */
    public AbortCondition getAbortCondition() {
        return abortCondition;
    }

    /**
     * Sets the value of the abortCondition property.
     * 
     * @param value
     *     allowed object is
     *     {@link AbortCondition }
     *     
     */
    public void setAbortCondition(AbortCondition value) {
        this.abortCondition = value;
    }

}