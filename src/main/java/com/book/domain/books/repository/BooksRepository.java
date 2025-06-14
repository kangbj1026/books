package com.book.domain.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.book.domain.books.entity.BooksEntity;
import com.book.domain.books.repository.querydsl.BooksRepositoryCustom;

public interface BooksRepository extends JpaRepository<BooksEntity, String>, BooksRepositoryCustom, QuerydslPredicateExecutor<BooksEntity> {
}
