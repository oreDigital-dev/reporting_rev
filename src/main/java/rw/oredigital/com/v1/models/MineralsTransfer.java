package rw.oredigital.com.v1.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import rw.oredigital.com.v1.models.superClasses.Transfer;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class MineralsTransfer extends Transfer {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "transfers_records", joinColumns = @JoinColumn(name = "record_id"), inverseJoinColumns = @JoinColumn(name = "transfer_id"))
    @JsonIgnore
    Set<MineralRecord> mineralRecords;
}
