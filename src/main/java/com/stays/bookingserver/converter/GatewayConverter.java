package com.stays.bookingserver.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.stays.bookingserver.entity.GatewayEntity;
import com.stays.bookingserver.helper.Util;
import com.stays.bookingserver.model.GatewayModel;

@Component
public class GatewayConverter extends CommonConverter implements BaseConverter<GatewayEntity, GatewayModel> {

	private static final long serialVersionUID = -5281286692511001742L;
	private static final Logger logger = LogManager.getLogger(GatewayConverter.class);

	@Override
	public GatewayEntity modelToEntity(GatewayModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GatewayModel entityToModel(GatewayEntity e) {

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- START");
		}

		GatewayModel gatewayModel = new GatewayModel();
		gatewayModel = (GatewayModel) Util.transform(modelMapper, e, gatewayModel);

		if (logger.isInfoEnabled()) {
			logger.info("entityToModel -- END");
		}

		return gatewayModel;
	}

	@Override
	public List<GatewayModel> entityListToModelList(List<GatewayEntity> es) {

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- START");
		}

		List<GatewayModel> gatewayModels = null;
		if (!CollectionUtils.isEmpty(es)) {
			gatewayModels = new ArrayList<>();
			for (GatewayEntity gatewayEntity : es) {
				gatewayModels.add(entityToModel(gatewayEntity));
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("entityListToModelList -- END");
		}

		return gatewayModels;
	}

}
