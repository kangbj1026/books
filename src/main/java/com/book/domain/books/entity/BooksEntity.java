package com.book.domain.books.entity;

import com.book.domain.books.dto.BooksDto;
import com.book.common.entity.BaseEntity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "books")
public class BooksEntity extends BaseEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long booksUID; // 고유 번호
    @Column(columnDefinition = "VARCHAR(256)")
    private String title; // 제목
    @Column(columnDefinition = "VARCHAR(256)")
    private String author; // 저자
    @Column(columnDefinition = "LONGTEXT")
    private String description; // 내용
    @Column(columnDefinition = "BIGINT")
    private Long price; // 가격
    @Column(columnDefinition = "BIGINT")
    private Long stockQuantity; // 재고
    @Column(columnDefinition = "VARCHAR(1024)")
    private String isbn; // 바코드 번호

    public Long updateResource(BooksDto booksDto) {
		this.title = booksDto.getTitle();
		this.author = booksDto.getAuthor();
		this.description = booksDto.getDescription();
		this.price = booksDto.getPrice();
		this.stockQuantity = booksDto.getStockQuantity();
		this.isbn = booksDto.getIsbn();
		return booksUID;
    }
}
