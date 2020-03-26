--first query
SELECT first_name,last_name FROM mentors;
--second query
SELECT nick_name FROM mentors
WHERE city = 'Miskolc';
--third query
SELECT concat(first_name,' ', last_name) full_name, phone_number FROM applicants;
--fourth query
SELECT concat(first_name,' ', last_name) full_name, phone_number FROM applicants
WHERE email LIKE '%@adipiscingenimmi.edu';
--fifth query
INSERT INTO applicants (id, first_name, last_name, phone_number, email, application_code)
VALUES (11 , 'Markus', 'Schaffarzyk', '003620/725-2666', 'djnovus@groovecoverage.com', 54823);
SELECT * FROM applicants
WHERE application_code = 54823;
--sixth query
UPDATE applicants
SET phone_number = '003670/223-7459'
WHERE first_name = 'Jemima' AND last_name = 'Foreman';
--seventh query
DELETE FROM applicants
WHERE email LIKE '%@mauriseu.net';