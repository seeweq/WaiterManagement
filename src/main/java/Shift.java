import java.util.ArrayList;
import java.util.List;


public class Shift{
    int id;
    Day day;

    List<Waiter> waiters = new ArrayList<>();

    public void addWaiter(Waiter waiter){
        waiters.add(waiter);
    }

    public List getWaiters() {
        return waiters;
    }

    public void setWaiters(List waiters) {
        this.waiters =  waiters;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Day getDay() {
        return day;
    }

    @Override
    public String toString() {
        return "Shift{" +
                "id=" + id +
                ", day=" + day +
                ", waiters=" + waiters +
                '}';
    }
}