package beans;

import javax.persistence.*;

/**
 * Created by Kris on 2016/6/6.
 */
@Entity
@Table(name = "devices", schema = "data")
public class DevicesEntity {
    private String date;
    private int working;
    private int testing;
    private int repairing;
    private int installed;
    private int totalDevices;

    @Id
    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "working")
    public int getWorking() {
        return working;
    }

    public void setWorking(int working) {
        this.working = working;
    }

    @Basic
    @Column(name = "testing")
    public int getTesting() {
        return testing;
    }

    public void setTesting(int testing) {
        this.testing = testing;
    }

    @Basic
    @Column(name = "repairing")
    public int getRepairing() {
        return repairing;
    }

    public void setRepairing(int repairing) {
        this.repairing = repairing;
    }

    @Basic
    @Column(name = "installed")
    public int getInstalled() {
        return installed;
    }

    public void setInstalled(int installed) {
        this.installed = installed;
    }

    @Basic
    @Column(name = "total_devices")
    public int getTotalDevices() {
        return totalDevices;
    }

    public void setTotalDevices(int totalDevices) {
        this.totalDevices = totalDevices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DevicesEntity that = (DevicesEntity) o;

        if (working != that.working) return false;
        if (testing != that.testing) return false;
        if (repairing != that.repairing) return false;
        if (installed != that.installed) return false;
        if (totalDevices != that.totalDevices) return false;
        return date != null ? date.equals(that.date) : that.date == null;

    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + working;
        result = 31 * result + testing;
        result = 31 * result + repairing;
        result = 31 * result + installed;
        result = 31 * result + totalDevices;
        return result;
    }
}
