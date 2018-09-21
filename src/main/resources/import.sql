DROP DATABASE if EXISTS mybatis;
CREATE database mybatis CHARACTER SET utf8;
USE mybatis;

CREATE TABLE tb_customer(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(20) DEFAULT ''
);
CREATE TABLE tb_order(
  id INT PRIMARY KEY AUTO_INCREMENT,
  goods VARCHAR(20) DEFAULT '',
  price INT DEFAULT '0',
  c_id INT,
  FOREIGN KEY(c_id) REFERENCES tb_customer(id)
);

INSERT INTO tb_customer(name) VALUES('triski');
INSERT INTO tb_customer(name) VALUES('chen');

INSERT INTO tb_order(goods,price,c_id) VALUES('paper',2,1);
INSERT INTO tb_order(goods,price,c_id) VALUES('flower',7,1);
INSERT INTO tb_order(goods,price,c_id) VALUES('apple',4,2);