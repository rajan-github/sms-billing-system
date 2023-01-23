package com.rajan.smsbillingsystem.services;

import com.rajan.smsbillingsystem.models.SentMessages;
import com.rajan.smsbillingsystem.repository.SentMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SentMessageServiceImpl implements SentMessageService {
    private final SentMessageRepository sentMessageRepository;

    @Autowired
    public SentMessageServiceImpl(SentMessageRepository sentMessageRepository) {
        this.sentMessageRepository = sentMessageRepository;
    }

    @Override
    public List<SentMessages> getSentMessageForDateRange(Date startDate, Date endDate, long customerId) {
        var sentMessages = sentMessageRepository.getSentMessagesByMonth(startDate, endDate, customerId);
        if (sentMessages == null || sentMessages.isEmpty())
            return List.of();
        return sentMessages;
    }
}
