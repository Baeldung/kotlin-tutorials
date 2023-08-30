CREATE TABLE company(
    id BIGINT PRIMARY KEY,
    name VARCHAR(200),
    address VARCHAR(200)
);

CREATE TABLE aircraft(
    id BIGINT PRIMARY KEY,
    serial_number TEXT UNIQUE NOT NULL,
    producer_name TEXT,
    company_id BIGINT REFERENCES company(id)
);

CREATE TABLE flight(
    id BIGINT PRIMARY KEY,
    departure_at TIMESTAMP NOT NULL,
    arrival_at TIMESTAMP NOT NULL,
    departure_from TEXT NOT NULL,
    departure_to  TEXT NOT NULL,
    aircraft_id BIGINT REFERENCES aircraft(id)
);
