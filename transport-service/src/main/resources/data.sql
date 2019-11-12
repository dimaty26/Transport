INSERT INTO transport (id, name, longitude, latitude, workload)
values ('9928698d-5eff-4ce7-9b25-40d41b6a64ed', 'LIAZ4450', 37.5880909, 55.7550373, 50),
       ('5fa0d0ae-462b-4e88-895a-7f78b344e93f', 'LIAZ4230', 37.62482643, 55.74296051, 40),
       ('d4d747c1-a2c3-40d2-95ba-ffc021bcbf13', 'LIAZ7250', 37.63941765, 55.75107653, 30)
on conflict (id) do nothing;

INSERT INTO card (card_id, password)
values ('9928698d-5eff-4ce7-9b25-40d41b6a64ed', 'pp'),
       ('5fa0d0ae-462b-4e88-895a-7f78b344e93f', 'pp'),
       ('d4d747c1-a2c3-40d2-95ba-ffc021bcbf13', 'pp')
on conflict (card_id) do nothing;

INSERT INTO bus_stop (bus_stop_id, bus_stop_name, latitude, longitude)
values ('9928698d-5eff-4ce7-9b25-40d41b6a64ed', 'FIRST', 37.6080909, 55.7500373),
       ('5fa0d0ae-462b-4e88-895a-7f78b344e93f', 'SECOND', 37.62482643, 55.76296051),
       ('d4d747c1-a2c3-40d2-95ba-ffc021bcbf13', 'THIRD', 37.65941765, 55.74107653)
on conflict (bus_stop_id) do nothing;

INSERT INTO validation (bus_stop_id, card_id)
values ('9928698d-5eff-4ce7-9b25-40d41b6a64ed', '9928698d-5eff-4ce7-9b25-40d41b6a64ed'),
       ('5fa0d0ae-462b-4e88-895a-7f78b344e93f', '5fa0d0ae-462b-4e88-895a-7f78b344e93f'),
       ('d4d747c1-a2c3-40d2-95ba-ffc021bcbf13', 'd4d747c1-a2c3-40d2-95ba-ffc021bcbf13');



