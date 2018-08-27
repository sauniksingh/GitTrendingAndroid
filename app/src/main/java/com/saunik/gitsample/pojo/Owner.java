package com.saunik.gitsample.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Saunik Singh on 27/8/18.
 * Cars24 Services Private Limited.
 */
public class Owner implements Parcelable{
    public String avatar_url, url;

    private Owner(Parcel in) {
        avatar_url = in.readString();
        url = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(avatar_url);
        dest.writeString(url);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Owner> CREATOR = new Parcelable.Creator<Owner>() {
        @Override
        public Owner createFromParcel(Parcel in) {
            return new Owner(in);
        }

        @Override
        public Owner[] newArray(int size) {
            return new Owner[size];
        }
    };
}
