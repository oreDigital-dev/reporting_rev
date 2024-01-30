package rw.oredigital.com.v1.models;

import lombok.Getter;
import rw.oredigital.com.v1.models.superClasses.Person;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@Getter
public class Employee extends Person {


    @OneToOne
    @JoinColumn(name = "profile_id")
    private User profile;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;
    public Employee(){
        super();
    };
    public Employee(String  firstName, String lastName, String email, String nationalId, Date dob){
        super(firstName,lastName,email,nationalId,dob);
    }
}
