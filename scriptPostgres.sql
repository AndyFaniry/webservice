create database mobilepostgres;

create table operateur(
    idOperateur int not null primary key,
    nom varchar(10),
    prefixe varchar(3),
    mdp varchar(32) not null
);

create sequence seqCompte start 1;
create table compte(
    idCompte int primary key,
    idClient int not null,
    idOperateur int not null,
    num varchar(10),
    mdp varchar(32) not null
);

create sequence seqCredit start 1;
create table credit(
    idCredit int primary key,
    idCompte int not null,
    code varchar(14),
    valeur int,
    daty timestamp
);

create sequence seqToken start 1;
create table token(
    idToken int,
    idCompte int,
    valeur varchar(32),
    daty timestamp,
    statu int check(statu=0 or statu=1)
);

create sequence seqTokenAdmin start 1;
create table tokenAdmin(
    idTokenAdmin int,
    idOperateur int,
    valeur varchar(32),
    daty timestamp,
    statu int check(statu=0 or statu=1)
);

create sequence seqMobileMoney start 1;
create table mobileMoney(
    idMobileMoney int primary key,
    idCompte int not null,
    valeur decimal(12,2),
    daty timestamp,
    statu int check(statu=1 or statu=0)
);

alter table compte add constraint FK_compte_idOperateur Foreign key (idOperateur) references operateur(idOperateur);
alter table credit add constraint FK_credit_idCompte Foreign key (idCompte) references compte(idCompte);
alter table token add constraint FK_token_idCompte Foreign key (idCompte) references compte(idCompte);
alter table tokenAdmin add constraint FK_tokenAdmin_idOperateur Foreign key (idOperateur) references operateur(idOperateur);
alter table mobileMoney add constraint FK_mobileMoney_idCompte Foreign key (idCompte) references compte(idCompte);

create view v_credit as select idCompte,sum(valeur) as valeur,max(daty) as daty from credit group by idCompte;

create view v_depot_non_valide as 
select mobileMoney.*,
compte.num,compte.idOperateur,
operateur.nom 
from mobileMoney join compte on (mobileMoney.idCompte=compte.idCompte)
join operateur on (operateur.idOperateur=compte.idOperateur) 
where valeur>0 and statu=0;

create view v_retrait_non_valide as 
select mobileMoney.*,
compte.num,compte.idOperateur,
operateur.nom 
from mobileMoney join compte on (mobileMoney.idCompte=compte.idCompte)
join operateur on (operateur.idOperateur=compte.idOperateur) 
where valeur<0 and statu=0;
