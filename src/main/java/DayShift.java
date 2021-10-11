import java.util.ArrayList;
import java.util.List;

public class DayShift {

    String weekName;

    List<String> waiters = new ArrayList<>();

    public DayShift(String weekName) {
        this.weekName = weekName;
    }

    public List<String> getWaiters() {
        return waiters;
    }

    public String getWeekName() {
        return weekName;
    }

    public void addWaiter(String waiterName) {
        waiters.add(waiterName);
    }

    @Override
    public String toString() {
        return "DayShift{" +
                "weekName='" + weekName + '\'' +
                ", waiters=" + waiters +
                '}';
    }
}
