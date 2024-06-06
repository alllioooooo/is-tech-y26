INSERT INTO roles (name) VALUES ('ADMIN'), ('USER');

INSERT INTO users (username, password, owner_id) VALUES
                                                     ('EMINBEGIN', 'jopa', (SELECT id FROM owners WHERE name = 'Эмин' AND surname = 'Керимов')),
                                                     ('stambolcan', 'telegramtelebotovich', (SELECT id FROM owners WHERE name = 'Тигран' AND surname = 'Стамболцян')),
                                                     ('LiamHamster', 'fordtranzitlover', (SELECT id FROM owners WHERE name = 'Богдан' AND surname = 'Галка')),
                                                     ('mishaganin', '239am', (SELECT id FROM owners WHERE name = 'Михаил' AND surname = 'Ганин'));

-- Назначение роли администратора
INSERT INTO users_roles (user_id, role_id) VALUES
                                               ((SELECT id FROM users WHERE username = 'EMINBEGIN'), (SELECT id FROM roles WHERE name = 'ADMIN')),
                                               ((SELECT id FROM users WHERE username = 'stambolcan'), (SELECT id FROM roles WHERE name = 'ADMIN'));

-- Назначение роли пользователя
INSERT INTO users_roles (user_id, role_id) VALUES
                                               ((SELECT id FROM users WHERE username = 'LiamHamster'), (SELECT id FROM roles WHERE name = 'USER')),
                                               ((SELECT id FROM users WHERE username = 'mishaganin'), (SELECT id FROM roles WHERE name = 'USER'));
