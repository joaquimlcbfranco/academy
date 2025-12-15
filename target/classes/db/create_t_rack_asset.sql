create table public.t_rack_asset
(
    id        uuid default gen_random_uuid() not null
        primary key,
    asset_tag varchar(50)                    not null,
    rack_id   uuid                           not null
        references public.t_rack
);

alter table public.t_rack_asset
    owner to postgres;

