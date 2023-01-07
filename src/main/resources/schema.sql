CREATE DATABASE IF NOT EXISTS zerock;

use zerock;

DROP TABLE IF EXISTS tb1_board;

CREATE TABLE IF NOT EXISTS tb1_board(
    bno INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    content VARCHAR(2000) NOT NULL,
    writer VARCHAR(50) NOT NULL,
    regDate DATETIME DEFAULT NOW(),
    updateDate DATETIME DEFAULT NOW()
);

INSERT INTO tb1_board(title, content, writer) VALUES("테스트 제목", "테스트 내용", "User00");