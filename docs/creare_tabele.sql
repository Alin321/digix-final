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
                    log_entry NUMBER(10),
                    access_type CHAR(1),
                    PRIMARY KEY (id),
                    FOREIGN KEY (user_id) references users (id) on delete cascade
                    )
/
create table user_friends ( id NUMBER(10),
                        user_id NUMBER(10),
                        friend_id NUMBER(10),
                        log_entry NUMBER(10),
                        PRIMARY KEY (id),
                        FOREIGN KEY (user_id) references users(id) on delete cascade)
/

create table file_tags ( id NUMBER(10),
                    file_id NUMBER(10),
                    type CHAR(1), 
                    tag VARCHAR2(20),
                    PRIMARY KEY(id),
                    FOREIGN KEY (file_id) references user_files (id) on delete cascade)
/
commit;
