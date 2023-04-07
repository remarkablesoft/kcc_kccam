package com.remarkablesoft.framework.service.notification.message.booking.model.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.remarkablesoft.framework.service.notification.message.booking.model.MessageBookingService;

@Service
@Transactional
public class MessageBookingServiceImpl implements MessageBookingService {

		@Autowired
		protected MessageBookingBLO messageBookingBLO;
		
}
