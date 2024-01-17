-- Televisions
ALTER SEQUENCE televisions_seq INCREMENT BY 1;
INSERT INTO televisions (id, type, brand, name, price, available_size, refresh_rate, screen_type, screen_quality,
                         smart_tv, wifi, voice_control, hdr, bluetooth, ambi_light, original_stock, sold)
VALUES (nextval('televisions_seq'), 'NH3216SMART', 'Nikkei', 'HD smart TV', 159, 32, 100, 'LED', 'HD ready', true, true,
        false, false, false, false, 235885, 45896),
       (nextval('televisions_seq'), '43PUS6504/12/L', 'Philips', '4K UHD LED Smart Tv', 379, 43, 60, 'LED', 'Ultra HD',
        true, true, false, true, false, false, 8569452, 5685489),
       (nextval('televisions_seq'), '43PUS6504/12/M', 'Philips', '4K UHD LED Smart Tv', 379, 50, 60, 'LED', 'Ultra HD',
        true, true, false, true, false, false, 345549, 244486),
       (nextval('televisions_seq'), '43PUS6504/12/S', 'Philips', '4K UHD LED Smart Tv', 379, 58, 60, 'LED', 'Ultra HD',
        true, true, false, true, false, false, 6548945, 4485741),
       (nextval('televisions_seq'), 'OLED55C16LA', 'LG', 'LG OLED55C16LA', 989, 55, 100, 'OLED', 'ULTRA HD', true, true,
        true, true, true, false, 50000, 20500),
       (nextval('televisions_seq'), 'OLED55C16LA', 'LG', 'LG OLED55C16LA', 989, 55, 100, 'OLED', 'ULTRA HD', true, true,
        true, true, true, false, 50000, 20500);


-- Remote Controllers
ALTER SEQUENCE remote_controllers_seq INCREMENT BY 1;
INSERT INTO remote_controllers(id, compatible_with, battery_type, name, brand, price, original_stock)
VALUES (nextval('remote_controllers_seq'), 'Philips', 'AAA', 'PH10103', 'Philips', 22.99, 2),
       (nextval('remote_controllers_seq'), 'LG', 'AAA', 'LG1130', 'LG', 28.39, 8);


-- CI-Modules
ALTER SEQUENCE ci_modules_seq INCREMENT BY 1;
INSERT INTO ci_modules(id, name, type, price)
VALUES (nextval('ci_modules_seq'), 'universal CI-module', 'ZZXT300', 89.99);


-- Wall Brackets
INSERT INTO wall_brackets (id, size, adjustable, name, price)
VALUES (1001, '25X32', false, 'LG bracket', 32.23),
       (1002, '25X32/32X40', true, 'LG bracket', 32.23),
       (1003, '25X25', false, 'Philips bracket', 32.23),
       (1004, '25X32/32X40', true, 'Nikkei bracket', 32.23),
       (1005, '25X32', false, 'Nikkei bracket', 32.23);

INSERT INTO television_wall_brackets (television_id, wall_bracket_id)
VALUES (1, 1001),
       (1, 1002);

INSERT INTO users (username, password, email, enabled)
VALUES ('mark', '$2a$12$NNX1923O9dUyRUlw/PM02e4x/nZ19zR25dJQjpbQxav/.0fw3ewWC', 'mark@test.nl', true),
       ('rowan', '$2a$12$kRHajL/pbSYaDYqCOzXn8OLRJljGSJVT65B3w4WRbNmNbHp3TGyx2', 'rowan@test.nl', true);

INSERT INTO authorities (username, authority)
VALUES ('rowan', 'ROLE_USER'),
       ('mark', 'ROLE_ADMIN');