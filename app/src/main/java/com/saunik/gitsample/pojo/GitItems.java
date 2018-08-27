package com.saunik.gitsample.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Saunik Singh on 27/8/18.
 * Cars24 Services Private Limited.
 */
public class GitItems implements Parcelable{
    public String name, description, clone_url;
    public Owner owner;

    private GitItems(Parcel in) {
        name = in.readString();
        description = in.readString();
        clone_url = in.readString();
        owner = (Owner) in.readValue(Owner.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(clone_url);
        dest.writeValue(owner);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<GitItems> CREATOR = new Parcelable.Creator<GitItems>() {
        @Override
        public GitItems createFromParcel(Parcel in) {
            return new GitItems(in);
        }

        @Override
        public GitItems[] newArray(int size) {
            return new GitItems[size];
        }
    };
}
