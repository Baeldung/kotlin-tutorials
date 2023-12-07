CREATE TABLE IF NOT EXISTS company(
    id BIGINT PRIMARY KEY,
    name VARCHAR(200),
    address VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS aircraft(
    id BIGINT PRIMARY KEY,
    serial_number TEXT UNIQUE NOT NULL,
    producer_name TEXT,
    company_id BIGINT REFERENCES company(id)
);

CREATE TABLE IF NOT EXISTS flight(
    id BIGINT PRIMARY KEY,
    departure_at TIMESTAMP NOT NULL,
    arrival_at TIMESTAMP NOT NULL,
    departure_from TEXT NOT NULL,
    departure_to TEXT NOT NULL,
    aircraft_id BIGINT REFERENCES aircraft(id)
);

TRUNCATE TABLE flight, aircraft, company;

INSERT INTO company(id, name, address) VALUES(2, 'Airlines', 'UK');
INSERT INTO aircraft(id, serial_number, producer_name, company_id) VALUES(1, '123456', 'Factory', 2);
INSERT INTO flight(id, departure_at, arrival_at, departure_from, departure_to, aircraft_id) VALUES(1, CURRENT_DATE - 1, CURRENT_DATE, 'UK', 'US', 1);