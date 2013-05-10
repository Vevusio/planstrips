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
    "averageDefOrBooleanDefOrCalculationDefOrCenterDefOrCentileDefOrChangeDefOrComparisonDefOrConstantOperationOrConstantRefOrDayOfMonthOrDayOfWeekOrDelayDefOrDurationDefOrEndDefOrEndPointDefOrEpisodeAnalysisDefOrEpisodeDefOrHistogramDefOrHourOrLimitDefOrLinearRegressionDefOrLogicalCombinationDefOrLogicalDependencyDefOrMinuteOrMonthOrNowOrNumericalConstantOrParameterRefOrQualitativeConstantOrQualitativeParameterDefOrRawDataDefOrSecondOrSelectionDefOrSlopeDefOrSpreadDefOrStandardDeviationDefOrStandardErrorDefOrStartDefOrStringConstantOrTimeToAlarmDefOrTimeWindowAnalysisDefOrTimeWindowDefOrValidTimeDefOrYear"
})
@XmlRootElement(name = "maximal-decrease")
public class MaximalDecrease {

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
        @XmlElement(name = "average-def", required = true, type = AverageDef.class),
        @XmlElement(name = "boolean-def", required = true, type = BooleanDef.class),
        @XmlElement(name = "calculation-def", required = true, type = CalculationDef.class),
        @XmlElement(name = "center-def", required = true, type = CenterDef.class),
        @XmlElement(name = "centile-def", required = true, type = CentileDef.class),
        @XmlElement(name = "change-def", required = true, type = ChangeDef.class),
        @XmlElement(name = "comparison-def", required = true, type = ComparisonDef.class),
        @XmlElement(name = "constant-operation", required = true, type = ConstantOperation.class),
        @XmlElement(name = "constant-ref", required = true, type = ConstantRef.class),
        @XmlElement(name = "day-of-month", required = true, type = DayOfMonth.class),
        @XmlElement(name = "day-of-week", required = true, type = DayOfWeek.class),
        @XmlElement(name = "delay-def", required = true, type = DelayDef.class),
        @XmlElement(name = "duration-def", required = true, type = DurationDef.class),
        @XmlElement(name = "end-def", required = true, type = EndDef.class),
        @XmlElement(name = "end-point-def", required = true, type = EndPointDef.class),
        @XmlElement(name = "episode-analysis-def", required = true, type = EpisodeAnalysisDef.class),
        @XmlElement(name = "episode-def", required = true, type = EpisodeDef.class),
        @XmlElement(name = "histogram-def", required = true, type = HistogramDef.class),
        @XmlElement(name = "hour", required = true, type = Hour.class),
        @XmlElement(name = "limit-def", required = true, type = LimitDef.class),
        @XmlElement(name = "linear-regression-def", required = true, type = LinearRegressionDef.class),
        @XmlElement(name = "logical-combination-def", required = true, type = LogicalCombinationDef.class),
        @XmlElement(name = "logical-dependency-def", required = true, type = LogicalDependencyDef.class),
        @XmlElement(name = "minute", required = true, type = Minute.class),
        @XmlElement(name = "month", required = true, type = Month.class),
        @XmlElement(name = "now", required = true, type = Now.class),
        @XmlElement(name = "numerical-constant", required = true, type = NumericalConstant.class),
        @XmlElement(name = "parameter-ref", required = true, type = ParameterRef.class),
        @XmlElement(name = "qualitative-constant", required = true, type = QualitativeConstant.class),
        @XmlElement(name = "qualitative-parameter-def", required = true, type = QualitativeParameterDef.class),
        @XmlElement(name = "raw-data-def", required = true, type = RawDataDef.class),
        @XmlElement(name = "second", required = true, type = Second.class),
        @XmlElement(name = "selection-def", required = true, type = SelectionDef.class),
        @XmlElement(name = "slope-def", required = true, type = SlopeDef.class),
        @XmlElement(name = "spread-def", required = true, type = SpreadDef.class),
        @XmlElement(name = "standard-deviation-def", required = true, type = StandardDeviationDef.class),
        @XmlElement(name = "standard-error-def", required = true, type = StandardErrorDef.class),
        @XmlElement(name = "start-def", required = true, type = StartDef.class),
        @XmlElement(name = "string-constant", required = true, type = StringConstant.class),
        @XmlElement(name = "time-to-alarm-def", required = true, type = TimeToAlarmDef.class),
        @XmlElement(name = "time-window-analysis-def", required = true, type = TimeWindowAnalysisDef.class),
        @XmlElement(name = "time-window-def", required = true, type = TimeWindowDef.class),
        @XmlElement(name = "valid-time-def", required = true, type = ValidTimeDef.class),
        @XmlElement(name = "year", required = true, type = Year.class)
    })
    protected List<Object> averageDefOrBooleanDefOrCalculationDefOrCenterDefOrCentileDefOrChangeDefOrComparisonDefOrConstantOperationOrConstantRefOrDayOfMonthOrDayOfWeekOrDelayDefOrDurationDefOrEndDefOrEndPointDefOrEpisodeAnalysisDefOrEpisodeDefOrHistogramDefOrHourOrLimitDefOrLinearRegressionDefOrLogicalCombinationDefOrLogicalDependencyDefOrMinuteOrMonthOrNowOrNumericalConstantOrParameterRefOrQualitativeConstantOrQualitativeParameterDefOrRawDataDefOrSecondOrSelectionDefOrSlopeDefOrSpreadDefOrStandardDeviationDefOrStandardErrorDefOrStartDefOrStringConstantOrTimeToAlarmDefOrTimeWindowAnalysisDefOrTimeWindowDefOrValidTimeDefOrYear;

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
     * Gets the value of the averageDefOrBooleanDefOrCalculationDefOrCenterDefOrCentileDefOrChangeDefOrComparisonDefOrConstantOperationOrConstantRefOrDayOfMonthOrDayOfWeekOrDelayDefOrDurationDefOrEndDefOrEndPointDefOrEpisodeAnalysisDefOrEpisodeDefOrHistogramDefOrHourOrLimitDefOrLinearRegressionDefOrLogicalCombinationDefOrLogicalDependencyDefOrMinuteOrMonthOrNowOrNumericalConstantOrParameterRefOrQualitativeConstantOrQualitativeParameterDefOrRawDataDefOrSecondOrSelectionDefOrSlopeDefOrSpreadDefOrStandardDeviationDefOrStandardErrorDefOrStartDefOrStringConstantOrTimeToAlarmDefOrTimeWindowAnalysisDefOrTimeWindowDefOrValidTimeDefOrYear property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the averageDefOrBooleanDefOrCalculationDefOrCenterDefOrCentileDefOrChangeDefOrComparisonDefOrConstantOperationOrConstantRefOrDayOfMonthOrDayOfWeekOrDelayDefOrDurationDefOrEndDefOrEndPointDefOrEpisodeAnalysisDefOrEpisodeDefOrHistogramDefOrHourOrLimitDefOrLinearRegressionDefOrLogicalCombinationDefOrLogicalDependencyDefOrMinuteOrMonthOrNowOrNumericalConstantOrParameterRefOrQualitativeConstantOrQualitativeParameterDefOrRawDataDefOrSecondOrSelectionDefOrSlopeDefOrSpreadDefOrStandardDeviationDefOrStandardErrorDefOrStartDefOrStringConstantOrTimeToAlarmDefOrTimeWindowAnalysisDefOrTimeWindowDefOrValidTimeDefOrYear property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAverageDefOrBooleanDefOrCalculationDefOrCenterDefOrCentileDefOrChangeDefOrComparisonDefOrConstantOperationOrConstantRefOrDayOfMonthOrDayOfWeekOrDelayDefOrDurationDefOrEndDefOrEndPointDefOrEpisodeAnalysisDefOrEpisodeDefOrHistogramDefOrHourOrLimitDefOrLinearRegressionDefOrLogicalCombinationDefOrLogicalDependencyDefOrMinuteOrMonthOrNowOrNumericalConstantOrParameterRefOrQualitativeConstantOrQualitativeParameterDefOrRawDataDefOrSecondOrSelectionDefOrSlopeDefOrSpreadDefOrStandardDeviationDefOrStandardErrorDefOrStartDefOrStringConstantOrTimeToAlarmDefOrTimeWindowAnalysisDefOrTimeWindowDefOrValidTimeDefOrYear().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AverageDef }
     * {@link BooleanDef }
     * {@link CalculationDef }
     * {@link CenterDef }
     * {@link CentileDef }
     * {@link ChangeDef }
     * {@link ComparisonDef }
     * {@link ConstantOperation }
     * {@link ConstantRef }
     * {@link DayOfMonth }
     * {@link DayOfWeek }
     * {@link DelayDef }
     * {@link DurationDef }
     * {@link EndDef }
     * {@link EndPointDef }
     * {@link EpisodeAnalysisDef }
     * {@link EpisodeDef }
     * {@link HistogramDef }
     * {@link Hour }
     * {@link LimitDef }
     * {@link LinearRegressionDef }
     * {@link LogicalCombinationDef }
     * {@link LogicalDependencyDef }
     * {@link Minute }
     * {@link Month }
     * {@link Now }
     * {@link NumericalConstant }
     * {@link ParameterRef }
     * {@link QualitativeConstant }
     * {@link QualitativeParameterDef }
     * {@link RawDataDef }
     * {@link Second }
     * {@link SelectionDef }
     * {@link SlopeDef }
     * {@link SpreadDef }
     * {@link StandardDeviationDef }
     * {@link StandardErrorDef }
     * {@link StartDef }
     * {@link StringConstant }
     * {@link TimeToAlarmDef }
     * {@link TimeWindowAnalysisDef }
     * {@link TimeWindowDef }
     * {@link ValidTimeDef }
     * {@link Year }
     * 
     * 
     */
    public List<Object> getAverageDefOrBooleanDefOrCalculationDefOrCenterDefOrCentileDefOrChangeDefOrComparisonDefOrConstantOperationOrConstantRefOrDayOfMonthOrDayOfWeekOrDelayDefOrDurationDefOrEndDefOrEndPointDefOrEpisodeAnalysisDefOrEpisodeDefOrHistogramDefOrHourOrLimitDefOrLinearRegressionDefOrLogicalCombinationDefOrLogicalDependencyDefOrMinuteOrMonthOrNowOrNumericalConstantOrParameterRefOrQualitativeConstantOrQualitativeParameterDefOrRawDataDefOrSecondOrSelectionDefOrSlopeDefOrSpreadDefOrStandardDeviationDefOrStandardErrorDefOrStartDefOrStringConstantOrTimeToAlarmDefOrTimeWindowAnalysisDefOrTimeWindowDefOrValidTimeDefOrYear() {
        if (averageDefOrBooleanDefOrCalculationDefOrCenterDefOrCentileDefOrChangeDefOrComparisonDefOrConstantOperationOrConstantRefOrDayOfMonthOrDayOfWeekOrDelayDefOrDurationDefOrEndDefOrEndPointDefOrEpisodeAnalysisDefOrEpisodeDefOrHistogramDefOrHourOrLimitDefOrLinearRegressionDefOrLogicalCombinationDefOrLogicalDependencyDefOrMinuteOrMonthOrNowOrNumericalConstantOrParameterRefOrQualitativeConstantOrQualitativeParameterDefOrRawDataDefOrSecondOrSelectionDefOrSlopeDefOrSpreadDefOrStandardDeviationDefOrStandardErrorDefOrStartDefOrStringConstantOrTimeToAlarmDefOrTimeWindowAnalysisDefOrTimeWindowDefOrValidTimeDefOrYear == null) {
            averageDefOrBooleanDefOrCalculationDefOrCenterDefOrCentileDefOrChangeDefOrComparisonDefOrConstantOperationOrConstantRefOrDayOfMonthOrDayOfWeekOrDelayDefOrDurationDefOrEndDefOrEndPointDefOrEpisodeAnalysisDefOrEpisodeDefOrHistogramDefOrHourOrLimitDefOrLinearRegressionDefOrLogicalCombinationDefOrLogicalDependencyDefOrMinuteOrMonthOrNowOrNumericalConstantOrParameterRefOrQualitativeConstantOrQualitativeParameterDefOrRawDataDefOrSecondOrSelectionDefOrSlopeDefOrSpreadDefOrStandardDeviationDefOrStandardErrorDefOrStartDefOrStringConstantOrTimeToAlarmDefOrTimeWindowAnalysisDefOrTimeWindowDefOrValidTimeDefOrYear = new ArrayList<Object>();
        }
        return this.averageDefOrBooleanDefOrCalculationDefOrCenterDefOrCentileDefOrChangeDefOrComparisonDefOrConstantOperationOrConstantRefOrDayOfMonthOrDayOfWeekOrDelayDefOrDurationDefOrEndDefOrEndPointDefOrEpisodeAnalysisDefOrEpisodeDefOrHistogramDefOrHourOrLimitDefOrLinearRegressionDefOrLogicalCombinationDefOrLogicalDependencyDefOrMinuteOrMonthOrNowOrNumericalConstantOrParameterRefOrQualitativeConstantOrQualitativeParameterDefOrRawDataDefOrSecondOrSelectionDefOrSlopeDefOrSpreadDefOrStandardDeviationDefOrStandardErrorDefOrStartDefOrStringConstantOrTimeToAlarmDefOrTimeWindowAnalysisDefOrTimeWindowDefOrValidTimeDefOrYear;
    }

}
