
package com.airline.bean;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Reply {

    private String flightNoFlight;
    private String flightFlightExisted;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getFlightNoFlight() {
        return flightNoFlight;
    }

    public void setFlightNoFlight(String flightNoFlight) {
        this.flightNoFlight = flightNoFlight;
    }

    public String getFlightFlightExisted() {
        return flightFlightExisted;
    }

    public void setFlightFlightExisted(String flightFlightExisted) {
        this.flightFlightExisted = flightFlightExisted;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(flightNoFlight).append(flightFlightExisted).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Reply) == false) {
            return false;
        }
        Reply rhs = ((Reply) other);
        return new EqualsBuilder().append(flightNoFlight, rhs.flightNoFlight).append(flightFlightExisted, rhs.flightFlightExisted).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
