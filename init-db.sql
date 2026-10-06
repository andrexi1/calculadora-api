CREATE TABLE personas (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50),
    apellido VARCHAR(50),
    ciudad VARCHAR(50),
    edad INTEGER
);

INSERT INTO personas (nombre, apellido, ciudad, edad) VALUES
('Paula', 'Álvarez', 'Bilbao', 29),
('Francisco', 'García', 'Málaga', 36),
('José', 'Torres', 'Valencia', 23),
('Valeria', 'Suárez', 'Jerez', 75),
('David', 'Pérez', 'Alcalá de Henares', 55);