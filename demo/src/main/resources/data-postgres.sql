----address---




INSERT INTO public.address(city, country, street_name,street_number,longitude,latitude) VALUES ('Novi Sad', 'Srbija', 'Lasla Gala','9',19.844961, 45.254708);
INSERT INTO public.address(city, country, street_name,street_number,longitude,latitude) VALUES ('Beograd', 'Srbija', 'Blagoja Parovica','3',20.415410,44.773590);
INSERT INTO public.address(city, country, street_name,street_number,longitude,latitude) VALUES ('Bijeljina', 'Bosna i Hercegovina', 'Nikola Tesla','12',19.239510, 44.660480);
INSERT INTO public.address(city, country, street_name,street_number,longitude,latitude) VALUES ('Novi Sad', 'Srbija', 'Fruškogorska','22',19.844961, 45.254708);
INSERT INTO public.address(city, country, street_name,street_number,longitude,latitude) VALUES ('Beograd', 'Srbija', 'Majska','22', 20.501251, 44.743571);
INSERT INTO public.address(city, country, street_name,street_number,longitude,latitude) VALUES ('Novi Sad', 'Srbija', 'Fruškogorska','50',19.844961, 45.254708);
INSERT INTO public.address(city, country, street_name,street_number,longitude,latitude) VALUES ('Novi Sad', 'Srbija', 'Fruškogorska','10',0.0, 0.0);
INSERT INTO public.address(city, country, street_name,street_number,longitude,latitude) VALUES ('Novi Sad', 'Srbija', 'Fruškogorska','11',0.0, 0.0);
----users---
--PREDEFINISANI ADMIN,LOZINKA JE admin123
INSERT INTO public.users(d_type, deleted, description_of_registration, email, first_name, is_activated, jmbg, last_name, password, phone_number, user_type,first_login, type,address,scored_points)
	VALUES ('ADMIN', false, 'Predefinisani admin', 'admin123@gmail.com', 'Anđela', true, '2209999185856', 'Bojčić','$2a$10$5s0fl1WYytSVxo0/ve85oe5R6G4MupNWoetyGDQF/xNhgZbvZzypC' , '066787797', 4,false, 0, 1,0);

INSERT INTO public.users(d_type, deleted, description_of_registration, email, first_name, is_activated, jmbg, last_name, password, phone_number, user_type, type,address,scored_points)
VALUES ('COTTAGE_OWNER', false, 'vlasnik vikendice', 'vikendica123@gmail.com', 'Marija', true, '2209999175856', 'Ilic','$2a$10$5s0fl1WYytSVxo0/ve85oe5R6G4MupNWoetyGDQF/xNhgZbvZzypC' , '066787797', 0, 0, 1,0);

INSERT INTO public.users(d_type, deleted, description_of_registration, email, first_name, is_activated, jmbg, last_name, password, phone_number, user_type, type,address,scored_points)
VALUES ('COTTAGE_OWNER', false, 'vlasnik vikendica', 'vecaaa5@gmail.com', 'Goran', true, '2209998175856', 'Ilic','$2a$10$5s0fl1WYytSVxo0/ve85oe5R6G4MupNWoetyGDQF/xNhgZbvZzypC' , '066785597', 0, 0, 1,0);


INSERT INTO public.users(d_type, deleted, description_of_registration, email, first_name, is_activated, jmbg, last_name, password, phone_number, user_type, type,address,scored_points)
VALUES ('SHIP_OWNER', false, 'vlasnik broda', 'milan@gmail.com', 'Milan', true, '2209988175856', 'Milic','$2a$10$5s0fl1WYytSVxo0/ve85oe5R6G4MupNWoetyGDQF/xNhgZbvZzypC' , '066785597', 1, 0, 1,0);

INSERT INTO public.users(d_type, deleted, description_of_registration, email, first_name, is_activated, jmbg, last_name, password, phone_number, user_type, type,address,scored_points)
VALUES ('SHIP_OWNER', false, 'imam vise brodova na izdavanje', 'milica@gmail.com', 'Milica', true, '2244488175856', 'Milivojevic','$2a$10$5s0fl1WYytSVxo0/ve85oe5R6G4MupNWoetyGDQF/xNhgZbvZzypC' , '0667555597', 1, 0, 1,0);

