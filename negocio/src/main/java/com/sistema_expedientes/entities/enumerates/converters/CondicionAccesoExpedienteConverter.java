package com.sistema_expedientes.entities.enumerates.converters;

import com.sistema_expedientes.entities.enumerates.CondicionAccesoExpediente;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CondicionAccesoExpedienteConverter implements AttributeConverter<CondicionAccesoExpediente, Byte> {
    @Override
    public Byte convertToDatabaseColumn(CondicionAccesoExpediente enumerador) {
        return enumerador == null ? null : enumerador.getCodigo();
    }

    @Override
    public CondicionAccesoExpediente convertToEntityAttribute(Byte codigo) {
        return CondicionAccesoExpediente.valueOf(codigo);
    }
}
