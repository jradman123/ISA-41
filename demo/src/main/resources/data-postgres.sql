---ADDRESS
INSERT INTO public.address(city, country, street_name,street_number) VALUES ('Eastbourne', 'UK', 'Marine Parade','22');
INSERT INTO public.address(city, country, street_name,street_number) VALUES ('Bijeljina', 'Bosna i Hercegovina', 'Nikola Tesla','12');
INSERT INTO public.address(city, country, street_name,street_number) VALUES ('Novi Sad', 'Srbija', 'Fruškogorska','22');
--PREDEFINISANI ADMIN,LOZINKA JE admin123
INSERT INTO public.users(d_type, deleted, description_of_registration, email, first_name, is_activated, jmbg, last_name, password, phone_number, user_type, type,address) VALUES ('ADMIN', false, 'Predefinisani admin', 'admin123@gmail.com', 'Anđela', true, '2209999185856', 'Bojčić','$2a$10$5s0fl1WYytSVxo0/ve85oe5R6G4MupNWoetyGDQF/xNhgZbvZzypC' , '066787797', 4, 0, 1);

INSERT INTO public.users(d_type, deleted, description_of_registration, email, first_name, is_activated, jmbg, last_name, password, phone_number, user_type,address) VALUES ('INSTRUCTOR', false, 'Želim da se registrujem kako bih mogla da ponudim svoje usluge instruktora pecanja', 'raandmjenale@gmail.com', 'Jelena', false, '2209999155856', 'Radman','$2a$10$5s0fl1WYytSVxo0/ve85oe5R6G4MupNWoetyGDQF/xNhgZbvZzypC' , '066782777', 2, 2);
INSERT INTO public.registration_request(email) VALUES ('raandmjenale@gmail.com');