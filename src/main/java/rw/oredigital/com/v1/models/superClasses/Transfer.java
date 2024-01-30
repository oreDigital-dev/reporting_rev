package rw.oredigital.com.v1.models.superClasses;
import lombok.Getter;
import lombok.Setter;
import rw.oredigital.com.v1.audits.InitiatorAudit;
import rw.oredigital.com.v1.enums.ETransferStatus;
import rw.oredigital.com.v1.enums.EVisibility;
import rw.oredigital.com.v1.models.location.DataLocation;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public class Transfer extends InitiatorAudit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private ETransferStatus status = ETransferStatus.INITIATED;

    private EVisibility visibility = EVisibility.VISIBLE;

    @ManyToOne
    @JoinColumn(name = "from_data_location")
    private DataLocation fromLocation;

    @ManyToOne
    @JoinColumn(name = "to_data_location")
    private DataLocation toLocation;


    private Date transferDate;

}
