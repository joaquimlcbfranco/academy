create table public.t_booking
(
    rack_id uuid
        references public.t_rack(id),
    requester_id uuid
        references public.t_team_member,
    book_from    timestamp not null,
    book_to      timestamp not null,
    created_at   timestamp default now(),
    modified_at  timestamp default now()
);

alter table public.t_booking
    owner to postgres;

