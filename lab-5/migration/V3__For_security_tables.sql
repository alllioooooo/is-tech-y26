CREATE TABLE roles (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       owner_id BIGINT,
                       FOREIGN KEY (owner_id) REFERENCES owners(id)
);

CREATE TABLE users_roles (
                             user_id BIGINT,
                             role_id BIGINT,
                             FOREIGN KEY (user_id) REFERENCES users(id),
                             FOREIGN KEY (role_id) REFERENCES roles(id),
                             PRIMARY KEY (user_id, role_id)
);