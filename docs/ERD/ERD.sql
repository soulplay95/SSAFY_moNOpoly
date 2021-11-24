CREATE TABLE `User` (
	`user_id`	INT	NOT NULL,
	`name`	VARCHAR(20)	NOT NULL,
	`email`	VARCHAR(50)	NOT NULL,
	`phone`	VARCHAR(20)	NOT NULL,
	`gender`	VARCHAR(10)	NOT NULL,
	`join_date`	timestamp	NOT NULL,
	`last_login_date`	timestamp	NULL,
	`token`	VARCHAR(255)	NULL,
	`type`	INT	NULL
);

CREATE TABLE `Seat` (
	`seat_id`	INT	NOT NULL,
	`section_id`	VARCHAR(50)	NOT NULL,
	`user_id`	INT	NOT NULL,
	`occupation`	BOOLEAN	NOT NULL	DEFAULT FALSE,
	`petrification`	BOOLEAN	NOT NULL	DEFAULT FALSE,
	`detection_time`	TIMESTAMP	NULL	DEFAULT now()
);

CREATE TABLE `Notice` (
	`notice_id`	INT	NOT NULL,
	`user_id`	VARCHAR(30)	NOT NULL,
	`title`	VARCHAR(30)	NOT NULL,
	`content`	VARCHAR(255)	NULL,
	`created_date`	TIMESTAMP	NOT NULL,
	`updated_date`	TIMESTAMP	NULL,
	`is_head`	BOOLEAN	NULL,
	`Field`	VARCHAR(255)	NULL
);

CREATE TABLE `Comments` (
	`comment_id`	INT	NOT NULL,
	`user_id`	VARCHAR(50)	NOT NULL,
	`notice_id`	VARCHAR(50)	NOT NULL,
	`content`	VARCHAR(255)	NOT NULL,
	`created_date`	TIMESTAMP	NOT NULL,
	`updated_date`	TIMESTAMP	NULL
);

CREATE TABLE `qna` (
	`qna_id`	INT	NOT NULL,
	`user_id`	INT	NOT NULL,
	`title`	VARCHAR(30)	NULL,
	`content`	VARCHAR(255)	NULL,
	`is_private`	BOOLEAN	NULL,
	`answer`	VARCHAR(255)	NULL,
	`created_date`	TIMESTAMP	NULL,
	`updated_date`	TIMESTAMP	NULL
);

CREATE TABLE `Room` (
	`room_id`	INT	NOT NULL,
	`library_id`	INT	NOT NULL,
	`name`	VARCHAR(30)	NOT NULL,
	`floor`	INT	NOT NULL	DEFAULT 1	COMMENT '지하 => -1부터',
	`seat_counts`	INT	NOT NULL	DEFAULT 0,
	`section_counts`	INT	NULL
);

CREATE TABLE `Section` (
	`section_id`	INT	NOT NULL,
	`room_id`	INT	NOT NULL,
	`name`	VARCHAR(30)	NOT NULL,
	`seat_counts`	INT	NOT NULL	DEFAULT 0,
	`updated_date`	TIMESTAMP	NULL
);

CREATE TABLE `Locker` (
	`locker_id`	INT	NOT NULL,
	`penalty_id`	INT	NOT NULL,
	`locker_occupation`	BOOLEAN	NULL,
	`location`	VARCHAR(30)	NULL,
	`expire_date`	TIMESTAMP	NULL	DEFAULT now(),
	`image`	varbinary(1000) NULL
);

CREATE TABLE `Penalty` (
	`penalty_id`	INT	NOT NULL,
	`user_id`	INT	NOT NULL,
	`seat_id`	INT	NOT NULL,
	`reason`	VARCHAR(50)	NULL,
	`date`	TIMESTAMP	NULL
);

CREATE TABLE `Reservation` (
	`reservation_id`	INT	NOT NULL,
	`user_id`	INT	NOT NULL,
	`seat_id`	INT	NOT NULL,
	`start_time`	TIMESTAMP	NOT NULL	DEFAULT now(),
	`end_time`	TIMESTAMP	NOT NULL
);

CREATE TABLE `Manager` (
	`id`	INT	NOT NULL,
	`library_id`	INT	NOT NULL,
	`auth_date`	TIMESTAMP	NULL
);

CREATE TABLE `Library` (
	`library_id`	INT	NOT NULL,
	`name`	VARCHAR(30)	NOT NULL,
	`address`	VARCHAR(100)	NOT NULL	COMMENT '다음 주소 API 사용. 지번, 상세주소 등등을 어떻게 조합하여 표현할 것인가?',
	`phone`	VARCHAR(20)	NULL
);

CREATE TABLE `Common Notice` (
	`com_notice_id`	INT	NOT NULL,
	`user_id`	INT	NOT NULL,
	`title`	VARCHAR(30)	NULL,
	`content`	VARCHAR(255)	NULL,
	`created_date`	TIMESTAMP	NULL,
	`updated_date`	TIMESTAMP	NULL,
	`is_head`	BOOLEAN	NULL,
	`Field`	VARCHAR(255)	NULL
);

CREATE TABLE `Common comments` (
	`com_comment_id`	INT	NOT NULL,
	`com_notice_id`	INT	NOT NULL,
	`user_id`	INT	NOT NULL,
	`content`	VARCHAR(255)	NULL,
	`created_date`	TIMESTAMP	NULL,
	`updated_date`	TIMESTAMP	NULL
);

CREATE TABLE `Bookmark` (
	`bookmakr_id`	INT	NOT NULL,
	`user_id`	INT	NOT NULL,
	`library_id`	INT	NOT NULL
);

ALTER TABLE `User` ADD CONSTRAINT `PK_USER` PRIMARY KEY (
	`user_id`
);

