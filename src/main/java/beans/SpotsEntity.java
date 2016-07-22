package beans;

import javax.persistence.*;

/**
 * Created by Kris on 2016/6/6.
 */
@Entity
@Table(name = "spots", schema = "data")
public class SpotsEntity {
    private String date;
    private int signed;
    private int surveyed;
    private int worked;
    private int installed;
    private int toRemove;
    private int totalSpots;

    @Id
    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "signed")
    public int getSigned() {
        return signed;
    }

    public void setSigned(int signed) {
        this.signed = signed;
    }

    @Basic
    @Column(name = "surveyed")
    public int getSurveyed() {
        return surveyed;
    }

    public void setSurveyed(int surveyed) {
        this.surveyed = surveyed;
    }

    @Basic
    @Column(name = "worked")
    public int getWorked() {
        return worked;
    }

    public void setWorked(int worked) {
        this.worked = worked;
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
    @Column(name = "to_remove")
    public int getToRemove() {
        return toRemove;
    }

    public void setToRemove(int toRemove) {
        this.toRemove = toRemove;
    }

    @Basic
    @Column(name = "total_spots")
    public int getTotalSpots() {
        return totalSpots;
    }

    public void setTotalSpots(int totalSpots) {
        this.totalSpots = totalSpots;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpotsEntity that = (SpotsEntity) o;

        if (signed != that.signed) return false;
        if (surveyed != that.surveyed) return false;
        if (worked != that.worked) return false;
        if (installed != that.installed) return false;
        if (toRemove != that.toRemove) return false;
        if (totalSpots != that.totalSpots) return false;
        return date != null ? date.equals(that.date) : that.date == null;

    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + signed;
        result = 31 * result + surveyed;
        result = 31 * result + worked;
        result = 31 * result + installed;
        result = 31 * result + toRemove;
        result = 31 * result + totalSpots;
        return result;
    }
}
