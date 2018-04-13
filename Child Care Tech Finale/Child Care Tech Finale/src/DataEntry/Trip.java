package DataEntry;

import java.util.Date;

public class Trip {
    private String meta;
    private Date data;
    private int durata;
    private String tappe;

    public Date getData() {
        return data;
    }

    public int getDurata() {
        return durata;
    }

    public String getMeta() {
        return meta;
    }

    public String getTappe() {
        return tappe;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public void setTappe(String tappe) {
        this.tappe = tappe;
    }
}

