package com.zrf.reader.model;

import java.util.List;

/**
 * User: zrf (625001446aq@gmail.com)
 * DateTime: 2016-03-13 13:05
 */
public class StoryDetail {

    /**
     * body: html文本
     * image_source: CFP
     * title: 复盘 AlphaGo 决胜性的一局，只能说它一夜之间突飞猛进
     * image: http://pic2.zhimg.com/0c2cee4410ddc5a735d0db32f23dbb21.jpg
     * share_url: http://daily.zhihu.com/story/7994440
     * js: []
     * ga_prefix: 031310
     * type: 0
     * id: 7994440
     * css: ["http://news-at.zhihu.com/css/news_qa.auto.css?v=77778"]
     */

    private int id;
    private String body;
    private String image;
    private String title;
    private int type;
    private List<String> css;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }

    @Override
    public String toString() {
        return "StoryDetail{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", css=" + css +
                '}';
    }
}
