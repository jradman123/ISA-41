----address---

INSERT INTO public.address(city, country, street_name,street_number) VALUES ('London', 'UK', 'Fleet Street','22');
INSERT INTO public.address(city, country, street_name,street_number) VALUES ('Paris', 'France', 'Avenija Fos','25');
INSERT INTO public.address(city, country, street_name,street_number) VALUES ('Bijeljina', 'Bosna i Hercegovina', 'Nikola Tesla','12');
INSERT INTO public.address(city, country, street_name,street_number) VALUES ('Novi Sad', 'Srbija', 'Fruškogorska','22');

----users---
--PREDEFINISANI ADMIN,LOZINKA JE admin123
INSERT INTO public.users(d_type, deleted, description_of_registration, email, first_name, is_activated, jmbg, last_name, password, phone_number, user_type, type,address)
	VALUES ('ADMIN', false, 'Predefinisani admin', 'admin123@gmail.com', 'Anđela', true, '2209999185856', 'Bojčić','$2a$10$5s0fl1WYytSVxo0/ve85oe5R6G4MupNWoetyGDQF/xNhgZbvZzypC' , '066787797', 4, 0, 1);

INSERT INTO public.users(d_type, deleted, description_of_registration, email, first_name, is_activated, jmbg, last_name, password, phone_number, user_type, type,address)
VALUES ('COTTAGE_OWNER', false, 'vlasnik vikendice', 'vikendica123@gmail.com', 'Marija', true, '2209999175856', 'Ilic','$2a$10$5s0fl1WYytSVxo0/ve85oe5R6G4MupNWoetyGDQF/xNhgZbvZzypC' , '066787797', 0, 0, 1);

INSERT INTO public.users(d_type, deleted, description_of_registration, email, first_name, is_activated, jmbg, last_name, password, phone_number, user_type, type,address)
VALUES ('COTTAGE_OWNER', false, 'vlasnik vikendica', 'goran@gmail.com', 'Goran', true, '2209998175856', 'Ilic','$2a$10$5s0fl1WYytSVxo0/ve85oe5R6G4MupNWoetyGDQF/xNhgZbvZzypC' , '066785597', 0, 0, 1);


INSERT INTO public.users(d_type, deleted, description_of_registration, email, first_name, is_activated, jmbg, last_name, password, phone_number, user_type, type,address)
VALUES ('SHIP_OWNER', false, 'vlasnik broda', 'milan@gmail.com', 'Milan', true, '2209988175856', 'Milic','$2a$10$5s0fl1WYytSVxo0/ve85oe5R6G4MupNWoetyGDQF/xNhgZbvZzypC' , '066785597', 1, 0, 1);

INSERT INTO public.users(d_type, deleted, description_of_registration, email, first_name, is_activated, jmbg, last_name, password, phone_number, user_type, type,address)
VALUES ('SHIP_OWNER', false, 'imam vise brodova na izdavanje', 'milica@gmail.com', 'Milica', true, '2244488175856', 'Milivojevic','$2a$10$5s0fl1WYytSVxo0/ve85oe5R6G4MupNWoetyGDQF/xNhgZbvZzypC' , '0667555597', 1, 0, 1);

INSERT INTO public.users(d_type, deleted, description_of_registration, email, first_name, is_activated, jmbg, last_name, password, phone_number, user_type,address) VALUES ('INSTRUCTOR', false, 'Želim da se registrujem kako bih mogla da ponudim svoje usluge instruktora pecanja', 'raandmjenale@gmail.com', 'Jelena', false, '2209999155856', 'Radman','$2a$10$5s0fl1WYytSVxo0/ve85oe5R6G4MupNWoetyGDQF/xNhgZbvZzypC' , '066782777', 2, 3);
INSERT INTO public.registration_request(email) VALUES ('raandmjenale@gmail.com');
---cottage---
INSERT INTO public.cottage(description, name, price, address,number_of_person, cottage_owner,is_deleted,image)
	VALUES ('Good cottage on the river', 'CotLux', 100, 1,20,2,false,'../Images/cottage1.png');
INSERT INTO public.cottage(description, name, price, address,number_of_person, cottage_owner,is_deleted,image)
	VALUES ('Big and good cottage with pool', 'LuxVil', 200, 1,10,2,false,'../Images/cottage2.png');
INSERT INTO public.cottage(description, name, price, address,number_of_person, cottage_owner,is_deleted,image)
    	VALUES ('Small  cottage', 'Vila', 50, 1,2,3,false,'cottage2.png');

---ships---

INSERT INTO public.ships(
	 name, capacity, ship_type, ship_length, number_of_engine, strength_of_engine, max_speed, price,address,description, ship_owner, deleted,cancelation_conditions,fishing_equipment)
	VALUES ('Sirena', 6, 'boat',5.2, 3, 120, 15, 120, 1, 'Deck boat', 4, false,0.0,'mreza,stap');
INSERT INTO public.ships(
    	 name, capacity, ship_type, ship_length, number_of_engine, strength_of_engine, max_speed, price,address,description, ship_owner, deleted,cancelation_conditions,fishing_equipment)
    	VALUES ('Zvijezda', 10, 'boat',5.2, 5, 200, 15, 120, 1, 'Big boat', 4, false,20.0,'stap');
INSERT INTO public.ships(
       	 name, capacity, ship_type, ship_length, number_of_engine, strength_of_engine, max_speed, price,address,description, ship_owner, deleted,cancelation_conditions,fishing_equipment)
       	VALUES ('More', 4, 'boat',5.4, 5, 100, 15, 120, 1, 'Small boat', 5, false,5.0,'');

---cottage-rules----
INSERT INTO public.rules(rule_description,is_deleted,cottage_id) VALUES ('No drinking',false,1);
INSERT INTO public.rules(rule_description,is_deleted,cottage_id) VALUES ('No smoking',false,1);
INSERT INTO public.rules(rule_description,is_deleted,cottage_id) VALUES ('20+',false,2);
INSERT INTO public.rules(rule_description,is_deleted,cottage_id) VALUES ('No pets',false,2);
INSERT INTO public.rules(rule_description,is_deleted,cottage_id) VALUES ('No noise after 12',false,3);

---cottage-rules----
INSERT INTO public.rules(rule_description,is_deleted,ship_id) VALUES ('No drinking',false,1);
INSERT INTO public.rules(rule_description,is_deleted,ship_id) VALUES ('No smoking',false,1);
INSERT INTO public.rules(rule_description,is_deleted,ship_id) VALUES ('20+',false,2);
INSERT INTO public.rules(rule_description,is_deleted,ship_id) VALUES ('No pets',false,2);
INSERT INTO public.rules(rule_description,is_deleted,ship_id) VALUES ('No noise after 12',false,1);

---image---

INSERT INTO public.image(url, cottage_id, is_deleted)
	VALUES ('cottage1.jpg', 1, false);
INSERT INTO public.image(url, cottage_id, is_deleted)
	VALUES ('cottage2.jpg', 1, false);
