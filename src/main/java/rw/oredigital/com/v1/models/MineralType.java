package rw.oredigital.com.v1.models;
import lombok.Getter;
import lombok.Setter;
import rw.oredigital.com.v1.audits.InitiatorAudit;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class MineralType extends InitiatorAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int  id;

    private String name;

    private String description;


}
