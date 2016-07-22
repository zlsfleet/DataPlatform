package beans;

import javax.persistence.*;

/**
 * Created by Kris on 2016/6/6.
 */
@Entity
@Table(name = "work_order", schema = "data")
public class WorkOrderEntity {
    private String date;
    private int remainingOrders;
    private int newOrders;
    private int finishedOrders;

    @Id
    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "remaining_orders")
    public int getRemainingOrders() {
        return remainingOrders;
    }

    public void setRemainingOrders(int remainingOrders) {
        this.remainingOrders = remainingOrders;
    }

    @Basic
    @Column(name = "new_orders")
    public int getNewOrders() {
        return newOrders;
    }

    public void setNewOrders(int newOrders) {
        this.newOrders = newOrders;
    }

    @Basic
    @Column(name = "finished_orders")
    public int getFinishedOrders() {
        return finishedOrders;
    }

    public void setFinishedOrders(int finishedOrders) {
        this.finishedOrders = finishedOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkOrderEntity that = (WorkOrderEntity) o;

        if (remainingOrders != that.remainingOrders) return false;
        if (newOrders != that.newOrders) return false;
        if (finishedOrders != that.finishedOrders) return false;
        return date != null ? date.equals(that.date) : that.date == null;

    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + remainingOrders;
        result = 31 * result + newOrders;
        result = 31 * result + finishedOrders;
        return result;
    }
}
