/**
 * @author SUDEEP
 */
package com.stays.bookingserver.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;

import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public abstract class GenericDAO<E, PK extends Serializable> implements Serializable {

	private static final long serialVersionUID = -997857275155537865L;

	@Value("${entitymanager.packagesToScan}")
	private String entitymanagerPackagesToScan;

	private Class<E> entityClass;

	@Autowired
	protected SessionFactory sessionFactory;

	public GenericDAO(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	public void create(E entity) {
		sessionFactory.getCurrentSession().persist(entity);
	}

	public void update(E entity) {
		sessionFactory.getCurrentSession().merge(entity);
	}

	public void delete(E entity) {

		sessionFactory.getCurrentSession().delete(entity);
	}

	public Serializable save(E entity) {

		return sessionFactory.getCurrentSession().save(entity);
	}

	public void saveOrUpdate(E entity) {

		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@SuppressWarnings("unchecked")
	public List<E> findAll() {

		Criteria cr = sessionFactory.getCurrentSession().createCriteria(entityClass);

		String sortField = "";

		Field[] fields = entityClass.getDeclaredFields();

		for (Field f : fields) {

			if (f.getAnnotation(Id.class) != null) {
				sortField = f.getName();
				break;
			}
		}

		cr.addOrder(Order.asc(sortField));
		return cr.list();
	}

	public E find(PK id) {

		return sessionFactory.getCurrentSession().get(entityClass, id);
	}

	public Class<E> getEntityClass() {

		return entityClass;
	}

	public void setEntityClass(Class<E> entityClass) {

		this.entityClass = entityClass;
	}

	@SuppressWarnings("unchecked")
	public List<E> fetchListBySubCiteria(Map<String, Map<String, Map<String, String>>> alliasMap) throws Exception {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(getEntityClass());

		for (Map.Entry<String, Map<String, Map<String, String>>> entry1 : alliasMap.entrySet()) {

			String alliasMapKey = entry1.getKey();
			if (alliasMapKey.equals(entityClass.getName())) { // Same as Parent Class
				Map<String, Map<String, String>> innerMap = entry1.getValue();
				for (Map.Entry<String, Map<String, String>> entry2 : innerMap.entrySet()) {

					String outerMapKey = entry2.getKey();
					Map<String, String> innerestMap = entry2.getValue();
					for (Map.Entry<String, String> entry3 : innerestMap.entrySet()) {

						String innerMapKey = entry3.getKey();
						String innerMapValue = entry3.getValue();

						Class<?> cls = Class.forName(entityClass.getName());
						Object type = "";
						Field[] fields1 = cls.getDeclaredFields();
						Field[] fields2 = cls.getSuperclass().getDeclaredFields();
						Field[] both = ArrayUtils.addAll(fields1, fields2);
						for (Field f : both) {

							if (innerMapKey.equals(f.getName())) {
								type = f.getType();
								if (type.toString().contains("Long")) {

									if (outerMapKey.equals("eq")) {
										criteria.add(Restrictions.eq(innerMapKey, Long.parseLong(innerMapValue)));
									} else if (outerMapKey.equals("ne")) {
										criteria.add(Restrictions.ne(innerMapKey, Long.parseLong(innerMapValue)));
									} else if (outerMapKey.equals("between")) {
										String[] betweenCase = innerMapValue.split(",");
										criteria.add(Restrictions.between(innerMapKey, Long.parseLong(betweenCase[0]),
												Long.parseLong(betweenCase[1])));
									} else if (outerMapKey.equals("in")) {
										String[] inCase = innerMapValue.split(",");
										Long[] longArrayInCase = new Long[inCase.length];
										for (int i = 0; i < inCase.length; i++) {

											String in = inCase[i];
											longArrayInCase[i] = Long.parseLong(in);
										}

										criteria.add(Restrictions.in(innerMapKey, longArrayInCase));
									} else if (outerMapKey.equals("isNull")) {
										criteria.add(Restrictions.isNull(innerMapKey));
									} else if (outerMapKey.equals("isNotNull")) {
										criteria.add(Restrictions.isNotNull(innerMapKey));
									} else if (outerMapKey.equals("asc")) {
										criteria.addOrder(Order.asc((innerMapKey)));
									} else if (outerMapKey.equals("desc")) {
										criteria.addOrder(Order.desc((innerMapKey)));
									}

								} else if (type.toString().contains("Integer")) {

									if (outerMapKey.equals("eq")) {
										criteria.add(Restrictions.eq(innerMapKey, Integer.parseInt(innerMapValue)));
									} else if (outerMapKey.equals("ne")) {
										criteria.add(Restrictions.ne(innerMapKey, Integer.parseInt(innerMapValue)));
									} else if (outerMapKey.equals("between")) {
										String[] betweenCase = innerMapValue.split(",");
										criteria.add(Restrictions.between(innerMapKey, Integer.parseInt(betweenCase[0]),
												Integer.parseInt(betweenCase[1])));
									} else if (outerMapKey.equals("in")) {
										String[] inCase = innerMapValue.split(",");
										Integer[] intArrayInCase = new Integer[inCase.length];
										for (int i = 0; i < inCase.length; i++) {

											String in = inCase[i];
											intArrayInCase[i] = Integer.parseInt(in);
										}

										criteria.add(Restrictions.in(innerMapKey, intArrayInCase));
									} else if (outerMapKey.equals("isNull")) {
										criteria.add(Restrictions.isNull(innerMapKey));
									} else if (outerMapKey.equals("isNotNull")) {
										criteria.add(Restrictions.isNotNull(innerMapKey));
									} else if (outerMapKey.equals("asc")) {
										criteria.addOrder(Order.asc((innerMapKey)));
									} else if (outerMapKey.equals("desc")) {
										criteria.addOrder(Order.desc((innerMapKey)));
									}

								} else if (type.toString().contains("Int")) {

									if (outerMapKey.equals("eq")) {
										criteria.add(Restrictions.eq(innerMapKey, Integer.parseInt(innerMapValue)));
									} else if (outerMapKey.equals("ne")) {
										criteria.add(Restrictions.ne(innerMapKey, Integer.parseInt(innerMapValue)));
									} else if (outerMapKey.equals("between")) {
										String[] betweenCase = innerMapValue.split(",");
										criteria.add(Restrictions.between(innerMapKey, Integer.parseInt(betweenCase[0]),
												Integer.parseInt(betweenCase[1])));
									} else if (outerMapKey.equals("in")) {
										String[] inCase = innerMapValue.split(",");
										Integer[] intArrayInCase = new Integer[inCase.length];
										for (int i = 0; i < inCase.length; i++) {

											String in = inCase[i];
											intArrayInCase[i] = Integer.parseInt(in);
										}

										criteria.add(Restrictions.in(innerMapKey, intArrayInCase));
									} else if (outerMapKey.equals("isNull")) {
										criteria.add(Restrictions.isNull(innerMapKey));
									} else if (outerMapKey.equals("isNotNull")) {
										criteria.add(Restrictions.isNotNull(innerMapKey));
									} else if (outerMapKey.equals("asc")) {
										criteria.addOrder(Order.asc((innerMapKey)));
									} else if (outerMapKey.equals("desc")) {
										criteria.addOrder(Order.desc((innerMapKey)));
									}

								} else if (type.toString().contains("Double")) {

									if (outerMapKey.equals("eq")) {
										criteria.add(Restrictions.eq(innerMapKey, Double.parseDouble(innerMapValue)));
									} else if (outerMapKey.equals("ne")) {
										criteria.add(Restrictions.ne(innerMapKey, Double.parseDouble(innerMapValue)));
									} else if (outerMapKey.equals("between")) {
										String[] betweenCase = innerMapValue.split(",");
										criteria.add(
												Restrictions.between(innerMapKey, Double.parseDouble(betweenCase[0]),
														Double.parseDouble(betweenCase[1])));
									} else if (outerMapKey.equals("isNull")) {
										criteria.add(Restrictions.isNull(innerMapKey));
									} else if (outerMapKey.equals("isNotNull")) {
										criteria.add(Restrictions.isNotNull(innerMapKey));
									} else if (outerMapKey.equals("asc")) {
										criteria.addOrder(Order.asc((innerMapKey)));
									} else if (outerMapKey.equals("desc")) {
										criteria.addOrder(Order.desc((innerMapKey)));
									}

								} else {

									if (outerMapKey.equals("eq")) {
										criteria.add(Restrictions.eq(innerMapKey, innerMapValue));
									} else if (outerMapKey.equals("ne")) {
										criteria.add(Restrictions.ne(innerMapKey, innerMapValue));
									} else if (outerMapKey.equals("between")) {
										String[] betweenCase = innerMapValue.split(",");
										criteria.add(Restrictions.between(innerMapKey, betweenCase[0], betweenCase[1]));
									} else if (outerMapKey.equals("isNull")) {
										criteria.add(Restrictions.isNull(innerMapKey));
									} else if (outerMapKey.equals("isNotNull")) {
										criteria.add(Restrictions.isNotNull(innerMapKey));
									} else if (outerMapKey.equals("asc")) {
										criteria.addOrder(Order.asc((innerMapKey)));
									} else if (outerMapKey.equals("desc")) {
										criteria.addOrder(Order.desc((innerMapKey)));
									} else if (outerMapKey.equals("ge")) {
										criteria.add(Restrictions.ge(innerMapKey, innerMapValue));
									} else if (outerMapKey.equals("le")) {
										criteria.add(Restrictions.ge(innerMapKey, innerMapValue));
									}

								}
								break;
							}
						}

					}

				}
			} else { // Condition Of Child Class

				Map<String, Map<String, String>> innerMap = entry1.getValue();
				for (Map.Entry<String, Map<String, String>> entry2 : innerMap.entrySet()) {

					String outerMapKey = entry2.getKey();
					Map<String, String> innerestMap = entry2.getValue();
					int count = 0;
					for (Map.Entry<String, String> entry3 : innerestMap.entrySet()) {
						count++;
						String alliasName = alliasMapKey + "_alias" + count;
						String innerMapKey = entry3.getKey();
						String innerMapValue = entry3.getValue();
						String firstLetter = alliasMapKey.substring(0, 1);
						firstLetter = firstLetter.toUpperCase();
						String alliasMapKey1 = entitymanagerPackagesToScan + "." + firstLetter
								+ alliasMapKey.substring(1, alliasMapKey.length());
						Class<?> cls = Class.forName(alliasMapKey1);
						Object type = "";
						Field[] fields1 = cls.getDeclaredFields();
						Field[] fields2 = cls.getSuperclass().getDeclaredFields();
						Field[] both = ArrayUtils.addAll(fields1, fields2);
						for (Field f : both) {

							if (innerMapKey.equals(f.getName())) {
								type = f.getType();
								if (type.toString().contains("Long")) {

									if (outerMapKey.equals("eq")) {
										criteria.createAlias(alliasMapKey, alliasName).add(Restrictions
												.eq(alliasName + "." + innerMapKey, Long.parseLong(innerMapValue)));
									} else if (outerMapKey.equals("ne")) {
										criteria.createAlias(alliasMapKey, alliasName).add(Restrictions
												.ne(alliasName + "." + innerMapKey, Long.parseLong(innerMapValue)));
									} else if (outerMapKey.equals("between")) {
										String[] betweenCase = innerMapValue.split(",");
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.between(alliasName + "." + innerMapKey,
														Long.parseLong(betweenCase[0]),
														Long.parseLong(betweenCase[1])));
									} else if (outerMapKey.equals("in")) {
										String[] inCase = innerMapValue.split(",");
										Long[] longArrayInCase = new Long[inCase.length];
										for (int i = 0; i < inCase.length; i++) {

											String in = inCase[i];
											longArrayInCase[i] = Long.parseLong(in);
										}

										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.in(alliasName + "." + innerMapKey, longArrayInCase));
									} else if (outerMapKey.equals("isNull")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.isNull(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("isNotNull")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.isNotNull(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("asc")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.addOrder(Order.asc(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("desc")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.addOrder(Order.desc(alliasName + "." + innerMapKey));
									}

								} else if (type.toString().contains("Integer")) {

									if (outerMapKey.equals("eq")) {
										criteria.createAlias(alliasMapKey, alliasName).add(Restrictions
												.eq(alliasName + "." + innerMapKey, Integer.parseInt(innerMapValue)));
									} else if (outerMapKey.equals("ne")) {
										criteria.createAlias(alliasMapKey, alliasName).add(Restrictions
												.ne(alliasName + "." + innerMapKey, Integer.parseInt(innerMapValue)));
									} else if (outerMapKey.equals("between")) {
										String[] betweenCase = innerMapValue.split(",");
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.between(alliasName + "." + innerMapKey,
														Integer.parseInt(betweenCase[0]),
														Integer.parseInt(betweenCase[1])));
									} else if (outerMapKey.equals("in")) {
										String[] inCase = innerMapValue.split(",");
										Integer[] intArrayInCase = new Integer[inCase.length];
										for (int i = 0; i < inCase.length; i++) {

											String in = inCase[i];
											intArrayInCase[i] = Integer.parseInt(in);
										}

										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.in(alliasName + "." + innerMapKey, intArrayInCase));
									} else if (outerMapKey.equals("isNull")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.isNull(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("isNotNull")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.isNotNull(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("asc")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.addOrder(Order.asc(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("desc")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.addOrder(Order.desc(alliasName + "." + innerMapKey));
									}

								} else if (type.toString().contains("Int")) {

									if (outerMapKey.equals("eq")) {
										criteria.createAlias(alliasMapKey, alliasName).add(Restrictions
												.eq(alliasName + "." + innerMapKey, Integer.parseInt(innerMapValue)));
									} else if (outerMapKey.equals("ne")) {
										criteria.createAlias(alliasMapKey, alliasName).add(Restrictions
												.ne(alliasName + "." + innerMapKey, Integer.parseInt(innerMapValue)));
									} else if (outerMapKey.equals("between")) {
										String[] betweenCase = innerMapValue.split(",");
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.between(alliasName + "." + innerMapKey,
														Integer.parseInt(betweenCase[0]),
														Integer.parseInt(betweenCase[1])));
									} else if (outerMapKey.equals("in")) {
										String[] inCase = innerMapValue.split(",");
										Integer[] intArrayInCase = new Integer[inCase.length];
										for (int i = 0; i < inCase.length; i++) {

											String in = inCase[i];
											intArrayInCase[i] = Integer.parseInt(in);
										}

										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.in(alliasName + "." + innerMapKey, intArrayInCase));
									} else if (outerMapKey.equals("isNull")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.isNull(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("isNotNull")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.isNotNull(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("asc")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.addOrder(Order.asc(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("desc")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.addOrder(Order.desc(alliasName + "." + innerMapKey));
									}

								} else if (type.toString().contains("Double")) {

									if (outerMapKey.equals("eq")) {
										criteria.createAlias(alliasMapKey, alliasName).add(Restrictions
												.eq(alliasName + "." + innerMapKey, Double.parseDouble(innerMapValue)));
									} else if (outerMapKey.equals("ne")) {
										criteria.createAlias(alliasMapKey, alliasName).add(Restrictions
												.ne(alliasName + "." + innerMapKey, Double.parseDouble(innerMapValue)));
									} else if (outerMapKey.equals("between")) {
										String[] betweenCase = innerMapValue.split(",");
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.between(alliasName + "." + innerMapKey,
														Double.parseDouble(betweenCase[0]),
														Double.parseDouble(betweenCase[1])));
									} else if (outerMapKey.equals("isNull")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.isNull(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("isNotNull")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.isNotNull(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("asc")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.addOrder(Order.asc(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("desc")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.addOrder(Order.desc(alliasName + "." + innerMapKey));
									}

								} else {

									if (outerMapKey.equals("eq")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.eq(alliasName + "." + innerMapKey, innerMapValue));
									} else if (outerMapKey.equals("ne")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.ne(alliasName + "." + innerMapKey, innerMapValue));
									} else if (outerMapKey.equals("between")) {
										String[] betweenCase = innerMapValue.split(",");
										criteria.createAlias(alliasMapKey, alliasName).add(Restrictions.between(
												alliasName + "." + innerMapKey, betweenCase[0], betweenCase[1]));
									} else if (outerMapKey.equals("isNull")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.isNull(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("isNotNull")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.isNotNull(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("asc")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.addOrder(Order.asc(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("desc")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.addOrder(Order.desc(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("ge")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.ge(alliasName + "." + innerMapKey, innerMapValue));
									} else if (outerMapKey.equals("le")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.le(alliasName + "." + innerMapKey, innerMapValue));
									}

								}
								break;
							}
						}

					}

				}
			}

		}

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<E> fetchListBySubCiteriaWithLimit(Map<String, Map<String, Map<String, String>>> alliasMap, int start,
			int max) throws Exception {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(getEntityClass());

		for (Map.Entry<String, Map<String, Map<String, String>>> entry1 : alliasMap.entrySet()) {

			String alliasMapKey = entry1.getKey();
			if (alliasMapKey.equals(entityClass.getName())) { // Same as Parent Class
				Map<String, Map<String, String>> innerMap = entry1.getValue();
				for (Map.Entry<String, Map<String, String>> entry2 : innerMap.entrySet()) {

					String outerMapKey = entry2.getKey();
					Map<String, String> innerestMap = entry2.getValue();
					for (Map.Entry<String, String> entry3 : innerestMap.entrySet()) {

						String innerMapKey = entry3.getKey();
						String innerMapValue = entry3.getValue();

						Class<?> cls = Class.forName(entityClass.getName());
						Object type = "";
						Field[] fields1 = cls.getDeclaredFields();
						Field[] fields2 = cls.getSuperclass().getDeclaredFields();
						Field[] both = ArrayUtils.addAll(fields1, fields2);
						for (Field f : both) {

							if (innerMapKey.equals(f.getName())) {
								type = f.getType();
								if (type.toString().contains("Long")) {

									if (outerMapKey.equals("eq")) {
										criteria.add(Restrictions.eq(innerMapKey, Long.parseLong(innerMapValue)));
									} else if (outerMapKey.equals("ne")) {
										criteria.add(Restrictions.ne(innerMapKey, Long.parseLong(innerMapValue)));
									} else if (outerMapKey.equals("between")) {
										String[] betweenCase = innerMapValue.split(",");
										criteria.add(Restrictions.between(innerMapKey, Long.parseLong(betweenCase[0]),
												Long.parseLong(betweenCase[1])));
									} else if (outerMapKey.equals("in")) {
										String[] inCase = innerMapValue.split(",");
										Long[] longArrayInCase = new Long[inCase.length];
										for (int i = 0; i < inCase.length; i++) {

											String in = inCase[i];
											longArrayInCase[i] = Long.parseLong(in);
										}

										criteria.add(Restrictions.in(innerMapKey, longArrayInCase));
									} else if (outerMapKey.equals("isNull")) {
										criteria.add(Restrictions.isNull(innerMapKey));
									} else if (outerMapKey.equals("isNotNull")) {
										criteria.add(Restrictions.isNotNull(innerMapKey));
									} else if (outerMapKey.equals("asc")) {
										criteria.addOrder(Order.asc((innerMapKey)));
									} else if (outerMapKey.equals("desc")) {
										criteria.addOrder(Order.desc((innerMapKey)));
									}

								} else if (type.toString().contains("Integer")) {

									if (outerMapKey.equals("eq")) {
										criteria.add(Restrictions.eq(innerMapKey, Integer.parseInt(innerMapValue)));
									} else if (outerMapKey.equals("ne")) {
										criteria.add(Restrictions.ne(innerMapKey, Integer.parseInt(innerMapValue)));
									} else if (outerMapKey.equals("between")) {
										String[] betweenCase = innerMapValue.split(",");
										criteria.add(Restrictions.between(innerMapKey, Integer.parseInt(betweenCase[0]),
												Integer.parseInt(betweenCase[1])));
									} else if (outerMapKey.equals("in")) {
										String[] inCase = innerMapValue.split(",");
										Integer[] intArrayInCase = new Integer[inCase.length];
										for (int i = 0; i < inCase.length; i++) {

											String in = inCase[i];
											intArrayInCase[i] = Integer.parseInt(in);
										}

										criteria.add(Restrictions.in(innerMapKey, intArrayInCase));
									} else if (outerMapKey.equals("isNull")) {
										criteria.add(Restrictions.isNull(innerMapKey));
									} else if (outerMapKey.equals("isNotNull")) {
										criteria.add(Restrictions.isNotNull(innerMapKey));
									} else if (outerMapKey.equals("asc")) {
										criteria.addOrder(Order.asc((innerMapKey)));
									} else if (outerMapKey.equals("desc")) {
										criteria.addOrder(Order.desc((innerMapKey)));
									}

								} else if (type.toString().contains("Int")) {

									if (outerMapKey.equals("eq")) {
										criteria.add(Restrictions.eq(innerMapKey, Integer.parseInt(innerMapValue)));
									} else if (outerMapKey.equals("ne")) {
										criteria.add(Restrictions.ne(innerMapKey, Integer.parseInt(innerMapValue)));
									} else if (outerMapKey.equals("between")) {
										String[] betweenCase = innerMapValue.split(",");
										criteria.add(Restrictions.between(innerMapKey, Integer.parseInt(betweenCase[0]),
												Integer.parseInt(betweenCase[1])));
									} else if (outerMapKey.equals("in")) {
										String[] inCase = innerMapValue.split(",");
										Integer[] intArrayInCase = new Integer[inCase.length];
										for (int i = 0; i < inCase.length; i++) {

											String in = inCase[i];
											intArrayInCase[i] = Integer.parseInt(in);
										}

										criteria.add(Restrictions.in(innerMapKey, intArrayInCase));
									} else if (outerMapKey.equals("isNull")) {
										criteria.add(Restrictions.isNull(innerMapKey));
									} else if (outerMapKey.equals("isNotNull")) {
										criteria.add(Restrictions.isNotNull(innerMapKey));
									} else if (outerMapKey.equals("asc")) {
										criteria.addOrder(Order.asc((innerMapKey)));
									} else if (outerMapKey.equals("desc")) {
										criteria.addOrder(Order.desc((innerMapKey)));
									}

								} else if (type.toString().contains("Double")) {

									if (outerMapKey.equals("eq")) {
										criteria.add(Restrictions.eq(innerMapKey, Double.parseDouble(innerMapValue)));
									} else if (outerMapKey.equals("ne")) {
										criteria.add(Restrictions.ne(innerMapKey, Double.parseDouble(innerMapValue)));
									} else if (outerMapKey.equals("between")) {
										String[] betweenCase = innerMapValue.split(",");
										criteria.add(
												Restrictions.between(innerMapKey, Double.parseDouble(betweenCase[0]),
														Double.parseDouble(betweenCase[1])));
									} else if (outerMapKey.equals("isNull")) {
										criteria.add(Restrictions.isNull(innerMapKey));
									} else if (outerMapKey.equals("isNotNull")) {
										criteria.add(Restrictions.isNotNull(innerMapKey));
									} else if (outerMapKey.equals("asc")) {
										criteria.addOrder(Order.asc((innerMapKey)));
									} else if (outerMapKey.equals("desc")) {
										criteria.addOrder(Order.desc((innerMapKey)));
									}

								} else {

									if (outerMapKey.equals("eq")) {
										criteria.add(Restrictions.eq(innerMapKey, innerMapValue));
									} else if (outerMapKey.equals("ne")) {
										criteria.add(Restrictions.ne(innerMapKey, innerMapValue));
									} else if (outerMapKey.equals("between")) {
										String[] betweenCase = innerMapValue.split(",");
										criteria.add(Restrictions.between(innerMapKey, betweenCase[0], betweenCase[1]));
									} else if (outerMapKey.equals("isNull")) {
										criteria.add(Restrictions.isNull(innerMapKey));
									} else if (outerMapKey.equals("isNotNull")) {
										criteria.add(Restrictions.isNotNull(innerMapKey));
									} else if (outerMapKey.equals("asc")) {
										criteria.addOrder(Order.asc((innerMapKey)));
									} else if (outerMapKey.equals("desc")) {
										criteria.addOrder(Order.desc((innerMapKey)));
									}

								}
								break;
							}
						}

					}

				}
			} else { // Condition Of Child Class

				Map<String, Map<String, String>> innerMap = entry1.getValue();
				for (Map.Entry<String, Map<String, String>> entry2 : innerMap.entrySet()) {

					String outerMapKey = entry2.getKey();
					Map<String, String> innerestMap = entry2.getValue();
					int count = 0;
					for (Map.Entry<String, String> entry3 : innerestMap.entrySet()) {
						count++;
						String alliasName = alliasMapKey + "_alias" + count;
						String innerMapKey = entry3.getKey();
						String innerMapValue = entry3.getValue();
						String firstLetter = alliasMapKey.substring(0, 1);
						firstLetter = firstLetter.toUpperCase();
						String alliasMapKey1 = entitymanagerPackagesToScan + "." + firstLetter
								+ alliasMapKey.substring(1, alliasMapKey.length());
						Class<?> cls = Class.forName(alliasMapKey1);
						Object type = "";
						Field[] fields1 = cls.getDeclaredFields();
						Field[] fields2 = cls.getSuperclass().getDeclaredFields();
						Field[] both = ArrayUtils.addAll(fields1, fields2);
						for (Field f : both) {

							if (innerMapKey.equals(f.getName())) {
								type = f.getType();
								if (type.toString().contains("Long")) {

									if (outerMapKey.equals("eq")) {
										criteria.createAlias(alliasMapKey, alliasName).add(Restrictions
												.eq(alliasName + "." + innerMapKey, Long.parseLong(innerMapValue)));
									} else if (outerMapKey.equals("ne")) {
										criteria.createAlias(alliasMapKey, alliasName).add(Restrictions
												.ne(alliasName + "." + innerMapKey, Long.parseLong(innerMapValue)));
									} else if (outerMapKey.equals("between")) {
										String[] betweenCase = innerMapValue.split(",");
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.between(alliasName + "." + innerMapKey,
														Long.parseLong(betweenCase[0]),
														Long.parseLong(betweenCase[1])));
									} else if (outerMapKey.equals("in")) {
										String[] inCase = innerMapValue.split(",");
										Long[] longArrayInCase = new Long[inCase.length];
										for (int i = 0; i < inCase.length; i++) {

											String in = inCase[i];
											longArrayInCase[i] = Long.parseLong(in);
										}

										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.in(alliasName + "." + innerMapKey, longArrayInCase));
									} else if (outerMapKey.equals("isNull")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.isNull(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("isNotNull")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.isNotNull(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("asc")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.addOrder(Order.asc(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("desc")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.addOrder(Order.desc(alliasName + "." + innerMapKey));
									}

								} else if (type.toString().contains("Integer")) {

									if (outerMapKey.equals("eq")) {
										criteria.createAlias(alliasMapKey, alliasName).add(Restrictions
												.eq(alliasName + "." + innerMapKey, Integer.parseInt(innerMapValue)));
									} else if (outerMapKey.equals("ne")) {
										criteria.createAlias(alliasMapKey, alliasName).add(Restrictions
												.ne(alliasName + "." + innerMapKey, Integer.parseInt(innerMapValue)));
									} else if (outerMapKey.equals("between")) {
										String[] betweenCase = innerMapValue.split(",");
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.between(alliasName + "." + innerMapKey,
														Integer.parseInt(betweenCase[0]),
														Integer.parseInt(betweenCase[1])));
									} else if (outerMapKey.equals("in")) {
										String[] inCase = innerMapValue.split(",");
										Integer[] intArrayInCase = new Integer[inCase.length];
										for (int i = 0; i < inCase.length; i++) {

											String in = inCase[i];
											intArrayInCase[i] = Integer.parseInt(in);
										}

										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.in(alliasName + "." + innerMapKey, intArrayInCase));
									} else if (outerMapKey.equals("isNull")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.isNull(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("isNotNull")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.isNotNull(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("asc")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.addOrder(Order.asc(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("desc")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.addOrder(Order.desc(alliasName + "." + innerMapKey));
									}

								} else if (type.toString().contains("Int")) {

									if (outerMapKey.equals("eq")) {
										criteria.createAlias(alliasMapKey, alliasName).add(Restrictions
												.eq(alliasName + "." + innerMapKey, Integer.parseInt(innerMapValue)));
									} else if (outerMapKey.equals("ne")) {
										criteria.createAlias(alliasMapKey, alliasName).add(Restrictions
												.ne(alliasName + "." + innerMapKey, Integer.parseInt(innerMapValue)));
									} else if (outerMapKey.equals("between")) {
										String[] betweenCase = innerMapValue.split(",");
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.between(alliasName + "." + innerMapKey,
														Integer.parseInt(betweenCase[0]),
														Integer.parseInt(betweenCase[1])));
									} else if (outerMapKey.equals("in")) {
										String[] inCase = innerMapValue.split(",");
										Integer[] intArrayInCase = new Integer[inCase.length];
										for (int i = 0; i < inCase.length; i++) {

											String in = inCase[i];
											intArrayInCase[i] = Integer.parseInt(in);
										}

										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.in(alliasName + "." + innerMapKey, intArrayInCase));
									} else if (outerMapKey.equals("isNull")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.isNull(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("isNotNull")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.isNotNull(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("asc")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.addOrder(Order.asc(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("desc")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.addOrder(Order.desc(alliasName + "." + innerMapKey));
									}

								} else if (type.toString().contains("Double")) {

									if (outerMapKey.equals("eq")) {
										criteria.createAlias(alliasMapKey, alliasName).add(Restrictions
												.eq(alliasName + "." + innerMapKey, Double.parseDouble(innerMapValue)));
									} else if (outerMapKey.equals("ne")) {
										criteria.createAlias(alliasMapKey, alliasName).add(Restrictions
												.ne(alliasName + "." + innerMapKey, Double.parseDouble(innerMapValue)));
									} else if (outerMapKey.equals("between")) {
										String[] betweenCase = innerMapValue.split(",");
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.between(alliasName + "." + innerMapKey,
														Double.parseDouble(betweenCase[0]),
														Double.parseDouble(betweenCase[1])));
									} else if (outerMapKey.equals("isNull")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.isNull(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("isNotNull")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.isNotNull(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("asc")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.addOrder(Order.asc(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("desc")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.addOrder(Order.desc(alliasName + "." + innerMapKey));
									}

								} else {

									if (outerMapKey.equals("eq")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.eq(alliasName + "." + innerMapKey, innerMapValue));
									} else if (outerMapKey.equals("ne")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.ne(alliasName + "." + innerMapKey, innerMapValue));
									} else if (outerMapKey.equals("between")) {
										String[] betweenCase = innerMapValue.split(",");
										criteria.createAlias(alliasMapKey, alliasName).add(Restrictions.between(
												alliasName + "." + innerMapKey, betweenCase[0], betweenCase[1]));
									} else if (outerMapKey.equals("isNull")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.isNull(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("isNotNull")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.isNotNull(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("asc")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.addOrder(Order.asc(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("desc")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.addOrder(Order.desc(alliasName + "." + innerMapKey));
									}

								}
								break;
							}
						}

					}

				}
			}

		}
		criteria.setFirstResult(start);
		criteria.setMaxResults(max);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public E fetchObjectBySubCiteria(Map<String, Map<String, Map<String, String>>> alliasMap) throws Exception {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(getEntityClass());

		for (Map.Entry<String, Map<String, Map<String, String>>> entry1 : alliasMap.entrySet()) {

			String alliasMapKey = entry1.getKey();
			if (alliasMapKey.equals(entityClass.getName())) { // Same as Parent Class
				Map<String, Map<String, String>> innerMap = entry1.getValue();
				for (Map.Entry<String, Map<String, String>> entry2 : innerMap.entrySet()) {

					String outerMapKey = entry2.getKey();
					Map<String, String> innerestMap = entry2.getValue();
					for (Map.Entry<String, String> entry3 : innerestMap.entrySet()) {

						String innerMapKey = entry3.getKey();
						String innerMapValue = entry3.getValue();

						Class<?> cls = Class.forName(entityClass.getName());
						Object type = "";
						Field[] fields1 = cls.getDeclaredFields();
						Field[] fields2 = cls.getSuperclass().getDeclaredFields();
						Field[] both = ArrayUtils.addAll(fields1, fields2);
						for (Field f : both) {

							if (innerMapKey.equals(f.getName())) {
								type = f.getType();
								if (type.toString().contains("Long")) {

									if (outerMapKey.equals("eq")) {
										criteria.add(Restrictions.eq(innerMapKey, Long.parseLong(innerMapValue)));
									} else if (outerMapKey.equals("ne")) {
										criteria.add(Restrictions.ne(innerMapKey, Long.parseLong(innerMapValue)));
									} else if (outerMapKey.equals("between")) {
										String[] betweenCase = innerMapValue.split(",");
										criteria.add(Restrictions.between(innerMapKey, Long.parseLong(betweenCase[0]),
												Long.parseLong(betweenCase[1])));
									} else if (outerMapKey.equals("in")) {
										String[] inCase = innerMapValue.split(",");
										Long[] longArrayInCase = new Long[inCase.length];
										for (int i = 0; i < inCase.length; i++) {

											String in = inCase[i];
											longArrayInCase[i] = Long.parseLong(in);
										}

										criteria.add(Restrictions.in(innerMapKey, longArrayInCase));
									} else if (outerMapKey.equals("isNull")) {
										criteria.add(Restrictions.isNull(innerMapKey));
									} else if (outerMapKey.equals("isNotNull")) {
										criteria.add(Restrictions.isNotNull(innerMapKey));
									} else if (outerMapKey.equals("asc")) {
										criteria.addOrder(Order.asc((innerMapKey)));
									} else if (outerMapKey.equals("desc")) {
										criteria.addOrder(Order.desc((innerMapKey)));
									}

								} else if (type.toString().contains("Integer")) {

									if (outerMapKey.equals("eq")) {
										criteria.add(Restrictions.eq(innerMapKey, Integer.parseInt(innerMapValue)));
									} else if (outerMapKey.equals("ne")) {
										criteria.add(Restrictions.ne(innerMapKey, Integer.parseInt(innerMapValue)));
									} else if (outerMapKey.equals("between")) {
										String[] betweenCase = innerMapValue.split(",");
										criteria.add(Restrictions.between(innerMapKey, Integer.parseInt(betweenCase[0]),
												Integer.parseInt(betweenCase[1])));
									} else if (outerMapKey.equals("in")) {
										String[] inCase = innerMapValue.split(",");
										Integer[] intArrayInCase = new Integer[inCase.length];
										for (int i = 0; i < inCase.length; i++) {

											String in = inCase[i];
											intArrayInCase[i] = Integer.parseInt(in);
										}

										criteria.add(Restrictions.in(innerMapKey, intArrayInCase));
									} else if (outerMapKey.equals("isNull")) {
										criteria.add(Restrictions.isNull(innerMapKey));
									} else if (outerMapKey.equals("isNotNull")) {
										criteria.add(Restrictions.isNotNull(innerMapKey));
									} else if (outerMapKey.equals("asc")) {
										criteria.addOrder(Order.asc((innerMapKey)));
									} else if (outerMapKey.equals("desc")) {
										criteria.addOrder(Order.desc((innerMapKey)));
									}

								} else if (type.toString().contains("Int")) {

									if (outerMapKey.equals("eq")) {
										criteria.add(Restrictions.eq(innerMapKey, Integer.parseInt(innerMapValue)));
									} else if (outerMapKey.equals("ne")) {
										criteria.add(Restrictions.ne(innerMapKey, Integer.parseInt(innerMapValue)));
									} else if (outerMapKey.equals("between")) {
										String[] betweenCase = innerMapValue.split(",");
										criteria.add(Restrictions.between(innerMapKey, Integer.parseInt(betweenCase[0]),
												Integer.parseInt(betweenCase[1])));
									} else if (outerMapKey.equals("in")) {
										String[] inCase = innerMapValue.split(",");
										Integer[] intArrayInCase = new Integer[inCase.length];
										for (int i = 0; i < inCase.length; i++) {

											String in = inCase[i];
											intArrayInCase[i] = Integer.parseInt(in);
										}

										criteria.add(Restrictions.in(innerMapKey, intArrayInCase));
									} else if (outerMapKey.equals("isNull")) {
										criteria.add(Restrictions.isNull(innerMapKey));
									} else if (outerMapKey.equals("isNotNull")) {
										criteria.add(Restrictions.isNotNull(innerMapKey));
									} else if (outerMapKey.equals("asc")) {
										criteria.addOrder(Order.asc((innerMapKey)));
									} else if (outerMapKey.equals("desc")) {
										criteria.addOrder(Order.desc((innerMapKey)));
									}

								} else if (type.toString().contains("Double")) {

									if (outerMapKey.equals("eq")) {
										criteria.add(Restrictions.eq(innerMapKey, Double.parseDouble(innerMapValue)));
									} else if (outerMapKey.equals("ne")) {
										criteria.add(Restrictions.ne(innerMapKey, Double.parseDouble(innerMapValue)));
									} else if (outerMapKey.equals("between")) {
										String[] betweenCase = innerMapValue.split(",");
										criteria.add(
												Restrictions.between(innerMapKey, Double.parseDouble(betweenCase[0]),
														Double.parseDouble(betweenCase[1])));
									} else if (outerMapKey.equals("isNull")) {
										criteria.add(Restrictions.isNull(innerMapKey));
									} else if (outerMapKey.equals("isNotNull")) {
										criteria.add(Restrictions.isNotNull(innerMapKey));
									} else if (outerMapKey.equals("asc")) {
										criteria.addOrder(Order.asc((innerMapKey)));
									} else if (outerMapKey.equals("desc")) {
										criteria.addOrder(Order.desc((innerMapKey)));
									}

								} else {

									if (outerMapKey.equals("eq")) {
										criteria.add(Restrictions.eq(innerMapKey, innerMapValue));
									} else if (outerMapKey.equals("ne")) {
										criteria.add(Restrictions.ne(innerMapKey, innerMapValue));
									} else if (outerMapKey.equals("between")) {
										String[] betweenCase = innerMapValue.split(",");
										criteria.add(Restrictions.between(innerMapKey, betweenCase[0], betweenCase[1]));
									} else if (outerMapKey.equals("isNull")) {
										criteria.add(Restrictions.isNull(innerMapKey));
									} else if (outerMapKey.equals("isNotNull")) {
										criteria.add(Restrictions.isNotNull(innerMapKey));
									} else if (outerMapKey.equals("asc")) {
										criteria.addOrder(Order.asc((innerMapKey)));
									} else if (outerMapKey.equals("desc")) {
										criteria.addOrder(Order.desc((innerMapKey)));
									}

								}
								break;
							}
						}

					}

				}
			} else { // Condition Of Child Class

				Map<String, Map<String, String>> innerMap = entry1.getValue();
				for (Map.Entry<String, Map<String, String>> entry2 : innerMap.entrySet()) {

					String outerMapKey = entry2.getKey();
					Map<String, String> innerestMap = entry2.getValue();
					int count = 0;
					for (Map.Entry<String, String> entry3 : innerestMap.entrySet()) {
						count++;
						String alliasName = alliasMapKey + "_alias" + count;
						String innerMapKey = entry3.getKey();
						String innerMapValue = entry3.getValue();
						String firstLetter = alliasMapKey.substring(0, 1);
						firstLetter = firstLetter.toUpperCase();
						String alliasMapKey1 = entitymanagerPackagesToScan + "." + firstLetter
								+ alliasMapKey.substring(1, alliasMapKey.length());
						Class<?> cls = Class.forName(alliasMapKey1);
						Object type = "";
						Field[] fields1 = cls.getDeclaredFields();
						Field[] fields2 = cls.getSuperclass().getDeclaredFields();
						Field[] both = ArrayUtils.addAll(fields1, fields2);
						for (Field f : both) {

							if (innerMapKey.equals(f.getName())) {
								type = f.getType();
								if (type.toString().contains("Long")) {

									if (outerMapKey.equals("eq")) {
										criteria.createAlias(alliasMapKey, alliasName).add(Restrictions
												.eq(alliasName + "." + innerMapKey, Long.parseLong(innerMapValue)));
									} else if (outerMapKey.equals("ne")) {
										criteria.createAlias(alliasMapKey, alliasName).add(Restrictions
												.ne(alliasName + "." + innerMapKey, Long.parseLong(innerMapValue)));
									} else if (outerMapKey.equals("between")) {
										String[] betweenCase = innerMapValue.split(",");
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.between(alliasName + "." + innerMapKey,
														Long.parseLong(betweenCase[0]),
														Long.parseLong(betweenCase[1])));
									} else if (outerMapKey.equals("in")) {
										String[] inCase = innerMapValue.split(",");
										Long[] longArrayInCase = new Long[inCase.length];
										for (int i = 0; i < inCase.length; i++) {

											String in = inCase[i];
											longArrayInCase[i] = Long.parseLong(in);
										}

										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.in(alliasName + "." + innerMapKey, longArrayInCase));
									} else if (outerMapKey.equals("isNull")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.isNull(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("isNotNull")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.isNotNull(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("asc")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.addOrder(Order.asc(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("desc")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.addOrder(Order.desc(alliasName + "." + innerMapKey));
									}

								} else if (type.toString().contains("Integer")) {

									if (outerMapKey.equals("eq")) {
										criteria.createAlias(alliasMapKey, alliasName).add(Restrictions
												.eq(alliasName + "." + innerMapKey, Integer.parseInt(innerMapValue)));
									} else if (outerMapKey.equals("ne")) {
										criteria.createAlias(alliasMapKey, alliasName).add(Restrictions
												.ne(alliasName + "." + innerMapKey, Integer.parseInt(innerMapValue)));
									} else if (outerMapKey.equals("between")) {
										String[] betweenCase = innerMapValue.split(",");
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.between(alliasName + "." + innerMapKey,
														Integer.parseInt(betweenCase[0]),
														Integer.parseInt(betweenCase[1])));
									} else if (outerMapKey.equals("in")) {
										String[] inCase = innerMapValue.split(",");
										Integer[] intArrayInCase = new Integer[inCase.length];
										for (int i = 0; i < inCase.length; i++) {

											String in = inCase[i];
											intArrayInCase[i] = Integer.parseInt(in);
										}

										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.in(alliasName + "." + innerMapKey, intArrayInCase));
									} else if (outerMapKey.equals("isNull")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.isNull(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("isNotNull")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.isNotNull(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("asc")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.addOrder(Order.asc(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("desc")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.addOrder(Order.desc(alliasName + "." + innerMapKey));
									}

								} else if (type.toString().contains("Int")) {

									if (outerMapKey.equals("eq")) {
										criteria.createAlias(alliasMapKey, alliasName).add(Restrictions
												.eq(alliasName + "." + innerMapKey, Integer.parseInt(innerMapValue)));
									} else if (outerMapKey.equals("ne")) {
										criteria.createAlias(alliasMapKey, alliasName).add(Restrictions
												.ne(alliasName + "." + innerMapKey, Integer.parseInt(innerMapValue)));
									} else if (outerMapKey.equals("between")) {
										String[] betweenCase = innerMapValue.split(",");
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.between(alliasName + "." + innerMapKey,
														Integer.parseInt(betweenCase[0]),
														Integer.parseInt(betweenCase[1])));
									} else if (outerMapKey.equals("in")) {
										String[] inCase = innerMapValue.split(",");
										Integer[] intArrayInCase = new Integer[inCase.length];
										for (int i = 0; i < inCase.length; i++) {

											String in = inCase[i];
											intArrayInCase[i] = Integer.parseInt(in);
										}

										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.in(alliasName + "." + innerMapKey, intArrayInCase));
									} else if (outerMapKey.equals("isNull")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.isNull(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("isNotNull")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.isNotNull(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("asc")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.addOrder(Order.asc(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("desc")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.addOrder(Order.desc(alliasName + "." + innerMapKey));
									}

								} else if (type.toString().contains("Double")) {

									if (outerMapKey.equals("eq")) {
										criteria.createAlias(alliasMapKey, alliasName).add(Restrictions
												.eq(alliasName + "." + innerMapKey, Double.parseDouble(innerMapValue)));
									} else if (outerMapKey.equals("ne")) {
										criteria.createAlias(alliasMapKey, alliasName).add(Restrictions
												.ne(alliasName + "." + innerMapKey, Double.parseDouble(innerMapValue)));
									} else if (outerMapKey.equals("between")) {
										String[] betweenCase = innerMapValue.split(",");
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.between(alliasName + "." + innerMapKey,
														Double.parseDouble(betweenCase[0]),
														Double.parseDouble(betweenCase[1])));
									} else if (outerMapKey.equals("isNull")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.isNull(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("isNotNull")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.isNotNull(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("asc")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.addOrder(Order.asc(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("desc")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.addOrder(Order.desc(alliasName + "." + innerMapKey));
									}

								} else {

									if (outerMapKey.equals("eq")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.eq(alliasName + "." + innerMapKey, innerMapValue));
									} else if (outerMapKey.equals("ne")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.ne(alliasName + "." + innerMapKey, innerMapValue));
									} else if (outerMapKey.equals("between")) {
										String[] betweenCase = innerMapValue.split(",");
										criteria.createAlias(alliasMapKey, alliasName).add(Restrictions.between(
												alliasName + "." + innerMapKey, betweenCase[0], betweenCase[1]));
									} else if (outerMapKey.equals("isNull")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.isNull(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("isNotNull")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.add(Restrictions.isNotNull(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("asc")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.addOrder(Order.asc(alliasName + "." + innerMapKey));
									} else if (outerMapKey.equals("desc")) {
										criteria.createAlias(alliasMapKey, alliasName)
												.addOrder(Order.desc(alliasName + "." + innerMapKey));
									}

								}
								break;
							}
						}

					}

				}
			}

		}

		return (E) criteria.uniqueResult();
	}

}