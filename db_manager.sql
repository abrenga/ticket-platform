use db_tickets_menager;




INSERT INTO db_tickets_menager.ticket
(category_id, `date`, id, state, user_id, title)
values(1,STR_TO_DATE("17/07/2024","%d/%m/%Y"), 0, "Lorem Ipsum è un testo segnaposto utilizzato nel settore della tipografia e della stampa. Lorem Ipsum è considerato il testo"),
(2,STR_TO_DATE("10/07/2024","%d/%m/%Y"), 0, "Lorem Ipsum è un testo segnaposto utilizzato nel settore della tipografia e della stampa. Lorem Ipsum è considerato il testo"),
(3,STR_TO_DATE("15/04/2024","%d/%m/%Y"), 0, "Lorem Ipsum è un testo segnaposto utilizzato nel settore della tipografia e della stampa. Lorem Ipsum è considerato il testo");

INSERT INTO db_tickets_menager.categories
(id, category)
VALUES(1, 'Assistenza clienti'),
(2, 'reclami'),
(3, 'gestione fatture'),
(4, 'aggiunte offerte');



INSERT INTO db_tickets_menager.`user`
(is_aviabele, email, password, user_name)
VALUES(TRUE, 123@HOTMAIL.IT, {sha256}97cde38028ad898ebc02e690819fa220e88c62e0699403e94fff291cfffaf8410849f27605abcbc0, Antonietta),
(TRUE, 123@HOTMAIL.IT, {sha256}97cde38028ad898ebc02e690819fa220e88c62e0699403e94fff291cfffaf8410849f27605abcbc0, Emanuele),
(TRUE, 123@HOTMAIL.IT, {sha256}97cde38028ad898ebc02e690819fa220e88c62e0699403e94fff291cfffaf8410849f27605abcbc0, Pincopallo),
(TRUE, 123@HOTMAIL.IT, {sha256}97cde38028ad898ebc02e690819fa220e88c62e0699403e94fff291cfffaf8410849f27605abcbc0, Bunny);



INSERT INTO db_tickets_menager.`role`
( name)
VALUES('ADMIN'),
('user');


INSERT INTO db_tickets_menager.user_roles
(roles_id, user_model_id)
VALUES(1, 1),
(2, 1),
(2, 2),
(2, 1);
