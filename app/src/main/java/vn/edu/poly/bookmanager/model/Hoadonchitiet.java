package vn.edu.poly.bookmanager.model;

public class Hoadonchitiet {

    int imganhhoadonchitiet;
    String mahoadonchitiet;
    String mahoadon;
    String masach;
    public int soluong;

    public Hoadonchitiet(String mahoadonchitiet, String mahoadon, String masach, int soluong) {
        this.mahoadonchitiet = mahoadonchitiet;
        this.mahoadon = mahoadon;
        this.masach = masach;
        this.soluong = soluong;
    }

    public Hoadonchitiet() {
    }

    public int getImganhhoadonchitiet() {
        return imganhhoadonchitiet;
    }

    public void setImganhhoadonchitiet(int imganhhoadonchitiet) {
        this.imganhhoadonchitiet = imganhhoadonchitiet;
    }

    public String getMahoadonchitiet() {
        return mahoadonchitiet;
    }

    public void setMahoadonchitiet(String mahoadonchitiet) {
        this.mahoadonchitiet = mahoadonchitiet;
    }

    public String getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(String mahoadon) {
        this.mahoadon = mahoadon;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getMasach() {
        return masach;
    }

    public void setMasach(String masach) {
        this.masach = masach;
    }
}
