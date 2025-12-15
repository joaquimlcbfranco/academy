create table public.t_rack
(
    id               uuid      default gen_random_uuid() not null
        primary key,
    serial_number    varchar(50)                         not null
        unique,
    team_id          uuid      default gen_random_uuid() not null,
    created_at       timestamp default CURRENT_TIMESTAMP,
    default_location varchar(10),
    status           varchar(20)
        constraint t_rack_status_check
            check ((status)::text = ANY
        ((ARRAY ['AVAILABLE':: character varying, 'BOOKED':: character varying, 'UNAVAILABLE':: character varying])::text[])
) ,
    modified_at      timestamp default now()
);

alter table public.t_rack
    owner to postgres;

