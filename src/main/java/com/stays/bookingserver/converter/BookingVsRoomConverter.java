package com.stays.bookingserver.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.stays.bookingserver.entity.BookingVsRoomEntity;
import com.stays.bookingserver.helper.Util;
import com.stays.bookingserver.model.BookingVsRoomModel;

@Component
public class BookingVsRoomConverter extends CommonConverter
		implements BaseConverter<BookingVsRoomEntity, BookingVsRoomModel> {

	@Autowired
	protected SacCodeConverter sacCodeConverter;

	private static final long serialVersionUID = -6543158351432501780L;
	private static final Logger logger = LogManager.getLogger(BookingVsRoomConverter.class);

	@Override
	public BookingVsRoomEntity modelToEntity(BookingVsRoomModel m) {
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- START");
		}

		BookingVsRoomEntity bookingVsRoomEntity = new BookingVsRoomEntity();

		bookingVsRoomEntity = (BookingVsRoomEntity) Util.transform(modelMapper, m, bookingVsRoomEntity);
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- END");
		}
		return bookingVsRoomEntity;
	}

	@Override
	public BookingVsRoomModel entityToModel(BookingVsRoomEntity e) {

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}

		BookingVsRoomModel bookingVsRoomModel = new BookingVsRoomModel();
		bookingVsRoomModel = (BookingVsRoomModel) Util.transform(modelMapper, e, bookingVsRoomModel);
		bookingVsRoomModel.setSacCodeModel(sacCodeConverter.entityToModel(e.getSacCodeEntity()));
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}

		return bookingVsRoomModel;
	}

	@Override
	public List<BookingVsRoomModel> entityListToModelList(List<BookingVsRoomEntity> es) {

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}

		List<BookingVsRoomModel> bookingVsRoomModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			bookingVsRoomModels = new ArrayList<>();
			for (BookingVsRoomEntity bookingVsRoomEntity : es) {
				bookingVsRoomModels.add(entityToModel(bookingVsRoomEntity));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}

		return bookingVsRoomModels;
	}

}
