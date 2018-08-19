
package com.example.tin.pulselive.models.content_detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailItem implements Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("subtitle")
    @Expose
    private String subtitle;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("date")
    @Expose
    private String date;

    private DetailItem(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.subtitle = ((String) in.readValue((String.class.getClassLoader())));
        this.body = ((String) in.readValue((String.class.getClassLoader())));
        this.date = ((String) in.readValue((String.class.getClassLoader())));
    }

    public DetailItem() {
    }

    public static final Creator<DetailItem> CREATOR = new Creator<DetailItem>() {
        @Override
        public DetailItem createFromParcel(Parcel in) {
            return new DetailItem(in);
        }

        @Override
        public DetailItem[] newArray(int size) {
            return new DetailItem[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getBody() {
        return body;
    }

    public String getDate() {
        return date;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(title);
        dest.writeValue(subtitle);
        dest.writeValue(body);
        dest.writeValue(date);
    }

    public int describeContents() {
        return  0;
    }

}
