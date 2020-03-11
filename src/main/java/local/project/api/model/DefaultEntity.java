package local.project.api.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class DefaultEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Basic(optional = false)
    @Column(name = "updated_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Basic(optional = false)
    @Column(name = "created_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Basic(optional = false)
    @Column(name = "is_del")
    private boolean is_del = false;

    @PrePersist
    void preInsert() {
        this.updatedAt = new Date();
        if (this.createdAt == null) {
            this.createdAt = new Date();
        }
    }
}