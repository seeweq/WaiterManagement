public class Day {
    int id;
    String day;
    int order_by;


    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Day{" +
                "id=" + id +
                ", day='" + day + '\'' +
                ", order_by=" + order_by +
                '}';
    }
}
