package com.hyteck.project.entity;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tecnology implements Parcelable {
    private String name;
    private String distance;
    private String energyConsumption;
    private String batery;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.distance);
        dest.writeString(this.energyConsumption);
        dest.writeString(this.batery);
    }

    public Tecnology() {
    }

    protected Tecnology(Parcel in) {
        this.name = in.readString();
        this.distance = in.readString();
        this.energyConsumption = in.readString();
        this.batery = in.readString();
    }

    public static final Parcelable.Creator<Tecnology> CREATOR = new Parcelable.Creator<Tecnology>() {
        @Override
        public Tecnology createFromParcel(Parcel source) {
            return new Tecnology(source);
        }

        @Override
        public Tecnology[] newArray(int size) {
            return new Tecnology[size];
        }
    };
}