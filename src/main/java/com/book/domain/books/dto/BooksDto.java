package com.book.domain.books.dto;
import com.book.domain.books.entity.BooksEntity;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BooksDto {

	private Long booksUID;
	private String title;
	private String author;
	private String description;
	private Long price;
	private Long stockQuantity;
	private String isbn;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public static BooksDto from(BooksEntity books) {
		return BooksDto.builder()
			.booksUID(books.getBooksUID())
			.title(books.getTitle())
			.author(books.getAuthor())
			.description(books.getDescription())
			.price(books.getPrice())
			.stockQuantity(books.getStockQuantity())
			.isbn(books.getIsbn())
			.createdAt(books.getCreatedAt())
			.updatedAt(books.getUpdatedAt())
				.build();
	}

	public BooksEntity toEntity() {
		return getBooks();
	}

	private BooksEntity getBooks() {
		return BooksEntity.builder()
			.booksUID(booksUID)
			.title(title)
			.author(author)
			.description(description)
			.price(price)
			.stockQuantity(stockQuantity)
			.isbn(isbn)
				.build();
	}
}
