package rw.oredigital.com.v1.models;
import lombok.Getter;
import lombok.Setter;
import rw.oredigital.com.v1.models.location.DataLocation;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class MineSite  extends DataLocation implements Serializable {

    @ManyToOne
    @JoinColumn(name = "concession_id")
    private MiningConcession concession;
}
