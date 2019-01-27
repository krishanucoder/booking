package com.stays.bookingserver.converter;

import java.io.Serializable;
import java.util.List;

public interface BaseConverter<E, M> extends Serializable {
	
	/**
	 * To convert {M} type object to {E} type
	 * @param m
	 * @return
	 */
	public E modelToEntity(M m) ;
	
	
	/**
	 * To convert {E} type object to {M} type
	 * @param e
	 * @return
	 */
	public M entityToModel(E e);
	
	
	/**
	 * To convert {E} type list of object to {M} type list of object
	 * @param es
	 * @return
	 */
	public List<M> entityListToModelList(List<E> es);

}