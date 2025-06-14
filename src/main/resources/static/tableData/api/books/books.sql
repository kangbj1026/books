-- database Focus;
use polypark_proto;

-- // ------------------------ Table Create ST ---------------------------- //
CREATE TABLE books (
	booksUID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY  COMMENT '고유 번호',
	title VARCHAR(256) DEFAULT NULL COMMENT '제목',
	author VARCHAR(256) DEFAULT NULL COMMENT '저자',
	description LONGTEXT COMMENT '내용',
	price BIGINT DEFAULT NULL COMMENT '가격',
	stockQuantity BIGINT DEFAULT NULL COMMENT '재고',
	isbn VARCHAR(1024) DEFAULT NULL COMMENT '바코드 번호'
	,createdAt DATETIME  COMMENT '등록날짜'
	,updatedAt DATETIME  COMMENT '업데이트날짜'
)
-- // ------------------------ Table Create END ---------------------------- //

-- // ------------------------ Stored Procedure ST ---------------------------- //

DELIMITER &&
CREATE PROCEDURE `SP_books_list_R` ()
BEGIN
	SELECT	
	*
	FROM	
	polypark_proto.books
	ORDER BY booksUID ASC;

END &&
DELIMITER ;

-- // ------------------------ Stored Procedure END ---------------------------- //

