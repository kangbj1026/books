package com.book.common.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class ShopSearchParameter extends SearchParameter
{
	private LocalDate startAt;
	private LocalDate endAt;
	private Long shopNo = 1L;
}