package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(name = "name")
   private String firstName;
   @Column(name = "last_name")
   private String lastName;
   @Column(name = "email")
   private String email;

   @OneToOne(cascade = CascadeType.PERSIST)
   @JoinColumn(name = "id")
   private Car car;

   public Car getCar() {
      return car;
   }

   public User() {}

   public User(String firstName, String lastName, String email, Car car) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.car = car;
   }

   @Override
   public String toString() {
      return String.format("[Id] Name = [%d] %s %s \nEmail = %s \nCar = %s\n-------------------", id, firstName, lastName, email, getCar());
   }
}
