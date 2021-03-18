create database mobilepostgres;
use mobilepostgres;

create sequence seqCompte start 1;
create table compte(
    idCompte int primary key,
    idClient int not null,
    num varchar(10),
    mdp varchar(32) not null
);

insert into compte(idCompte,idClient,num,mdp) VALUES
(nextval('seqCompte'),1,'0332914338',md5('iavotiana')),
(nextval('seqCompte'),2,'0343433434',md5('Faniry')),
(nextval('seqCompte'),3,'0323233232',md5('Maria'));

create sequence seqCredit start 1;
create table credit(
    idCredit int primary key,
    idCompte int not null,
    code varchar(14),
    valeur int,
    daty timestamp
);
insert into credit(idCredit,idCompte,code,valeur,daty)VALUES
(nextval('seqCredit'),1,'12345678901234',1000,'2021-03-18T11:05:03.119131400');

create sequence seqInternet start 1;
create table internet(
    idInternet int primary key,
    idCompte int not null,
    valeur int check >0,
    daty dateTime 
);
create sequence seqAppel start 1;
create table appel(
    idAppel int primary key,
    idCompte int not null,
    valeur int check >0,
    daty dateTime 
);
create sequence seqMobileMoney start 1;
create table mobileMoney(
    idMobileMoney int primary key,
    idCompte int not null,
    valeur int check (valeur)>0),
    daty timestamp,
    statu int check(statu=1 or statu=1)
);

create sequence seqOffreAppel start 1;
create table offreAppel(
    idOffreAppel int primary key,
    idOperateur varchar(2),
    prix int check >0,
    valeur number(10) check >0,
    daty dateTime 
);

create sequence seqOffreInternet start 1;
create table offreInternet(
    idOffreInternet int primary key,
    idOperateur varchar(2),
    prix int check >0,
    valeur number(5) check >0,
    daty dateTime 
);

create sequence seqToken start 1;
create table token(
    idToken int,
    idCompte int,
    valeur varchar(32),
    daty timestamp,
    statu int check(statu=0 or statu=1)
);

alter table credit add constraint FK_credit_idCompte Foreign key (idCompte) references compte(idCompte);
alter table internet add constraint FK_internet_idCompte Foreign key (idCompte) references compte(idCompte);
alter table appel add constraint FK_appel_idCompte Foreign key (idCompte) references compte(idCompte);
alter table mobileMoney add constraint FK_mobileMoney_idCompte Foreign key (idCompte) references compte(idCompte);
alter table token add constraint FK_token_idCompte Foreign key (idCompte) references compte(idCompte);

insert into token(idToken,idCompte,valeur,daty,statu) VALUES
(nextval('seqToken'),1,'b23d2dbda7b3e8d9629dee463d0ff883',CURRENT_TIMESTAMP,0);
SELECT CURRENT_TIMESTAMP as now;
select md5('2021-03-17 15:31:36.07742+03@1233e0abed2dc8e44175af8f713ddfec3f2');
select * from token where valeur='5c07d038b7efbe31b2625c3f96f40e66' and statu=1;

select idCompte,sum(valeur),max(daty) as valeur from credit group by idCompte;