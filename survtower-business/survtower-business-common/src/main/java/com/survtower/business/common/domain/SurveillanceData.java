package com.survtower.business.common.domain;

import com.survtower.business.common.BaseEntity;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.NumberFormat;
import java.util.Objects;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Takunda Dhlakama
 */
@Entity
@XmlRootElement
public class SurveillanceData extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @XmlTransient
    private Surveillance surveillance;
    @ManyToOne
    private Indicator indicator;
    private Double denominatorValue = 0.0;
    private Double numeratorValue = 0.0;
    private Double manualValue;
    private Boolean manual = Boolean.FALSE;
    @Transient
    private Double calculatedValue;
    @Transient
    private Boolean valid;
    private Boolean errorMarked = Boolean.FALSE;
    private String comment;

    @XmlTransient
    public Surveillance getSurveillance() {
        return surveillance;
    }

    public void setSurveillance(Surveillance surveillance) {
        this.surveillance = surveillance;
    }

    public Indicator getIndicator() {
        return indicator;
    }

    public void setIndicator(Indicator indicator) {
        this.indicator = indicator;
    }

    public Double getDenominatorValue() {
        return denominatorValue;
    }

    public void setDenominatorValue(Double denominatorValue) {
        this.denominatorValue = denominatorValue;
    }

    public Double getNumeratorValue() {
        return numeratorValue;
    }

    public void setNumeratorValue(Double numeratorValue) {
        this.numeratorValue = numeratorValue;
    }

    public Double getManualValue() {
        return manualValue;
    }

    public void setManualValue(Double manualValue) {
        this.manualValue = manualValue;
    }

    public Boolean isManual() {
        return manual;
    }

    public void setManual(Boolean manual) {
        this.manual = manual;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getErrorMarked() {
        if (errorMarked == null) {
            return Boolean.FALSE;
        }
        return errorMarked;
    }

    public void setErrorMarked(Boolean errorMarked) {
        this.errorMarked = errorMarked;
    }

    public Double getCalculatedValue() {
        if (isManual()) {
            return getManualValue(); // if manual value ignore Denominator value - Consider Only Numerator value
        } else {
            try {
                double result = (getNumeratorValue() / getDenominatorValue()) * getIndicator().getIndicatorType().getFactor();

                NumberFormat form = DecimalFormat.getNumberInstance();

                form.setMaximumFractionDigits(getIndicator().getIndicatorType().getDecimalPlaces());
                form.setRoundingMode(RoundingMode.UP);

                return Double.parseDouble(form.format(result));

            } catch (Exception e) {
                return 0.0;
            }
        }
    }

    public Boolean getValid() {
        if (getNumeratorValue() == null || getDenominatorValue() == null) {
            return Boolean.FALSE;
        }
        if (getNumeratorValue() == 0.0) {
            return Boolean.FALSE;
        }
        if (getDenominatorValue() == 0.0) {
            return Boolean.FALSE;
        }
        if (getNumeratorValue() > getDenominatorValue()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.surveillance);
        hash = 67 * hash + Objects.hashCode(this.indicator);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SurveillanceData other = (SurveillanceData) obj;
        if (!Objects.equals(this.surveillance, other.surveillance)) {
            return false;
        }
        if (!Objects.equals(this.indicator, other.indicator)) {
            return false;
        }
        return true;
    }

}
