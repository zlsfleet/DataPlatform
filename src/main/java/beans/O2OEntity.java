package beans;

import javax.persistence.*;

/**
 * Created by Kris on 2016/6/6.
 */
@Entity
@Table(name = "o2o", schema = "data")
public class O2OEntity {
    private String date;
    private int appUsers;
    private int appUsersPlus;
    private int orderedUsers;
    private int orderedUsersPlus;
    private int appOrders;
    private int appOrdersPlus;
    private int homeVisits;

    @Id
    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "app_users")
    public int getAppUsers() {
        return appUsers;
    }

    public void setAppUsers(int appUsers) {
        this.appUsers = appUsers;
    }

    @Basic
    @Column(name = "app_users_plus")
    public int getAppUsersPlus() {
        return appUsersPlus;
    }

    public void setAppUsersPlus(int appUsersPlus) {
        this.appUsersPlus = appUsersPlus;
    }

    @Basic
    @Column(name = "ordered_users")
    public int getOrderedUsers() {
        return orderedUsers;
    }

    public void setOrderedUsers(int orderedUsers) {
        this.orderedUsers = orderedUsers;
    }

    @Basic
    @Column(name = "ordered_users_plus")
    public int getOrderedUsersPlus() {
        return orderedUsersPlus;
    }

    public void setOrderedUsersPlus(int orderedUsersPlus) {
        this.orderedUsersPlus = orderedUsersPlus;
    }

    @Basic
    @Column(name = "app_orders")
    public int getAppOrders() {
        return appOrders;
    }

    public void setAppOrders(int appOrders) {
        this.appOrders = appOrders;
    }

    @Basic
    @Column(name = "app_orders_plus")
    public int getAppOrdersPlus() {
        return appOrdersPlus;
    }

    public void setAppOrdersPlus(int appOrdersPlus) {
        this.appOrdersPlus = appOrdersPlus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        O2OEntity o2OEntity = (O2OEntity) o;

        if (appUsers != o2OEntity.appUsers) return false;
        if (appUsersPlus != o2OEntity.appUsersPlus) return false;
        if (orderedUsers != o2OEntity.orderedUsers) return false;
        if (orderedUsersPlus != o2OEntity.orderedUsersPlus) return false;
        if (appOrders != o2OEntity.appOrders) return false;
        return appOrdersPlus == o2OEntity.appOrdersPlus && (date != null ? date.equals(o2OEntity.date) : o2OEntity.date == null);

    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + appUsers;
        result = 31 * result + appUsersPlus;
        result = 31 * result + orderedUsers;
        result = 31 * result + orderedUsersPlus;
        result = 31 * result + appOrders;
        result = 31 * result + appOrdersPlus;

        return result;
    }

    @Basic
    @Column(name = "home_visits")
    public int getHomeVisits() {
        return homeVisits;
    }

    public void setHomeVisits(int homeVisits) {
        this.homeVisits = homeVisits;
    }
}
