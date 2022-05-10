---ADDRESS

INSERT INTO public.address(city, country, street_name,street_number) VALUES ('Eastbourne', 'UK', 'Marine Parade','22');
--PREDEFINISANI ADMIN,LOZINKA JE admin123
INSERT INTO public.users(d_type, deleted, description_of_registration, email, first_name, is_activated, jmbg, last_name, password, phone_number, user_type, type,address)
	VALUES ('ADMIN', false, 'Predefinisani admin', 'admin123@gmail.com', 'Anđela', true, '2209999185856', 'Bojčić','$2a$10$5s0fl1WYytSVxo0/ve85oe5R6G4MupNWoetyGDQF/xNhgZbvZzypC' , '066787797', 4, 0, 1);


INSERT INTO public.users(d_type, deleted, description_of_registration, email, first_name, is_activated, jmbg, last_name, password, phone_number, user_type, type,address)
VALUES ('COTTAGE OWNER', false, 'Predefinisani vlasnik vikendice', 'vikendica123@gmail.com', 'Marija', true, '2209999185856', 'Ilic','$2a$10$5s0fl1WYytSVxo0/ve85oe5R6G4MupNWoetyGDQF/xNhgZbvZzypC' , '066787797', 4, 0, 1);

---cottage---
INSERT INTO public.cottage(description, name, price, address,number_of_person, cottage_owner)
	VALUES ('Good cottage on the river', 'CotLux', 100, 1,20,1);