INSERT INTO public.users(d_type, deleted, description_of_registration, email, first_name, is_activated, jmbg, last_name, password, phone_number, user_type,type,address,scored_points)
VALUES ('INSTRUCTOR', false, 'Želim da se registrujem kako bih mogla da ponudim svoje usluge instruktora pecanja', 'raandmjenale@gmail.com', 'Jelena', true, '2209999155856', 'Radman','$2a$10$5s0fl1WYytSVxo0/ve85oe5R6G4MupNWoetyGDQF/xNhgZbvZzypC' , '066782777', 2,0, 3,0);

INSERT INTO public.users(d_type, deleted, description_of_registration, email, first_name, is_activated, jmbg, last_name, password, phone_number, user_type,type,address,penalties,scored_points)
VALUES ('REGISTERED_USER', false, '-----', 'psw.company2@gmail.com', 'Marko', true, '2209999255856', 'Marković','$2a$10$5s0fl1WYytSVxo0/ve85oe5R6G4MupNWoetyGDQF/xNhgZbvZzypC' , '066787777', 3,null, 7,0,0);

INSERT INTO public.users(d_type, deleted, description_of_registration, email, first_name, is_activated, jmbg, last_name, password, phone_number, user_type,type,address,penalties,scored_points)
VALUES ('REGISTERED_USER', false, '-----', 'testsemailpsw@gmail.com', 'Mileva', true, '2209968255856', 'Majstorovic','$2a$10$5s0fl1WYytSVxo0/ve85oe5R6G4MupNWoetyGDQF/xNhgZbvZzypC' , '066781777', 3,null, 8,0,0);

--registration requests
INSERT INTO public.registration_request(email) VALUES ('raandmjenale@gmail.com');

--requests for deleting account
INSERT INTO public.request_for_deleting_account(email,reason) VALUES ('raandmjenale@gmail.com','Ne želim više koristiti usluge vašeg sajta.');

---cottage---
INSERT INTO public.cottage(description, name, price, address,number_of_person, cottage_owner,is_deleted,cancelation_conditions)
	VALUES ('Good cottage on the river', 'CotLux', 100, 1,20,2,true,5.0);
INSERT INTO public.cottage(description, name, price, address,number_of_person, cottage_owner,is_deleted,cancelation_conditions)
	VALUES ('Big and good cottage with pool', 'LuxVil', 200, 1,10,2,true,5.0);
INSERT INTO public.cottage(description, name, price, address,number_of_person, cottage_owner,is_deleted,cancelation_conditions)
    	VALUES ('Small  cottage', 'Vila', 50, 2,2,3,false,0.0);

INSERT INTO public.cottage(description, name, price, address,number_of_person, cottage_owner,is_deleted,cancelation_conditions)
    	VALUES ('Small and sweet  cottage', 'Marija', 50, 1,2,3,false,10.0);

---ships---

INSERT INTO public.ships(
	 name, capacity, ship_type, ship_length, number_of_engine, strength_of_engine, max_speed, price,address,description, ship_owner, deleted,cancelation_conditions,fishing_equipment)
	VALUES ('Sirena', 6, 'boat',5.2, 3, 120, 15, 120, 1, 'Deck boat', 4, false,0.0,'mreza,stap');
INSERT INTO public.ships(
    	 name, capacity, ship_type, ship_length, number_of_engine, strength_of_engine, max_speed, price,address,description, ship_owner, deleted,cancelation_conditions,fishing_equipment)
    	VALUES ('Zvijezda', 10, 'boat',5.2, 5, 200, 15, 120, 1, 'Big boat', 4, false,20.0,'stap');
INSERT INTO public.ships(
       	 name, capacity, ship_type, ship_length, number_of_engine, strength_of_engine, max_speed, price,address,description, ship_owner, deleted,cancelation_conditions,fishing_equipment)
       	VALUES ('More', 4, 'boat',5.4, 5, 100, 15, 120, 1, 'Small boat', 5, true,5.0,'');

