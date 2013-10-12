package freepander.service;

import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import freepander.model.PageModel;

@Component("baseService")
public class BaseService {
	@Resource
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * 根据类 和id 查找对象
	 */
	public Object get(@SuppressWarnings("rawtypes") Class entityClass,
			Serializable id) {
		return this.hibernateTemplate.get(entityClass, id);
	}

	public Object load(@SuppressWarnings("rawtypes") Class entityClass,
			Serializable id) {
		return this.hibernateTemplate.load(entityClass, id);
	}

	/**
	 * 删除一个对象
	 * 
	 * @param object
	 *            要删除的对象
	 */
	public void delete(Object object) {
		this.hibernateTemplate.delete(object);
	}

	/**
	 * 跟新一个对象
	 * 
	 * @param object
	 *            要更新的对象
	 */
	public void update(Object object) {
		this.hibernateTemplate.update(object);
	}

	/**
	 * 保存一个对象
	 */
	public void save(Object object) {
		this.hibernateTemplate.save(object);
	}

	/**
	 * 主键存在 更新对象 主键不存在 保存对象
	 */
	public void saveOrUpate(Object object) {
		this.hibernateTemplate.saveOrUpdate(object);
	}

	/**
	 * 通过hql语句查找对象
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getObjectListByQuerySql(String QuerySql) {
		return this.hibernateTemplate.find(QuerySql);
	}

	/**
	 * 拿到一组满足条件的对象
	 * 
	 * @param entityName
	 *            实体名称
	 * @param pageNum
	 *            页号
	 * @param countOfOnePage
	 *            第几页
	 * @param orderby
	 *            排序
	 * @param conditionQuestion
	 *            限制条件
	 * @param conditionAnswer
	 *            限制条件
	 * @return 一组满足条件的对象
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getObjectList(final String entityName,
			final int pageNum, final int countOfOnePage,
			final List<String> orderby, final List<String> conditionQuestion,
			final List<Object> conditionAnswer) throws Exception {
		@SuppressWarnings("rawtypes")
		List list = null;
		try {
			list = hibernateTemplate.executeFind(new HibernateCallback() {
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String queryStr = " from " + entityName + " t where 1=1  ";
					if (conditionQuestion != null) {
						for (String con : conditionQuestion) {
							queryStr += " and t." + con;
						}
					}
					if (orderby != null) {
						if (orderby != null && orderby.size() > 0) {
							queryStr += " order by t." + orderby.get(0);
						}
						for (int i = 1; i < orderby.size(); i++) {
							queryStr += (" , t." + orderby.get(i));
						}
					}
					Query query = session.createQuery(queryStr);
					if (conditionAnswer != null) {
						for (int i = 0; i < conditionAnswer.size(); i++) {
							query.setParameter(i, conditionAnswer.get(i));
						}
					}
					query.setFirstResult((pageNum - 1) * countOfOnePage);
					query.setMaxResults(countOfOnePage);

					@SuppressWarnings("rawtypes")
					List list = query.list();
					return list;
				}
			});
		} catch (Exception e) {
			throw e;
		}
		return list;
	}

	/**
	 * 和上一方法类似 只不过上一个方法的限定条件之间是and关系 此方法限定条件之间为or关系
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Object> getObjectListOr(final String entityName,
			final int pageNum, final int countOfOnePage,
			final List<String> orderby, final List<String> conditionQuestion,
			final List<Object> conditionAnswer) throws Exception {
		List list = null;
		try {
			list = hibernateTemplate.executeFind(new HibernateCallback() {
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String queryStr = " from " + entityName + " t where 1=0  ";
					if (conditionQuestion != null) {
						for (String con : conditionQuestion) {
							queryStr += " or t." + con;
						}
					}
					if (orderby != null) {
						if (orderby != null && orderby.size() > 0) {
							queryStr += " order by t." + orderby.get(0);
						}
						for (int i = 1; i < orderby.size(); i++) {
							queryStr += (" , t." + orderby.get(i));
						}
					}
					Query query = session.createQuery(queryStr);
					if (conditionAnswer != null) {
						for (int i = 0; i < conditionAnswer.size(); i++) {
							query.setParameter(i, conditionAnswer.get(i));
						}
					}
					query.setFirstResult((pageNum - 1) * countOfOnePage);
					query.setMaxResults(countOfOnePage);

					List list = query.list();
					return list;
				}
			});
		} catch (Exception e) {
			throw e;
		}
		return list;
	}

	/**
	 * 更新对象
	 */
	public int bulkUpdate(String queryString) {
		return hibernateTemplate.bulkUpdate(queryString);
	}

