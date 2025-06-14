package com.book.domain.books.dto.request;

import com.book.domain.books.dto.BooksDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateBooksRequest { 

	private String title;
	private String author;
	private String description;
	private Long price;
	private Long stockQuantity;
	private String isbn;

	public BooksDto toDto() {
		return BooksDto.builder()
			.title(title)
			.author(author)
			.description(description)
			.price(price)
			.stockQuantity(stockQuantity)
			.isbn(isbn)
		.build();
	}
}