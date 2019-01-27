package com.stays.bookingserver.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.stays.bookingserver.entity.BookingEntity;
import com.stays.bookingserver.helper.Util;
import com.stays.bookingserver.model.BookingModel;

@Component
public class BookingConverter extends CommonConverter implements BaseConverter<BookingEntity, BookingModel> {

	private static final long serialVersionUID = 754163973023221139L;
	private static final Logger logger = LogManager.getLogger(BookingConverter.class);

	@Autowired
	protected BookingInfoConverter bookingInfoConverter;

	@Autowired
	protected BookingVsRoomConverter bookingVsRoomConverter;

	@Autowired
	protected BookingVsPaymentConverter bookingVsPaymentConverter;

	@Autowired
	protected CancellationConverter cancellationConverter;

	@Override
	public BookingEntity modelToEntity(BookingModel m) {
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- START");
		}

		BookingEntity bookingEntity = new BookingEntity();

		bookingEntity = (BookingEntity) Util.transform(modelMapper, m, bookingEntity);
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- END");
		}
		return bookingEntity;
	}

	@Override
	public BookingModel entityToModel(BookingEntity e) {

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}

		BookingModel bookingModel = new BookingModel();
		bookingModel = (BookingModel) Util.transform(modelMapper, e, bookingModel);
		bookingModel.setBookingVsRoomModels(bookingVsRoomConverter.entityListToModelList(e.getBookingVsRoomEntities()));
		bookingModel.setBookingVsPaymentModels(
				bookingVsPaymentConverter.entityListToModelList(e.getBookingVsPaymentEntities()));
		// bookingModel.setConvenienceModel(convenienceConverter.entityToModel(e.getConvenienceEntity()));
		bookingModel.setBookingInfoModel(bookingInfoConverter.entityToModel(e.getBookingInfoEntity()));
		if (e.getCancellationEntity() != null)
			bookingModel.setCancellationModel(cancellationConverter.entityToModel(e.getCancellationEntity()));
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}

		return bookingModel;
	}

	@Override
	public List<BookingModel> entityListToModelList(List<BookingEntity> es) {

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}

		List<BookingModel> bookingModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			bookingModels = new ArrayList<>();
			for (BookingEntity bookingEntity : es) {
				bookingModels.add(entityToModel(bookingEntity));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}

		return bookingModels;
	}
}