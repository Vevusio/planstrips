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
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "deltaLinkOrGmtLink",
    "bibrefOrCommentOrNotYetDefinedOrUrl",
    "minimumValue",
    "maximumValue",
    "maximalIncrease",
    "maximalDecrease",
    "trustPeriod",
    "cyclicalTimeAnnotationOrRepeatSpecification"
})
@XmlRootElement(name = "raw-data-def")
public class RawDataDef {

    @XmlAttribute(name = "unit")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String unit;
    @XmlAttribute(name = "scale")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String scale;
    @XmlAttribute(name = "mode", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String mode;
    @XmlAttribute(name = "channel-name")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String channelName;
    @XmlAttribute(name = "user-text")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String userText;
    @XmlAttribute(name = "use-as-context")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String useAsContext;
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
    @XmlElement(name = "minimum-value")
    protected MinimumValue minimumValue;
    @XmlElement(name = "maximum-value")
    protected MaximumValue maximumValue;
    @XmlElement(name = "maximal-increase")
    protected MaximalIncrease maximalIncrease;
    @XmlElement(name = "maximal-decrease")
    protected MaximalDecrease maximalDecrease;
    @XmlElement(name = "trust-period")
    protected TrustPeriod trustPeriod;
    @XmlElements({
        @XmlElement(name = "cyclical-time-annotation", type = CyclicalTimeAnnotation.class),
        @XmlElement(name = "repeat-specification", type = RepeatSpecification.class)
    })
    protected List<Object> cyclicalTimeAnnotationOrRepeatSpecification;

    /**
     * Gets the value of the unit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets the value of the unit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnit(String value) {
        this.unit = value;
    }

    /**
     * Gets the value of the scale property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScale() {
        return scale;
    }

    /**
     * Sets the value of the scale property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScale(String value) {
        this.scale = value;
    }

    /**
     * Gets the value of the mode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMode() {
        return mode;
    }

    /**
     * Sets the value of the mode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMode(String value) {
        this.mode = value;
    }

    /**
     * Gets the value of the channelName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * Sets the value of the channelName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelName(String value) {
        this.channelName = value;
    }

    /**
     * Gets the value of the userText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserText() {
        return userText;
    }

    /**
     * Sets the value of the userText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserText(String value) {
        this.userText = value;
    }

    /**
     * Gets the value of the useAsContext property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUseAsContext() {
        if (useAsContext == null) {
            return "no";
        } else {
            return useAsContext;
        }
    }

    /**
     * Sets the value of the useAsContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUseAsContext(String value) {
        this.useAsContext = value;
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
     * Gets the value of the minimumValue property.
     * 
     * @return
     *     possible object is
     *     {@link MinimumValue }
     *     
     */
    public MinimumValue getMinimumValue() {
        return minimumValue;
    }

    /**
     * Sets the value of the minimumValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link MinimumValue }
     *     
     */
    public void setMinimumValue(MinimumValue value) {
        this.minimumValue = value;
    }

    /**
     * Gets the value of the maximumValue property.
     * 
     * @return
     *     possible object is
     *     {@link MaximumValue }
     *     
     */
    public MaximumValue getMaximumValue() {
        return maximumValue;
    }

    /**
     * Sets the value of the maximumValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link MaximumValue }
     *     
     */
    public void setMaximumValue(MaximumValue value) {
        this.maximumValue = value;
    }

    /**
     * Gets the value of the maximalIncrease property.
     * 
     * @return
     *     possible object is
     *     {@link MaximalIncrease }
     *     
     */
    public MaximalIncrease getMaximalIncrease() {
        return maximalIncrease;
    }

    /**
     * Sets the value of the maximalIncrease property.
     * 
     * @param value
     *     allowed object is
     *     {@link MaximalIncrease }
     *     
     */
    public void setMaximalIncrease(MaximalIncrease value) {
        this.maximalIncrease = value;
    }

    /**
     * Gets the value of the maximalDecrease property.
     * 
     * @return
     *     possible object is
     *     {@link MaximalDecrease }
     *     
     */
    public MaximalDecrease getMaximalDecrease() {
        return maximalDecrease;
    }

    /**
     * Sets the value of the maximalDecrease property.
     * 
     * @param value
     *     allowed object is
     *     {@link MaximalDecrease }
     *     
     */
    public void setMaximalDecrease(MaximalDecrease value) {
        this.maximalDecrease = value;
    }

    /**
     * Gets the value of the trustPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link TrustPeriod }
     *     
     */
    public TrustPeriod getTrustPeriod() {
        return trustPeriod;
    }

    /**
     * Sets the value of the trustPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrustPeriod }
     *     
     */
    public void setTrustPeriod(TrustPeriod value) {
        this.trustPeriod = value;
    }

    /**
     * Gets the value of the cyclicalTimeAnnotationOrRepeatSpecification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cyclicalTimeAnnotationOrRepeatSpecification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCyclicalTimeAnnotationOrRepeatSpecification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CyclicalTimeAnnotation }
     * {@link RepeatSpecification }
     * 
     * 
     */
    public List<Object> getCyclicalTimeAnnotationOrRepeatSpecification() {
        if (cyclicalTimeAnnotationOrRepeatSpecification == null) {
            cyclicalTimeAnnotationOrRepeatSpecification = new ArrayList<Object>();
        }
        return this.cyclicalTimeAnnotationOrRepeatSpecification;
    }

}
