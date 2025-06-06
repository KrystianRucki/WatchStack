CREATE TABLE custom_lists
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    user_id     BIGINT                                  NOT NULL,
    name        VARCHAR(255)                            NOT NULL,
    description VARCHAR(255),
    CONSTRAINT pk_custom_lists PRIMARY KEY (id)
);

CREATE TABLE list_items
(
    id             BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    custom_list_id BIGINT                                  NOT NULL,
    movie_id       BIGINT                                  NOT NULL,
    position       INTEGER,
    CONSTRAINT pk_list_items PRIMARY KEY (id)
);

CREATE TABLE movies
(
    id    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    title VARCHAR(255)                            NOT NULL,
    year  INTEGER,
    CONSTRAINT pk_movies PRIMARY KEY (id)
);

CREATE TABLE users
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    username VARCHAR(255)                            NOT NULL,
    email    VARCHAR(255)                            NOT NULL,
    password VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE watched_entries
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    user_id    BIGINT                                  NOT NULL,
    movie_id   BIGINT                                  NOT NULL,
    watched_at TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
    rating     INTEGER                                 NOT NULL,
    comment    TEXT,
    CONSTRAINT pk_watched_entries PRIMARY KEY (id)
);

CREATE TABLE watchlist_items
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    user_id  BIGINT                                  NOT NULL,
    priority VARCHAR(255)                            NOT NULL,
    movie_id BIGINT                                  NOT NULL,
    added_at TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
    CONSTRAINT pk_watchlist_items PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE users
    ADD CONSTRAINT uc_users_username UNIQUE (username);

ALTER TABLE custom_lists
    ADD CONSTRAINT FK_CUSTOM_LISTS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE list_items
    ADD CONSTRAINT FK_LIST_ITEMS_ON_CUSTOM_LIST FOREIGN KEY (custom_list_id) REFERENCES custom_lists (id);

ALTER TABLE list_items
    ADD CONSTRAINT FK_LIST_ITEMS_ON_MOVIE FOREIGN KEY (movie_id) REFERENCES movies (id);

ALTER TABLE watched_entries
    ADD CONSTRAINT FK_WATCHED_ENTRIES_ON_MOVIE FOREIGN KEY (movie_id) REFERENCES movies (id);

ALTER TABLE watched_entries
    ADD CONSTRAINT FK_WATCHED_ENTRIES_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE watchlist_items
    ADD CONSTRAINT FK_WATCHLIST_ITEMS_ON_MOVIE FOREIGN KEY (movie_id) REFERENCES movies (id);

ALTER TABLE watchlist_items
    ADD CONSTRAINT FK_WATCHLIST_ITEMS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);