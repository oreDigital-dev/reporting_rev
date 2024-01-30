package rw.oredigital.com.v1.models;

import lombok.Getter;
import lombok.Setter;
import rw.oredigital.com.v1.models.superClasses.Transfer;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class TagsTransfer extends Transfer {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "transfer_tags", joinColumns = @JoinColumn(name = "transfer_id") , inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;
}
