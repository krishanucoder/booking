package com.stays.bookingserver.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.stays.bookingserver.entity.CancellationEntity;
import com.stays.bookingserver.helper.Util;
import com.stays.bookingserver.model.CancellationModel;

@Component
public class CancellationConverter extends CommonConverter
		implements BaseConverter<CancellationEntity, CancellationModel> {

	private static final long serialVersionUID = -7101212308719678276L;

	private static final Logger logger = LogManager.getLogger(CancellationConverter.class);

	@Autowired
	protected CancellationVsRoomConverter cancellationVsRoomConverter;

	@Override
	public CancellationEntity modelToEntity(CancellationModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CancellationModel entityToModel(CancellationEntity e) {
		// TODO Auto-generated method stub
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		CancellationModel cancellationModel = new CancellationModel();
		cancellationModel = (CancellationModel) Util.transform(modelMapper, e, cancellationModel);
		if (e.getCancellationVsRoomsEntities() != null)
			cancellationModel.setCancellationVsRoomsModels(
					cancellationVsRoomConverter.entityListToModelList(e.getCancellationVsRoomsEntities()));
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		return cancellationModel;
	}

	@Override
	public List<CancellationModel> entityListToModelList(List<CancellationEntity> es) {
		// TODO Auto-generated method stub
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		List<CancellationModel> cancellationModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			cancellationModels = new ArrayList<>();
			for (CancellationEntity cancellationEntity : es) {
				cancellationModels.add(entityToModel(cancellationEntity));
			}
		}
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		return cancellationModels;
	}

}