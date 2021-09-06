package lk.sliit.hotel.entity.manager;


import lk.sliit.hotel.entity.SuperEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class solidDate implements SuperEntity {

    @Id
    private int noticeId ;
    private String title;
    @Column(length = 1000)
    private String cost;
    private String date;


    public solidDate(int noticeId, String title, String cost, String date) {
        this.noticeId = noticeId;
        this.title = title;
        this.cost = cost;
        this.date = date;

    }

    public solidDate() {
    }

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return cost;
    }

    public void setDescription(String cost) {
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}//End Class
