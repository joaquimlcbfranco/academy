create table public.t_team_member
(
    id          uuid        default gen_random_uuid() not null
        primary key,
    team_id     uuid
        references public.t_team,
    ctw_id      varchar(10) default gen_random_uuid()
        unique,
    name        text                                  not null,
    created_at  timestamp   default now(),
    modified_at timestamp   default now()
);

alter table public.t_team_member
    owner to postgres;
