create database webCarrito;
use webCarrito;

CREATE TABLE CDS (
    id INT NOT NULL AUTO_INCREMENT, 
    album VARCHAR(300) NOT NULL,
    artist VARCHAR(303), 
    country VARCHAR(100) NOT NULL, 
    price float NOT NULL, 
    PRIMARY KEY (id)
);


INSERT INTO CDS values ('1', 'Yuan','The Guo Brothers','China', '4.95');
INSERT INTO CDS values ('2', 'Drums of Passion','Babatunde Olatunji','Nigeria', '6.95');
INSERT INTO CDS values ('3', 'Kaira','Tounami Diabate','Mali', '6.95');
INSERT INTO CDS values ('4', 'The Lion is Loose','Eliades Ochoa','Cuba', '3.95');
INSERT INTO CDS values ('5', 'Dance the Devil Away','Outback','Australia', '4.95');
INSERT INTO CDS values ('6', 'Record of Changes','Samulnori','Korea', '2.95');
INSERT INTO CDS values ('7', 'Djelika','Tounami Diabate','Mali', '4.95');
INSERT INTO CDS values ('8', 'Cesaria Evora','Cesaria Evora','Cape Verde', '6.95');
INSERT INTO CDS values ('9', 'Ibuki','Kodo','Japan', '3.95');