	public int bulkUpdate(String queryString, Object value) {
		return this.hibernateTemplate.bulkUpdate(queryString, value);
	}

	public int bulkUpdate(String queryString, Object[] values) {
		return this.hibernateTemplate.bulkUpdate(queryString, values);
	}

	/**
	 * 查询记录数
	 */
	public Long getObjectListCount(String entityName,
			List<String> conditionQuestion, List<Object> conditionAnswer) {
		String queryStr = "select count(t)  from " + entityName
				+ " t where 1=1  ";
		if (conditionQuestion != null) {
			for (String con : conditionQuestion) {
				queryStr += " and t." + con;
			}
		}
		if (conditionAnswer != null) {
			return (Long) this.hibernateTemplate.find(queryStr,
					conditionAnswer.toArray()).get(0);
		} else {
			return (Long) this.hibernateTemplate.find(queryStr).get(0);
		}
	}

	public Long getObjectListCountOr(String entityName,
			List<String> conditionQuestion, List<Object> conditionAnswer) {
		// TODO Auto-generated method stub
		String queryStr = "select count(t)  from " + entityName
				+ " t where 1=0  ";
		if (conditionQuestion != null) {
			for (String con : conditionQuestion) {
				queryStr += " or t." + con;
			}
		}
		if (conditionAnswer != null) {
			return (Long) this.hibernateTemplate.find(queryStr,
					conditionAnswer.toArray()).get(0);
		} else {
			return (Long) this.hibernateTemplate.find(queryStr).get(0);
		}
	}

	/**
	 * orderby.add("year desc") orderby.add("year")
	 * 
	 * 查询2009级以后的班级 q.add("year>=?") a.add(2009)
	 */
	public PageModel getPageModel(String entityName, int pageNum,
			int countOfOnePage, List<String> orderby,
			List<String> conditionQuestion, List<Object> conditionAnswer)
			throws Exception {
		PageModel pm = new PageModel()
				.setCountOfOnePage(countOfOnePage)
				.setPageNum(pageNum)
				.setCount(
						this.getObjectListCount(entityName, conditionQuestion,
								conditionAnswer))
				.setObjects(
						this.getObjectList(entityName, pageNum, countOfOnePage,
								orderby, conditionQuestion, conditionAnswer));
		return pm;
	}

	public PageModel getPageModelOr(String entityName, int pageNum,
			int countOfOnePage, List<String> orderby,
			List<String> conditionQuestion, List<Object> conditionAnswer)
			throws Exception {
		PageModel pm = new PageModel()
				.setCountOfOnePage(countOfOnePage)
				.setPageNum(pageNum)
				.setCount(
						this.getObjectListCountOr(entityName,
								conditionQuestion, conditionAnswer))
				.setObjects(
						this.getObjectListOr(entityName, pageNum,
								countOfOnePage, orderby, conditionQuestion,
								conditionAnswer));
		return pm;
	}

	public Date getdate() {
		return null;
	}

	@SuppressWarnings("rawtypes")
	public List getByHal(String hql) {
		return this.hibernateTemplate.find(hql);
	}

	public PageModel getPageModel(String entityName, int pageNum,
			int countOfOnePage) throws Exception {
		return this.getPageModel(entityName, pageNum, countOfOnePage, null,
				null, null);
	}

	public PageModel getPageModel(String entityName, int pageNum,
			int countOfOnePage, List<String> conditionQuestion,
			List<Object> conditionAnswer) throws Exception {
		return this.getPageModel(entityName, pageNum, countOfOnePage, null,
				conditionQuestion, conditionAnswer);
	}

	public PageModel getPageModel(String entityName, int pageNum,
			int countOfOnePage, List<String> orderby) throws Exception {
		return this.getPageModel(entityName, pageNum, countOfOnePage, orderby,
				null, null);
	}

}
