package com.zrf.reader.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * User: zrf (625001446aq@gmail.com)
 * DateTime: 2016-03-13 13:55
 */
public class Latest {

    private String date;
    private List<StorySimple> stories;
    @SerializedName("top_stories")
    private List<TopStory> topStories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StorySimple> getStories() {
        return stories;
    }

    public void setStories(List<StorySimple> stories) {
        this.stories = stories;
    }

    public List<TopStory> getTopStories() {
        return topStories;
    }

    public void setTopStories(List<TopStory> topStories) {
        this.topStories = topStories;
    }

    public class TopStory{
        /**
         * image: http://pic4.zhimg.com/f960fa6b1a5cb8e4014f575d9a37ee1b.jpg
         * type: 0,
         * id: 7952768,
         * ga_prefix: 031309,
         * title: 去南极，是我 30 年人生中最明智的旅行经历
         */

        private int id;
        private int type;
        private String image;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "TopStory{" +
                    "id=" + id +
                    ", type=" + type +
                    ", image='" + image + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Latest{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                ", topStories=" + topStories +
                '}';
    }
}
