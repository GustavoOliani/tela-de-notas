COPY notes(id, site, equipment, variable, timestamp, author, message)
FROM '/var/lib/postgresql/data_seeds/notes.csv' 
DELIMITER ',' 
CSV HEADER;