package com.survtower.business.member.domain;

import com.survtower.business.common.BaseEntity;
import com.survtower.business.common.domain.SurveillanceData;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tdhlakama
 */
@Entity
@XmlRootElement
@Table(name = "region_surveillance_data")
public class RegionSurveillanceData extends BaseEntity {

    @ManyToOne
    private Region region;

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @XmlTransient
    @JoinColumn(name = "surveillance_data_id")
    private SurveillanceData surveillanceData;
    @Column(name = "denominator_value")
    private Double denominatorValue = 0.0;
    @Column(name = "numerator_value")
    private Double numeratorValue = 0.0;
    @Column(name = "manual_value")
    private Double manualValue;
    @Column(name = "manual")
    private Boolean manual = Boolean.FALSE;

    public SurveillanceData getSurveillanceData() {
        return surveillanceData;
    }

    public void setSurveillanceData(SurveillanceData surveillanceData) {
        this.surveillanceData = surveillanceData;
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Double getCalculatedValue() {
        if (isManual()) {
            return getManualValue(); // if manual value ignore Denominator value - Consider Only Numerator value
        } else {
            try {
                double result = (getNumeratorValue() / getDenominatorValue()) * getSurveillanceData().getIndicator().getIndicatorType().getFactor();

                NumberFormat form = DecimalFormat.getNumberInstance();

                form.setMaximumFractionDigits(getSurveillanceData().getIndicator().getIndicatorType().getDecimalPlaces());
                form.setRoundingMode(RoundingMode.UP);

                return Double.parseDouble(form.format(result));

            } catch (Exception e) {
                return 0.0;
            }
        }
    }

    public Boolean getValid() {
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
        hash = 67 * hash + Objects.hashCode(this.region);
        hash = 67 * hash + Objects.hashCode(this.surveillanceData);
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
        final RegionSurveillanceData other = (RegionSurveillanceData) obj;
        if (!Objects.equals(this.region, other.region)) {
            return false;
        }
        if (!Objects.equals(this.getSurveillanceData(), other.getSurveillanceData())) {
            return false;
        }
        return true;
    }

}