---cottage-rules----
INSERT INTO public.rules(rule_description,is_deleted_by_cottage,is_deleted_by_ship,cottage_id,ship_id) VALUES ('No drinking',false,false,1,1);
INSERT INTO public.rules(rule_description,is_deleted_by_cottage,is_deleted_by_ship,cottage_id,ship_id) VALUES ('No smoking',false,false,1,1);
INSERT INTO public.rules(rule_description,is_deleted_by_cottage,is_deleted_by_ship,cottage_id,ship_id) VALUES ('20+',false,false,2,2);


INSERT INTO public.rules(rule_description,is_deleted_by_cottage,is_deleted_by_ship,cottage_id,ship_id) VALUES ('No pets',false,false,2,2);
INSERT INTO public.rules(rule_description,is_deleted_by_cottage,is_deleted_by_ship,cottage_id,ship_id) VALUES ('No noise after 12',false,false,3,1);




---image---

INSERT INTO public.image(url)
	VALUES ('assets/images/cottage.jpg');
INSERT INTO public.image(url)
	VALUES ('assets/images/cottage1.jpg');
INSERT INTO public.image(url)
    	VALUES ('assets/images/cottage2.jpg');


-----utilities---
INSERT INTO public.utility(name)	VALUES ('TV');
INSERT INTO public.utility(name)	VALUES ('Free food');
INSERT INTO public.utility(name)	VALUES ('Free drinks');
INSERT INTO public.utility(name)	VALUES ('Wifi');
INSERT INTO public.utility(name)	VALUES ('Washing machine');
INSERT INTO public.utility(name)	VALUES ('Speakers');
INSERT INTO public.utility(name)	VALUES ('Sauna');

---cottage utilities---

INSERT INTO public.cottage_utility(price, cottage_id, utility_id,is_deleted)	VALUES (20.0, 3, 1,false);
INSERT INTO public.cottage_utility(price, cottage_id, utility_id,is_deleted)	VALUES (10.0, 3, 2,false);
INSERT INTO public.cottage_utility(price, cottage_id, utility_id,is_deleted)	VALUES (0.0, 1, 3,false);
INSERT INTO public.cottage_utility(price, cottage_id, utility_id,is_deleted)	VALUES (10.0, 2, 5,false);
INSERT INTO public.cottage_utility(price, cottage_id, utility_id,is_deleted)	VALUES (0.0, 2, 4,false);

INSERT INTO public.ship_utility(price, ship_id, utility_id,is_deleted)	VALUES (0.0, 1, 1,false);
INSERT INTO public.ship_utility(price, ship_id, utility_id,is_deleted)	VALUES (0.0, 1, 5,false);
INSERT INTO public.ship_utility(price, ship_id, utility_id,is_deleted)	VALUES (0.0, 1, 4,false);
INSERT INTO public.ship_utility(price, ship_id, utility_id,is_deleted)	VALUES (10.0, 2, 5,false);
INSERT INTO public.ship_utility(price, ship_id, utility_id,is_deleted)	VALUES (0.0, 2, 4,false);

INSERT INTO public.navigation_equipment(name, ship_id,is_deleted)	VALUES ('radio' , 1,false);
INSERT INTO public.navigation_equipment( name, ship_id,is_deleted)	VALUES ('GPS',1,false);
INSERT INTO public.navigation_equipment(name, ship_id,is_deleted)	VALUES ('VHFRadio',2,false);
INSERT INTO public.navigation_equipment(name, ship_id,is_deleted)	VALUES ('Radar',2,false);
INSERT INTO public.navigation_equipment(name, ship_id,is_deleted)	VALUES ('Campas',1,false);


INSERT INTO public.rooms(number_of_beds, cottage_id,is_deleted)	VALUES (2, 3,false);
INSERT INTO public.rooms(number_of_beds, cottage_id,is_deleted)	VALUES (2, 3,false);
INSERT INTO public.rooms(number_of_beds, cottage_id,is_deleted)	VALUES (3, 1,false);
INSERT INTO public.rooms(number_of_beds, cottage_id,is_deleted)	VALUES (3, 2,false);
INSERT INTO public.rooms(number_of_beds, cottage_id,is_deleted)	VALUES (2, 2,false);
INSERT INTO public.rooms(number_of_beds, cottage_id,is_deleted)	VALUES (4, 2,false);



----cottage-availability-----


