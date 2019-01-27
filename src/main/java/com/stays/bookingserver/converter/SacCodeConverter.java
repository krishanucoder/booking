package com.stays.bookingserver.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.stays.bookingserver.entity.SacCodeEntity;
import com.stays.bookingserver.helper.Util;
import com.stays.bookingserver.model.SacCodeModel;

@Component
public class SacCodeConverter extends CommonConverter implements BaseConverter<SacCodeEntity, SacCodeModel> {

	private static final long serialVersionUID = -144950212377649911L;
	private static final Logger logger = LogManager.getLogger(SacCodeConverter.class);

	@Override
	public SacCodeEntity modelToEntity(SacCodeModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SacCodeModel entityToModel(SacCodeEntity e) {

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}

		SacCodeModel sacCodeModel = new SacCodeModel();
		sacCodeModel = (SacCodeModel) Util.transform(modelMapper, e, sacCodeModel);

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}

		return sacCodeModel;
	}

	@Override
	public List<SacCodeModel> entityListToModelList(List<SacCodeEntity> es) {

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}

		List<SacCodeModel> sacCodeModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			sacCodeModels = new ArrayList<>();
			for (SacCodeEntity sacCodeEntity : es) {
				sacCodeModels.add(entityToModel(sacCodeEntity));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}

		return sacCodeModels;
	}

}
