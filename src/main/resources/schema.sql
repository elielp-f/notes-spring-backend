CREATE TABLE IF NOT EXISTS author
(
    firstName varchar not null,
    lastName varchar not null,
    email varchar not null unique,
    hash varchar not null,
    id uuid default gen_random_uuid() primary key
);

CREATE TABLE IF NOT EXISTS note
(
    title varchar,
    content varchar,
    author_id uuid unique references author(id),
    id uuid default gen_random_uuid() primary key
);
