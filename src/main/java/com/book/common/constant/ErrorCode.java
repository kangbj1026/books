package com.book.common.constant;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode
{
	// 인증
	LOGIN_REQUIRED(HttpStatus.UNAUTHORIZED, "A-001", "로그인이 필요합니다."),
	TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "A-002", "토큰이 만료되었습니다."),
	INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "A-003", "유효하지 않은 토큰입니다."),
	PASSWORD_MISMATCH(HttpStatus.UNAUTHORIZED, "A-004", "비밀번호가 일치하지 않습니다."),

	// 어드민
	ADMIN_NOT_FOUND(HttpStatus.NOT_FOUND, "AD-001", "존재하지 않는 관리자 입니다."),
	ADMIN_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "AD-002", "이미 존재하는 관리자 아이디 입니다."),

	// 권한 관련 에러
	NO_PERMISSION(HttpStatus.UNAUTHORIZED, "A-005", "권한이 부족합니다."),   // 권한이 없을 때
	ROLE_NOT_ALLOWED(HttpStatus.UNAUTHORIZED, "A-006", "해당 역할로 접근할 수 없습니다."), // 특정 역할이 허용되지 않은 경우
	ACCESS_DENIED(HttpStatus.UNAUTHORIZED, "A-007", "접근이 거부되었습니다."), // 접근 거부
	ADMIN_ROLE_REQUIRED(HttpStatus.UNAUTHORIZED, "A-008", "관리자 권한이 필요합니다."), // 관리자 권한이 필요한 경우
	SUPER_ROLE_REQUIRED(HttpStatus.UNAUTHORIZED, "A-009", "슈퍼 관리자 권한이 필요합니다."), // 슈퍼 관리자 권한이 필요한 경우

	// convert
	UNABLE_TO_CONVERT_LIST_TO_STRING(HttpStatus.BAD_REQUEST, "CO-001", "변환할 수 없는 값입니다."),

	// TABLE CREATE
	TABLE_UID_EXISTS(HttpStatus.BAD_REQUEST, "TABLE-001", "UID in table does not exist."),
	TABLE_BODY_EXISTS(HttpStatus.BAD_REQUEST, "TABLE-001", "Body values do not match."),

//	// 유저
//	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U-001", "존재하지 않는 회원 입니다."),
//	ALREADY_REGISTERED_USER(HttpStatus.CONFLICT, "U-001", "이미 가입된 회원 입니다."),
//
//	// 포인트
//	INSUFFICIENT_POINTS(HttpStatus.BAD_REQUEST, "P-001", "전환할 포인트가 충분하지 않습니다."),
//	LOW_AVAILABLE_POINTS(HttpStatus.BAD_REQUEST, "P-002", "사용가능 포인트가 전환할 포인트보다 낮습니다."),
//	DECREASE_ERROR_POINTS(HttpStatus.BAD_REQUEST, "P-002", "차감 가능한 포인트가 초과하였습니다."),
//
//	// 파일 업로드
//	UNSUPPORTED_FORMAT(HttpStatus.BAD_REQUEST, "F-001", "지원하지 않는 파일 형식입니다."),
//	FILE_SIZE_LIMIT_EXCEEDED(HttpStatus.BAD_REQUEST, "F-002", "파일 용량이 초과되었습니다."),
//
//
//	// 스타일/매장
//	STYLING_NOT_FOUND(HttpStatus.NOT_FOUND, "S-001", "존재하지 않는 스타일링입니다."),
//	STAFFPICK_NOT_FOUND(HttpStatus.NOT_FOUND, "S-002", "존재하지 않는 스태프픽입니다."),
//	STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "S-003", "존재하지 않는 매장입니다."),
//	COORDINATION_NOT_FOUND(HttpStatus.NOT_FOUND, "S-004", "존재하지 않는 코디입니다."),
//
//	// 이벤트
//	EVENT_NOT_FOUND(HttpStatus.NOT_FOUND, "E-001", "존재하지 않는 이벤트입니다."),
//
//
//	// 검색
//	SEARCH_POPULAR_NOT_FOUND(HttpStatus.NOT_FOUND, "SE-001", "존재하지 않는 인기 검색어입니다."),
//	SEARCH_LOG_NOT_FOUND(HttpStatus.NOT_FOUND, "SE-002", "존재하지 않는 검색 기록입니다."),
//	SEARCH_RECOMMEND_NOT_FOUND(HttpStatus.NOT_FOUND, "SE-003", "존재하지 않는 추천 검색어입니다."),

	// 공통
	NOT_FOUND(HttpStatus.NOT_FOUND, "G-001", HttpStatus.NOT_FOUND.getReasonPhrase())
	;

	ErrorCode(HttpStatus httpStatus, String errorCode, String message)
	{
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.message = message;
	}

	private final HttpStatus httpStatus;
	private final String errorCode;
	private final String message;

}