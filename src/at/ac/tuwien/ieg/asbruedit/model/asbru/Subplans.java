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
    "waitFor",
    "abortIf",
    "askOrCyclicalPlanOrForEachPlanOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrIterativePlanOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrReferToOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrSubplansOrToBeDefinedOrUserPerformedOrVariableAssignment"
})
@XmlRootElement(name = "subplans")
public class Subplans {

    @XmlAttribute(name = "label")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String label;
    @XmlAttribute(name = "type", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String type;
    @XmlAttribute(name = "wait-for-optional-subplans")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String waitForOptionalSubplans;
    @XmlAttribute(name = "retry-aborted-subplans")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String retryAbortedSubplans;
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
    @XmlElement(name = "wait-for", required = true)
    protected WaitFor waitFor;
    @XmlElement(name = "abort-if")
    protected AbortIf abortIf;
    @XmlElements({
        @XmlElement(name = "ask", required = true, type = Ask.class),
        @XmlElement(name = "cyclical-plan", required = true, type = CyclicalPlan.class),
        @XmlElement(name = "for-each-plan", required = true, type = ForEachPlan.class),
        @XmlElement(name = "go-to-next", required = true, type = GoToNext.class),
        @XmlElement(name = "go-to-previous", required = true, type = GoToPrevious.class),
        @XmlElement(name = "if-then-else", required = true, type = IfThenElse.class),
        @XmlElement(name = "insert-after-iterator", required = true, type = InsertAfterIterator.class),
        @XmlElement(name = "insert-after-position", required = true, type = InsertAfterPosition.class),
        @XmlElement(name = "insert-before-iterator", required = true, type = InsertBeforeIterator.class),
        @XmlElement(name = "insert-before-position", required = true, type = InsertBeforePosition.class),
        @XmlElement(name = "iterative-plan", required = true, type = IterativePlan.class),
        @XmlElement(name = "parameter-assignment", required = true, type = ParameterAssignment.class),
        @XmlElement(name = "plan-activation", required = true, type = PlanActivation.class),
        @XmlElement(name = "put-first", required = true, type = PutFirst.class),
        @XmlElement(name = "put-last", required = true, type = PutLast.class),
        @XmlElement(name = "refer-to", required = true, type = ReferTo.class),
        @XmlElement(name = "remove-at-iterator", required = true, type = RemoveAtIterator.class),
        @XmlElement(name = "remove-at-position", required = true, type = RemoveAtPosition.class),
        @XmlElement(name = "remove-element", required = true, type = RemoveElement.class),
        @XmlElement(name = "reset-iterator", required = true, type = ResetIterator.class),
        @XmlElement(name = "set-context", required = true, type = SetContext.class),
        @XmlElement(name = "set-iterator", required = true, type = SetIterator.class),
        @XmlElement(name = "subplans", required = true, type = Subplans.class),
        @XmlElement(name = "to-be-defined", required = true, type = ToBeDefined.class),
        @XmlElement(name = "user-performed", required = true, type = UserPerformed.class),
        @XmlElement(name = "variable-assignment", required = true, type = VariableAssignment.class)
    })
    protected List<Object> askOrCyclicalPlanOrForEachPlanOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrIterativePlanOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrReferToOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrSubplansOrToBeDefinedOrUserPerformedOrVariableAssignment;

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
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the waitForOptionalSubplans property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWaitForOptionalSubplans() {
        if (waitForOptionalSubplans == null) {
            return "no";
        } else {
            return waitForOptionalSubplans;
        }
    }

    /**
     * Sets the value of the waitForOptionalSubplans property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWaitForOptionalSubplans(String value) {
        this.waitForOptionalSubplans = value;
    }

    /**
     * Gets the value of the retryAbortedSubplans property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRetryAbortedSubplans() {
        if (retryAbortedSubplans == null) {
            return "no";
        } else {
            return retryAbortedSubplans;
        }
    }

    /**
     * Sets the value of the retryAbortedSubplans property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRetryAbortedSubplans(String value) {
        this.retryAbortedSubplans = value;
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
     * Gets the value of the waitFor property.
     * 
     * @return
     *     possible object is
     *     {@link WaitFor }
     *     
     */
    public WaitFor getWaitFor() {
        return waitFor;
    }

    /**
     * Sets the value of the waitFor property.
     * 
     * @param value
     *     allowed object is
     *     {@link WaitFor }
     *     
     */
    public void setWaitFor(WaitFor value) {
        this.waitFor = value;
    }

    /**
     * Gets the value of the abortIf property.
     * 
     * @return
     *     possible object is
     *     {@link AbortIf }
     *     
     */
    public AbortIf getAbortIf() {
        return abortIf;
    }

    /**
     * Sets the value of the abortIf property.
     * 
     * @param value
     *     allowed object is
     *     {@link AbortIf }
     *     
     */
    public void setAbortIf(AbortIf value) {
        this.abortIf = value;
    }

    /**
     * Gets the value of the askOrCyclicalPlanOrForEachPlanOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrIterativePlanOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrReferToOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrSubplansOrToBeDefinedOrUserPerformedOrVariableAssignment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the askOrCyclicalPlanOrForEachPlanOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrIterativePlanOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrReferToOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrSubplansOrToBeDefinedOrUserPerformedOrVariableAssignment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAskOrCyclicalPlanOrForEachPlanOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrIterativePlanOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrReferToOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrSubplansOrToBeDefinedOrUserPerformedOrVariableAssignment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Ask }
     * {@link CyclicalPlan }
     * {@link ForEachPlan }
     * {@link GoToNext }
     * {@link GoToPrevious }
     * {@link IfThenElse }
     * {@link InsertAfterIterator }
     * {@link InsertAfterPosition }
     * {@link InsertBeforeIterator }
     * {@link InsertBeforePosition }
     * {@link IterativePlan }
     * {@link ParameterAssignment }
     * {@link PlanActivation }
     * {@link PutFirst }
     * {@link PutLast }
     * {@link ReferTo }
     * {@link RemoveAtIterator }
     * {@link RemoveAtPosition }
     * {@link RemoveElement }
     * {@link ResetIterator }
     * {@link SetContext }
     * {@link SetIterator }
     * {@link Subplans }
     * {@link ToBeDefined }
     * {@link UserPerformed }
     * {@link VariableAssignment }
     * 
     * 
     */
    public List<Object> getAskOrCyclicalPlanOrForEachPlanOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrIterativePlanOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrReferToOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrSubplansOrToBeDefinedOrUserPerformedOrVariableAssignment() {
        if (askOrCyclicalPlanOrForEachPlanOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrIterativePlanOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrReferToOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrSubplansOrToBeDefinedOrUserPerformedOrVariableAssignment == null) {
            askOrCyclicalPlanOrForEachPlanOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrIterativePlanOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrReferToOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrSubplansOrToBeDefinedOrUserPerformedOrVariableAssignment = new ArrayList<Object>();
        }
        return this.askOrCyclicalPlanOrForEachPlanOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrIterativePlanOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrReferToOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrSubplansOrToBeDefinedOrUserPerformedOrVariableAssignment;
    }

}
