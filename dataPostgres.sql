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

