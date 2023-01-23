package com.rajan.smsbillingsystem.repository;

import com.rajan.smsbillingsystem.models.SentMessages;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface SentMessageRepository extends CrudRepository<SentMessages, Long> {
    @Query("select a from SentMessages a where a.sentDate>=:startDate and a.sentDate<=:endDate and a.sentBy.id=:customerId")
    public List<SentMessages> getSentMessagesByMonth(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("customerId") long customerId);
}