INSERT INTO public.cottage_availability(start_date, end_date,cottage_id)	VALUES ('20220506 10:00:00 AM', '20220531 10:00:00 AM',3);
INSERT INTO public.cottage_availability(start_date, end_date,cottage_id)	VALUES ('20220913 10:00:00 AM', '20220920 10:00:00 AM',3);

INSERT INTO public.ship_availability(start_date, end_date,ship_id)	VALUES ('20220913 10:00:00 AM', '20220920 10:00:00 AM',1);


----appointments-----
INSERT INTO public.appointments(capacity, end_date,is_deleted,is_reserved,price,start_date, cottage_id,valid_until)	VALUES (5, '06-01-2022 10:00',false,false,40.0, '06-05-2022 10:00', 3,'06-01-2022 10:00');

INSERT INTO public.appointments(capacity, end_date,is_deleted,is_reserved,price,start_date, cottage_id,valid_until)
	VALUES (5, '07-07-2022 10:00',false,false,50.0, '01-07-2022 10:00', 3,'30-06-2022 10:00');

INSERT INTO public.appointments(capacity, end_date,is_deleted,is_reserved,price,start_date, ship_id,valid_until)
	VALUES (5, '07-07-2022 10:00',false,false,50.0, '01-07-2022 10:00', 1,'30-06-2022 10:00');

INSERT INTO public.cottage_reservation(id,is_canceled,is_deleted,is_reserved,number_of_person,price,reservation_end,reservation_start,user_id,cottage_id,have_report,type)
VALUES (1,false,false,true,2,50.0,'31-08-2022 09:00','28-08-2022 12:00',2,3,false,'COTTAGE');
INSERT INTO public.cottage_reservation(id,is_canceled,is_deleted,is_reserved,number_of_person,price,reservation_end,reservation_start,user_id,cottage_id,have_report,type)
VALUES (2,false,false,true,2,300.0,'09-10-2022 09:00','09-03-2022 12:00',2,3,false,'COTTAGE');

INSERT INTO public.cottage_reservation(id,is_canceled,is_deleted,is_reserved,number_of_person,price,reservation_end,reservation_start,user_id,cottage_id,have_report,type)
VALUES (3,false,false,true,2,50.0,'07-01-2022 09:00','05-01-2022 12:00',2,3,false,'COTTAGE');
INSERT INTO public.cottage_reservation(id,is_canceled,is_deleted,is_reserved,number_of_person,price,reservation_end,reservation_start,user_id,cottage_id,have_report,type)
VALUES (4,false,false,true,2,50.0,'20-08-2022 09:00','18-08-2022 12:00',2,3,false,'COTTAGE');
INSERT INTO public.ship_reservation(id,is_canceled,is_deleted,is_reserved,number_of_person,price,reservation_end,reservation_start,user_id,ship_id,have_report,type)
VALUES (5,false,false,true,2,50.0,'07-01-2022 09:00','05-01-2022 12:00',2,1,false,'SHIP');


INSERT INTO public.ship_reservation(id,is_canceled,is_deleted,is_reserved,number_of_person,price,reservation_end,reservation_start,user_id,ship_id,have_report,type)
 VALUES (6,false,false,true,2,50.0,'20-08-2022 09:00','18-08-2022 12:00',3,1,false,'SHIP');
INSERT INTO public.ship_reservation(id,is_canceled,is_deleted,is_reserved,number_of_person,price,reservation_end,reservation_start,user_id,ship_id,have_report,type)
VALUES (7,false,false,true,2,50.0,'29-08-2022 09:00','25-08-2022 12:00',3,1,false,'SHIP');

INSERT INTO public.ship_reservation(id,is_canceled,is_deleted,is_reserved,number_of_person,price,reservation_end,reservation_start,user_id,ship_id,have_report,type)
VALUES (8,false,false,true,2,50.0,'29-08-2022 09:00','25-08-2022 12:00',3,1,false,'SHIP');
INSERT INTO public.ship_reservation(id,is_canceled,is_deleted,is_reserved,number_of_person,price,reservation_end,reservation_start,user_id,ship_id,have_report,type)
VALUES (9,false,false,true,2,50.0,'31-08-2022 09:00','29-08-2022 12:00',3,1,false,'SHIP');
INSERT INTO public.ship_reservation(id,is_canceled,is_deleted,is_reserved,number_of_person,price,reservation_end,reservation_start,user_id,ship_id,have_report,type)
VALUES (10,false,false,true,2,50.0,'10-09-2022 09:00','05-09-2022 12:00',3,1,false,'SHIP');


