CREATE DATABASE expedientes_origen_database;

SELECT * FROM unidades_administrativas;

CREATE TABLE IF NOT EXISTS series_documentales(
    id NUMERIC(3,0) NOT NULL,
    nombre VARCHAR UNIQUE NOT NULL,
    serie VARCHAR UNIQUE NOT NULL,
    serie_padre NUMERIC(3,0) NULL DEFAULT NULL,
    valor_documental_legal BIT NOT NULL,
    valor_documental_administrativo BIT NOT NULL,
    valor_documental_contable BIT NOT NULL,
    en_tramite NUMERIC(2,0) NOT NULL,
    en_concentracion NUMERIC(2,0) NOT NULL,
    procedimiento_final NUMERIC(2,0) NOT NULL,
    observaciones TEXT NULL,
    CONSTRAINT PK_SERIE_DOCUMENTAL
        PRIMARY KEY(id),
    CONSTRAINT FK_SERIE_SUBSERIE
        FOREIGN KEY(serie_padre)
            REFERENCES series_documentales(id)
            ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS unidades_administrativas(
    id DECIMAL(2) NOT NULL,
    clave VARCHAR(10) NOT NULL,
    nombre VARCHAR NOT NULL,
    CONSTRAINT PK_UNIDAD_ADMINISTRATIVA
        PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS expedientes(
    identificador_numerico SMALLINT NOT NULL,
    periodo_apertura NUMERIC(4,0) NOT NULL,
    unidad_administrativa NUMERIC(3,0) NOT NULL,
    serie_documental NUMERIC(3,0) NOT NULL,
    periodo_cierre NUMERIC(3,0) NULL DEFAULT NULL,
    CONSTRAINT PK_EXPEDIENTES
          PRIMARY KEY(identificador_numerico, periodo_apertura, unidad_administrativa, serie_documental),
    CONSTRAINT FK_UNIDAD_ADMINISTRATIVA
          FOREIGN KEY(unidad_administrativa) REFERENCES unidades_administrativas(id),
    CONSTRAINT FK_SERIE_DOCUMENTAL
          FOREIGN KEY(serie_documental) REFERENCES series_documentales(id)
);

CREATE TABLE IF NOT EXISTS documentos(
    id BIGSERIAL NOT NULL,
    nombre VARCHAR NOT NULL,
    hojas DECIMAL(3,0) NOT NULL,
    CONSTRAINT PK_DOCUMENTOS
        PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS legajos(
    identificador_expediente NUMERIC(5,0) NOT NULL,
    periodo_apertura NUMERIC(4,0) NOT NULL,
    unidad_administrativa_expediente NUMERIC(3,0) NOT NULL,
    serie_documental_expediente NUMERIC(3,0) NOT NULL,
    identificador_documento INTEGER NOT NULL,
    legajo NUMERIC(2,0) NOT NULL DEFAULT 1,
    CONSTRAINT FK_EXPEDIENTES
        FOREIGN KEY(identificador_expediente, periodo_apertura, unidad_administrativa_expediente, serie_documental_expediente)
            REFERENCES expedientes(identificador_numerico, periodo_apertura, unidad_administrativa, serie_documental) MATCH FULL,
    CONSTRAINT FK_DOCUMENTOS
        FOREIGN KEY(identificador_documento) REFERENCES documentos(id)
);

GRANT SELECT, INSERT, UPDATE, DELETE ON series_documentales TO spring_app_usr;
GRANT SELECT, INSERT, UPDATE, DELETE ON unidades_administrativas TO spring_app_usr;
GRANT SELECT, INSERT, UPDATE, DELETE ON expedientes TO spring_app_usr;
GRANT SELECT, INSERT, UPDATE, DELETE ON documentos TO spring_app_usr;
GRANT SELECT, INSERT, UPDATE, DELETE ON legajos TO spring_app_usr;

INSERT INTO unidades_administrativas(id, clave, nombre) VALUES
        (1, 'DAAP', 'Dirección Adjunta de Administración de Proyectos'),
        (2, 'SRH', 'Subgerencia de Recursos Humanos'),
        (3, 'SGD', 'Subgerencia de Docencia')
