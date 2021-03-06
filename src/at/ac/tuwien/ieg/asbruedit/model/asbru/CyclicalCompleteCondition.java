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
    "explanation",
    "endTimeOrTimesCompletedOrUntilCondition"
})
@XmlRootElement(name = "cyclical-complete-condition")
public class CyclicalCompleteCondition {

    @XmlAttribute(name = "label")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String label;
    @XmlAttribute(name = "overridable")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String overridable;
    @XmlAttribute(name = "confirmation-required")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String confirmationRequired;
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
    protected List<Explanation> explanation;
    @XmlElements({
        @XmlElement(name = "end-time", required = true, type = EndTime.class),
        @XmlElement(name = "times-completed", required = true, type = TimesCompleted.class),
        @XmlElement(name = "until-condition", required = true, type = UntilCondition.class)
    })
    protected List<Object> endTimeOrTimesCompletedOrUntilCondition;

    /**
     * Gets the value of the label property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets the value of the label property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabel(String value) {
        this.label = value;
    }

    /**
     * Gets the value of the overridable property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOverridable() {
        if (overridable == null) {
            return "no";
        } else {
            return overridable;
        }
    }

    /**
     * Sets the value of the overridable property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOverridable(String value) {
        this.overridable = value;
    }

    /**
     * Gets the value of the confirmationRequired property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfirmationRequired() {
        if (confirmationRequired == null) {
            return "no";
        } else {
            return confirmationRequired;
        }
    }

    /**
     * Sets the value of the confirmationRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfirmationRequired(String value) {
        this.confirmationRequired = value;
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
     * Gets the value of the explanation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the explanation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExplanation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Explanation }
     * 
     * 
     */
    public List<Explanation> getExplanation() {
        if (explanation == null) {
            explanation = new ArrayList<Explanation>();
        }
        return this.explanation;
    }

    /**
     * Gets the value of the endTimeOrTimesCompletedOrUntilCondition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the endTimeOrTimesCompletedOrUntilCondition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEndTimeOrTimesCompletedOrUntilCondition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EndTime }
     * {@link TimesCompleted }
     * {@link UntilCondition }
     * 
     * 
     */
    public List<Object> getEndTimeOrTimesCompletedOrUntilCondition() {
        if (endTimeOrTimesCompletedOrUntilCondition == null) {
            endTimeOrTimesCompletedOrUntilCondition = new ArrayList<Object>();
        }
        return this.endTimeOrTimesCompletedOrUntilCondition;
    }

}
