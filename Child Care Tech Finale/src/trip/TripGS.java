package trip;

import java.io.Serializable;
import java.util.Date;

public class TripGS implements Serializable{

    private String id;
    private String meta;
    private Date andata;
    private Date ritorno;

    public TripGS(String id, String meta, Date andata, Date ritorno){

        this.id = id;
        this.meta = meta;
        this.andata = andata;
        this.ritorno = ritorno;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public Date getAndata() {
        return andata;
    }

    public void setAndata(Date andata) {
        this.andata = andata;
    }

    public Date getRitorno() {
        return ritorno;
    }

    public void setRitorno(Date ritorno) {
        this.ritorno = ritorno;
    }
}
