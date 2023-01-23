package com.rajan.smsbillingsystem.services;

import com.rajan.smsbillingsystem.models.SentMessages;

import java.util.Date;
import java.util.List;

public interface SentMessageService {
    public List<SentMessages> getSentMessageForDateRange(final Date startDate, final Date endDate, final long customerId);
}
