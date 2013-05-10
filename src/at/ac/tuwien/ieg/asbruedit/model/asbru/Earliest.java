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
    "argumentRefOrArrayRefOrConstantRefOrFieldRefOrFunctionCallOrGetPositionOrListRefOrMultiSetRefOrNowOrNumericalConstantOrOperationOrParameterRefOrPlanStateTransitionOrQualitativeConstantOrSelfOrSetRefOrStringConstantOrVariableRef"
})
@XmlRootElement(name = "earliest")
public class Earliest {

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
        @XmlElement(name = "argument-ref", required = true, type = ArgumentRef.class),
        @XmlElement(name = "array-ref", required = true, type = ArrayRef.class),
        @XmlElement(name = "constant-ref", required = true, type = ConstantRef.class),
        @XmlElement(name = "field-ref", required = true, type = FieldRef.class),
        @XmlElement(name = "function-call", required = true, type = FunctionCall.class),
        @XmlElement(name = "get-position", required = true, type = GetPosition.class),
        @XmlElement(name = "list-ref", required = true, type = ListRef.class),
        @XmlElement(name = "multi-set-ref", required = true, type = MultiSetRef.class),
        @XmlElement(name = "now", required = true, type = Now.class),
        @XmlElement(name = "numerical-constant", required = true, type = NumericalConstant.class),
        @XmlElement(name = "operation", required = true, type = Operation.class),
        @XmlElement(name = "parameter-ref", required = true, type = ParameterRef.class),
        @XmlElement(name = "plan-state-transition", required = true, type = PlanStateTransition.class),
        @XmlElement(name = "qualitative-constant", required = true, type = QualitativeConstant.class),
        @XmlElement(name = "self", required = true, type = Self.class),
        @XmlElement(name = "set-ref", required = true, type = SetRef.class),
        @XmlElement(name = "string-constant", required = true, type = StringConstant.class),
        @XmlElement(name = "variable-ref", required = true, type = VariableRef.class)
    })
    protected List<Object> argumentRefOrArrayRefOrConstantRefOrFieldRefOrFunctionCallOrGetPositionOrListRefOrMultiSetRefOrNowOrNumericalConstantOrOperationOrParameterRefOrPlanStateTransitionOrQualitativeConstantOrSelfOrSetRefOrStringConstantOrVariableRef;

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
     * Gets the value of the argumentRefOrArrayRefOrConstantRefOrFieldRefOrFunctionCallOrGetPositionOrListRefOrMultiSetRefOrNowOrNumericalConstantOrOperationOrParameterRefOrPlanStateTransitionOrQualitativeConstantOrSelfOrSetRefOrStringConstantOrVariableRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the argumentRefOrArrayRefOrConstantRefOrFieldRefOrFunctionCallOrGetPositionOrListRefOrMultiSetRefOrNowOrNumericalConstantOrOperationOrParameterRefOrPlanStateTransitionOrQualitativeConstantOrSelfOrSetRefOrStringConstantOrVariableRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArgumentRefOrArrayRefOrConstantRefOrFieldRefOrFunctionCallOrGetPositionOrListRefOrMultiSetRefOrNowOrNumericalConstantOrOperationOrParameterRefOrPlanStateTransitionOrQualitativeConstantOrSelfOrSetRefOrStringConstantOrVariableRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ArgumentRef }
     * {@link ArrayRef }
     * {@link ConstantRef }
     * {@link FieldRef }
     * {@link FunctionCall }
     * {@link GetPosition }
     * {@link ListRef }
     * {@link MultiSetRef }
     * {@link Now }
     * {@link NumericalConstant }
     * {@link Operation }
     * {@link ParameterRef }
     * {@link PlanStateTransition }
     * {@link QualitativeConstant }
     * {@link Self }
     * {@link SetRef }
     * {@link StringConstant }
     * {@link VariableRef }
     * 
     * 
     */
    public List<Object> getArgumentRefOrArrayRefOrConstantRefOrFieldRefOrFunctionCallOrGetPositionOrListRefOrMultiSetRefOrNowOrNumericalConstantOrOperationOrParameterRefOrPlanStateTransitionOrQualitativeConstantOrSelfOrSetRefOrStringConstantOrVariableRef() {
        if (argumentRefOrArrayRefOrConstantRefOrFieldRefOrFunctionCallOrGetPositionOrListRefOrMultiSetRefOrNowOrNumericalConstantOrOperationOrParameterRefOrPlanStateTransitionOrQualitativeConstantOrSelfOrSetRefOrStringConstantOrVariableRef == null) {
            argumentRefOrArrayRefOrConstantRefOrFieldRefOrFunctionCallOrGetPositionOrListRefOrMultiSetRefOrNowOrNumericalConstantOrOperationOrParameterRefOrPlanStateTransitionOrQualitativeConstantOrSelfOrSetRefOrStringConstantOrVariableRef = new ArrayList<Object>();
        }
        return this.argumentRefOrArrayRefOrConstantRefOrFieldRefOrFunctionCallOrGetPositionOrListRefOrMultiSetRefOrNowOrNumericalConstantOrOperationOrParameterRefOrPlanStateTransitionOrQualitativeConstantOrSelfOrSetRefOrStringConstantOrVariableRef;
    }

}
