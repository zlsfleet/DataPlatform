package beans;

import javax.persistence.*;

/**
 * Created by Kris on 2016/7/5.
 */
@Entity
@Table(name = "service", schema = "data")
public class ServiceEntity {
    private String date;
    private int callOut;
    private int callIn;
    private int blacklist;
    private int toVerify;

    @Id
    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "call_out")
    public int getCallOut() {
        return callOut;
    }

    public void setCallOut(int callOut) {
        this.callOut = callOut;
    }

    @Basic
    @Column(name = "call_in")
    public int getCallIn() {
        return callIn;
    }

    public void setCallIn(int callIn) {
        this.callIn = callIn;
    }

    @Basic
    @Column(name = "blacklist")
    public int getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(int blacklist) {
        this.blacklist = blacklist;
    }

    @Basic
    @Column(name = "to_verify")
    public int getToVerify() {
        return toVerify;
    }

    public void setToVerify(int toVerify) {
        this.toVerify = toVerify;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceEntity that = (ServiceEntity) o;

        if (callOut != that.callOut) return false;
        if (callIn != that.callIn) return false;
        if (blacklist != that.blacklist) return false;
        if (toVerify != that.toVerify) return false;
        return date != null ? date.equals(that.date) : that.date == null;

    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + callOut;
        result = 31 * result + callIn;
        result = 31 * result + blacklist;
        result = 31 * result + toVerify;
        return result;
    }
}
