package com.pddstudio.james.http.model;

import com.google.gson.annotations.SerializedName;

/**
 * This Class was created by Patrick J
 * on 20.03.16. For more Details and Licensing
 * have a look at the README.md
 */
public class TwilioResponse {

    @SerializedName("country_code") private String countryCode;
    @SerializedName("phone_number") private String phoneNumber;
    @SerializedName("national_format") private String nationalFormat;
    @SerializedName("url") private String url;
    @SerializedName("carrier") private TwilioResponseCarrier responseCarrier;

    public String getCountryCode() {
        return countryCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getNationalFormat() {
        return nationalFormat;
    }

    public String getUrl() {
        return url;
    }

    public String getCarrierType() {
        return responseCarrier.getType();
    }

    public String getCarrierErrorCode() {
        return responseCarrier.getErrorCode();
    }

    public String getCarrierMobileNetworkCode() {
        return responseCarrier.getMobileNetworkCode();
    }

    public String getCarrierMobileCountryCode() {
        return responseCarrier.getMobileCountryCode();
    }

    public String getCarrierName() {
        return responseCarrier.getCarrierName();
    }

}
