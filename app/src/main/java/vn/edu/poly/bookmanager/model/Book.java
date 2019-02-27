package vn.edu.poly.bookmanager.model;

public class Book {
    private int imganhbook;
    private String maSach;
    private String maTheLoai;
    private String tenSach;
    private String tacGia;
    private String NXB;
    private long giaBan;
    private int soLuong;

    public Book(String maSach, String maTheLoai, String tenSach, String tacGia, String NXB, long giaBan, int soLuong) {
        this.maSach = maSach;
        this.maTheLoai = maTheLoai;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.NXB = NXB;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
    }

    public Book() {
    }

    public long getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(long giaBan) {
        this.giaBan = giaBan;
    }

    public int getImganhbook() {
        return imganhbook;
    }

    public void setImganhbook(int imganhbook) {
        this.imganhbook = imganhbook;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getNXB() {
        return NXB;
    }

    public void setNXB(String NXB) {
        this.NXB = NXB;
    }



    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
