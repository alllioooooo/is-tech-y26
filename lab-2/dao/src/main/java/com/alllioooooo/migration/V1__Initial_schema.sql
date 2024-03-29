CREATE TYPE cat_color AS ENUM ('black', 'white', 'gray', 'red', 'brown', 'cream');

CREATE TABLE IF NOT EXISTS  owners (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS cats (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    breed VARCHAR(255),
    color cat_color NOT NULL,
    owner_id INTEGER,
    FOREIGN KEY (owner_id) REFERENCES owners(id)
);

CREATE TABLE IF NOT EXISTS cat_friends (
    cat_id INTEGER,
    friend_cat_id INTEGER,
    PRIMARY KEY (cat_id, friend_cat_id),
    FOREIGN KEY (cat_id) REFERENCES cats(id),
    FOREIGN KEY (friend_cat_id) REFERENCES cats(id)
);