INSERT INTO public.adventure(cancellation_conditions, deleted, description, guest_limit, name, price, address, instructor)
	VALUES (15.0, false, 'Vožnja čamcem uz pecanje na rijeci.', 5, 'Riječni raj', 15, 5, 6);
INSERT INTO public.adventure(cancellation_conditions, deleted, description, guest_limit, name, price, address, instructor)
	VALUES (0.0, false, 'Pecanje sa obale i sa rijeke.', 7, 'Magija na vodi', 20, 6, 6);
INSERT INTO public.adventure_reservation(id,is_canceled,is_deleted,is_reserved,number_of_person,price,reservation_end,reservation_start,instructor_id,user_id,adventure_id,have_report,type)
    VALUES (11,false,false,true,4,60.0,'10-08-2022 12:00','10-08-2022 08:00',6,7,1,false,'ADVENTURE');
INSERT INTO public.adventure_reservation(id,is_canceled,is_deleted,is_reserved,number_of_person,price,reservation_end,reservation_start,instructor_id,user_id,adventure_id,have_report,type)
    VALUES (12,false,false,true,2,30.0,'05-09-2022 09:00','04-09-2022 08:00',6,7,1,false,'ADVENTURE');

INSERT INTO public.adventure_reservation(id,is_canceled,is_deleted,is_reserved,number_of_person,price,reservation_end,reservation_start,instructor_id,user_id,adventure_id,have_report,type)
    VALUES (13,true,false,true,3,45.0,'10-05-2021 12:00','10-05-2021 08:00',6,8,1,false,'ADVENTURE');
INSERT INTO public.adventure_reservation(id,is_canceled,is_deleted,is_reserved,number_of_person,price,reservation_end,reservation_start,instructor_id,user_id,adventure_id,have_report,type)
    VALUES (14,false,false,true,2,30.0,'02-06-2019 09:00','02-06-2019 08:00',6,8,1,false,'ADVENTURE');
INSERT INTO public.adventure_reservation(id,is_canceled,is_deleted,is_reserved,number_of_person,price,reservation_end,reservation_start,instructor_id,user_id,adventure_id,have_report,type)
    VALUES (15,false,false,true,2,30.0,'02-06-2022 09:00','02-06-2022 08:00',6,8,1,false,'ADVENTURE');
INSERT INTO public.adventure_reservation(id,is_canceled,is_deleted,is_reserved,number_of_person,price,reservation_end,reservation_start,instructor_id,user_id,adventure_id,have_report,type)
    VALUES (16,false,false,true,2,30.0,'06-09-2022 23:00','05-09-2022 08:00',6,8,1,false,'ADVENTURE');
INSERT INTO public.adventure_reservation(id,is_canceled,is_deleted,is_reserved,number_of_person,price,reservation_end,reservation_start,instructor_id,user_id,adventure_id,have_report,type)
    VALUES (17,false,false,true,2,30.0,'12-09-2022 09:00','09-09-2022 08:00',6,8,1,false,'ADVENTURE');



INSERT INTO public.adventure_rules(is_deleted, rule_description) VALUES (false, 'No smoking');
INSERT INTO public.adventure_rules(is_deleted, rule_description) VALUES (false, 'No alcohol');


INSERT INTO public.adventure_adventure_rules(adventure_id, adventure_rule_id) VALUES (1, 2);
INSERT INTO public.adventure_adventure_rules(adventure_id, adventure_rule_id) VALUES (2, 1);

INSERT INTO public.fishing_equipment(name, is_deleted) VALUES ('Fishing rod', false);
INSERT INTO public.fishing_equipment(name, is_deleted) VALUES ('Bag-net', false);

INSERT INTO public.adventure_fishing_equipment(adventure_id, equipment_id) VALUES (1, 1);
INSERT INTO public.adventure_fishing_equipment(adventure_id, equipment_id) VALUES (2, 2);

