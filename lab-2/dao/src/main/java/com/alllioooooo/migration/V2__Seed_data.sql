INSERT INTO owners (name, surname) VALUES
    ('Михаил', 'Манжелевский'),
    ('Мурад', 'Алиев'),
    ('Богдан', 'Галка'),
    ('Рафаэль', 'Рафиков'),
    ('Эмин', 'Керимов'),
    ('Михаил', 'Ганин'),
    ('Тигран', 'Стамболцян');

-- по хорошему нужен энамчик на породы но долго
INSERT INTO cats (name, birth_date, breed, color, owner_id) VALUES
    ('Джавик', '2023-02-25', 'Ориентальная', 'black', (SELECT id FROM owners WHERE name = 'Рафаэль' AND surname = 'Рафиков')),
    ('Эйваз', '2024-02-29', 'Бурма', 'brown', (SELECT id FROM owners WHERE name = 'Эмин' AND surname = 'Керимов')),
    ('Форд', '2014-08-02', NULL, 'red', (SELECT id FROM owners WHERE name = 'Богдан' AND surname = 'Галка')),
    ('Шарпик', '2023-09-01', 'Девон-рекс', 'white', (SELECT id FROM owners WHERE name = 'Михаил' AND surname = 'Манжелевский')),
    ('Транзит', '2018-09-03', NULL, 'gray', (SELECT id FROM owners WHERE name = 'Тигран' AND surname = 'Стамболцян'));

INSERT INTO cat_friends (cat_id, friend_cat_id) VALUES
    ((SELECT id FROM cats WHERE name = 'Джавик'), (SELECT id FROM cats WHERE name = 'Шарпик')),
    ((SELECT id FROM cats WHERE name = 'Форд'), (SELECT id FROM cats WHERE name = 'Транзит'));

INSERT INTO cat_friends (cat_id, friend_cat_id) VALUES
    ((SELECT id FROM cats WHERE name = 'Шарпик'), (SELECT id FROM cats WHERE name = 'Джавик')),
    ((SELECT id FROM cats WHERE name = 'Транзит'), (SELECT id FROM cats WHERE name = 'Форд'));
