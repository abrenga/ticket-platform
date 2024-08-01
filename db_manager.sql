use db_tickets_menager;

INSERT INTO db_tickets_menager.`user`
(is_available , email, password, username)
values(TRUE, "123@HOTMAIL.IT", "{sha256}97cde38028ad898ebc02e690819fa220e88c62e0699403e94fff291cfffaf8410849f27605abcbc0", "Bunny"),
(TRUE, "123@HOTMAIL.IT", "{sha256}97cde38028ad898ebc02e690819fa220e88c62e0699403e94fff291cfffaf8410849f27605abcbc0", "Antonietta"),
(TRUE," 123@HOTMAIL.IT", "{sha256}97cde38028ad898ebc02e690819fa220e88c62e0699403e94fff291cfffaf8410849f27605abcbc0", "Pinco"),
(TRUE, "123@HOTMAIL.IT", "{sha256}97cde38028ad898ebc02e690819fa220e88c62e0699403e94fff291cfffaf8410849f27605abcbc0", "pallino"),
(False, "123@HOTMAIL.IT", "{sha256}97cde38028ad898ebc02e690819fa220e88c62e0699403e94fff291cfffaf8410849f27605abcbc0", "Gina");


INSERT INTO db_tickets_menager.categories
(category)
VALUES( "Fatture"),
( "Reclami"),
( "gestione clienti"),
("offerte");



INSERT INTO db_tickets_menager.`role`
(name)
VALUES("ADMIN"),
("USER");

INSERT INTO db_tickets_menager.ticket
(category_id, `date`, state, user_id, `text`, title)
values(1,STR_TO_DATE("17/07/2024","%d/%m/%Y"), 0,2,"segnaposto utilizzato nel settore della tipografia e della stampa. Lorem Ipsum è considerato il testo","Lorem Ipsum è un testo"),
(2,STR_TO_DATE("10/07/2024","%d/%m/%Y"), 0,2,"segnaposto utilizzato nel settore della tipografia e della stampa. Lorem Ipsum è considerato il testo","Lorem Ipsum è un testo"),
(3,STR_TO_DATE("15/08/2024","%d/%m/%Y"), 0,3,"Lorem Ipsum è un testo segnaposto utilizzato nel settore della tipografia e della stampa. Lorem Ipsum è considerato il testo","Lorem Ipsum è un testo"),
(1,STR_TO_DATE("15/09/2024","%d/%m/%Y"), 0,4,"Lorem Ipsum è un testo segnaposto utilizzato nel settore della tipografia e della stampa. Lorem Ipsum è considerato il testo","Lorem Ipsum è un testo");





INSERT INTO db_tickets_menager.user_roles
(roles_id, user_model_id)
VALUES(1, 1),
(2, 2),
(2, 3),
(2, 4),
(2, 5);
