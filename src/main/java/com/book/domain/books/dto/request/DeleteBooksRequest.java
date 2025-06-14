package com.book.domain.books.dto.request;

import com.book.domain.books.dto.BooksDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DeleteBooksRequest { 

	private String title;

	public BooksDto toDto() {
		return BooksDto.builder()
			.title(title)
		.build();
	}
}