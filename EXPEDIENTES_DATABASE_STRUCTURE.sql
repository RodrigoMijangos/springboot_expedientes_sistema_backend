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
    FOREIGN KEY (unidad_administrativa_generadora) REFERENCES unidades_administrativas(clave)
    );

CREATE TABLE IF NOT EXISTS documentos(
                                         identificador BIGSERIAL NOT NULL,
                                         nombre VARCHAR NOT NULL,
                                         url VARCHAR NULL,
                                         fecha_creacion timestamptz NOT NULL DEFAULT now(),
    fecha_edicion timestamptz NOT NULL DEFAULT now(),
    CONSTRAINT PK_DOCUMENTOS PRIMARY KEY (identificador)
    );

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