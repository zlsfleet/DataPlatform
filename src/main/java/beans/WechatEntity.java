package beans;

import javax.persistence.*;

/**
 * Created by Kris on 2016/6/7.
 */
@Entity
@Table(name = "wechat", schema = "data")
public class WechatEntity {
    private String date;
    private int wxUsers;
    private int wxUsersPlus;
    private int wxUsersMinus;
    private int wxOrders;
    private int wxOrdersPlus;

    @Id
    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "wx_users")
    public int getWxUsers() {
        return wxUsers;
    }

    public void setWxUsers(int wxUsers) {
        this.wxUsers = wxUsers;
    }

    @Basic
    @Column(name = "wx_users_plus")
    public int getWxUsersPlus() {
        return wxUsersPlus;
    }

    public void setWxUsersPlus(int wxUsersPlus) {
        this.wxUsersPlus = wxUsersPlus;
    }

    @Basic
    @Column(name = "wx_users_minus")
    public int getWxUsersMinus() {
        return wxUsersMinus;
    }

    public void setWxUsersMinus(int wxUsersMinus) {
        this.wxUsersMinus = wxUsersMinus;
    }

    @Basic
    @Column(name = "wx_orders")
    public int getWxOrders() {
        return wxOrders;
    }

    public void setWxOrders(int wxOrders) {
        this.wxOrders = wxOrders;
    }

    @Basic
    @Column(name = "wx_orders_plus")
    public int getWxOrdersPlus() {
        return wxOrdersPlus;
    }

    public void setWxOrdersPlus(int wxOrdersPlus) {
        this.wxOrdersPlus = wxOrdersPlus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WechatEntity that = (WechatEntity) o;

        if (wxUsers != that.wxUsers) return false;
        if (wxUsersPlus != that.wxUsersPlus) return false;
        if (wxUsersMinus != that.wxUsersMinus) return false;
        if (wxOrders != that.wxOrders) return false;
        if (wxOrdersPlus != that.wxOrdersPlus) return false;
        return date != null ? date.equals(that.date) : that.date == null;

    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + wxUsers;
        result = 31 * result + wxUsersPlus;
        result = 31 * result + wxUsersMinus;
        result = 31 * result + wxOrders;
        result = 31 * result + wxOrdersPlus;
        return result;
    }
}
