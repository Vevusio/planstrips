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
    "askOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrVariableAssignment"
})
@XmlRootElement(name = "the-single-step")
public class TheSingleStep {

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
        @XmlElement(name = "ask", required = true, type = Ask.class),
        @XmlElement(name = "go-to-next", required = true, type = GoToNext.class),
        @XmlElement(name = "go-to-previous", required = true, type = GoToPrevious.class),
        @XmlElement(name = "if-then-else", required = true, type = IfThenElse.class),
        @XmlElement(name = "insert-after-iterator", required = true, type = InsertAfterIterator.class),
        @XmlElement(name = "insert-after-position", required = true, type = InsertAfterPosition.class),
        @XmlElement(name = "insert-before-iterator", required = true, type = InsertBeforeIterator.class),
        @XmlElement(name = "insert-before-position", required = true, type = InsertBeforePosition.class),
        @XmlElement(name = "parameter-assignment", required = true, type = ParameterAssignment.class),
        @XmlElement(name = "plan-activation", required = true, type = PlanActivation.class),
        @XmlElement(name = "put-first", required = true, type = PutFirst.class),
        @XmlElement(name = "put-last", required = true, type = PutLast.class),
        @XmlElement(name = "remove-at-iterator", required = true, type = RemoveAtIterator.class),
        @XmlElement(name = "remove-at-position", required = true, type = RemoveAtPosition.class),
        @XmlElement(name = "remove-element", required = true, type = RemoveElement.class),
        @XmlElement(name = "reset-iterator", required = true, type = ResetIterator.class),
        @XmlElement(name = "set-context", required = true, type = SetContext.class),
        @XmlElement(name = "set-iterator", required = true, type = SetIterator.class),
        @XmlElement(name = "variable-assignment", required = true, type = VariableAssignment.class)
    })
    protected List<Object> askOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrVariableAssignment;

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
     * Gets the value of the askOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrVariableAssignment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the askOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrVariableAssignment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAskOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrVariableAssignment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Ask }
     * {@link GoToNext }
     * {@link GoToPrevious }
     * {@link IfThenElse }
     * {@link InsertAfterIterator }
     * {@link InsertAfterPosition }
     * {@link InsertBeforeIterator }
     * {@link InsertBeforePosition }
     * {@link ParameterAssignment }
     * {@link PlanActivation }
     * {@link PutFirst }
     * {@link PutLast }
     * {@link RemoveAtIterator }
     * {@link RemoveAtPosition }
     * {@link RemoveElement }
     * {@link ResetIterator }
     * {@link SetContext }
     * {@link SetIterator }
     * {@link VariableAssignment }
     * 
     * 
     */
    public List<Object> getAskOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrVariableAssignment() {
        if (askOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrVariableAssignment == null) {
            askOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrVariableAssignment = new ArrayList<Object>();
        }
        return this.askOrGoToNextOrGoToPreviousOrIfThenElseOrInsertAfterIteratorOrInsertAfterPositionOrInsertBeforeIteratorOrInsertBeforePositionOrParameterAssignmentOrPlanActivationOrPutFirstOrPutLastOrRemoveAtIteratorOrRemoveAtPositionOrRemoveElementOrResetIteratorOrSetContextOrSetIteratorOrVariableAssignment;
    }

}
