insert into operateur(idOperateur,nom,prefixe,mdp) VALUES
(1,'telma','034',md5('@admin123telma')),
(2,'orange','032',md5('@admin123orange')),
(3,'airtel','033',md5('@admin123airtel'));

insert into compte(idCompte,idClient,idOperateur,num,mdp) VALUES
(nextval('seqCompte'),1,3,'0332914338',md5('@client123iavotiana')),
(nextval('seqCompte'),2,1,'0343433434',md5('@client123faniry')),
(nextval('seqCompte'),3,2,'0323233232',md5('@client123maria'));

insert into credit(idCredit,idCompte,code,valeur,daty)VALUES
(nextval('seqCredit'),1,'12345678901234',1000,'2021-03-18T11:05:03.119131400');


insert into mobileMoney (idMobileMoney,idCompte,valeur,daty,statu) values 
(nextval('seqMobileMoney'),1,10000,' 2021-03-18 23:14:14.687104+03',1),
(nextval('seqMobileMoney'),2,10000,' 2021-03-18 23:14:14.687104+03',0),
(nextval('seqMobileMoney'),3,10000,' 2021-03-18 23:14:14.687104+03',0),
(nextval('seqMobileMoney'),1,10000,' 2021-03-19 23:14:14.687104+03',0),
(nextval('seqMobileMoney'),1,20000,' 2021-03-19 23:14:14.687104+03',0),
(nextval('seqMobileMoney'),1,-5000,' 2021-03-19 23:14:14.687104+03',0),
(nextval('seqMobileMoney'),1,-10000,' 2021-03-19 23:14:14.687104+03',0);

insert into offre(idOffre,idOperateur,nom,code,prix,validite)VALUES
(nextval('seqOffre'),1,'Telma net one month','#322*80#',15000,30),
(nextval('seqOffre'),1,'Telma net one day','#322*6#',1000,1),
(nextval('seqOffre'),1,'Telma mora','#322*20#',500,1),
(nextval('seqOffre'),2,'Orange offre Be','224*1*1',500,1),
(nextval('seqOffre'),2,'Orange offre Be Connect 100','224*2*1',1000,1),
(nextval('seqOffre'),2,'Orange offre Be Connect 10','224*2*2',200,1),
(nextval('seqOffre'),2,'Orange offre Be SMS','224*3*1',500,1),
(nextval('seqOffre'),3,'Airtel Net MLay 100','*114*68#',100,1);
(nextval('seqOffre'),3,'Airtel club SMS','*114*100#',500,1);

insert into detailOffreAppel(idOAppel, idOffre,valeurTTC,puMemeOp,puAutreOp) VALUES
(nextval('seqDetailsOffreAppel'),3,500,1,3),
(nextval('seqDetailsOffreAppel'),4,500,1,2);

insert into detailOffreInternet(idOInternet, idOffre,mo) VALUES
(nextval('seqDetailsOffreInternet'),1,1500),
(nextval('seqDetailsOffreInternet'),2,150),
(nextval('seqDetailsOffreInternet'),5,100);

insert into detailOffreSms(idOSms, idOffre,nbrSms) VALUES
(nextval('seqDetailsOffreSms'),7,100),
(nextval('seqDetailsOffreSms'),9,200);

update mobileMoney set statu=1  where idMobileMoney=4;
