package com.sistema_expedientes.expediente.enumerates.converters;

import com.sistema_expedientes.expediente.enumerates.FormatoExpediente;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class FormatoExpedienteConverter implements AttributeConverter<FormatoExpediente, Byte> {
    @Override
    public Byte convertToDatabaseColumn(FormatoExpediente attribute) {
        return attribute == null ? null : attribute.getCodigo();
    }

    @Override
    public FormatoExpediente convertToEntityAttribute(Byte dbData) {
        return FormatoExpediente.valueOf(dbData);
    }
}
