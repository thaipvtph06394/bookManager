package vn.edu.poly.bookmanager.model;

public class Bill {
    public int imganhhoadon;
    public String idhoadon;
    public long datehoadon;

    public Bill(String idhoadon, long datehoadon) {
        this.idhoadon = idhoadon;
        this.datehoadon = datehoadon;
    }

    public Bill() {
    }

    public int getImganhhoadon() {
        return imganhhoadon;
    }

    public void setImganhhoadon(int imganhhoadon) {
        this.imganhhoadon = imganhhoadon;
    }

    public String getIdhoadon() {
        return idhoadon;
    }

    public void setIdhoadon(String idhoadon) {
        this.idhoadon = idhoadon;
    }

    public long getDatehoadon() {
        return datehoadon;
    }

    public void setDatehoadon(long datehoadon) {
        this.datehoadon = datehoadon;
    }
}
