create table book_stats
(
    id                 bigint primary key generated always as identity,
    user_id            uuid unique not null,
    books_read         int         not null default 0,
    min_days_read_time int
);

create table comment_stats
(
    id              bigint primary key generated always as identity,
    user_id         uuid unique not null,
    comments_left   int         not null default 0,
    good_rate_count int         not null default 0,
    bad_rate_count  int         not null default 0
);

create table user_achievements
(
    id               bigint primary key generated always as identity,
    user_id          uuid                     not null,
    achievement_type varchar(256)             not null,
    achieved_at      timestamp with time zone not null
);

create index user_achievements_user_id_idx on user_achievements (user_id);
