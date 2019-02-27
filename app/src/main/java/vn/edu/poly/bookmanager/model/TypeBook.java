package vn.edu.poly.bookmanager.model;

public class TypeBook {
    private int imganhtheloai;
    private String id;
    private String name;
    private String description;
    private int position;

    public TypeBook(String id, String name, String description, int position) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.position = position;
    }

    public TypeBook() {
    }

    public int getImganhtheloai() {
        return imganhtheloai;
    }

    public void setImganhtheloai(int imganhtheloai) {
        this.imganhtheloai = imganhtheloai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