ALTER TABLE `Seat` ADD CONSTRAINT `PK_SEAT` PRIMARY KEY (
	`seat_id`,
	`section_id`,
	`user_id`
);

ALTER TABLE `Notice` ADD CONSTRAINT `PK_NOTICE` PRIMARY KEY (
	`notice_id`,
	`user_id`
);

ALTER TABLE `Comments` ADD CONSTRAINT `PK_COMMENTS` PRIMARY KEY (
	`comment_id`,
	`user_id`,
	`notice_id`
);

ALTER TABLE `qna` ADD CONSTRAINT `PK_QNA` PRIMARY KEY (
	`qna_id`,
	`user_id`
);

ALTER TABLE `Room` ADD CONSTRAINT `PK_ROOM` PRIMARY KEY (
	`room_id`,
	`library_id`
);

ALTER TABLE `Section` ADD CONSTRAINT `PK_SECTION` PRIMARY KEY (
	`section_id`,
	`room_id`
);

ALTER TABLE `Locker` ADD CONSTRAINT `PK_LOCKER` PRIMARY KEY (
	`locker_id`,
	`penalty_id`
);

ALTER TABLE `Penalty` ADD CONSTRAINT `PK_PENALTY` PRIMARY KEY (
	`penalty_id`,
	`user_id`,
	`seat_id`
);

ALTER TABLE `Reservation` ADD CONSTRAINT `PK_RESERVATION` PRIMARY KEY (
	`reservation_id`,
	`user_id`,
	`seat_id`
);

ALTER TABLE `Manager` ADD CONSTRAINT `PK_MANAGER` PRIMARY KEY (
	`id`,
	`library_id`
);

ALTER TABLE `Library` ADD CONSTRAINT `PK_LIBRARY` PRIMARY KEY (
	`library_id`
);

ALTER TABLE `Common Notice` ADD CONSTRAINT `PK_COMMON NOTICE` PRIMARY KEY (
	`com_notice_id`,
	`user_id`
);

ALTER TABLE `Common comments` ADD CONSTRAINT `PK_COMMON COMMENTS` PRIMARY KEY (
	`com_comment_id`,
	`com_notice_id`,
	`user_id`
);

ALTER TABLE `Bookmark` ADD CONSTRAINT `PK_BOOKMARK` PRIMARY KEY (
	`bookmakr_id`,
	`user_id`,
	`library_id`
);

ALTER TABLE `Seat` ADD CONSTRAINT `FK_Section_TO_Seat_1` FOREIGN KEY (
	`section_id`
)
REFERENCES `Section` (
	`section_id`
);

ALTER TABLE `Seat` ADD CONSTRAINT `FK_User_TO_Seat_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `User` (
	`user_id`
);

ALTER TABLE `Notice` ADD CONSTRAINT `FK_User_TO_Notice_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `User` (
	`user_id`
);

ALTER TABLE `Comments` ADD CONSTRAINT `FK_User_TO_Comments_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `User` (
	`user_id`
);

ALTER TABLE `Comments` ADD CONSTRAINT `FK_Notice_TO_Comments_1` FOREIGN KEY (
	`notice_id`
)
REFERENCES `Notice` (
	`notice_id`
);

ALTER TABLE `qna` ADD CONSTRAINT `FK_User_TO_qna_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `User` (
	`user_id`
);

ALTER TABLE `Room` ADD CONSTRAINT `FK_Library_TO_Room_1` FOREIGN KEY (
	`library_id`
)
REFERENCES `Library` (
	`library_id`
);

ALTER TABLE `Section` ADD CONSTRAINT `FK_Room_TO_Section_1` FOREIGN KEY (
	`room_id`
)
REFERENCES `Room` (
	`room_id`
);

ALTER TABLE `Locker` ADD CONSTRAINT `FK_Penalty_TO_Locker_1` FOREIGN KEY (
	`penalty_id`
)
REFERENCES `Penalty` (
	`penalty_id`
);

ALTER TABLE `Penalty` ADD CONSTRAINT `FK_User_TO_Penalty_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `User` (
	`user_id`
);

ALTER TABLE `Penalty` ADD CONSTRAINT `FK_Seat_TO_Penalty_1` FOREIGN KEY (
	`seat_id`
)
REFERENCES `Seat` (
	`seat_id`
);

ALTER TABLE `Reservation` ADD CONSTRAINT `FK_User_TO_Reservation_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `User` (
	`user_id`
);

ALTER TABLE `Reservation` ADD CONSTRAINT `FK_Seat_TO_Reservation_1` FOREIGN KEY (
	`seat_id`
)
REFERENCES `Seat` (
	`seat_id`
);

ALTER TABLE `Manager` ADD CONSTRAINT `FK_Library_TO_Manager_1` FOREIGN KEY (
	`library_id`
)
REFERENCES `Library` (
	`library_id`
);

ALTER TABLE `Common Notice` ADD CONSTRAINT `FK_User_TO_Common Notice_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `User` (
	`user_id`
);

ALTER TABLE `Common comments` ADD CONSTRAINT `FK_Common Notice_TO_Common comments_1` FOREIGN KEY (
	`com_notice_id`
)
REFERENCES `Common Notice` (
	`com_notice_id`
);

ALTER TABLE `Common comments` ADD CONSTRAINT `FK_Common Notice_TO_Common comments_2` FOREIGN KEY (
	`user_id`
)
REFERENCES `Common Notice` (
	`user_id`
);

ALTER TABLE `Bookmark` ADD CONSTRAINT `FK_User_TO_Bookmark_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `User` (
	`user_id`
);

ALTER TABLE `Bookmark` ADD CONSTRAINT `FK_Library_TO_Bookmark_1` FOREIGN KEY (
	`library_id`
)
REFERENCES `Library` (
	`library_id`
);

