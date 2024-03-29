CREATE DATABASE expedientes_origen_database;

/*CREATE TABLE IF NOT EXISTS usuarios(
                                       identificador BIGSERIAL NOT NULL ,
                                       email VARCHAR NOT NULL UNIQUE ,
                                       clave_acceso VARCHAR NOT NULL,
                                       nombre_usuario VARCHAR NOT NULL UNIQUE,
                                       CONSTRAINT PK_USUARIOS
                                           PRIMARY KEY(identificador)
);

CREATE TABLE IF NOT EXISTS roles(
                                    identificador BIGSERIAL NOT NULL,
                                    nivel_acceso VARCHAR NOT NULL UNIQUE,
                                    CONSTRAINT PK_ROLES
                                        PRIMARY KEY(identificador)
);

CREATE TABLE IF NOT EXISTS roles_usuario(
                                            usuario_identificador BIGINT NOT NULL,
                                            rol_identificador BIGINT NOT NULL,
                                            CONSTRAINT FK_USUARIOS
                                                FOREIGN KEY(usuario_identificador) REFERENCES usuarios(identificador),
                                            CONSTRAINT FK_ROLES
                                                FOREIGN KEY(rol_identificador) REFERENCES roles(identificador)
);*/

/*CREATE TABLE IF NOT EXISTS entidades(
                                        identificador INTEGER NOT NULL UNIQUE,
                                        nombre_entidad VARCHAR NOT NULL UNIQUE,
                                        CONSTRAINT PK_ENTIDADES
                                            PRIMARY KEY (identificador)

);

CREATE TABLE IF NOT EXISTS permisos_roles(
                                             identificador_rol BIGINT NOT NULL,
                                             identificador_entidad INTEGER NOT NULL,
                                             accion_permitida SMALLINT NOT NULL,
                                             CONSTRAINT FK_ROLES
                                                 FOREIGN KEY(identificador_rol) REFERENCES roles(identificador),
                                             CONSTRAINT FK_ENTIDADES
                                                 FOREIGN KEY(identificador_entidad) REFERENCES entidades(identificador)
);*/

CREATE TABLE IF NOT EXISTS secciones(
                                        clave VARCHAR NOT NULL,
                                        nombre VARCHAR NOT NULL,
                                        descripcion VARCHAR NULL DEFAULT '',
                                        fecha_borrado timestamptz NULL DEFAULT NULL,
                                        active BOOLEAN NOT NULL DEFAULT TRUE,
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
                                                  fecha_borrado timestamptz NULL DEFAULT NULL,
                                                  observaciones VARCHAR NOT NULL,
                                                  active BOOLEAN NOT NULL DEFAULT TRUE,
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
                                                       google_drive_folder_id VARCHAR NOT NULL,
                                                       fecha_borrado timestamptz NULL DEFAULT NULL,
                                                       active BOOLEAN NOT NULL DEFAULT TRUE,
                                                       CONSTRAINT PK_UNIDADES_ADMINISTRATIVAS
                                                           PRIMARY KEY (clave),
                                                       CONSTRAINT FK_UNIDAD_PRINCIPAL
                                                           FOREIGN KEY (unidad_principal) REFERENCES unidades_administrativas(clave)
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
                                          google_drive_folder_id VARCHAR NOT NULL,
                                          CONSTRAINT PK_EXPEDIENTES
                                              PRIMARY KEY (serie_documental, unidad_administrativa_generadora, numero_expediente, fecha_apertura),
                                          CONSTRAINT FK_SERIE_DOCUMENTAL
                                              FOREIGN KEY (serie_documental) REFERENCES series_documentales(identificador),
                                          CONSTRAINT FK_UNIDADES_ADMINISTRATIVAS
                                              FOREIGN KEY (unidad_administrativa_generadora) REFERENCES unidades_administrativas(clave)
);

CREATE TABLE IF NOT EXISTS tipos_expediente (
                                                identificador SMALLINT NOT NULL,
                                                tipo_expediente VARCHAR(255),
                                                vigente BOOLEAN,
                                                descripcion VARCHAR(255),
                                                PRIMARY KEY (identificador)
);

CREATE TABLE IF NOT EXISTS documentos(
                                         identificador BIGSERIAL NOT NULL,
                                         tipo_documento SMALLINT NOT NULL,
                                         nombre VARCHAR NOT NULL,
                                         url_web_view VARCHAR NULL,
                                         google_drive_file_id VARCHAR NOT NULL,
                                         fecha_creacion timestamptz NOT NULL DEFAULT now(),
                                         fecha_edicion timestamptz NOT NULL DEFAULT now(),
                                         CONSTRAINT PK_DOCUMENTOS PRIMARY KEY (identificador),
                                         CONSTRAINT FK_TIPO_DOCUMENTO
                                             FOREIGN KEY (tipo_documento) REFERENCES tipos_expediente(identificador)
);


CREATE TABLE tipos_documento (
                                 identificador SMALLINT NOT NULL PRIMARY KEY,
                                 tipo_documento VARCHAR(255),
                                 vigente BOOLEAN,
                                 descripcion VARCHAR(255)
);

/**
  Codigo Permisos

  1 Ver,
  2 Crear,
  3 Editar,
  4 Marcar como Eliminado,
  5 Eliminar desde BD

  */

