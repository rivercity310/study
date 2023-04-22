use assignment;

ALTER TABLE board AUTO_INCREMENT=1;
SET @COUNT = 0;
UPDATE board SET seq = @COUNT := @COUNT + 1;

INSERT INTO board(id, position, reward, content, tech) VALUES(2, "ML Engineer", 4000000, "머신러닝 엔지니어", "Tensorflow")
INSERT INTO board(id, position, reward, content, tech) VALUES(2, "Frontend Engineer", 3400000, "프론트엔드 엔지니어", "Javascript")


INSERT INTO company VALUES(1, "네이버", "한국", "판교");
INSERT INTO company VALUES(2, "카카오", "한국", "판교");

INSERT INTO user VALUES(2, "h970126", "황승수", 26)
