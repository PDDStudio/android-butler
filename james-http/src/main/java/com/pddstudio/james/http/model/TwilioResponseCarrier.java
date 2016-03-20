package com.pddstudio.james.http.model;

import com.google.gson.annotations.SerializedName;

/**
 * This Class was created by Patrick J
 * on 20.03.16. For more Details and Licensing
 * have a look at the README.md
 */
public class TwilioResponseCarrier {

    @SerializedName("type")
    private String type;
    @SerializedName("error_code")
    private String errorCode;
    @SerializedName("mobile_network_code")
    private String mobileNetworkCode;
    @SerializedName("mobile_country_code")
    private String mobileCountryCode;
    @SerializedName("name")
    private String carrierName;

    public String getType() {
        return type;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMobileNetworkCode() {
        return mobileNetworkCode;
    }

    public String getMobileCountryCode() {
        return mobileCountryCode;
    }

    public String getCarrierName() {
        return carrierName;
    }
}