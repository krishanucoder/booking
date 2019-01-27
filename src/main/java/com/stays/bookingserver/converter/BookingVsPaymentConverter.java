package com.stays.bookingserver.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.stays.bookingserver.entity.BookingVsPaymentEntity;
import com.stays.bookingserver.helper.Util;
import com.stays.bookingserver.model.BookingVsPaymentModel;

@Component
public class BookingVsPaymentConverter extends CommonConverter
		implements BaseConverter<BookingVsPaymentEntity, BookingVsPaymentModel> {

	private static final long serialVersionUID = -9102357208519258911L;
	private static final Logger logger = LogManager.getLogger(BookingVsPaymentConverter.class);

	@Override
	public BookingVsPaymentEntity modelToEntity(BookingVsPaymentModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookingVsPaymentModel entityToModel(BookingVsPaymentEntity e) {

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}

		BookingVsPaymentModel bookingVsPaymentModel = new BookingVsPaymentModel();
		bookingVsPaymentModel = (BookingVsPaymentModel) Util.transform(modelMapper, e, bookingVsPaymentModel);

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}

		return bookingVsPaymentModel;
	}

	@Override
	public List<BookingVsPaymentModel> entityListToModelList(List<BookingVsPaymentEntity> es) {

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}

		List<BookingVsPaymentModel> bookingVsPaymentModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			bookingVsPaymentModels = new ArrayList<>();
			for (BookingVsPaymentEntity bookingVsPaymentEntity : es) {
				bookingVsPaymentModels.add(entityToModel(bookingVsPaymentEntity));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}

		return bookingVsPaymentModels;
	}

}
