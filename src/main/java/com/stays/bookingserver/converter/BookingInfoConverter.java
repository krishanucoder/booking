package com.stays.bookingserver.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.stays.bookingserver.entity.BookingInfoEntity;
import com.stays.bookingserver.helper.Util;
import com.stays.bookingserver.model.BookingInfoModel;
@Component
public class BookingInfoConverter extends CommonConverter
		implements BaseConverter<BookingInfoEntity, BookingInfoModel> {

	private static final long serialVersionUID = 4689490274519200855L;
	private static final Logger logger = LogManager.getLogger(BookingInfoConverter.class);

	@Override
	public BookingInfoEntity modelToEntity(BookingInfoModel m) {
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- START");
		}

		BookingInfoEntity bookingInfoEntity = new BookingInfoEntity();

		bookingInfoEntity = (BookingInfoEntity) Util.transform(modelMapper, m, bookingInfoEntity);
		if (logger.isInfoEnabled()) {
			logger.info("modelToEntity -- END");
		}
		return bookingInfoEntity;
	}

	@Override
	public BookingInfoModel entityToModel(BookingInfoEntity e) {

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}

		BookingInfoModel bookingInfoModel = new BookingInfoModel();
		bookingInfoModel = (BookingInfoModel) Util.transform(modelMapper, e, bookingInfoModel);

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}

		return bookingInfoModel;
	}

	@Override
	public List<BookingInfoModel> entityListToModelList(List<BookingInfoEntity> es) {

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}

		List<BookingInfoModel> bookingInfoModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			bookingInfoModels = new ArrayList<>();
			for (BookingInfoEntity bookingInfoEntity : es) {
				bookingInfoModels.add(entityToModel(bookingInfoEntity));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}

		return bookingInfoModels;
	}

}
