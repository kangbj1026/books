package com.book.domain.books.repository.querydsl;

import java.util.List;

import com.book.domain.books.dto.BooksDto;
import com.book.common.dto.request.ShopSearchParameter;
import com.book.common.dto.response.PaginationResponse;

public interface BooksRepositoryCustom // Spring Data JPA의 커스텀 Repository 인터페이스 입니다.
{
	// 이 인터페이스는 Repository의 비즈니스별 쿼리를 구현할 수 있도록 별도로 만들어집니다.

	// 조건과 페이지 정보가 포함되어 있는 ShopSearchParameter를 전달받아 해당 조건에 맞는 도서 리스트를 페이지 정보 포함해서 반환 합니다.
	// 이는 JPQL이나 QueryDSL 같은 커스텀 쿼리를 사용할 때 유용하게 활용 됩니다.
	PaginationResponse<BooksDto> getListPage(ShopSearchParameter searchParameter) throws NoSuchFieldException, IllegalAccessException;

}