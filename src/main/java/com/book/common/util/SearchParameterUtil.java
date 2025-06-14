package com.book.common.util;

import com.book.common.dto.request.SearchParameter;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

import static org.springframework.util.StringUtils.hasText;

@Slf4j
public class SearchParameterUtil {

	public static void sp(SearchParameter searchParameter, BooleanBuilder whereBuilder, Field field, Object o) {
		field.setAccessible(true);
		if (field.getType().isAssignableFrom(NumberPath.class) && hasText(searchParameter.getSearchValue()))
		{
			NumberPath<Long> numberPath = (NumberPath<Long>) o;
			whereBuilder.and(numberPath.eq(Long.valueOf(searchParameter.getSearchValue())));
		}
		else if (field.getType().isAssignableFrom(BooleanPath.class) && hasText(searchParameter.getSearchValue()))
		{
			BooleanPath booleanPath = (BooleanPath) o;
			whereBuilder.and(booleanPath.eq(Boolean.valueOf(searchParameter.getSearchValue())));
		}
		else
		{
			whereBuilder.and(((StringPath) o).like("%" + searchParameter.getSearchValue() + "%"));
		}
	}
}
