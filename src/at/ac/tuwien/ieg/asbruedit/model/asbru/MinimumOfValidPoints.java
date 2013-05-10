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
    "constantOperationOrConstantRefOrNumericalConstantOrQualitativeConstantOrStringConstant"
})
@XmlRootElement(name = "minimum-of-valid-points")
public class MinimumOfValidPoints {

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
        @XmlElement(name = "constant-operation", required = true, type = ConstantOperation.class),
        @XmlElement(name = "constant-ref", required = true, type = ConstantRef.class),
        @XmlElement(name = "numerical-constant", required = true, type = NumericalConstant.class),
        @XmlElement(name = "qualitative-constant", required = true, type = QualitativeConstant.class),
        @XmlElement(name = "string-constant", required = true, type = StringConstant.class)
    })
    protected List<Object> constantOperationOrConstantRefOrNumericalConstantOrQualitativeConstantOrStringConstant;

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
     * Gets the value of the constantOperationOrConstantRefOrNumericalConstantOrQualitativeConstantOrStringConstant property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the constantOperationOrConstantRefOrNumericalConstantOrQualitativeConstantOrStringConstant property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConstantOperationOrConstantRefOrNumericalConstantOrQualitativeConstantOrStringConstant().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConstantOperation }
     * {@link ConstantRef }
     * {@link NumericalConstant }
     * {@link QualitativeConstant }
     * {@link StringConstant }
     * 
     * 
     */
    public List<Object> getConstantOperationOrConstantRefOrNumericalConstantOrQualitativeConstantOrStringConstant() {
        if (constantOperationOrConstantRefOrNumericalConstantOrQualitativeConstantOrStringConstant == null) {
            constantOperationOrConstantRefOrNumericalConstantOrQualitativeConstantOrStringConstant = new ArrayList<Object>();
        }
        return this.constantOperationOrConstantRefOrNumericalConstantOrQualitativeConstantOrStringConstant;
    }

}
