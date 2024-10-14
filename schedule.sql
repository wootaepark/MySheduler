CREATE TABLE `User` (
                        `id` BIGINT NOT NULL  AUTO_INCREMENT COMMENT '유저 아이디',
                        `username` VARCHAR(255) NOT NULL COMMENT '유저 이름',
                        `email` VARCHAR(255) NOT NULL COMMENT '유저 이메일',
                        `created_at` DATETIME NOT NULL DEFAULT NOW() COMMENT '유저 등록일',
                        `updated_at` DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '유저 수정일',
                        PRIMARY KEY (`id`)
);

CREATE TABLE `Schedule` (
                            `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '일정 아이디',
                            `title` VARCHAR(100) NOT NULL COMMENT '일정 제목',
                            `content` VARCHAR(1000) NULL COMMENT '일정 내용',
                            `schedule_date` DATE NOT NULL COMMENT '일정 예정 일시',
                            `created_at` DATETIME NOT NULL DEFAULT NOW() COMMENT '일정 등록일',
                            `updated_at` DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '일정 수정일 (최근)',
                            PRIMARY KEY (`id`)
);

CREATE TABLE `Comment` (
                           `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '댓글 아이디',
                           `content` VARCHAR(1000) NOT NULL COMMENT '댓글 내용',
                           `created_at` DATETIME NOT NULL DEFAULT NOW() COMMENT '댓글 작성일',
                           `updated_at` DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '댓글 수정일',
                           `schedule_id` BIGINT NOT NULL COMMENT '일정 아이디',
                           `user_id` BIGINT NOT NULL COMMENT '유저 아이디',
                           PRIMARY KEY (`id`),
                           CONSTRAINT `FK_Schedule_TO_Comment_1` FOREIGN KEY (`schedule_id`) REFERENCES `Schedule` (`id`),
                           CONSTRAINT `FK_User_TO_Comment_1` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
);

CREATE TABLE `User_Schedule` (
                                `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '유저일정의 아이디',
                                `user_id` BIGINT NOT NULL COMMENT '유저 아이디',
                                `schedule_id` BIGINT NOT NULL COMMENT '일정 아이디',
                                `created_at` DATETIME NOT NULL DEFAULT NOW() COMMENT '유저 일정 등록일',
                                `updated_at` DATETIME NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '유저 일정 수정일',
                                PRIMARY KEY (`id`),
                                CONSTRAINT `FK_User_TO_UserSchedule_1` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`),
                                CONSTRAINT `FK_Schedule_TO_UserSchedule_1` FOREIGN KEY (`schedule_id`) REFERENCES `Schedule` (`id`)
);
