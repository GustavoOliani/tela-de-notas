CREATE TABLE notes (
    id UUID PRIMARY KEY,
    site VARCHAR(100) NOT NULL,
    equipment VARCHAR(100) NOT NULL,
    variable VARCHAR(50) NOT NULL,
    timestamp TIMESTAMPTZ NOT NULL,
    author VARCHAR(100) NOT NULL,
    message TEXT NOT NULL
);