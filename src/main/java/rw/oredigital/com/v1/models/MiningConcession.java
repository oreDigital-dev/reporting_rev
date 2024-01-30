package rw.oredigital.com.v1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import rw.oredigital.com.v1.models.location.DataLocation;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
public class MiningConcession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToMany
    @JsonIgnore
    private Set<MineSite> mineSites;

    @ManyToOne
    @JoinColumn(name = "data_location_id")
    private DataLocation dataLocation;

}
