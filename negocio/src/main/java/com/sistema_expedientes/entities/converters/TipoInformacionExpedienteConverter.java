package com.sistema_expedientes.entities.converters;

import com.sistema_expedientes.entities.enumerates.TipoInformacionExpediente;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class TipoInformacionExpedienteConverter implements AttributeConverter<TipoInformacionExpediente, Byte> {


    @Override
    public Byte convertToDatabaseColumn(TipoInformacionExpediente attribute) {
        return attribute == null ? null : attribute.getCodigo();
    }

    @Override
    public TipoInformacionExpediente convertToEntityAttribute(Byte dbData) {
        return TipoInformacionExpediente.valueOf(dbData);
    }
}
