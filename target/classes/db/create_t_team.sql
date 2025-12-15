create table public.t_team
(
    id               uuid      default gen_random_uuid() not null
        primary key,
    name             text                                not null,
    product          text                                not null,
    created_at       timestamp default now(),
    modified_at      timestamp default now(),
    default_location text                                not null
);

alter table public.t_team
    owner to postgres;