INSERT INTO public.adventure_utility(is_deleted, name, price) VALUES (false,'Diving',10.0);

INSERT INTO public.adventure_adventure_utility(adventure_id, adventure_utility_id) VALUES (1, 1);

INSERT INTO public.instructor_availability(end_date, start_date, instructor_id)

VALUES ('30-08-2022 14:00', '28-08-2022  08:00', 6);
INSERT INTO public.instructor_availability(end_date, start_date, instructor_id)
VALUES ('05-09-2022 10:00', '31-08-2022  08:00', 6);
INSERT INTO public.instructor_availability(end_date, start_date, instructor_id)
VALUES ('12-09-2022 20:00', '06-09-2022  08:00', 6);
INSERT INTO public.instructor_availability(end_date, start_date, instructor_id)
VALUES ('30-09-2022 20:00', '14-09-2022  08:00', 6);

VALUES ('08-20-2022 10:00', '08-15-2022  10:00', 6);



----review---
INSERT INTO public.review(comment, mark, reservation_id,approved,unapproved)
VALUES ('bad', 1, 1,true,false);
INSERT INTO public.review(comment, mark, reservation_id,approved,unapproved)
VALUES ('vrh', 5, 2,true,false);


INSERT INTO public.adventure_users(
	adventure_id, users_id)
	VALUES (1, 7);
INSERT INTO public.adventure_users(
	adventure_id, users_id)
	VALUES (2, 7);
INSERT INTO public.adventure_users(
	adventure_id, users_id)
	VALUES (1, 8);
INSERT INTO public.adventure_users(
	adventure_id, users_id)
	VALUES (2, 8);

INSERT INTO public.cottage_reservation(id,is_canceled,is_deleted,is_reserved,number_of_person,price,reservation_end,reservation_start,user_id,cottage_id,have_report,type)
VALUES (18,false,false,true,2,50.0,'18-09-2022 09:00','16-09-2022 12:00',7,3,false,'COTTAGE');

INSERT INTO public.ship_reservation(id,is_canceled,is_deleted,is_reserved,number_of_person,price,reservation_end,reservation_start,user_id,ship_id,have_report,type)
VALUES (19,false,false,true,2,50.0,'23-09-2022 09:00','20-09-2022 12:00',7,1,false,'SHIP');

INSERT INTO public.adventure_quick_reservation(
end_time, guest_limit, is_deleted, is_reserved, price, start_time, valid_until, adventure_id)
	VALUES ('22-09-2022 17:00', 3, false, false, 20.0, '22-09-2022 12:00', '21-09-2022 23:59', 1);

INSERT INTO public.cottage_users(
	cottage_id, users_id)
	VALUES (3, 7);


INSERT INTO public.ship_users(
	ship_id, users_id)
	VALUES (1, 7);

INSERT INTO public.review(comment, mark, reservation_id,approved,unapproved)
VALUES ('Loše', 1, 11,false,false);
INSERT INTO public.review(comment, mark, reservation_id,approved,unapproved)
VALUES ('Prosjek.', 3, 11,false,false);
INSERT INTO public.review(comment, mark, reservation_id,approved,unapproved)
VALUES ('Avantura je zaista bila zanimljiva.Sve pohvale.', 5, 11,true,false);

INSERT INTO public.report(
	appeared, approvedby_admin, comment, report_date, sanctioned, unapprovedby_admin, reservation_id)
	VALUES (false, false, 'Nije se pojavio.', '22-08-2022 09:00', false, false, 1);

INSERT INTO public.complaint(comment,reservation_id,answered)
VALUES ('Instruktor je kasnio pola sata,i krajnje se neprofesionalno ponašao tokom avanture.', 11,false);

INSERT INTO public.category(
	discount, max_points, min_points, name)
	VALUES (0, 10, 0, 'REGULAR');
INSERT INTO public.category(
    	discount, max_points, min_points, name)
    	VALUES (25, 30, 11, 'SILVER');
   INSERT INTO public.category(
       	discount, max_points, min_points, name)
       	VALUES (50, 500, 31, 'GOLD');

 INSERT INTO public.points(
 	client,owner,keeps_app)
 	VALUES (4, 2, 5.0);