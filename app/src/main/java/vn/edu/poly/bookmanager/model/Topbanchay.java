package vn.edu.poly.bookmanager.model;

public class Topbanchay {
    private int imganhtopbanchay;
    private String masach;
    private String soluong;

    public Topbanchay(String masach, String soluong) {
        this.masach = masach;
        this.soluong = soluong;
    }

    public int getImganhtopbanchay() {
        return imganhtopbanchay;
    }

    public void setImganhtopbanchay(int imganhtopbanchay) {
        this.imganhtopbanchay = imganhtopbanchay;
    }

    public String getMasach() {
        return masach;
    }

    public void setMasach(String masach) {
        this.masach = masach;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }
}
