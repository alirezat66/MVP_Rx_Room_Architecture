package ir.greencode.androidmvprxroom.mvp.model;

public class GroupResponseResult {
    private String catImg;
    private int catId;
    private int timeOfCheck;
    private String catName;
    private int subType;
    private String title;
    private String desc;

    public String getCatImg() {
        return this.catImg;
    }

    public void setCatImg(String catImg) {
        this.catImg = catImg;
    }

    public int getCatId() {
        return this.catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public int getTimeOfCheck() {
        return this.timeOfCheck;
    }

    public void setTimeOfCheck(int timeOfCheck) {
        this.timeOfCheck = timeOfCheck;
    }

    public String getCatName() {
        return this.catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public int getSubType() {
        return this.subType;
    }

    public void setSubType(int subType) {
        this.subType = subType;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
