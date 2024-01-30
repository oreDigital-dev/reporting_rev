package rw.oredigital.com.v1.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import rw.oredigital.com.v1.audits.InitiatorAudit;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Tag  extends InitiatorAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int  id;

    private String tagNumber;

    private String mineralName;

    private String quantity;

    @ManyToOne
    private TagsTransfer currentTransfer;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<TagsTransfer> transfers;
}
