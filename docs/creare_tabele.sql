drop table file_tags;
/
drop table user_files;
/
drop table user_friends;
/
drop table user_logs;
/
drop table users;
/
drop table familie;
/
create table users (id NUMBER(10) NOT NULL,
                    email VARCHAR2(25),
                    password VARCHAR2(50),
                    first_name VARCHAR2(50),
                    last_name VARCHAR2(50),
                    birth_date DATE,
                    PRIMARY KEY (id))
/

create table user_logs (id NUMBER(10),
                    user_id NUMBER(10),
                    action VARCHAR2(50),
                    action_date DATE,
                    PRIMARY KEY (id),
                    FOREIGN KEY (user_id) references users(id) on delete cascade)
/

create table user_files (id NUMBER(10),
                    user_id NUMBER(10),
                    type VARCHAR2(10),
                    location VARCHAR2(50),
                    date_added DATE,
                    access_type CHAR(1),
                    PRIMARY KEY (id),
                    FOREIGN KEY (user_id) references users (id) on delete cascade
                    )
/
create table user_friends ( id NUMBER(10),
                        user_id NUMBER(10),
                        friend_id NUMBER(10),
                        PRIMARY KEY (id),
                        FOREIGN KEY (user_id) references users(id) on delete cascade)
/

create table file_tags ( id NUMBER(10),
                    file_id NUMBER(10),
                    tag VARCHAR2(100),
                    PRIMARY KEY(id),
                    FOREIGN KEY (file_id) references user_files (id) on delete cascade)
/
create table familie ( 
  id NUMBER(10),
  parinte_id number(10),
  copil_id number(10),
  PRIMARY KEY(id)
)
/
insert into users values(100,'sturza.razvan@yahoo.com','sturza','Razvan','Sturza',TO_DATE('07/01/1995', 'dd/mm/yyyy'));
insert into users values(101,'stefan.david@yahoo.com','stefan','Stefan','David',TO_DATE('27/04/1995', 'dd/mm/yyyy'));
insert into users values(102,'alin.cuzuc@yahoo.com','alincuzuc','Alin','Cuzuc',TO_DATE('27/11/1995', 'dd/mm/yyyy'));
insert into users values(103,'andreea.sacara@yahoo.com','andreea','Andreea','Sacara-Munteanu',TO_DATE('26/11/1995', 'dd/mm/yyyy'));
insert into users values(104,'sturza.nuta@yahoo.com','sturza','Nuta','Sturza',TO_DATE('01/05/1971', 'dd/mm/yyyy'));
insert into users values(105,'sturza.gelu@yahoo.com','sturza','Gelu','Sturza',TO_DATE('24/03/1973', 'dd/mm/yyyy'));
insert into users values(106,'sturza.gabi@yahoo.com','sturza','Gabi','Sturza',TO_DATE('11/11/1993', 'dd/mm/yyyy'));
insert into users values(107,'sturza.toader@yahoo.com','sturza','Toader','Sturza',TO_DATE('04/11/1950', 'dd/mm/yyyy'));
insert into users values(108,'barba.ghita@yahoo.com','sturza','Ghita','Barba',TO_DATE('04/01/1933', 'dd/mm/yyyy'));
insert into users values(109,'sturza.maria@yahoo.com','sturza','Maria','Sturza',TO_DATE('04/06/1943', 'dd/mm/yyyy'));


insert into user_logs values(1,100,'add_file',sysdate);
insert into user_logs values(2,100,'add_file',sysdate);
insert into user_logs values(3,100,'add_friend',sysdate);
insert into user_logs values(14,100,'add_file',sysdate);
insert into user_logs values(15,100,'add_file',sysdate);
insert into user_logs values(4,101,'add_friend',sysdate);
insert into user_logs values(5,101,'add_file',sysdate);
insert into user_logs values(6,102,'add_file',sysdate);
insert into user_logs values(7,103,'add_file',sysdate);
insert into user_logs values(8,104,'add_file',sysdate);
insert into user_logs values(9,105,'add_file',sysdate);
insert into user_logs values(10,106,'add_file',sysdate);
insert into user_logs values(11,107,'add_file',sysdate);
insert into user_logs values(12,108,'add_file',sysdate);
insert into user_logs values(13,109,'add_file',sysdate);


insert into user_files values(1,100,'poza','C:\\upload\sturza.razvan@yahoo.com\poza1.jpg',sysdate,'P');
insert into user_files values(2,100,'poza','C:\\upload\sturza.razvan@yahoo.com\poza2.jpg',sysdate,'P');
insert into user_files values(3,100,'act','C:\\upload\sturza.razvan@yahoo.com\act1.pdf',sysdate,'P');
insert into user_files values(4,100,'act','C:\\upload\sturza.razvan@yahoo.com\act2.pdf',sysdate,'P');
insert into user_files values(5,101,'poza','C:\\upload\stefan.david@yahoo.com\poza.jpg',sysdate,'P');
insert into user_files values(6,102,'poza','C:\\upload\alin.cuzuc@yahoo.com\poza.jpg',sysdate,'P');
insert into user_files values(7,103,'poza','C:\\upload\andreea.sacara@yahoo.com\poza.jpg',sysdate,'P');
insert into user_files values(8,104,'poza','C:\\upload\sturza.nuta@yahoo.com\poza.jpg',sysdate,'P');
insert into user_files values(9,105,'poza','C:\\upload\sturza.gelu@yahoo.com\poza.jpg',sysdate,'P');
insert into user_files values(10,106,'poza','C:\\upload\sturza.gabi@yahoo.com\poza.jpg',sysdate,'P');
insert into user_files values(11,107,'poza','C:\\upload\sturza.toader@yahoo.com\poza.jpg',sysdate,'P');
insert into user_files values(12,108,'poza','C:\\upload\barba.ghita@yahoo.com\poza.jpg',sysdate,'P');
insert into user_files values(13,109,'poza','C:\\upload\sturza.maria@yahoo.com\poza.jpg',sysdate,'P');


insert into user_friends values(1,100,101);
insert into user_friends values(2,100,102);
insert into user_friends values(3,100,103);
insert into user_friends values(4,100,104);
insert into user_friends values(5,100,105);
insert into user_friends values(6,100,106);
insert into user_friends values(7,100,107);
insert into user_friends values(8,100,108);
insert into user_friends values(9,100,109);
insert into user_friends values(10,101,102);
insert into user_friends values(11,101,103);
insert into user_friends values(12,104,102);
insert into user_friends values(13,105,102);
insert into user_friends values(14,106,102);


insert into file_tags values(1,1,'#tag#copou#pizza');
insert into file_tags values(2,2,'#tag#copou#pizza#smiley');
insert into file_tags values(3,3,'#tag#copou#retele');
insert into file_tags values(4,4,'#tag#copou#retele#hard');
insert into file_tags values(5,5,'#trist#gras');
insert into file_tags values(6,6,'#campion');


insert into familie values(1,104,100);
insert into familie values(2,105,100);
insert into familie values(3,104,106);
insert into familie values(4,105,106);
insert into familie values(5,107,105);
insert into familie values(6,109,105);
insert into familie values(7,108,104);
/
--select * from users;
--select * from user_logs;
--select * from user_files;
--select * from user_friends;
--select * from file_tags;
/
commit;
