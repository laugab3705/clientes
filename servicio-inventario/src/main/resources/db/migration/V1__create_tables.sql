CREATE TABLE productos (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    cantidad_disponible INT NOT NULL DEFAULT 0
);

CREATE TABLE reservas (
    id BIGSERIAL PRIMARY KEY,
    producto_id BIGINT NOT NULL,
    pedido_id BIGINT NOT NULL,
    cantidad INT NOT NULL,
    estado VARCHAR(50) NOT NULL,
    CONSTRAINT fk_reservas_productos FOREIGN KEY (producto_id) REFERENCES productos(id)
);
