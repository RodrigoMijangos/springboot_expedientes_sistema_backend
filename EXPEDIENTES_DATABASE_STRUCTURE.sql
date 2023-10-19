CREATE DATABASE expedientes_origen_database;

CREATE TABLE IF NOT EXISTS secciones(
                                        clave VARCHAR NOT NULL,
                                        nombre VARCHAR NOT NULL,
                                        descripcion VARCHAR NULL DEFAULT '',
                                        CONSTRAINT PK_SECCIONES
                                        PRIMARY KEY (clave)
    );

CREATE TABLE IF NOT EXISTS tecnicas_seleccion(
                                                 identificador SMALLINT NOT NULL,
                                                 tecnica_seleccion VARCHAR NOT NULL,
                                                 descripcion VARCHAR NOT NULL DEFAULT '',
                                                 CONSTRAINT PK_TECNICAS_SELECCION
                                                 PRIMARY KEY (identificador)
    );

CREATE TABLE IF NOT EXISTS series_documentales(
                                                  identificador SMALLSERIAL NOT NULL,
                                                  serie_documental_padre SMALLINT NULL DEFAULT NULL,
                                                  clave VARCHAR(5) NOT NULL,
    seccion VARCHAR NULL DEFAULT NULL,
    nombre VARCHAR NOT NULL,
    valor_documental_administrativo BOOLEAN NOT NULL DEFAULT FALSE,
    valor_documental_legal BOOLEAN NOT NULL DEFAULT FALSE,
    valor_documental_contable BOOLEAN NOT NULL DEFAULT FALSE,
    periodos_conservacion_tramite SMALLINT NOT NULL,
    periodos_conservacion_concentracion SMALLINT NOT NULL,
    tecnica_seleccion SMALLINT NOT NULL,
    observaciones VARCHAR NOT NULL,
    CONSTRAINT PK_SERIES_DOCUMENTALES
    PRIMARY KEY (identificador),
    CONSTRAINT FK_SECCIONES
    FOREIGN KEY (seccion) REFERENCES secciones(clave),
    CONSTRAINT FK_TECNICAS_SELECCION
    FOREIGN KEY (tecnica_seleccion) REFERENCES tecnicas_seleccion(identificador),
    CONSTRAINT FK_SUBSERIE
    FOREIGN KEY (serie_documental_padre) REFERENCES series_documentales(identificador)
    );

CREATE TABLE IF NOT EXISTS unidades_administrativas(
    clave VARCHAR(5) NOT NULL,
    nombre VARCHAR NOT NULL,
    unidad_principal VARCHAR(5) NULL DEFAULT 0,
    piso VARCHAR(2) NOT NULL,
    extension_telefonica VARCHAR(5) NOT NULL,
    CONSTRAINT PK_UNIDADES_ADMINISTRATIVAS
    PRIMARY KEY (clave),
    CONSTRAINT FK_UNIDAD_PRINCIPAL
    FOREIGN KEY (unidad_principal) REFERENCES unidades_administrativas(clave)
    );

CREATE TABLE IF NOT EXISTS formatos_expediente(
                                                  identificador SMALLINT NOT NULL,
                                                  tipo_formato_expediente VARCHAR NOT NULL,
                                                  descripcion VARCHAR NULL DEFAULT NULL,
                                                  CONSTRAINT PK_FORMATOS_EXPEDIENTE
                                                  PRIMARY KEY (identificador)
    );

CREATE TABLE IF NOT EXISTS condiciones_acceso_expediente(
                                                            identificador SMALLINT NOT NULL,
                                                            condicion_acceso_expediente VARCHAR NOT NULL,
                                                            descripcion VARCHAR NULL DEFAULT NULL,
                                                            CONSTRAINT PK_CONDICIONES_ACCESO_EXPEDIENTE
                                                            PRIMARY KEY (identificador)
    );

CREATE TABLE IF NOT EXISTS tradiciones_documentales_expediente(
                                                                  identificador SMALLINT NOT NULL,
                                                                  tradicion_documental_expediente VARCHAR NOT NULL,
                                                                  descripcion VARCHAR NULL DEFAULT NULL,
                                                                  CONSTRAINT PK_TRADICION_DOCUMENTAL_EXPEDIENTE
                                                                  PRIMARY KEY (identificador)
    );

CREATE TABLE IF NOT EXISTS tipos_informacion_expediente(
                                                           identificador SMALLINT NOT NULL,
                                                           tipo_informacion_expediente VARCHAR NOT NULL,
                                                           descripcion VARCHAR NULL DEFAULT NULL,
                                                           CONSTRAINT PK_TIPOS_INFORMACION_EXPEDIENTE
                                                           PRIMARY KEY (identificador)
    );

