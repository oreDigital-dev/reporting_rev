package rw.oredigital.com.v1.models.superClasses;
import lombok.Getter;
import lombok.Setter;
import rw.oredigital.com.v1.audits.InitiatorAudit;
import rw.oredigital.com.v1.enums.ERecordStatus;
import rw.oredigital.com.v1.models.User;
import rw.oredigital.com.v1.models.location.Location;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public class Person extends InitiatorAudit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String firstName;
    private String lastName;
    private String email;
    private String nationalId;
    private Date dob;

    @ManyToOne
    private Location location;

    private ERecordStatus status = ERecordStatus.PENDING;

    @ManyToOne
    private User profile;


    public Person(){

    }

    public Person(String  firstName, String lastName, String email, String nationalId, Date dob){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.nationalId = nationalId;
        this.dob = dob;
    }

}
