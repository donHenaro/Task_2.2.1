package hiber.model;

import javax.persistence.*;

@Entity
@Table(name="cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long car_id;
    @Column
    private String model;
    @Column
    private int series;

    @OneToOne(mappedBy = "car")
    private User user;

    public User getUser() {
        return user;
    }

    public Car() {
    }
    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    @Override
    public String toString() {
        return String.format("{[id] модель = [%d] %s, серия = %d}", car_id, model, series);
    }
}
