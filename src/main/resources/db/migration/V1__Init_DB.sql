create table charter (
    id bigint not null,
    done TINYINT not null,
    finish TINYINT not null,
    revers TINYINT not null,
    telegram TINYINT not null,
    primary key (id)
    );

create table comeback (
    id bigint not null,
    coment varchar(255),
    date date,
    finish TINYINT not null,
    telegram TINYINT not null,
    resolution bigint,
    primary key (id)
    );

create table contact (
    id bigint not null,
    authenticate TINYINT,
    mail varchar(255),
    phone varchar(255),
    telegram_id varchar(255),
    primary key (id)
    );

create table dbuser (
    id bigint not null,
    password varchar(255),
    username varchar(255),
    information bigint,
    primary key (id)
    );

create table executant (
    id bigint not null,
    coment varchar(255),
    resolution bigint,
    doer bigint,
    primary key (id)
    );

create table file (
    id bigint not null,
    coment varchar(255),
    date date,
    name varchar(255),
    number varchar(255),
    resolution TINYINT not null,
    telegram bit not null,
    author bigint,
    primary key (id)
    );

create table hibernate_sequence (
    next_val bigint
    );

insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );

create table info (
    id bigint not null,
    contact bigint,
    person bigint,
    primary key (id)
    );

create table person (
    id bigint not null,
    caption varchar(255),
    initials varchar(255),
    middlename varchar(255),
    name varchar(255),
    post varchar(255),
    surname varchar(255),
    primary key (id)
    );

create table resolve (
    id bigint not null,
    coment varchar(255),
    date date,
    agrees bigint,
    document bigint,
    filling bigint,
    status bigint,
    visa bigint,
    primary key (id)
    );

create table sending (
    id bigint not null,
    user bigint,
    primary key (id)
    );

create table stat (
    id bigint not null,
    doer varchar(255),
    initials varchar(255),
    visa bigint,
    primary key (id)
    );

create table user_role (
    user_id bigint not null,
    roles varchar(255)
    );

create table visa (
    id bigint not null,
    agrees varchar(255),
    data date,
    position varchar(255),
    primary key (id)
    );

alter table comeback add constraint FK3otu3w9ydxsr9obx98tmnhh09 foreign key (resolution) references resolve (id);
alter table dbuser add constraint FKelwahlu9c9wglpnwdhyl1wn1p foreign key (information) references info (id);
alter table executant add constraint FK64f8bbewmsmhmy0hctqi4afp foreign key (resolution) references resolve (id);
alter table executant add constraint FKsme2320b41syqbiemkkru0gjc foreign key (doer) references dbuser (id);
alter table file add constraint FK5ga8g5eou3nbkd0ksojd95hfg foreign key (author) references dbuser (id);
alter table info add constraint FKnuar85l8q578fdo4fnk5j9ife foreign key (contact) references contact (id);
alter table info add constraint FKl2frcin0c0ww9qv7e13j7nof4 foreign key (person) references person (id);
alter table resolve add constraint FK39e19tx1n73extpmcybf6rnxb foreign key (agrees) references dbuser (id);
alter table resolve add constraint FK4jki3fnonp6mtjsucc0t210g4 foreign key (document) references file (id);
alter table resolve add constraint FKct22lckbfxrypq8kf8l3cmpx0 foreign key (filling) references dbuser (id);
alter table resolve add constraint FK3vodjcc4g9l0ail30vv8huo8b foreign key (status) references charter (id);
alter table resolve add constraint FKc4c2v066uhrxbnmbj15u8jokv foreign key (visa) references visa (id);
alter table sending add constraint FKnn20jy2fjjpm8bckavdasyxly foreign key (user) references dbuser (id);
alter table stat add constraint FKaby0v0vfy9wbhgr94pghaeel1 foreign key (visa) references visa (id);
alter table user_role add constraint FK6mv2kp9k78nvgtgkvqw5an6y2 foreign key (user_id) references dbuser (id);
