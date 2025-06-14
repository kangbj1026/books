package com.book.domain.books.repository.querydsl;

import static com.book.domain.books.entity.QBooksEntity.booksEntity;

import java.lang.reflect.Field;;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;

import com.book.domain.books.entity.QBooksEntity;
import com.book.domain.books.dto.BooksDto;
import com.book.common.dto.request.ShopSearchParameter;
import com.book.common.dto.response.PaginationResponse;
import com.book.common.util.SearchParameterUtil;
import com.book.domain.books.entity.BooksEntity;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BooksRepositoryImpl implements BooksRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public PaginationResponse<BooksDto> getListPage(ShopSearchParameter searchParameter) throws NoSuchFieldException, IllegalAccessException {
		BooleanBuilder whereBuilder = whereClause(searchParameter);
		int skip = (searchParameter.getPage() - 1) * searchParameter.getLimit();

		JPAQuery<BooksEntity> query = queryFactory.selectFrom(booksEntity).where(whereBuilder);
		long count = query.stream().count();

		// 정렬 조건 설정 - orderBy 필드에 따라 동적으로 정렬
		OrderSpecifier<?> orderBy = getOrderSpecifier(searchParameter);

		List<BooksEntity> results = query
				.limit(searchParameter.getLimit())
				.offset(skip)
				.orderBy(orderBy)
				.fetch();

		List<BooksDto> list = results.stream().map(BooksDto::from).toList();

		return PaginationResponse.<BooksDto>builder()
				.totalCount((int) count)
				.currentPage(searchParameter.getPage())
				.totalPages(count > 0 ? (int) Math.ceil((double) count / searchParameter.getLimit()) : 1)
				.items(list)
				.build();
	}

	private BooleanBuilder whereClause(ShopSearchParameter searchParameter) throws NoSuchFieldException, IllegalAccessException {
		BooleanBuilder whereBuilder = new BooleanBuilder();

		// 검색 조건 처리
		if (hasText(searchParameter.getSearchType()) && hasText(searchParameter.getSearchValue())) {
			Field field = QBooksEntity.class.getDeclaredField(searchParameter.getSearchType());
			SearchParameterUtil.sp(searchParameter, whereBuilder, field, field.get(booksEntity));
		}

		// 날짜 범위 검색
		LocalDate startAt = searchParameter.getStartAt();
		LocalDate endAt = searchParameter.getEndAt();
		if (startAt != null && endAt != null) {
			whereBuilder.and(booksEntity.createdAt.between(
					startAt.atStartOfDay(),
					endAt.plusDays(1).atStartOfDay()
			));
		}

		return whereBuilder;
	}

	private OrderSpecifier<?> getOrderSpecifier(ShopSearchParameter searchParameter) {
		String orderBy = searchParameter.getOrderBy();
		Sort.Direction sort = searchParameter.getSort();

		// 정렬 필드에 따른 OrderSpecifier 반환
		return switch (orderBy) {
			case "title" -> sort.equals(Sort.Direction.DESC) ? booksEntity.title.desc() : booksEntity.title.asc();
			case "author" -> sort.equals(Sort.Direction.DESC) ? booksEntity.author.desc() : booksEntity.author.asc();
			case "price" -> sort.equals(Sort.Direction.DESC) ? booksEntity.price.desc() : booksEntity.price.asc();
			case "stockQuantity" ->
					sort.equals(Sort.Direction.DESC) ? booksEntity.stockQuantity.desc() : booksEntity.stockQuantity.asc();
			case "updatedAt" ->
					sort.equals(Sort.Direction.DESC) ? booksEntity.updatedAt.desc() : booksEntity.updatedAt.asc();
			default -> sort.equals(Sort.Direction.DESC) ? booksEntity.createdAt.desc() : booksEntity.createdAt.asc();
		};
	}

	// 검색 조건을 위한 BooleanExpression (기존 코드 유지)
	private BooleanExpression searchEq(ShopSearchParameter searchParameter) {
		return (hasText(searchParameter.getSearchType()) &&
				hasText(searchParameter.getSearchValue())) ?

				Expressions.booleanTemplate("{0} LIKE concat('%', {1}, '%')",
						Expressions.path(String.class, booksEntity, searchParameter.getSearchType()),
						searchParameter.getSearchValue()) : null;
	}

	// hasText 메서드 (StringUtils.hasText와 동일한 기능)
	private boolean hasText(String str) {
		return str != null && !str.trim().isEmpty();
	}
}