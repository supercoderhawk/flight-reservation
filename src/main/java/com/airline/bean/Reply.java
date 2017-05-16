
package com.airline.bean;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Reply {

    private String globalParameterEmpty;
    private String appInitFail;
    private String appTimeFormatError;
    private String appInputInvalidate;
    private String flightFlightIDEmpty;
    private String flightFlightSerialEmpty;
    private String flightStartCityEmpty;
    private String flightArrivalCityEmpty;
    private String flightNoFlight;
    private String flightFlightExisted;
    private String flightFlightPublished;
    private String flightNotSetupAllTime;
    private String flightQueryStrategyError;
    private String flightCantDeleteFlight;
    private String flightFunctionInvokeError;
    private String flightParameterEmpty;
    private String flightStartTimeInvalidate;
    private String flightArrivalTimeInvalidate;
    private String flightDepartureDateInvalidate;
    private String flightArrivalDateInvalidate;
    private String flightStartTimeError;
    private String flightStartArrivalTimeError;
    private String flightNoSerialAtUpdate;
    private String orderIDNotExist;
    private String orderPayCancel;
    private String orderFlightCantReserve;
    private String orderUnsupportedCancelFlight;
    private String orderNoOrdersToUnsubscribe;
    private String orderNoOrderID;
    private String orderInvalidateIDToPassenger;
    private String passengerNameEmpty;
    private String passengerIdentityLengthError;
    private String passengerPasswordEmpty;
    private String passengerAuthenticateFailed;
    private String passengerNotExist;
    private String passengerIdentityExisted;
    private String adminUserNameEmpty;
    private String adminUserNameExisted;
    private String adminUserNameNoExist;
    private String adminPasswordEmpty;
    private String adminAuthenticateFail;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getGlobalParameterEmpty() {
        return globalParameterEmpty;
    }

    public void setGlobalParameterEmpty(String globalParameterEmpty) {
        this.globalParameterEmpty = globalParameterEmpty;
    }

    public String getAppInitFail() {
        return appInitFail;
    }

    public void setAppInitFail(String appInitFail) {
        this.appInitFail = appInitFail;
    }

    public String getAppTimeFormatError() {
        return appTimeFormatError;
    }

    public void setAppTimeFormatError(String appTimeFormatError) {
        this.appTimeFormatError = appTimeFormatError;
    }

    public String getAppInputInvalidate() {
        return appInputInvalidate;
    }

    public void setAppInputInvalidate(String appInputInvalidate) {
        this.appInputInvalidate = appInputInvalidate;
    }

    public String getFlightFlightIDEmpty() {
        return flightFlightIDEmpty;
    }

    public void setFlightFlightIDEmpty(String flightFlightIDEmpty) {
        this.flightFlightIDEmpty = flightFlightIDEmpty;
    }

    public String getFlightFlightSerialEmpty() {
        return flightFlightSerialEmpty;
    }

    public void setFlightFlightSerialEmpty(String flightFlightSerialEmpty) {
        this.flightFlightSerialEmpty = flightFlightSerialEmpty;
    }

    public String getFlightStartCityEmpty() {
        return flightStartCityEmpty;
    }

    public void setFlightStartCityEmpty(String flightStartCityEmpty) {
        this.flightStartCityEmpty = flightStartCityEmpty;
    }

    public String getFlightArrivalCityEmpty() {
        return flightArrivalCityEmpty;
    }

    public void setFlightArrivalCityEmpty(String flightArrivalCityEmpty) {
        this.flightArrivalCityEmpty = flightArrivalCityEmpty;
    }

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

    public String getFlightFlightPublished() {
        return flightFlightPublished;
    }

    public void setFlightFlightPublished(String flightFlightPublished) {
        this.flightFlightPublished = flightFlightPublished;
    }

    public String getFlightNotSetupAllTime() {
        return flightNotSetupAllTime;
    }

    public void setFlightNotSetupAllTime(String flightNotSetupAllTime) {
        this.flightNotSetupAllTime = flightNotSetupAllTime;
    }

    public String getFlightQueryStrategyError() {
        return flightQueryStrategyError;
    }

    public void setFlightQueryStrategyError(String flightQueryStrategyError) {
        this.flightQueryStrategyError = flightQueryStrategyError;
    }

    public String getFlightCantDeleteFlight() {
        return flightCantDeleteFlight;
    }

    public void setFlightCantDeleteFlight(String flightCantDeleteFlight) {
        this.flightCantDeleteFlight = flightCantDeleteFlight;
    }

    public String getFlightFunctionInvokeError() {
        return flightFunctionInvokeError;
    }

    public void setFlightFunctionInvokeError(String flightFunctionInvokeError) {
        this.flightFunctionInvokeError = flightFunctionInvokeError;
    }

    public String getFlightParameterEmpty() {
        return flightParameterEmpty;
    }

    public void setFlightParameterEmpty(String flightParameterEmpty) {
        this.flightParameterEmpty = flightParameterEmpty;
    }

    public String getFlightStartTimeInvalidate() {
        return flightStartTimeInvalidate;
    }

    public void setFlightStartTimeInvalidate(String flightStartTimeInvalidate) {
        this.flightStartTimeInvalidate = flightStartTimeInvalidate;
    }

    public String getFlightArrivalTimeInvalidate() {
        return flightArrivalTimeInvalidate;
    }

    public void setFlightArrivalTimeInvalidate(String flightArrivalTimeInvalidate) {
        this.flightArrivalTimeInvalidate = flightArrivalTimeInvalidate;
    }

    public String getFlightDepartureDateInvalidate() {
        return flightDepartureDateInvalidate;
    }

    public void setFlightDepartureDateInvalidate(String flightDepartureDateInvalidate) {
        this.flightDepartureDateInvalidate = flightDepartureDateInvalidate;
    }

    public String getFlightArrivalDateInvalidate() {
        return flightArrivalDateInvalidate;
    }

    public void setFlightArrivalDateInvalidate(String flightArrivalDateInvalidate) {
        this.flightArrivalDateInvalidate = flightArrivalDateInvalidate;
    }

    public String getFlightStartTimeError() {
        return flightStartTimeError;
    }

    public void setFlightStartTimeError(String flightStartTimeError) {
        this.flightStartTimeError = flightStartTimeError;
    }

    public String getFlightStartArrivalTimeError() {
        return flightStartArrivalTimeError;
    }

    public void setFlightStartArrivalTimeError(String flightStartArrivalTimeError) {
        this.flightStartArrivalTimeError = flightStartArrivalTimeError;
    }

    public String getFlightNoSerialAtUpdate() {
        return flightNoSerialAtUpdate;
    }

    public void setFlightNoSerialAtUpdate(String flightNoSerialAtUpdate) {
        this.flightNoSerialAtUpdate = flightNoSerialAtUpdate;
    }

    public String getOrderIDNotExist() {
        return orderIDNotExist;
    }

    public void setOrderIDNotExist(String orderIDNotExist) {
        this.orderIDNotExist = orderIDNotExist;
    }

    public String getOrderPayCancel() {
        return orderPayCancel;
    }

    public void setOrderPayCancel(String orderPayCancel) {
        this.orderPayCancel = orderPayCancel;
    }

    public String getOrderFlightCantReserve() {
        return orderFlightCantReserve;
    }

    public void setOrderFlightCantReserve(String orderFlightCantReserve) {
        this.orderFlightCantReserve = orderFlightCantReserve;
    }

    public String getOrderUnsupportedCancelFlight() {
        return orderUnsupportedCancelFlight;
    }

    public void setOrderUnsupportedCancelFlight(String orderUnsupportedCancelFlight) {
        this.orderUnsupportedCancelFlight = orderUnsupportedCancelFlight;
    }

    public String getOrderNoOrdersToUnsubscribe() {
        return orderNoOrdersToUnsubscribe;
    }

    public void setOrderNoOrdersToUnsubscribe(String orderNoOrdersToUnsubscribe) {
        this.orderNoOrdersToUnsubscribe = orderNoOrdersToUnsubscribe;
    }

    public String getOrderNoOrderID() {
        return orderNoOrderID;
    }

    public void setOrderNoOrderID(String orderNoOrderID) {
        this.orderNoOrderID = orderNoOrderID;
    }

    public String getOrderInvalidateIDToPassenger() {
        return orderInvalidateIDToPassenger;
    }

    public void setOrderInvalidateIDToPassenger(String orderInvalidateIDToPassenger) {
        this.orderInvalidateIDToPassenger = orderInvalidateIDToPassenger;
    }

    public String getPassengerNameEmpty() {
        return passengerNameEmpty;
    }

    public void setPassengerNameEmpty(String passengerNameEmpty) {
        this.passengerNameEmpty = passengerNameEmpty;
    }

    public String getPassengerIdentityLengthError() {
        return passengerIdentityLengthError;
    }

    public void setPassengerIdentityLengthError(String passengerIdentityLengthError) {
        this.passengerIdentityLengthError = passengerIdentityLengthError;
    }

    public String getPassengerPasswordEmpty() {
        return passengerPasswordEmpty;
    }

    public void setPassengerPasswordEmpty(String passengerPasswordEmpty) {
        this.passengerPasswordEmpty = passengerPasswordEmpty;
    }

    public String getPassengerAuthenticateFailed() {
        return passengerAuthenticateFailed;
    }

    public void setPassengerAuthenticateFailed(String passengerAuthenticateFailed) {
        this.passengerAuthenticateFailed = passengerAuthenticateFailed;
    }

    public String getPassengerNotExist() {
        return passengerNotExist;
    }

    public void setPassengerNotExist(String passengerNotExist) {
        this.passengerNotExist = passengerNotExist;
    }

    public String getPassengerIdentityExisted() {
        return passengerIdentityExisted;
    }

    public void setPassengerIdentityExisted(String passengerIdentityExisted) {
        this.passengerIdentityExisted = passengerIdentityExisted;
    }

    public String getAdminUserNameEmpty() {
        return adminUserNameEmpty;
    }

    public void setAdminUserNameEmpty(String adminUserNameEmpty) {
        this.adminUserNameEmpty = adminUserNameEmpty;
    }

    public String getAdminUserNameExisted() {
        return adminUserNameExisted;
    }

    public void setAdminUserNameExisted(String adminUserNameExisted) {
        this.adminUserNameExisted = adminUserNameExisted;
    }

    public String getAdminUserNameNoExist() {
        return adminUserNameNoExist;
    }

    public void setAdminUserNameNoExist(String adminUserNameNoExist) {
        this.adminUserNameNoExist = adminUserNameNoExist;
    }

    public String getAdminPasswordEmpty() {
        return adminPasswordEmpty;
    }

    public void setAdminPasswordEmpty(String adminPasswordEmpty) {
        this.adminPasswordEmpty = adminPasswordEmpty;
    }

    public String getAdminAuthenticateFail() {
        return adminAuthenticateFail;
    }

    public void setAdminAuthenticateFail(String adminAuthenticateFail) {
        this.adminAuthenticateFail = adminAuthenticateFail;
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
        return new HashCodeBuilder().append(globalParameterEmpty).append(appInitFail).append(appTimeFormatError).append(appInputInvalidate).append(flightFlightIDEmpty).append(flightFlightSerialEmpty).append(flightStartCityEmpty).append(flightArrivalCityEmpty).append(flightNoFlight).append(flightFlightExisted).append(flightFlightPublished).append(flightNotSetupAllTime).append(flightQueryStrategyError).append(flightCantDeleteFlight).append(flightFunctionInvokeError).append(flightParameterEmpty).append(flightStartTimeInvalidate).append(flightArrivalTimeInvalidate).append(flightDepartureDateInvalidate).append(flightArrivalDateInvalidate).append(flightStartTimeError).append(flightStartArrivalTimeError).append(flightNoSerialAtUpdate).append(orderIDNotExist).append(orderPayCancel).append(orderFlightCantReserve).append(orderUnsupportedCancelFlight).append(orderNoOrdersToUnsubscribe).append(orderNoOrderID).append(orderInvalidateIDToPassenger).append(passengerNameEmpty).append(passengerIdentityLengthError).append(passengerPasswordEmpty).append(passengerAuthenticateFailed).append(passengerNotExist).append(passengerIdentityExisted).append(adminUserNameEmpty).append(adminUserNameExisted).append(adminUserNameNoExist).append(adminPasswordEmpty).append(adminAuthenticateFail).append(additionalProperties).toHashCode();
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
        return new EqualsBuilder().append(globalParameterEmpty, rhs.globalParameterEmpty).append(appInitFail, rhs.appInitFail).append(appTimeFormatError, rhs.appTimeFormatError).append(appInputInvalidate, rhs.appInputInvalidate).append(flightFlightIDEmpty, rhs.flightFlightIDEmpty).append(flightFlightSerialEmpty, rhs.flightFlightSerialEmpty).append(flightStartCityEmpty, rhs.flightStartCityEmpty).append(flightArrivalCityEmpty, rhs.flightArrivalCityEmpty).append(flightNoFlight, rhs.flightNoFlight).append(flightFlightExisted, rhs.flightFlightExisted).append(flightFlightPublished, rhs.flightFlightPublished).append(flightNotSetupAllTime, rhs.flightNotSetupAllTime).append(flightQueryStrategyError, rhs.flightQueryStrategyError).append(flightCantDeleteFlight, rhs.flightCantDeleteFlight).append(flightFunctionInvokeError, rhs.flightFunctionInvokeError).append(flightParameterEmpty, rhs.flightParameterEmpty).append(flightStartTimeInvalidate, rhs.flightStartTimeInvalidate).append(flightArrivalTimeInvalidate, rhs.flightArrivalTimeInvalidate).append(flightDepartureDateInvalidate, rhs.flightDepartureDateInvalidate).append(flightArrivalDateInvalidate, rhs.flightArrivalDateInvalidate).append(flightStartTimeError, rhs.flightStartTimeError).append(flightStartArrivalTimeError, rhs.flightStartArrivalTimeError).append(flightNoSerialAtUpdate, rhs.flightNoSerialAtUpdate).append(orderIDNotExist, rhs.orderIDNotExist).append(orderPayCancel, rhs.orderPayCancel).append(orderFlightCantReserve, rhs.orderFlightCantReserve).append(orderUnsupportedCancelFlight, rhs.orderUnsupportedCancelFlight).append(orderNoOrdersToUnsubscribe, rhs.orderNoOrdersToUnsubscribe).append(orderNoOrderID, rhs.orderNoOrderID).append(orderInvalidateIDToPassenger, rhs.orderInvalidateIDToPassenger).append(passengerNameEmpty, rhs.passengerNameEmpty).append(passengerIdentityLengthError, rhs.passengerIdentityLengthError).append(passengerPasswordEmpty, rhs.passengerPasswordEmpty).append(passengerAuthenticateFailed, rhs.passengerAuthenticateFailed).append(passengerNotExist, rhs.passengerNotExist).append(passengerIdentityExisted, rhs.passengerIdentityExisted).append(adminUserNameEmpty, rhs.adminUserNameEmpty).append(adminUserNameExisted, rhs.adminUserNameExisted).append(adminUserNameNoExist, rhs.adminUserNameNoExist).append(adminPasswordEmpty, rhs.adminPasswordEmpty).append(adminAuthenticateFail, rhs.adminAuthenticateFail).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
