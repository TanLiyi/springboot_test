package com.liyi.utils;

import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.google.common.collect.Lists;

@SuppressWarnings("unchecked")
public class CustomSpecications {

	/**
	 * 参考自springside,根据SearchFilter的集合返回值比较的Spec,
	 * 如果SearchFilter的value为null或空串将忽略,比较类型见SearchFilter.Operator
	 * 
	 * @param filters
	 *            SearchFilter的集合
	 * @param entityClazz
	 *            entity的class
	 * @return Specification<T>
	 */
	public static <T> Specification<T> searchFilterSpec(final Collection<SearchFilter> filters,
			final Class<T> entityClazz) {
		return new Specification<T>() {

			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				if (!filters.isEmpty()) {
					List<Predicate> predicates = Lists.newArrayList();
					for (SearchFilter filter : filters) {
						// 值为空或空串忽略
						if (filter.value == null || StringUtils.isBlank(String.valueOf(filter.value)))
							continue;
						// if(StringUtils.isBlank( String.valueOf(filter.value)
						// )) continue;

						// nested path translate, 如Task的名为"user.name"的filedName,
						// 转换为Task.user.name属性
						String[] names = StringUtils.split(filter.fieldName, ".");
						Path expression = root.get(names[0]);
						for (int i = 1; i < names.length; i++) {
							expression = expression.get(names[i]);
						}

						// logic operator
						switch (filter.operator) {
						case EQ:
							predicates.add(builder.equal(expression, filter.value));
							break;
						case LIKE:
							predicates.add(builder.like(expression,
									"%" + EscapeUtils.escapeSqlLike(filter.value.toString()) + "%"));
							break;
						case GT:
							predicates.add(builder.greaterThan(expression, (Comparable) filter.value));
							break;
						case LT:
							predicates.add(builder.lessThan(expression, (Comparable) filter.value));
							break;
						case GTE:
							predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.value));
							break;
						case LTE:
							predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.value));
							break;
						}
					}

					// 将所有条件用 and 联合起来
					if (!predicates.isEmpty()) {
						return builder.and(predicates.toArray(new Predicate[predicates.size()]));
					}
				}

				return builder.conjunction();
			}

		};
	}

	public static <T> Specification<T> addLeftFetchSpec(final Class<T> entityClazz, final String... fetchColumns) {
		return (root, query, cb) -> {
			Predicate conj = cb.conjunction();

			if (!query.getResultType().equals(Long.class)) {
				// count查询不能使用fetch语句
				for (String fetchColumn : fetchColumns) {
					root.fetch(fetchColumn, JoinType.LEFT);
				}
			}
			return conj;
		};
	}
}
