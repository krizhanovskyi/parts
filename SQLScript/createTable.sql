USE test;
DROP TABLE IF EXISTS part;
CREATE TABLE part(
 id INT(11) NOT NULL AUTO_INCREMENT,
 name VARCHAR(255) NOT NULL,
 necessary bit(1) NOT NULL,
 quantity INT(11) NOT NULL,
 PRIMARY KEY (id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
INSERT INTO part (name, quantity, necessary) VALUES
('материнская плата',5,1),
('процессор',4,1),
('звуковая карта',3,0),
('память',20,1),
('видеокарта',5,0),
('корпус',2,1),
('SSD диск',6,1),
('HDD диск',7,0),
('подсветка корпуса',4,0),
('беспроводная мышь',8,1),
('клавиатура',7,1),
('монитор',5,1),
('usb-хаб',3,0),
('кардридер',2,0),
('блок питания',11,1),
('источник бесперебойного питания',10,0),
('сетевая карта',10,0),
('веб-камера',7,0),
('акустика',10,0),
('оптический привод',9,0),
('кабель питания',7,1),
('карман внешний',10,0)
;