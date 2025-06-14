package com.book.common.dto.request;

import jakarta.annotation.Nullable;
import lombok.*;
import org.springframework.data.domain.Sort;

/**
 * 공통 검색 조건
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SearchParameter {
    int page = 1;

    int limit = 10;

    @Nullable
    String searchType;

    @Nullable
    String searchValue;

    String orderBy = "createdAt";

    Sort.Direction sort = Sort.Direction.DESC;
}
