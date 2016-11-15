package com.jhowcs.nasameteoritelandings.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

/**
 * Created by jonathan on 13/11/16.
 */

public class MeteoriteModel  implements Parcelable {

    private double mass;
    private String name;
    private String recclass;
    private double reclat;
    private double reclong;
    private String year;

    public static final Parcelable.Creator<MeteoriteModel> CREATOR =
            new Creator<MeteoriteModel>() {
                @Override
                public MeteoriteModel createFromParcel(Parcel parcel) {
                    return new MeteoriteModel(parcel);
                }

                @Override
                public MeteoriteModel[] newArray(int size) {
                    return new MeteoriteModel[size];
                }
            };

    private MeteoriteModel(Parcel in) {
        mass = in.readDouble();
        name = in.readString();
        recclass = in.readString();
        reclat = in.readDouble();
        reclong = in.readDouble();
        year = in.readString();
    }

    public MeteoriteModel() {
    }

    /**
     *
     * @return
     *     The mass
     */
    public double getMass() {
        return mass;
    }

    /**
     *
     * @param mass
     *     The mass
     */
    public void setMass(double mass) {
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
    public double getReclat() {
        return reclat;
    }

    /**
     *
     * @param reclat
     *     The reclat
     */
    public void setReclat(double reclat) {
        this.reclat = reclat;
    }

    /**
     *
     * @return
     *     The reclong
     */
    public double getReclong() {
        return reclong;
    }

    /**
     *
     * @param reclong
     *     The reclong
     */
    public void setReclong(double reclong) {
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
        parcel.writeDouble(mass);
        parcel.writeString(name);
        parcel.writeString(recclass);
        parcel.writeDouble(reclat);
        parcel.writeDouble(reclong);
        parcel.writeString(year);
    }

    public static final Comparator<MeteoriteModel> meteoriteSizeComparator =
            new Comparator<MeteoriteModel>() {
                @Override
                public int compare(MeteoriteModel t1, MeteoriteModel t2) {
                    Double m1 = t1.getMass();
                    Double m2 = t2.getMass();

                    return m1.compareTo(m2);
                }
            };
}

