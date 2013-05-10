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
    "numericalScaleDefOrPatientRecordDefOrQualitativeScaleDefOrRecordDefOrUnitDef",
    "constantDefOrIteratorDefOrVariableDef",
    "functionDef",
    "cyclicalTimeAnnotationAssignmentOrCyclicalTimePointAssignmentOrTimeAnnotationAssignment"
})
@XmlRootElement(name = "library-defs")
public class LibraryDefs {

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
        @XmlElement(name = "numerical-scale-def", type = NumericalScaleDef.class),
        @XmlElement(name = "patient-record-def", type = PatientRecordDef.class),
        @XmlElement(name = "qualitative-scale-def", type = QualitativeScaleDef.class),
        @XmlElement(name = "record-def", type = RecordDef.class),
        @XmlElement(name = "unit-def", type = UnitDef.class)
    })
    protected List<Object> numericalScaleDefOrPatientRecordDefOrQualitativeScaleDefOrRecordDefOrUnitDef;
    @XmlElements({
        @XmlElement(name = "constant-def", type = ConstantDef.class),
        @XmlElement(name = "iterator-def", type = IteratorDef.class),
        @XmlElement(name = "variable-def", type = VariableDef.class)
    })
    protected List<Object> constantDefOrIteratorDefOrVariableDef;
    @XmlElement(name = "function-def")
    protected List<FunctionDef> functionDef;
    @XmlElements({
        @XmlElement(name = "cyclical-time-annotation-assignment", type = CyclicalTimeAnnotationAssignment.class),
        @XmlElement(name = "cyclical-time-point-assignment", type = CyclicalTimePointAssignment.class),
        @XmlElement(name = "time-annotation-assignment", type = TimeAnnotationAssignment.class)
    })
    protected List<Object> cyclicalTimeAnnotationAssignmentOrCyclicalTimePointAssignmentOrTimeAnnotationAssignment;

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
     * Gets the value of the numericalScaleDefOrPatientRecordDefOrQualitativeScaleDefOrRecordDefOrUnitDef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the numericalScaleDefOrPatientRecordDefOrQualitativeScaleDefOrRecordDefOrUnitDef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNumericalScaleDefOrPatientRecordDefOrQualitativeScaleDefOrRecordDefOrUnitDef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NumericalScaleDef }
     * {@link PatientRecordDef }
     * {@link QualitativeScaleDef }
     * {@link RecordDef }
     * {@link UnitDef }
     * 
     * 
     */
    public List<Object> getNumericalScaleDefOrPatientRecordDefOrQualitativeScaleDefOrRecordDefOrUnitDef() {
        if (numericalScaleDefOrPatientRecordDefOrQualitativeScaleDefOrRecordDefOrUnitDef == null) {
            numericalScaleDefOrPatientRecordDefOrQualitativeScaleDefOrRecordDefOrUnitDef = new ArrayList<Object>();
        }
        return this.numericalScaleDefOrPatientRecordDefOrQualitativeScaleDefOrRecordDefOrUnitDef;
    }

    /**
     * Gets the value of the constantDefOrIteratorDefOrVariableDef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the constantDefOrIteratorDefOrVariableDef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConstantDefOrIteratorDefOrVariableDef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConstantDef }
     * {@link IteratorDef }
     * {@link VariableDef }
     * 
     * 
     */
    public List<Object> getConstantDefOrIteratorDefOrVariableDef() {
        if (constantDefOrIteratorDefOrVariableDef == null) {
            constantDefOrIteratorDefOrVariableDef = new ArrayList<Object>();
        }
        return this.constantDefOrIteratorDefOrVariableDef;
    }

    /**
     * Gets the value of the functionDef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the functionDef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFunctionDef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FunctionDef }
     * 
     * 
     */
    public List<FunctionDef> getFunctionDef() {
        if (functionDef == null) {
            functionDef = new ArrayList<FunctionDef>();
        }
        return this.functionDef;
    }

    /**
     * Gets the value of the cyclicalTimeAnnotationAssignmentOrCyclicalTimePointAssignmentOrTimeAnnotationAssignment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cyclicalTimeAnnotationAssignmentOrCyclicalTimePointAssignmentOrTimeAnnotationAssignment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCyclicalTimeAnnotationAssignmentOrCyclicalTimePointAssignmentOrTimeAnnotationAssignment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CyclicalTimeAnnotationAssignment }
     * {@link CyclicalTimePointAssignment }
     * {@link TimeAnnotationAssignment }
     * 
     * 
     */
    public List<Object> getCyclicalTimeAnnotationAssignmentOrCyclicalTimePointAssignmentOrTimeAnnotationAssignment() {
        if (cyclicalTimeAnnotationAssignmentOrCyclicalTimePointAssignmentOrTimeAnnotationAssignment == null) {
            cyclicalTimeAnnotationAssignmentOrCyclicalTimePointAssignmentOrTimeAnnotationAssignment = new ArrayList<Object>();
        }
        return this.cyclicalTimeAnnotationAssignmentOrCyclicalTimePointAssignmentOrTimeAnnotationAssignment;
    }

}
