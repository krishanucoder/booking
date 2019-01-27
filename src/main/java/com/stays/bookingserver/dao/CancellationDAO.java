package com.stays.bookingserver.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.stays.bookingserver.entity.CancellationEntity;

@Repository
public class CancellationDAO extends GenericDAO<CancellationEntity, Long> {

	private static final long serialVersionUID = 5876332836788821115L;

	public CancellationDAO() {
		super(CancellationEntity.class);

	}

	public CancellationEntity getCancellationByBookingId(String bookingId) {
		String hql = "FROM CancellationEntity ce where ce.bookingEntity.bookingId = " + Long.parseLong(bookingId);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("rawtypes")
		List results = query.list();
		if (results != null && results.size() > 0)
			return (CancellationEntity) results.get(0);
		return null;
	}
}