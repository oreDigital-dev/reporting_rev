package rw.oredigital.com.v1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import rw.oredigital.com.v1.audits.InitiatorAudit;
import rw.oredigital.com.v1.models.location.DataLocation;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class MineralRecord  extends InitiatorAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private MineralType type;

    @ManyToOne
    @JoinColumn(name = "origin_id")
    private MineSite origin;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @ManyToOne
    @JoinColumn(name = "currentTransfer")
    private MineralsTransfer currentTransfer;

    @ManyToMany(mappedBy = "mineralRecords",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<MineralsTransfer> transfers;

}
