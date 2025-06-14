package com.book.common.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * 페이징 처리 시 공통 응답
 */
@Builder
@AllArgsConstructor
@Getter
public class PaginationResponse<T> {
    private int totalCount; // 전체 항목 수
    private int currentPage; // 현재 페이지 번호
    private int totalPages; // 전체 페이지 수
    private List<T> items;
}
