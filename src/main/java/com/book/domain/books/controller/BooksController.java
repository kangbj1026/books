package com.book.domain.books.controller;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Parameter;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;

import com.book.common.dto.response.CommonResponse;
import com.book.common.dto.response.PaginationResponse;
import com.book.common.dto.request.ShopSearchParameter;
import com.book.domain.books.dto.BooksDto;
import com.book.domain.books.service.BooksService;
import com.book.domain.books.dto.response.CreateBooksResponse;
import com.book.domain.books.dto.request.CreateBooksRequest;
import com.book.domain.books.dto.response.UpdateBooksResponse;
import com.book.domain.books.dto.request.UpdateBooksRequest;
import com.book.domain.books.dto.request.DeleteBooksRequest;

import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BooksController // BooksController 클래스를 선언합니다. 이 클래스는 도서 정보 CRUD API의 controller 계층 입니다.
{

	private final BooksService booksService; // 비즈니스 로직 수행과 데이터 접근 역할의 service 인스턴스를 주입 받을 변수 입니다.

	// 생성자 주입으로 booksService 인스턴스를 할당 합니다.
	// @RequiredArgsConstructor 어노테이션 때문에 별도로 생성자가 작성되어 있습니다.

	// ---------------------- GET START --------------------------------

	// GET 요청시 해당 조건에 맞는 도서 리스트를 조회 합니다.
	@GetMapping("")
	public CommonResponse<PaginationResponse<BooksDto>> getBooksList(ShopSearchParameter searchParameter) throws NoSuchFieldException, IllegalAccessException // searchParameter 조건에 맞추어 서비스에서 리스트를 가져온 후 응답합니다.
	{
		return CommonResponse.OK(booksService.getList(searchParameter)); // booksService.getList의 결과를 OK 상태로 감싸서 응답합니다.
	}

	// GET 요청시 해당 UID의 도서 하나를 조회 합니다.
	@GetMapping("/{booksUID}")
	public CommonResponse<BooksDto> getBooksById(@PathVariable String booksUID) // URL path에서 booksUID 값을 받죠.
	{
		return CommonResponse.OK(booksService.getBooksById(booksUID)); // 해당 UID의 도서 정보를 service에서 가져온 후 OK 응답으로 반환합니다.
	}

	// ---------------------- GET END --------------------------------

	// ---------------------- POST START --------------------------------

	// POST 요청시 신규 도서를 생성 합니다.
	@PostMapping("")
	public CommonResponse<CreateBooksResponse> CreateBooks(@RequestBody @Valid CreateBooksRequest createData) // @Valid 유효성 검증 수행
	{
		Long newUID = booksService.createBooks(createData.toDto()); // service에서 신규 도서를 생성하고 UID를 반환합니다.
		// 생성 후 해당 UID를 포함한 응답 DTO를 만들어서 CREATE 응답으로 반환합니다.
		return CommonResponse.CREATE(CreateBooksResponse.builder().booksUID(newUID).build());
	}

	// ---------------------- POST END --------------------------------

	// ---------------------- PUT START --------------------------------

	// PUT 요청시 해당 UID의 도서를 변경합니다.
	@PutMapping("{uid}")
	public CommonResponse<UpdateBooksResponse> UpdateBooks(@PathVariable(value = "uid") String uid,@RequestBody @Valid UpdateBooksRequest updateData) // @Valid는 변경 정보의 유효성을 확인합니다.
	{
		// service에서 변경 후 변경된 UID를 반환합니다.
		Long updatedUID = booksService.updateBooks(uid, updateData.toDto());
		// 변경된 UID를 포함한 응답 DTO를 만들어서 반환합니다.
		return CommonResponse.UPDATE(UpdateBooksResponse.builder().booksUID(updatedUID).build());
	}

	// ---------------------- PUT END --------------------------------

	// ---------------------- DELETE START --------------------------------

	// DELETE 요청시 해당 UID의 도서를 삭제합니다.
	@DeleteMapping("{uid}")
	public CommonResponse<String> DeleteBooks(@PathVariable(value = "uid") String uid,@RequestBody @Valid DeleteBooksRequest deleteData) // @Valid는 삭제시에도 유효성을 확인합니다.
	{
		// service에서 삭제 후 해당 UID를 반환합니다.
		String resultUID = booksService.deleteBooks(uid, deleteData.toDto());
		// 만약 삭제된 UID가 요청시 넘겼던 UID랑 일치한다면 OK, 아니면 BAD 응답합니다.
		if (Objects.equals(resultUID, uid)) return CommonResponse.DELETE(resultUID);
		else return CommonResponse.BAD(resultUID);
	}

	// ---------------------- DELETE END --------------------------------
}
