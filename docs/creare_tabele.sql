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


insert into user_files values(1,100,'poza','upload/sturza.razvan@yahoo.com/poza1.JPG',sysdate,'S');
insert into user_files values(2,100,'poza','upload/sturza.razvan@yahoo.com/poza2.JPG',sysdate,'P');
insert into user_files values(3,100,'act','upload/sturza.razvan@yahoo.com/act1.pdf',sysdate,'P');
insert into user_files values(4,100,'act','upload/sturza.razvan@yahoo.com/act2.pdf',sysdate,'P');
insert into user_files values(5,101,'poza','upload/stefan.david@yahoo.com/poza.jpg',sysdate,'P');
insert into user_files values(6,102,'poza','upload/alin.cuzuc@yahoo.com/poza.jpg',sysdate,'P');
insert into user_files values(7,103,'poza','upload/andreea.sacara@yahoo.com/poza.jpg',sysdate,'P');
insert into user_files values(8,104,'poza','upload/sturza.nuta@yahoo.com/poza.jpg',sysdate,'P');
insert into user_files values(9,105,'poza','upload/sturza.gelu@yahoo.com/poza.jpg',sysdate,'P');
insert into user_files values(10,106,'poza','upload/sturza.gabi@yahoo.com/poza.jpg',sysdate,'P');
insert into user_files values(11,107,'poza','upload/sturza.toader@yahoo.com/poza.jpg',sysdate,'P');
insert into user_files values(12,108,'poza','upload/barba.ghita@yahoo.com/poza.jpg',sysdate,'P');
insert into user_files values(13,109,'poza','upload/sturza.maria@yahoo.com/poza.jpg',sysdate,'P');
insert into user_files values(14,100,'video','upload/sturza.razvan@yahoo.com/video.mp4',sysdate,'P');
insert into user_files values(15,102,'poza','upload/alin.cuzuc@yahoo.com/poza1.jpg',sysdate,'P');
insert into user_files values(16,102,'video','upload/alin.cuzuc@yahoo.com/video.mp4',sysdate,'S');
insert into user_files values(17,102,'poza','upload/alin.cuzuc@yahoo.com/poza2.jpg',sysdate,'P');
insert into user_files values(18,100,'video','upload/sturza.razvan@yahoo.com/twenty.mp4',sysdate,'P');
insert into user_files values(19,102,'video','upload/alin.cuzuc@yahoo.com/bro.mp4',sysdate,'P');
insert into user_files values(20,103,'poza1','upload/andreea.sacara@yahoo.com/poza1.jpg',sysdate,'P');
insert into user_files values(21,103,'poza2','upload/andreea.sacara@yahoo.com/poza2.jpg',sysdate,'S');
insert into user_files values(22,103,'poza3','upload/andreea.sacara@yahoo.com/poza3.jpg',sysdate,'P');
insert into user_files values(23,103,'video','upload/andreea.sacara@yahoo.com/visine.mp4',sysdate,'P');
insert into user_files values(24,101,'poza','upload/stefan.david@yahoo.com/poza1.jpg',sysdate,'P');
insert into user_files values(25,101,'poza','upload/stefan.david@yahoo.com/poza2.jpg',sysdate,'S');
insert into user_files values(26,101,'poza','upload/stefan.david@yahoo.com/poza3.jpg',sysdate,'P');
insert into user_files values(27,101,'video','upload/stefan.david@yahoo.com/pants.mp4',sysdate,'P');
insert into user_files values(28,106,'poza','upload/sturza.gabi@yahoo.com/poza1.jpg',sysdate,'P');
insert into user_files values(29,106,'poza','upload/sturza.gabi@yahoo.com/poza2.jpg',sysdate,'P');
insert into user_files values(30,106,'poza','upload/sturza.gabi@yahoo.com/poza3.jpg',sysdate,'S');
insert into user_files values(31,106,'video','upload/sturza.gabi@yahoo.com/video.mp4',sysdate,'P');
insert into user_files values(32,105,'poza','upload/sturza.gelu@yahoo.com/poza1.jpg',sysdate,'P');
insert into user_files values(33,105,'video','upload/sturza.gelu@yahoo.com/video.mp4',sysdate,'P');
insert into user_files values(34,104,'poza','upload/sturza.nuta@yahoo.com/poza1.jpg',sysdate,'S');
insert into user_files values(35,104,'poza','upload/sturza.nuta@yahoo.com/poza2.jpg',sysdate,'P');
insert into user_files values(36,104,'poza','upload/sturza.nuta@yahoo.com/poza3.jpg',sysdate,'S');
insert into user_files values(37,104,'poza','upload/sturza.nuta@yahoo.com/poza4.jpg',sysdate,'P');
insert into user_files values(38,104,'poza','upload/sturza.nuta@yahoo.com/poza5.jpg',sysdate,'P');
insert into user_files values(39,104,'video','upload/sturza.nuta@yahoo.com/video.mp4',sysdate,'P');
insert into user_files values(40,100,'poza','upload/sturza.razvan@yahoo.com/poza3.jpg',sysdate,'P');
insert into user_files values(41,100,'poza','upload/sturza.razvan@yahoo.com/poza4.jpg',sysdate,'P');
insert into user_files values(42,100,'poza','upload/sturza.razvan@yahoo.com/poza5.jpg',sysdate,'P');
insert into user_files values(43,100,'poza','upload/sturza.razvan@yahoo.com/poza6.jpg',sysdate,'S');
insert into user_files values(44,100,'poza','upload/sturza.razvan@yahoo.com/poza7.jpg',sysdate,'P');
insert into user_files values(45,100,'poza','upload/sturza.razvan@yahoo.com/poza8.jpg',sysdate,'P');
insert into user_files values(46,100,'video','upload/sturza.razvan@yahoo.com/video1.mp4',sysdate,'P');
insert into user_files values(47,100,'video','upload/sturza.razvan@yahoo.com/video2.mp4',sysdate,'S');
insert into user_files values(48,100,'video','upload/sturza.razvan@yahoo.com/video3.mp4',sysdate,'P');
insert into user_files values(49,100,'act','upload/sturza.razvan@yahoo.com/act1.jpg',sysdate,'S');
insert into user_files values(50,100,'act','upload/sturza.razvan@yahoo.com/act2.jpg',sysdate,'P');
insert into user_files values(51,100,'act','upload/sturza.razvan@yahoo.com/act3.jpg',sysdate,'P');
insert into user_files values(52,103,'poza','upload/andreea.sacara@yahoo.com/act1.jpg',sysdate,'S');
insert into user_files values(53,103,'poza','upload/andreea.sacara@yahoo.com/act2.jpg',sysdate,'S');
insert into user_files values(54,103,'poza','upload/andreea.sacara@yahoo.com/act3.jpg',sysdate,'S');
insert into user_files values(55,102,'act','upload/alin.cuzuc@yahoo.com/act1.jpg',sysdate,'P');
insert into user_files values(56,102,'act','upload/alin.cuzuc@yahoo.com/act2.jpg',sysdate,'P');
insert into user_files values(57,102,'act','upload/alin.cuzuc@yahoo.com/act3.jpg',sysdate,'P');


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


