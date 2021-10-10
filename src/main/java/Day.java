public class Day {
    int day_id;
    String day;
    int order_by;


    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setId(int id) {
        this.day_id = id;
    }

    public int getId() {
        return day_id;
    }

    @Override
    public String toString() {
        return "Day{" +
                "id=" + day_id +
                ", day='" + day + '\'' +
                ", order_by=" + order_by +
                '}';
    }
}
