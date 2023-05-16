create table user_progress
(
    username       varchar(255)  not null,
    languageID     varchar(255)  not null,
    language_level int default 0 null,
    language_exp   int default 0 null,
    primary key (username, languageID),
    constraint user_progress_relation_1
        foreign key (username) references users (username)
            on update cascade on delete cascade,
    constraint user_progress_relation_2
        foreign key (languageID) references languages (languageID)
            on update cascade on delete cascade
);

