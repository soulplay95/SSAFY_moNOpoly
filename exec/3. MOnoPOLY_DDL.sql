use ssafy_monopoly_db;

-- -----------------------------------------------------
-- 유저
-- ID(PK, 카카오 사용자 정보 id) / 닉네임 / 이메일 / 연락처 / 성별 / 유형(0 : 일반회원, 1 : 도서관 관리자, 2 : 사이트 관리자)
-- -----------------------------------------------------
CREATE TABLE `user` ( 
  `id` BIGINT NOT NULL,
  `nickname` VARCHAR(50),
  `email` VARCHAR(50),
  `phone` VARCHAR(50),
  `gender` VARCHAR(10),
  `type` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
);

-- -----------------------------------------------------
-- 도서관별 관리자
-- ID(PK, 카카오 사용자 정보 id) / 도서관 ID(FK - library) + 생성, 수정 시간
-- -----------------------------------------------------
CREATE TABLE `admin` (
	`id` BIGINT NOT NULL,
    `library_id` BIGINT NOT NULL,
	`created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `modified_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);

-- -----------------------------------------------------
-- 예약
-- ID(PK, AI) / 유저ID(FK - user) / 좌석ID(FK - seat) / 시작시간 / 종료시간 / 최대 설정 시간(8시간)
-- -----------------------------------------------------
CREATE TABLE `reservation` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `seat_id` BIGINT NOT NULL,
    `start_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `end_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `max_set_time` INT DEFAULT 480,
    PRIMARY KEY (`id`)
);

-- -----------------------------------------------------
-- 패널티 로그
-- ID(PK, AI) / 유저ID(FK - user) / 좌석ID(FK - seat) + JPA Auditing(생성, 수정시간 자동화)
-- -----------------------------------------------------
CREATE TABLE `penalty_log` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `seat_id` BIGINT NOT NULL,
	`created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `modified_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);

-- -----------------------------------------------------
-- 공지사항
-- ID(PK, AI) / 유저ID(FK - user) / 도서관ID(FK - library) / 제목 / 내용 / 공지사항 헤드 여부(0: false, 1: true) / 조회수 + JPA Auditing(생성, 수정시간 자동화)
-- -----------------------------------------------------
CREATE TABLE `notice` (    
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`user_id` BIGINT NOT NULL,
    `library_id` BIGINT NOT NULL,
    `title` VARCHAR(50) NOT NULL,
    `content` TEXT,
    `is_head` TINYINT NOT NULL DEFAULT 0,
    `hit_cnt` BIGINT NOT NULL DEFAULT 0,
    `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `modified_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);
-- alter table ssafy_monopoly_db.notice add foreign key(`user_id`) references ssafy_monopoly_db.user(id);

-- -----------------------------------------------------
-- 물품보관함
-- ID(PK, AI) / 패널티로그ID(FK - penalty_log) / 도서관ID(FK - library) / 제목 / 내용 / 만료날짜 + JPA Auditing(생성, 수정시간 자동화)
-- -----------------------------------------------------
CREATE TABLE `locker` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
    `penalty_log_id` BIGINT NOT NULL,
    `library_id` BIGINT NOT NULL,
	`title` VARCHAR(50) NOT NULL,
    `content` TEXT,
    `expire_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `modified_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);

-- -----------------------------------------------------
-- 도서관
-- ID(PK, AI) / 도서관명 / 시도명 / 구군명 / 열람좌석수 / 도로명주소 / 전화번호 / 홈페이지주소
-- -----------------------------------------------------
CREATE TABLE `library` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `sido` VARCHAR(50) NOT NULL,
    `gugun` VARCHAR(50) NOT NULL,
    `seat_counts` VARCHAR(20),
    `address` VARCHAR(255),
    `phone` VARCHAR(100),
    `url` VARCHAR(100),
    PRIMARY KEY (`id`)
);

-- -----------------------------------------------------
-- 이미지
-- ID(PK, AI) / 게시판 타입(notice or locker) / 게시판 ID / url + 생성, 수정 시간
-- -----------------------------------------------------
CREATE TABLE `image` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
    `board_type` VARCHAR(20) NOT NULL,
    `board_id` BIGINT NOT NULL,
    `url` VARCHAR(255) NOT NULL,
    `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `modified_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);

-- -----------------------------------------------------
-- 즐겨찾기
-- ID(PK, AI) / 유저 ID(FK - user) / 도서관 ID(FK - library) / + 생성, 수정 시간
-- -----------------------------------------------------
CREATE TABLE `bookmark` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `library_id` BIGINT NOT NULL,
    `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `modified_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);

-- -----------------------------------------------------
-- 열람실
-- ID(PK, AI) / 도서관 ID(FK - library) / 열람실명 / 층 / 열람실 내 총 좌석 수 / section 수(카메라 수)
-- -----------------------------------------------------
CREATE TABLE `room` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
    `library_id` BIGINT NOT NULL,
    `name` VARCHAR(20) NOT NULL,
    `floor` TINYINT,
    `seat_counts` INT,
    `section_counts` INT,
    PRIMARY KEY (`id`)
);

-- -----------------------------------------------------
-- 섹션
-- ID(PK, AI) / 열람실 ID(FK - room) / 섹션 내 총 좌석 수 / + 생성, 수정 시간
-- -----------------------------------------------------
CREATE TABLE `section` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
    `room_id` BIGINT NOT NULL,
    `seat_counts` INT NOT NULL,
    `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `modified_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);

-- -----------------------------------------------------
-- 좌석
-- ID(PK, AI) / 유저 ID(FK - user) / 섹션 ID(FK - section) / FE 좌석 상태 / CAM 상태 / x 좌표(cam) / y 좌표(cam) / x 좌표(fe) / y 좌표(fe) / 사석화 감지 시작 시간
-- -----------------------------------------------------
CREATE TABLE `seat` (
	`id` BIGINT NOT NULL,
    `user_id` BIGINT DEFAULT 0,
    `section_id` BIGINT NOT NULL DEFAULT 1,
    `front_state` TINYINT NOT NULL DEFAULT 0,
    `cam_state` TINYINT NOT NULL DEFAULT 0,
    `coordinate_x` INT DEFAULT 0,
    `coordinate_y` INT DEFAULT 0,
    `fe_coordinate_x` INT DEFAULT 0,
    `fe_coordinate_y` INT DEFAULT 0,
    `detection_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);

commit;