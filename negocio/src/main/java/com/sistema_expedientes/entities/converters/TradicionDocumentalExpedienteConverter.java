package com.sistema_expedientes.entities.converters;

import com.sistema_expedientes.entities.enumerates.TradicionDocumentalExpediente;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class TradicionDocumentalExpedienteConverter implements AttributeConverter<TradicionDocumentalExpediente, Byte> {

    @Override
    public Byte convertToDatabaseColumn(TradicionDocumentalExpediente attribute) {
        return attribute == null ? null : attribute.getCodigo();
    }

    @Override
    public TradicionDocumentalExpediente convertToEntityAttribute(Byte dbData) {
        return TradicionDocumentalExpediente.valueOf(dbData);
    }
}
