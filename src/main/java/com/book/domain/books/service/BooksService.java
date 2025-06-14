package com.book.domain.books.service;

import jakarta.persistence.PreUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import com.book.common.dto.response.PaginationResponse;
import com.book.common.dto.request.ShopSearchParameter;
import com.book.common.exception.BusinessException;
import com.book.common.constant.ErrorCode;
import com.book.domain.books.dto.BooksDto;
import com.book.domain.books.entity.BooksEntity;
import com.book.domain.books.repository.BooksRepository;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
@Slf4j
public class BooksService // 비즈니스 계층의 서비스 클래스를 나타내며, 도서 정보의 CRUD 기능과 비즈니스 규칙 등을 수행 합니다.
{

	// Repository 계층과 연결되어 있으며, 이는 Spring Data JPA의 Repository입니다.
	private final BooksRepository booksRepository; // 도서 정보에 접근할 수 있도록 주입됩니다.

	// -----------------------------------------------------------------------------

	// 조건과 페이지 정보에 맞추어 도서 리스트를 조회 합니다.
	// searchParameter에는 페이지나 조건 정보가 포함되어 있습니다.
	public PaginationResponse<BooksDto> getList(ShopSearchParameter searchParameter) throws NoSuchFieldException, IllegalAccessException
	{ // 해당 조건과 페이지 정보에 맞추어 Repository의 getListPage 메서드를 호출 합니다.
		return booksRepository.getListPage(searchParameter);
	}

	// -----------------------------------------------------------------------------

	// UID를 통해 해당 도서의 엔터티를 조회 합니다.
	// 만약 해당 UID의 도서가 없으면 Exception 발생 합니다.
	@Transactional(readOnly = true) // 변경 하지 않고, 조회만 수행할 것임을 의미 합니다.
	public BooksEntity findById(String uid) // Repository의 findById를 통해 해당 도서를 가져온 후, 없으면 Exception 발생 합니다.
	{
		return booksRepository.findById(uid).orElseThrow(
				() -> new BusinessException(ErrorCode.TABLE_UID_EXISTS) // 해당 Exception과 ErrorCode는 비즈니스 규칙 입니다.
		);
	}

	// -----------------------------------------------------------------------------

	// 도서 UID로 해당 도서의 DTO를 조회합니다.
	@Transactional(readOnly = true) // 변경 하지 않고, 조회만 수행할 것임을 의미 합니다.
	public BooksDto getBooksById(String uid) // findById로 가져온 도서 엔터티를 DTO로 변환 합니다.
	{
		return BooksDto.from(findById(uid)); // from 메서드가 해당 엔터티를 DTO로 변경 합니다.
	}

	// -----------------------------------------------------------------------------

	// 신규 도서를 생성 합니다.
	// 전달받은 DTO를 엔터티로 변경해서 저장 합니다.
	// 저장 후 생성된 도서의 PK 값을 반환 합니다.
	public Long createBooks(BooksDto createDto) // DTO를 엔터티로 변경해서 저장 합니다.
	{
		// 저장 후 해당 저장된 엔터티를 DTO로 변경해서 PK 값을 가져온 후 반환 합니다.
		return BooksDto.from(booksRepository.save(createDto.toEntity())).getBooksUID();
	}

	// -----------------------------------------------------------------------------

	// 도서 정보를 변경 합니다.
	// 해당 UID의 도서를 먼저 조회해서 변경 후 PK 값을 반환 합니다.
	@PreUpdate // 이는 JPA의 Callback이나 Auditing시 사용할 수 있습니다.
	public Long updateBooks(String uid, BooksDto updateDto)
	{
		// 먼저 해당 도서를 조회 합니다.
		BooksEntity books = findById(uid);
		// 해당 도서의 정보를 변경 합니다.
		// updateResource는 변경 후 PK 값을 반환 합니다.
		return books.updateResource(updateDto);
	}

	// -----------------------------------------------------------------------------

	// 도서 정보를 삭제 합니다.
	// 해당 도서의 제목과 전달된 제목이 일치할 경우에만 삭제 합니다.
	// 만약 제목이 일치하지 않으면 ErrorCode 메시지를 UID에 넣어 반환 합니다.
	public String deleteBooks(String uid,BooksDto deleteDto)
	{
		// 먼저 해당 도서를 조회합니다.
		BooksEntity books = findById(uid);
		// 제목 확인 후 삭제하거나 오류 메시지 할당합니다.
		if (books.getTitle().equals(deleteDto.getTitle())) booksRepository.delete(books);
		else uid = ErrorCode.TABLE_BODY_EXISTS.getMessage();

		// 변경된 UID나 오류 메시지를 반환합니다.
		return uid;
	}
}