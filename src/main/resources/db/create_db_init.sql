
-- create new table 't_team'

CREATE SEQUENCE IF NOT EXISTS t_team_seq;
CREATE TABLE t_team
(
    id           BIGINT PRIMARY KEY DEFAULT NEXTVAL('t_team_seq'),
    name             varchar(20) NOT NULL,
    product          varchar(20) NOT NULL,
    created_at       timestamp NOT NULL,
    modified_at      timestamp NOT NULL,
    default_location varchar(50) NOT NULL
);

-- create new table 't_rack'

CREATE DOMAIN status_domain as varchar(20)
    CONSTRAINT check_status check (VALUE IN ('AVAILABLE', 'BOOKED', 'UNAVAILABLE'));

CREATE SEQUENCE IF NOT EXISTS t_rack_seq;
CREATE TABLE t_rack
(
    id           BIGINT PRIMARY KEY DEFAULT NEXTVAL('t_rack_seq'),
    serial_number    varchar(20) NOT NULL UNIQUE,
    team_id          int         NOT NULL,
    default_location varchar(50),
    created_at       timestamp NOT NULL,
    modified_at      timestamp NOT NULL,
    status           status_domain,
    FOREIGN KEY (team_id) REFERENCES t_team (id)
);

-- create new table 't_rack_asset'

CREATE SEQUENCE IF NOT EXISTS t_rack_asset_seq;
CREATE TABLE t_rack_asset
(
    id           BIGINT PRIMARY KEY DEFAULT NEXTVAL('t_rack_asset_seq'),
    asset_tag varchar(20) NOT NULL,
    rack_id   int         NOT NULL,
    FOREIGN KEY (rack_id) REFERENCES t_rack (id)
);


-- create new table 't_team_member'

CREATE SEQUENCE IF NOT EXISTS t_team_member_seq;
CREATE TABLE t_team_member
(
    id           BIGINT PRIMARY KEY DEFAULT NEXTVAL('t_team_member_seq'),
    team_id     int         NOT NULL,
    ctw_id      varchar(10) NOT NULL,
    name        varchar(20) NOT NULL,
    created_at  timestamp NOT NULL,
    modified_at timestamp NOT NULL,
    FOREIGN KEY (team_id) REFERENCES t_team (id)
);

-- create new table 't_booking'

CREATE SEQUENCE IF NOT EXISTS t_booking_seq;
CREATE TABLE t_booking
(
    id           BIGINT PRIMARY KEY DEFAULT NEXTVAL('t_booking_seq'),
    rack_id      int       NOT NULL,
    requester_id int       NOT NULL,
    book_from    timestamp NOT NULL,
    book_to      timestamp NOT NULL,
    created_at   timestamp NOT NULL,
    modified_at  timestamp NOT NULL,
    FOREIGN KEY (rack_id) REFERENCES t_rack (id),
    FOREIGN KEY (requester_id) REFERENCES t_team_member (id)
);



