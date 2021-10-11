import java.util.ArrayList;
import java.util.List;


public class Shift{
    int id;
    int waiter_id;
    String waiter;
    int day_id;
    String day;

    List<Waiter> waiters = new ArrayList<>();
    public int getDay_id() {
        return day_id;
    }

    public void setDay_id(int day_id) {
        this.day_id = day_id;
    }

    public int getWaiter_id() {
        return waiter_id;
    }

    public void setWaiter_id(int waiter_id) {
        this.waiter_id = waiter_id;
    }

    public void addWaiter(Waiter waiter){
        waiters.add(waiter);
    }

    public List getWaiters() {
        return waiters;
    }

    public void setWaiters(List waiters) {
        this.waiters =  waiters;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getWaiter() {
        return waiter;
    }

    public void setWaiter(String waiter) {
        this.waiter = waiter;
    }

    public String getDay() {
        return day;
    }

    @Override
    public String toString() {
        return "Shift{" +
                "id=" + id +
                ", waiter_id=" + waiter_id +
                ", waiter='" + waiter + '\'' +
//                ", day_id=" + day_id +
                ", day=" + day +
                ", waiters=" + waiters +
                '}';
    }
}