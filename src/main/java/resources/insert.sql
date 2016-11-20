drop DATABASE ITProjects;
create database ITProjects;
use ITProjects;
create table Developers(
	  id int auto_increment not null,
    name varchar(50) not null,
    speciality varchar(50) not null,
    salary int not null,
    id_Team int NOT NULL,
    primary key (id)
    )ENGINE=InnoDB CHARACTER SET=UTF8;

create table Projects(
	  id int auto_increment not null,
    name varchar(50) not null,
    id_Team int NOT NULL DEFAULT 0,
    primary key (id)
    )ENGINE=InnoDB CHARACTER SET=UTF8;

create table Teams(
	  id int auto_increment not null,
    name varchar(50) not null,
    size int not null,
    primary key (id)
    )ENGINE=InnoDB CHARACTER SET=UTF8;

insert into Developers(name, speciality, salary, id_Team) values('Sergiy Zamikhovskyy', 'Java', 1000, 1);
insert into Developers(name, speciality, salary, id_Team) values('Andriy Kolesnik', 'C++', 1500, 1);
insert into Developers(name, speciality, salary, id_Team) values('Olexiy Kucherov', 'PHP', 1500, 1);
insert into Developers(name, speciality, salary, id_Team) values('Olexiy Tokaryev', 'C++', 2000, 2);
insert into Developers(name, speciality, salary, id_Team) values('Michael Pervoliev', 'Java/Android', 2500, 2);
insert into Developers(name, speciality, salary, id_Team) values('John Hammond', 'EmbeddedC/C#', 800, 2);
insert into Developers(name, speciality, salary, id_Team) values('Derek Handerson', 'ASM', 850, 3);
insert into Developers(name, speciality, salary, id_Team) values('Drue Gurts', 'HTML/CSS', 1200, 3);
insert into Developers(name, speciality, salary, id_Team) values('Celine Yazerk', 'C++/C', 1500, 3);
insert into Developers(name, speciality, salary, id_Team) values('Thomas Folres', 'Python', 2000, 3);

insert into Projects(name, id_Team) values('Big Internet Trad Proj', 1);
insert into Projects(name, id_Team) values('Small Desktop Project', 2);
insert into Projects(name, id_Team) values('Middle Mail Project', 3);

insert into teams(name, size) values('team1', 3);
insert into teams(name, size) values('team2', 3);
insert into teams(name, size) values('team3', 4);


