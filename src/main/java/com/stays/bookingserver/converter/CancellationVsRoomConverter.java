package com.stays.bookingserver.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.stays.bookingserver.entity.CancellationVsRoomEntity;
import com.stays.bookingserver.helper.Util;
import com.stays.bookingserver.model.CancellationVsRoomModel;

@Component
public class CancellationVsRoomConverter extends CommonConverter
		implements BaseConverter<CancellationVsRoomEntity, CancellationVsRoomModel> {

	private static final long serialVersionUID = -3097439333727958284L;
	private static final Logger logger = LogManager.getLogger(CancellationVsRoomConverter.class);

	@Override
	public CancellationVsRoomEntity modelToEntity(CancellationVsRoomModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CancellationVsRoomModel entityToModel(CancellationVsRoomEntity e) {
		// TODO Auto-generated method stub
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}
		CancellationVsRoomModel cancellationVsRoomModel = new CancellationVsRoomModel();
		cancellationVsRoomModel = (CancellationVsRoomModel) Util.transform(modelMapper, e, cancellationVsRoomModel);
		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}
		return cancellationVsRoomModel;

	}

	@Override
	public List<CancellationVsRoomModel> entityListToModelList(List<CancellationVsRoomEntity> es) {
		// TODO Auto-generated method stub
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}
		List<CancellationVsRoomModel> cancellationVsRoomModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			cancellationVsRoomModels = new ArrayList<>();
			for (CancellationVsRoomEntity cancellationEntity : es) {
				cancellationVsRoomModels.add(entityToModel(cancellationEntity));
			}
		}
		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}
		return cancellationVsRoomModels;
	}

}