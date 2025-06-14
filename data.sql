CREATE TABLE `books` (
    `booksUID` bigint NOT NULL AUTO_INCREMENT COMMENT '고유 번호',
    `title` varchar(256) DEFAULT NULL COMMENT '제목',
    `author` varchar(256) DEFAULT NULL COMMENT '저자',
    `description` longtext COMMENT '내용',
    `price` bigint DEFAULT NULL COMMENT '가격',
    `stockQuantity` bigint DEFAULT NULL COMMENT '재고',
    `isbn` varchar(1024) DEFAULT NULL COMMENT '바코드 번호',
    `createdAt` datetime DEFAULT NULL COMMENT '등록날짜',
    `updatedAt` datetime DEFAULT NULL COMMENT '업데이트날짜',
    PRIMARY KEY (`booksUID`)
);

INSERT INTO `books` (`title`, `author`, `description`, `price`, `stockQuantity`, `isbn`, `createdAt`, `updatedAt`) VALUES
('해리 포터와 마법사의 돌', 'J.K. 롤링', '호그와트 마법학교에 입학한 해리 포터의 첫 번째 모험을 그린 판타지 소설', 15000, 25, '9788983920775', '2024-01-15 09:30:00', '2024-01-15 09:30:00'),
('1984', '조지 오웰', '전체주의 사회의 공포를 그린 디스토피아 소설의 걸작', 12000, 30, '9788937460777', '2024-01-16 10:15:00', '2024-01-16 10:15:00'),
('데미안', '헤르만 헤세', '한 소년의 내적 성장과 자아 발견을 그린 성장소설', 10000, 20, '9788932917245', '2024-01-17 14:20:00', '2024-01-17 14:20:00'),
('죽은 시인의 사회', 'N.H. 클라이넨바움', '자유로운 사고와 개성을 추구하는 교육에 대한 이야기', 13000, 15, '9788937462788', '2024-01-18 11:45:00', '2024-01-18 11:45:00'),
('어린 왕자', '앙투안 드 생텍쥐페리', '어른들을 위한 동화로 사랑과 우정의 소중함을 전하는 작품', 9000, 40, '9788932917139', '2024-01-19 16:30:00', '2024-01-19 16:30:00'),
('코스모스', '칼 세이건', '우주와 과학에 대한 경이로운 여행을 안내하는 과학교양서', 18000, 12, '9788983711892', '2024-01-20 13:10:00', '2024-01-20 13:10:00'),
('사피엔스', '유발 하라리', '인류의 역사와 미래를 통찰력 있게 분석한 베스트셀러', 16000, 35, '9788934972464', '2024-01-21 09:55:00', '2024-01-21 09:55:00'),
('연금술사', '파울로 코엘료', '꿈을 향한 여행과 자아 실현에 대한 우화적 소설', 11000, 28, '9788982814471', '2024-01-22 15:25:00', '2024-01-22 15:25:00'),
('백년 동안의 고독', '가브리엘 가르시아 마르케스', '마술적 리얼리즘의 대표작으로 부엔디아 가문의 7대에 걸친 이야기', 14000, 18, '9788937462825', '2024-01-23 12:40:00', '2024-01-23 12:40:00'),
('노르웨이의 숲', '무라카미 하루키', '청춘의 상실과 사랑을 그린 일본 현대문학의 대표작', 13500, 22, '9788932917511', '2024-01-24 17:15:00', '2024-01-24 17:15:00'),
('미움받을 용기', '기시미 이치로', '아들러 심리학을 바탕으로 한 인생철학서', 14500, 33, '9788996991304', '2024-01-25 10:20:00', '2024-01-25 10:20:00'),
('82년생 김지영', '조남주', '한국 여성의 현실을 담은 사회적 이슈 소설', 12500, 26, '9788936434267', '2024-01-26 14:50:00', '2024-01-26 14:50:00'),
('채식주의자', '한강', '맨부커상을 수상한 한국 현대문학의 걸작', 11500, 19, '9788936434120', '2024-01-27 11:35:00', '2024-01-27 11:35:00'),
('정의란 무엇인가', '마이클 샌델', '하버드대 철학 강의로 유명한 정치철학서', 15500, 24, '9788934962939', '2024-01-28 16:45:00', '2024-01-28 16:45:00'),
('총, 균, 쇠', '재레드 다이아몬드', '인류 문명 발달의 비밀을 지리학적 관점에서 분석한 명저', 17000, 16, '9788934972532', '2024-01-29 09:25:00', '2024-01-29 09:25:00'),
('아프니까 청춘이다', '김난도', '청춘에게 보내는 위로와 격려의 메시지', 10500, 31, '9788936434311', '2024-01-30 13:55:00', '2024-01-30 13:55:00'),
('호모 데우스', '유발 하라리', '인공지능 시대 인류의 미래를 예측한 미래학서', 16500, 21, '9788934985464', '2024-02-01 12:10:00', '2024-02-01 12:10:00'),
('멋진 신세계', '올더스 헉슬리', '과학기술 발달이 가져올 디스토피아적 미래를 그린 소설', 11000, 27, '9788937462979', '2024-02-02 15:30:00', '2024-02-02 15:30:00'),
('동물농장', '조지 오웰', '독재정치를 우화로 풍자한 정치우화소설', 9500, 29, '9788937460760', '2024-02-03 10:45:00', '2024-02-03 10:45:00'),
('이방인', '알베르 카뮈', '부조리한 현실과 인간 존재에 대한 철학적 소설', 10000, 23, '9788937462887', '2024-02-04 14:25:00', '2024-02-04 14:25:00'),
('위대한 개츠비', 'F. 스콧 피츠제럴드', '1920년대 미국 사회를 배경으로 한 미국문학의 고전', 12000, 17, '9788937462894', '2024-02-05 11:15:00', '2024-02-05 11:15:00'),
('파친코', '이민진', '재일교포 가족의 4대에 걸친 삶을 그린 대하소설', 15000, 20, '9788936434465', '2024-02-06 16:20:00', '2024-02-06 16:20:00'),
('나미야 잡화점의 기적', '히가시노 게이고', '시공간을 넘나드는 편지를 통해 펼쳐지는 따뜻한 이야기', 13000, 32, '9788934985723', '2024-02-07 09:40:00', '2024-02-07 09:40:00'),
('인간실격', '다자이 오사무', '절망과 고독에 빠진 한 인간의 내면을 그린 일본문학 걸작', 10500, 25, '9788937462672', '2024-02-08 13:25:00', '2024-02-08 13:25:00'),
('모모', '미하엘 엔데', '시간의 소중함을 일깨워주는 독일 판타지 소설', 11500, 18, '9788932917764', '2024-02-09 15:50:00', '2024-02-09 15:50:00'),
('지구 끝의 온실', '김초엽', '한국 SF 문학의 새로운 가능성을 보여준 작품집', 12500, 22, '9791164843206', '2024-02-10 10:35:00', '2024-02-10 10:35:00'),
('삼체', '류츠신', '중국 SF 소설의 걸작으로 휴고상을 수상한 작품', 14000, 19, '9788934985792', '2024-02-11 14:15:00', '2024-02-11 14:15:00'),
('달러구트 꿈 백화점', '이미예', '꿈을 파는 신비한 백화점을 배경으로 한 판타지 소설', 13500, 28, '9791165341909', '2024-02-12 11:55:00', '2024-02-12 11:55:00'),
('원씽', '게리 켈러', '집중의 힘으로 성공을 이루는 방법을 제시한 자기계발서', 13000, 24, '9788934985808', '2024-02-13 16:40:00', '2024-02-13 16:40:00'),
('김치국물을 마시며', '이영도', '한국적 정서가 담긴 일상 에세이', 11000, 30, '9791165340896', '2024-02-14 12:30:00', '2024-02-14 12:30:00');