CREATE TABLE IF NOT EXISTS expedientes(
                                          serie_documental SMALLINT NOT NULL,
                                          unidad_administrativa_generadora VARCHAR(5) NOT NULL,
    numero_expediente SMALLINT NOT NULL,
    fecha_apertura DATE NOT NULL DEFAULT CURRENT_DATE,
    periodo_cierre SMALLINT NULL DEFAULT NULL,
    asunto VARCHAR NOT NULL,
    tipo_expediente SMALLINT NULL,
    numero_proyecto VARCHAR NOT NULL,
    nombre_proyecto VARCHAR NOT NULL,
    acronimo_institucion VARCHAR NOT NULL,
    nombre_institucion VARCHAR NOT NULL,
    numero_contrato VARCHAR NOT NULL,
    cantidad_hojas SMALLINT NOT NULL,
    tipo_formato SMALLINT NOT NULL,
    condicion_acceso SMALLINT NOT NULL,
    tradicion_documental SMALLINT NOT NULL,
    tipo_informacion SMALLINT NOT NULL,
    CONSTRAINT PK_EXPEDIENTES
    PRIMARY KEY (serie_documental, unidad_administrativa_generadora, numero_expediente, fecha_apertura),
    CONSTRAINT FK_SERIE_DOCUMENTAL
    FOREIGN KEY (serie_documental) REFERENCES series_documentales(identificador),
    CONSTRAINT FK_UNIDADES_ADMINISTRATIVAS
    FOREIGN KEY (unidad_administrativa_generadora) REFERENCES unidades_administrativas(clave),
    CONSTRAINT FK_TIPOS_FORMATO
    FOREIGN KEY (tipo_formato) REFERENCES formatos_expediente(identificador),
    CONSTRAINT FK_CONDICIONES_ACCESO
    FOREIGN KEY (condicion_acceso) REFERENCES condiciones_acceso_expediente(identificador),
    CONSTRAINT FK_TRADICION_DOCUMENTAL
    FOREIGN KEY (tradicion_documental) REFERENCES tradiciones_documentales_expediente(identificador),
    CONSTRAINT FK_TIPO_INFORMACION
    FOREIGN KEY (tipo_informacion) REFERENCES tipos_informacion_expediente(identificador)
    );

GRANT SELECT, INSERT, UPDATE, DELETE ON secciones TO spring_app_usr;
GRANT SELECT, INSERT, UPDATE, DELETE ON tecnicas_seleccion TO spring_app_usr;
GRANT SELECT, INSERT, UPDATE, DELETE ON series_documentales TO spring_app_usr;
GRANT USAGE ON series_documentales_identificador_seq to spring_app_usr;
GRANT SELECT, INSERT, UPDATE, DELETE ON unidades_administrativas TO spring_app_usr;
GRANT SELECT, INSERT, UPDATE, DELETE ON formatos_expediente TO spring_app_usr;
GRANT SELECT, INSERT, UPDATE, DELETE ON condiciones_acceso_expediente TO spring_app_usr;
GRANT SELECT, INSERT, UPDATE, DELETE ON tradiciones_documentales_expediente TO spring_app_usr;
GRANT SELECT, INSERT, UPDATE, DELETE ON tipos_informacion_expediente TO spring_app_usr;
GRANT SELECT, INSERT, UPDATE, DELETE ON expedientes TO spring_app_usr;

INSERT INTO secciones(clave, nombre, descripcion) VALUES
                                                      ('1C', 'LEGISLACIÓN', ''), ('2C', 'ASUNTOS JURIDICOS', ''), ('1S', 'GOBIERNO', '');
INSERT INTO tecnicas_seleccion(identificador, tecnica_seleccion) VALUES
                                                                     (1, 'CONSERVACION'), (2, 'ELIMINACION'), (3, 'MUESTREO');
INSERT INTO series_documentales(
    serie_documental_padre, clave, seccion, nombre, valor_documental_administrativo,
    valor_documental_legal, valor_documental_contable, periodos_conservacion_tramite,
    periodos_conservacion_concentracion, tecnica_seleccion, observaciones) VALUES
                                                                               (null, '10', '1C', 'INSTRUMENTOS JURIDICOS', '1', '1', '0', 2, 5, 1, ''),
                                                                               (NULL ,'3', '2C', 'REGISTRO Y CERTIFICACION DE FIRMAS', '1', '1', '0', 2, 5, 2, ''),
                                                                               (NULL, '4', '2C', 'Registro y certificación de firmas acreditadas ante la dependencia ', '1', '1', '0', 2, 5, 1, ''),
                                                                               (NULL, '1', '1S', 'Órganos colegiados', '1', '0', '0', 2, 5, 3, '');

INSERT INTO series_documentales (serie_documental_padre, clave, seccion, nombre, valor_documental_administrativo, valor_documental_legal, valor_documental_contable, periodos_conservacion_tramite, periodos_conservacion_concentracion, tecnica_seleccion, observaciones) VALUES
    (4, '1', NULL, 'Comité Técnico', '1', '0', '0', 2, 5, 2, '');
