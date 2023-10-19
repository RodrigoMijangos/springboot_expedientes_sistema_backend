package com.sistema_expedientes.entities.converters;

import com.sistema_expedientes.entities.enumerates.CondicionAccesoExpediente;
import jakarta.persistence.AttributeConverter;

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
