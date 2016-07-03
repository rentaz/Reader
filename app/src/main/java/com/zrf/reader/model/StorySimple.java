package com.zrf.reader.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * User: zrf (625001446aq@gmail.com)
 * DateTime: 2016-03-13 13:00
 */
public class StorySimple implements Parcelable {
    /**
     * images : ["http://pic2.zhimg.com/15d36cbb9f3224883e2a60ae5371bfe1.jpg"]
     * type : 0
     * id : 7994440
     * ga_prefix : 031310
     * title : 复盘 AlphaGo 决胜性的一局，只能说它一夜之间突飞猛进
     * multipic : false/true/或者没有返回
     */

    private int id;
    private List<String> images;
    private String title;
    private String multipic;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMultipic() {
        return multipic;
    }

    public void setMultipic(String multipic) {
        this.multipic = multipic;
    }

    @Override
    public String toString() {
        return "StorySimple{" +
                "id=" + id +
                ", images=" + images +
                ", title='" + title + '\'' +
                ", multipic='" + multipic + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeStringList(this.images);
        dest.writeString(this.title);
        dest.writeString(this.multipic);
    }

    public StorySimple() {
    }

    protected StorySimple(Parcel in) {
        this.id = in.readInt();
        this.images = in.createStringArrayList();
        this.title = in.readString();
        this.multipic=in.readString();
    }

    public static final Parcelable.Creator<StorySimple> CREATOR
            = new Parcelable.Creator<StorySimple>() {
        public StorySimple createFromParcel(Parcel source) {
            return new StorySimple(source);
        }

        public StorySimple[] newArray(int size) {
            return new StorySimple[size];
        }
    };
}
