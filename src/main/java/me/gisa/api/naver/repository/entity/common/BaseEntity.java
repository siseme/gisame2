package me.gisa.api.naver.repository.entity.common;

import static java.time.Instant.ofEpochMilli;
import static java.time.LocalDateTime.ofInstant;
import static java.time.ZoneId.systemDefault;
import static java.util.Objects.isNull;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
public class BaseEntity {

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", updatable = false)
    private Date createdDateTime;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date", updatable = true)
    private Date lastModifiedDateTime;

    public LocalDateTime getCreatedDateTime() {
        return getLocalDateTimeFrom(createdDateTime);
    }

    public void setCreatedDateTime(final LocalDateTime createdDateTime) {
        this.createdDateTime = getDateFrom(createdDateTime);
    }

    public LocalDateTime getLastModifiedDateTime() {
        return getLocalDateTimeFrom(lastModifiedDateTime);
    }

    public void setLastModifiedDateTime(final LocalDateTime lastModifiedDateTime) {
        this.lastModifiedDateTime = getDateFrom(lastModifiedDateTime);
    }

    private LocalDateTime getLocalDateTimeFrom(Date date) {
        return isNull(date) ? null : ofInstant(ofEpochMilli(date.getTime()), systemDefault());
    }

    private Date getDateFrom(LocalDateTime localDateTime) {
        return isNull(localDateTime) ? null : Date.from(localDateTime.atZone(systemDefault())
                                                                     .toInstant());
    }
}
