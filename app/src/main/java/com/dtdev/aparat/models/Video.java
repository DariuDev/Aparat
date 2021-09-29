package com.dtdev.aparat.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.dtdev.aparat.activites.VideoPlayerActivity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Entity
public class Video implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    int videoId;
    @SerializedName("id")
    @Expose
    @ColumnInfo(name = "id")
    private String id;
    @SerializedName("cat_id")
    @Expose
    @ColumnInfo(name = "catId")
    private String catId;
    @SerializedName("title")
    @Expose
    @ColumnInfo(name = "title")
    private String title;
    @SerializedName("icon")
    @Expose
    @ColumnInfo(name = "icon")
    private String icon;
    @SerializedName("creator")
    @Expose
    @ColumnInfo(name = "creator")
    private String creator;
    @SerializedName("description")
    @Expose
    @ColumnInfo(name = "description")
    private String description;
    @SerializedName("link")
    @Expose
    @ColumnInfo(name = "link")
    private String link;
    @SerializedName("view")
    @Expose
    private String view;
    @SerializedName("time")
    @Expose
    @ColumnInfo(name = "time")
    private String time;
    @SerializedName("special")
    @Expose
    @ColumnInfo(name = "special")
    private String special;

    public Video(String catId){
        this.catId = catId;
    }

    public Video( String id, String catId, String title, String icon, String creator, String description, String link, String view, String time, String special) {
        this.id = id;
        this.catId = catId;
        this.title = title;
        this.icon = icon;
        this.creator = creator;
        this.description = description;
        this.link = link;
        this.view = view;
        this.time = time;
        this.special = special;
    }
    @Ignore
    public Video(int videoId, String id, String catId, String title, String icon, String creator, String description, String link, String view, String time, String special) {
        this.videoId = videoId;
        this.id = id;
        this.catId = catId;
        this.title = title;
        this.icon = icon;
        this.creator = creator;
        this.description = description;
        this.link = link;
        this.view = view;
        this.time = time;
        this.special = special;
    }
    protected Video(Parcel in) {
        id = in.readString();
        catId = in.readString();
        title = in.readString();
        icon = in.readString();
        creator = in.readString();
        description = in.readString();
        link = in.readString();
        view = in.readString();
        time = in.readString();
        special = in.readString();
    }

    public static final Creator<Video> CREATOR = new Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel in) {
            return new Video(in);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(catId);
        dest.writeString(title);
        dest.writeString(icon);
        dest.writeString(creator);
        dest.writeString(description);
        dest.writeString(link);
        dest.writeString(view);
        dest.writeString(time);
        dest.writeString(special);
    }
    public Video(){

    }
    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

}