CREATE TABLE IF NOT EXISTS legajos(
                                      serie_documental_expediente SMALLINT NOT NULL,
                                      unidad_administrativa_generadora_expediente VARCHAR NOT NULL,
                                      numero_expediente SMALLINT NOT NULL,
                                      fecha_apertura_expediente DATE NOT NULL,
                                      numero_legajo SMALLINT NOT NULL,
                                      numero_mueble VARCHAR NOT NULL,
                                      letra_estante VARCHAR NOT NULL,
                                      numero_pasillo VARCHAR NOT NULL,
                                      letra_bateria VARCHAR NOT NULL,
                                      google_drive_folder_id VARCHAR NOT NULL,
                                      CONSTRAINT PK_LEGAJOS
                                          PRIMARY KEY (serie_documental_expediente, unidad_administrativa_generadora_expediente, numero_expediente, fecha_apertura_expediente, numero_legajo),
                                      CONSTRAINT FK_EXPEPEDIENTES
                                          FOREIGN KEY (serie_documental_expediente, unidad_administrativa_generadora_expediente, numero_expediente, fecha_apertura_expediente)
                                              REFERENCES expedientes(serie_documental, unidad_administrativa_generadora, numero_expediente, fecha_apertura)
);

CREATE TABLE IF NOT EXISTS contenido_legajo(
                                               serie_documental_expediente SMALLINT NOT NULL,
                                               unidad_administrativa_generadora_expediente VARCHAR NOT NULL,
                                               numero_expediente SMALLINT NOT NULL,
                                               fecha_apertura_expediente DATE NOT NULL,
                                               numero_legajo SMALLINT NOT NULL,
                                               identificador_documento BIGINT NOT NULL,
                                               CONSTRAINT FK_LEGAJOS
                                                   FOREIGN KEY (serie_documental_expediente, unidad_administrativa_generadora_expediente, numero_expediente, fecha_apertura_expediente, numero_legajo)
                                                       REFERENCES legajos(serie_documental_expediente, unidad_administrativa_generadora_expediente, numero_expediente, fecha_apertura_expediente, numero_legajo),
                                               CONSTRAINT FK_DOCUMENTOS FOREIGN KEY (identificador_documento) REFERENCES documentos(identificador)

    );


CREATE TABLE IF NOT EXISTS roles
(
    role_id BIGSERIAL PRIMARY KEY,
    authority VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS users
(
    user_id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255),
    password VARCHAR(255),
    username VARCHAR(255),
    UNIQUE (email),
    UNIQUE (username)
    );

CREATE TABLE IF NOT EXISTS user_role_function
(
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    PRIMARY KEY (role_id, user_id),
    FOREIGN KEY (role_id)
    REFERENCES roles (role_id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    FOREIGN KEY (user_id)
    REFERENCES users (user_id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
    );


select * from users;

INSERT INTO roles (role_id, authority) VALUES (1, 'ADMIN');
INSERT INTO roles (role_id, authority) VALUES (2, 'USER');


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

INSERT INTO tipos_expediente (identificador, tipo_expediente, vigente, descripcion)
VALUES
    (1, 'Expediente Tipo 1', true, 'Descripción del Expediente Tipo 1'),
    (2, 'Expediente Tipo 2', true, 'Descripción del Expediente Tipo 2'),
    (3, 'Expediente Tipo 3', false, 'Descripción del Expediente Tipo 3');

INSERT INTO tipos_documento (identificador, tipo_documento, vigente, descripcion) VALUES
  (1, 'Tipo Documento 1', TRUE, 'Descripción Tipo Documento 1'),
  (2, 'Tipo Documento 2', TRUE, 'Descripción Tipo Documento 2'),
  (3, 'Tipo Documento 3', TRUE, 'Descripción Tipo Documento 3'),
  (4, 'Tipo Documento 4', TRUE, 'Descripción Tipo Documento 4'),
  (5, 'Tipo Documento 5', TRUE, 'Descripción Tipo Documento 5'),
  (6, 'Tipo Documento 6', TRUE, 'Descripción Tipo Documento 6'),
  (7, 'Tipo Documento 7', TRUE, 'Descripción Tipo Documento 7'),
  (8, 'Tipo Documento 8', TRUE, 'Descripción Tipo Documento 8'),
  (9, 'Tipo Documento 9', TRUE, 'Descripción Tipo Documento 9'),
  (10, 'Tipo Documento 10', TRUE, 'Descripción Tipo Documento 10');


INSERT INTO tipos_expediente (identificador, tipo_expediente, vigente, descripcion) VALUES
  (1, 'Tipo Expediente 1', TRUE, 'Descripción Tipo Expediente 1'),
  (2, 'Tipo Expediente 2', TRUE, 'Descripción Tipo Expediente 2'),
  (3, 'Tipo Expediente 3', TRUE, 'Descripción Tipo Expediente 3'),
  (4, 'Tipo Expediente 4', TRUE, 'Descripción Tipo Expediente 4'),
  (5, 'Tipo Expediente 5', TRUE, 'Descripción Tipo Expediente 5'),
  (6, 'Tipo Expediente 6', TRUE, 'Descripción Tipo Expediente 6'),
  (7, 'Tipo Expediente 7', TRUE, 'Descripción Tipo Expediente 7'),
  (8, 'Tipo Expediente 8', TRUE, 'Descripción Tipo Expediente 8'),
  (9, 'Tipo Expediente 9', TRUE, 'Descripción Tipo Expediente 9'),
  (10, 'Tipo Expediente 10', TRUE, 'Descripción Tipo Expediente 10');