/*insert into file_tags values(1,1,'copou');
insert into file_tags values(2,1,'pizza');
insert into file_tags values(3,1,'retele');
insert into file_tags values(4,2,'gras');
insert into file_tags values(5,2,'iasi');
insert into file_tags values(6,2,'stadion');
insert into file_tags values(7,3,'copou');
insert into file_tags values(8,4,'bucuresti');
insert into file_tags values(9,4,'romania');
insert into file_tags values(8,4,'bunica');
insert into file_tags values(9,4,'relax');*/


insert into familie values(1,104,100);
insert into familie values(2,105,100);
insert into familie values(3,104,106);
insert into familie values(4,105,106);
insert into familie values(5,107,105);
insert into familie values(6,109,105);
insert into familie values(7,108,104);
/
drop table numeTaguri;
/
create table numeTaguri ( nume varchar2(100)
);
/
insert into numeTaguri values('piata');
insert into numeTaguri values('iasi');
insert into numeTaguri values('stadion');
insert into numeTaguri values('copou');
insert into numeTaguri values('mama');
insert into numeTaguri values('tata');
insert into numeTaguri values('caine');
insert into numeTaguri values('trist');
insert into numeTaguri values('romania');
insert into numeTaguri values('europa');
insert into numeTaguri values('pizza');
insert into numeTaguri values('bucuresti');
insert into numeTaguri values('bere');
insert into numeTaguri values('mici');
insert into numeTaguri values('terasa');
insert into numeTaguri values('meci');
insert into numeTaguri values('biblioteca');
insert into numeTaguri values('bunicu');
insert into numeTaguri values('sora');
insert into numeTaguri values('frate');
insert into numeTaguri values('facultate');
insert into numeTaguri values('cafea');
/
set serveroutput on;
declare
 v_i number:=0;
 nr_files number:=0;
 nr_taguri number:=0;
 v_nume varchar2(100):=' ';
 v_id number:=0;
begin
  select count(*) into nr_files from user_files;
  for v_i in 1..nr_files loop
    nr_taguri:=TRUNC(DBMS_RANDOM.VALUE(1,6));
    while(nr_taguri > 0) loop
      v_id:=v_id+1;
      SELECT nume into v_nume FROM ( SELECT * FROM numeTaguri ORDER BY DBMS_RANDOM.RANDOM) WHERE  rownum < 2;
      insert into file_tags(id,file_id,tag) values(v_id,v_i,v_nume);
      nr_taguri:=nr_taguri-1;
    end loop;
  end loop;
end;
/
--select * from users;
--select * from user_logs;
--select * from user_files;
--select * from user_friends;
--select * from file_tags;
/
commit;
