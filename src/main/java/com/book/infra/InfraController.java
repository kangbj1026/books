package com.book.infra;

import com.book.common.dto.request.ShopSearchParameter;
import com.book.common.dto.response.PaginationResponse;
import com.book.domain.books.dto.BooksDto;
import com.book.domain.books.entity.BooksEntity;
import com.book.domain.books.service.BooksService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/book")
public class InfraController
{
	private final BooksService booksService;
	String url = "adm/book/";
	String index = "index";

	@GetMapping()
	public String bookListPage(ShopSearchParameter searchParameter, Model model) throws NoSuchFieldException, IllegalAccessException
	{
		log.info("========= bookListPage =========");
		// 페이지 번호가 0 이하인 경우 1로 설정
		if (searchParameter.getPage() <= 0)
		{
			searchParameter.setPage(1);
		}

		PaginationResponse<BooksDto> books = booksService.getList(searchParameter);

		// 페이징 정보 계산
		int currentPage = books.getCurrentPage();
		int totalPages = books.getTotalPages();

		// 페이징 네비게이션을 위한 정보 계산
		int startPage = Math.max(1, currentPage - 2);
		int endPage = Math.min(totalPages, currentPage + 2);

		// 이전/다음 페이지 존재 여부
		boolean hasPrevious = currentPage > 1;
		boolean hasNext = currentPage < totalPages;

		model.addAttribute("books", books);
		model.addAttribute("searchParameter", searchParameter);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("hasPrevious", hasPrevious);
		model.addAttribute("hasNext", hasNext);

		return url + "list";
	}

	@GetMapping("/create")
	public String create()
	{
		return url + "create";
	}

	@GetMapping("/{booksUID}")
	public String info(Model model , @PathVariable String booksUID)
	{
		BooksEntity book = booksService.findById(booksUID);
		model.addAttribute("book", book);
		return url + "info";
	}
}
