package br.com.sitedoph.fexco.postcode.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Address.
 */

@Document(collection = "address")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("addressline_1")
    private String addressline1;

    @Field("addressline_2")
    private String addressline2;

    @Field("addressline_3")
    private String addressline3;

    @Field("summaryline")
    private String summaryline;

    @Field("organisation")
    private String organisation;

    @Field("buildingname")
    private String buildingname;

    @Field("premise")
    private String premise;

    @Field("street")
    private String street;

    @Field("dependentlocality")
    private String dependentlocality;

    @Field("posttown")
    private String posttown;

    @Field("county")
    private String county;

    @Field("postcode")
    private String postcode;

    @Field("number")
    private String number;

    @Field("pobox")
    private String pobox;

    @Field("departmentname")
    private String departmentname;

    @Field("subbuildingname")
    private String subbuildingname;

    @Field("dependentstreet")
    private String dependentstreet;

    @Field("doubledependentlocality")
    private String doubledependentlocality;

    @Field("recodes")
    private String recodes;

    @Field("morevalues")
    private Boolean morevalues;

    @Field("nextpage")
    private Integer nextpage;

    @Field("totalresults")
    private Integer totalresults;

    @Field("latitude")
    private String latitude;

    @Field("longitude")
    private String longitude;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddressline1() {
        return addressline1;
    }

    public void setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
    }

    public Address addressline1(String addressline1) {
        this.addressline1 = addressline1;
        return this;
    }

    public String getAddressline2() {
        return addressline2;
    }

    public void setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
    }

    public Address addressline2(String addressline2) {
        this.addressline2 = addressline2;
        return this;
    }

    public String getAddressline3() {
        return addressline3;
    }

    public void setAddressline3(String addressline3) {
        this.addressline3 = addressline3;
    }

    public Address addressline3(String addressline3) {
        this.addressline3 = addressline3;
        return this;
    }

    public String getSummaryline() {
        return summaryline;
    }

    public void setSummaryline(String summaryline) {
        this.summaryline = summaryline;
    }

    public Address summaryline(String summaryline) {
        this.summaryline = summaryline;
        return this;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public Address organisation(String organisation) {
        this.organisation = organisation;
        return this;
    }

    public String getBuildingname() {
        return buildingname;
    }

    public void setBuildingname(String buildingname) {
        this.buildingname = buildingname;
    }

    public Address buildingname(String buildingname) {
        this.buildingname = buildingname;
        return this;
    }

    public String getPremise() {
        return premise;
    }

    public void setPremise(String premise) {
        this.premise = premise;
    }

    public Address premise(String premise) {
        this.premise = premise;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Address street(String street) {
        this.street = street;
        return this;
    }

    public String getDependentlocality() {
        return dependentlocality;
    }

    public void setDependentlocality(String dependentlocality) {
        this.dependentlocality = dependentlocality;
    }

    public Address dependentlocality(String dependentlocality) {
        this.dependentlocality = dependentlocality;
        return this;
    }

    public String getPosttown() {
        return posttown;
    }

    public void setPosttown(String posttown) {
        this.posttown = posttown;
    }

    public Address posttown(String posttown) {
        this.posttown = posttown;
        return this;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public Address county(String county) {
        this.county = county;
        return this;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Address postcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Address number(String number) {
        this.number = number;
        return this;
    }

    public String getPobox() {
        return pobox;
    }

    public void setPobox(String pobox) {
        this.pobox = pobox;
    }

    public Address pobox(String pobox) {
        this.pobox = pobox;
        return this;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public Address departmentname(String departmentname) {
        this.departmentname = departmentname;
        return this;
    }

    public String getSubbuildingname() {
        return subbuildingname;
    }

    public void setSubbuildingname(String subbuildingname) {
        this.subbuildingname = subbuildingname;
    }

    public Address subbuildingname(String subbuildingname) {
        this.subbuildingname = subbuildingname;
        return this;
    }

    public String getDependentstreet() {
        return dependentstreet;
    }

    public void setDependentstreet(String dependentstreet) {
        this.dependentstreet = dependentstreet;
    }

    public Address dependentstreet(String dependentstreet) {
        this.dependentstreet = dependentstreet;
        return this;
    }

    public String getDoubledependentlocality() {
        return doubledependentlocality;
    }

    public void setDoubledependentlocality(String doubledependentlocality) {
        this.doubledependentlocality = doubledependentlocality;
    }

    public Address doubledependentlocality(String doubledependentlocality) {
        this.doubledependentlocality = doubledependentlocality;
        return this;
    }

    public String getRecodes() {
        return recodes;
    }

    public void setRecodes(String recodes) {
        this.recodes = recodes;
    }

    public Address recodes(String recodes) {
        this.recodes = recodes;
        return this;
    }

    public Boolean isMorevalues() {
        return morevalues;
    }

    public Address morevalues(Boolean morevalues) {
        this.morevalues = morevalues;
        return this;
    }

    public void setMorevalues(Boolean morevalues) {
        this.morevalues = morevalues;
    }

    public Integer getNextpage() {
        return nextpage;
    }

    public void setNextpage(Integer nextpage) {
        this.nextpage = nextpage;
    }

    public Address nextpage(Integer nextpage) {
        this.nextpage = nextpage;
        return this;
    }

    public Integer getTotalresults() {
        return totalresults;
    }

    public void setTotalresults(Integer totalresults) {
        this.totalresults = totalresults;
    }

    public Address totalresults(Integer totalresults) {
        this.totalresults = totalresults;
        return this;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Address latitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Address longitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        if (address.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, address.id);
    }

    @Override
    public String toString() {
        return "Address{" +
            "id=" + id +
            ", addressline1='" + addressline1 + "'" +
            ", addressline2='" + addressline2 + "'" +
            ", addressline3='" + addressline3 + "'" +
            ", summaryline='" + summaryline + "'" +
            ", organisation='" + organisation + "'" +
            ", buildingname='" + buildingname + "'" +
            ", premise='" + premise + "'" +
            ", street='" + street + "'" +
            ", dependentlocality='" + dependentlocality + "'" +
            ", posttown='" + posttown + "'" +
            ", county='" + county + "'" +
            ", postcode='" + postcode + "'" +
            ", number='" + number + "'" +
            ", pobox='" + pobox + "'" +
            ", departmentname='" + departmentname + "'" +
            ", subbuildingname='" + subbuildingname + "'" +
            ", dependentstreet='" + dependentstreet + "'" +
            ", doubledependentlocality='" + doubledependentlocality + "'" +
            ", recodes='" + recodes + "'" +
            ", morevalues='" + morevalues + "'" +
            ", nextpage='" + nextpage + "'" +
            ", totalresults='" + totalresults + "'" +
            ", latitude='" + latitude + "'" +
            ", longitude='" + longitude + "'" +
            '}';
    }
}
