package com.umes.jeb.hww.eis.bo;

import com.umes.jeb.hww.eis.bo.BaseBO;

/**
 * Created by elioth010 on 9/27/16.
 */

public class Configuration extends BaseBO<Configuration> {

    private Integer timeToRefresh;
    private Boolean showSensorBreath;
    private Boolean showSensorTemperature;
    private Boolean showSensorPulse;
    private Boolean showSensorBloodPressure;

    public Configuration() {
        super();
    }

    public Configuration(Integer timeToRefresh, Boolean showSensorBreath, Boolean showSensorTemperature, Boolean showSensorPulse, Boolean showSensorBloodPressure) {
        this.timeToRefresh = timeToRefresh;
        this.showSensorBreath = showSensorBreath;
        this.showSensorTemperature = showSensorTemperature;
        this.showSensorPulse = showSensorPulse;
        this.showSensorBloodPressure = showSensorBloodPressure;
    }

    public Integer getTimeToRefresh() {
        return timeToRefresh;
    }

    public void setTimeToRefresh(Integer timeToRefresh) {
        this.timeToRefresh = timeToRefresh;
    }

    public Boolean getShowSensorBreath() {
        return showSensorBreath;
    }

    public void setShowSensorBreath(Boolean showSensorBreath) {
        this.showSensorBreath = showSensorBreath;
    }

    public Boolean getShowSensorTemperature() {
        return showSensorTemperature;
    }

    public void setShowSensorTemperature(Boolean showSensorTemperature) {
        this.showSensorTemperature = showSensorTemperature;
    }

    public Boolean getShowSensorPulse() {
        return showSensorPulse;
    }

    public void setShowSensorPulse(Boolean showSensorPulse) {
        this.showSensorPulse = showSensorPulse;
    }

    public Boolean getShowSensorBloodPressure() {
        return showSensorBloodPressure;
    }

    public void setShowSensorBloodPressure(Boolean showSensorBloodPressure) {
        this.showSensorBloodPressure = showSensorBloodPressure;
    }
}
