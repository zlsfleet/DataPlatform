package beans;

import javax.persistence.*;

/**
 * Created by Kris on 2016/6/6.
 */
@Entity
@Table(name = "express", schema = "data")
public class ExpressEntity {
    private String date;
    private int recipients;
    private int recipientsPlus;
    private int packages;
    private int packagesPlus;
    private int returns;
    private int couriers;
    private int couriersPlus;

    @Id
    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "recipients")
    public int getRecipients() {
        return recipients;
    }

    public void setRecipients(int recipients) {
        this.recipients = recipients;
    }

    @Basic
    @Column(name = "recipients_plus")
    public int getRecipientsPlus() {
        return recipientsPlus;
    }

    public void setRecipientsPlus(int recipientsPlus) {
        this.recipientsPlus = recipientsPlus;
    }

    @Basic
    @Column(name = "packages")
    public int getPackages() {
        return packages;
    }

    public void setPackages(int packages) {
        this.packages = packages;
    }

    @Basic
    @Column(name = "packages_plus")
    public int getPackagesPlus() {
        return packagesPlus;
    }

    public void setPackagesPlus(int packagesPlus) {
        this.packagesPlus = packagesPlus;
    }

    @Basic
    @Column(name = "returns")
    public int getReturns() {
        return returns;
    }

    public void setReturns(int returns) {
        this.returns = returns;
    }

    @Basic
    @Column(name = "couriers")
    public int getCouriers() {
        return couriers;
    }

    public void setCouriers(int couriers) {
        this.couriers = couriers;
    }

    @Basic
    @Column(name = "couriers_plus")
    public int getCouriersPlus() {
        return couriersPlus;
    }

    public void setCouriersPlus(int couriersPlus) {
        this.couriersPlus = couriersPlus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExpressEntity that = (ExpressEntity) o;

        if (recipients != that.recipients) return false;
        if (recipientsPlus != that.recipientsPlus) return false;
        if (packages != that.packages) return false;
        if (packagesPlus != that.packagesPlus) return false;
        if (returns != that.returns) return false;
        if (couriers != that.couriers) return false;
        if (couriersPlus != that.couriersPlus) return false;
        return date != null ? date.equals(that.date) : that.date == null;

    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + recipients;
        result = 31 * result + recipientsPlus;
        result = 31 * result + packages;
        result = 31 * result + packagesPlus;
        result = 31 * result + returns;
        result = 31 * result + couriers;
        result = 31 * result + couriersPlus;
        return result;
    }
}
