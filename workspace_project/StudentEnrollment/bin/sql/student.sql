create table student(
name varchar2(30) not null,
year varchar2(30) ,
id varchar2(30) ,
grade int,
balance int,
primary key(id)
);
create table course(
s_id varchar2(30),
course_name varchar2(30),
primary key(s_id),
foreign key(s_id) references student (id)
);

create table course_price(
c_name varchar2(30),
c_price int,
primary key(c_name)
);

insert into course_price values(
'Computer Science 101',6000
);
insert into course_price values(
'History 101',6000
);
insert into course_price values(
'Mathematics 101',6000
);
insert into course_price values(
'English 101',6000
);
insert into course_price values(
'Chemistry 101',6000
);
insert into student 
values('kim','0000001111111','11111',1,10000);

select * from student
select * from course
select * from course_price
drop table student;
drop table course;
drop table course_price;
delete casecade STUDENT;
delete from course_price;
delete from student where grade=1;

