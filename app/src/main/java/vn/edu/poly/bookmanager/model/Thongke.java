package vn.edu.poly.bookmanager.model;

public class Thongke {
    private int imganhthongke;
    private String thongke;
    private int soluongthongke;

    public Thongke(String thongke, int soluongthongke) {
        this.thongke = thongke;
        this.soluongthongke = soluongthongke;
    }

    public int getImganhthongke() {
        return imganhthongke;
    }

    public void setImganhthongke(int imganhthongke) {
        this.imganhthongke = imganhthongke;
    }

    public String getThongke() {
        return thongke;
    }

    public void setThongke(String thongke) {
        this.thongke = thongke;
    }

    public int getSoluongthongke() {
        return soluongthongke;
    }

    public void setSoluongthongke(int soluongthongke) {
        this.soluongthongke = soluongthongke;
    }
}
