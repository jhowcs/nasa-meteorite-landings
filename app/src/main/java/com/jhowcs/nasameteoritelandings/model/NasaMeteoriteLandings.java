
package com.jhowcs.nasameteoritelandings.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

public class NasaMeteoriteLandings implements Parcelable {

    @SerializedName("fall")
    @Expose
    private String fall;
    @SerializedName("geolocation")
    @Expose
    private Geolocation geolocation;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("mass")
    @Expose
    private String mass;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("nametype")
    @Expose
    private String nametype;
    @SerializedName("recclass")
    @Expose
    private String recclass;
    @SerializedName("reclat")
    @Expose
    private String reclat;
    @SerializedName("reclong")
    @Expose
    private String reclong;
    @SerializedName("year")
    @Expose
    private String year;

    public static final Parcelable.Creator<NasaMeteoriteLandings> CREATOR =
            new Creator<NasaMeteoriteLandings>() {
                @Override
                public NasaMeteoriteLandings createFromParcel(Parcel parcel) {
                    return new NasaMeteoriteLandings(parcel);
                }

                @Override
                public NasaMeteoriteLandings[] newArray(int size) {
                    return new NasaMeteoriteLandings[size];
                }
            };

    private NasaMeteoriteLandings(Parcel in) {
        fall = in.readString();
        geolocation = in.readParcelable(Geolocation.class.getClassLoader());
        id = in.readString();
        mass = in.readString();
        name = in.readString();
        nametype = in.readString();
        recclass = in.readString();
        reclat = in.readString();
        reclong = in.readString();
        year = in.readString();
    }

    public NasaMeteoriteLandings() {
    }

    /**
     * 
     * @return
     *     The fall
     */
    public String getFall() {
        return fall;
    }

    /**
     * 
     * @param fall
     *     The fall
     */
    public void setFall(String fall) {
        this.fall = fall;
    }

    /**
     * 
     * @return
     *     The geolocation
     */
    public Geolocation getGeolocation() {
        return geolocation;
    }

    /**
     * 
     * @param geolocation
     *     The geolocation
     */
    public void setGeolocation(Geolocation geolocation) {
        this.geolocation = geolocation;
    }

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The mass
     */
    public String getMass() {
        return mass;
    }

    /**
     * 
     * @param mass
     *     The mass
     */
    public void setMass(String mass) {
        this.mass = mass;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The nametype
     */
    public String getNametype() {
        return nametype;
    }

    /**
     * 
     * @param nametype
     *     The nametype
     */
    public void setNametype(String nametype) {
        this.nametype = nametype;
    }

    /**
     * 
     * @return
     *     The recclass
     */
    public String getRecclass() {
        return recclass;
    }

    /**
     * 
     * @param recclass
     *     The recclass
     */
    public void setRecclass(String recclass) {
        this.recclass = recclass;
    }

    /**
     * 
     * @return
     *     The reclat
     */
    public String getReclat() {
        return reclat;
    }

    /**
     * 
     * @param reclat
     *     The reclat
     */
    public void setReclat(String reclat) {
        this.reclat = reclat;
    }

    /**
     * 
     * @return
     *     The reclong
     */
    public String getReclong() {
        return reclong;
    }

    /**
     * 
     * @param reclong
     *     The reclong
     */
    public void setReclong(String reclong) {
        this.reclong = reclong;
    }

    /**
     * 
     * @return
     *     The year
     */
    public String getYear() {
        return year;
    }

    /**
     * 
     * @param year
     *     The year
     */
    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(fall);
        parcel.writeParcelable(geolocation, i);
        parcel.writeString(id);
        parcel.writeString(mass);
        parcel.writeString(name);
        parcel.writeString(nametype);
        parcel.writeString(recclass);
        parcel.writeString(reclat);
        parcel.writeString(reclong);
        parcel.writeString(year);
    }

    public static final Comparator<NasaMeteoriteLandings> meteoriteSizeComparator =
            new Comparator<NasaMeteoriteLandings>() {
                @Override
                public int compare(NasaMeteoriteLandings t1, NasaMeteoriteLandings t2) {
                    Double m1 = Double.valueOf(t1.getMass());
                    Double m2 = Double.valueOf(t2.getMass());

                    return m1.compareTo(m2);
                }
            };
}
