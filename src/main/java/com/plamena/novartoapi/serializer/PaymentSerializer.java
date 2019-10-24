package com.plamena.novartoapi.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.plamena.novartoapi.entity.Invoice;
import com.plamena.novartoapi.entity.Payment;

import java.io.IOException;

public class PaymentSerializer extends JsonSerializer<Payment> {

    @Override
    public void serialize(Payment entity, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {

        jgen.writeStartObject();
        jgen.writeObjectField("id", entity.getId());
        jgen.writeObjectField("price", entity.getPrice());
        jgen.writeObjectField("subscription", entity.getSubscription());

        if (entity.getDate() != null) {
            jgen.writeObjectField("date", entity.getDate().getTime());
        } else {
            jgen.writeNullField("date");
        }

        if (entity.getInvoices() != null && entity.getInvoices().size() > 0) {
            jgen.writeArrayFieldStart("invoices");
            for (Invoice invoice : entity.getInvoices()) {
                jgen.writeStartObject();
                jgen.writeObjectField("id", invoice.getId());
                jgen.writeObjectField("paidAmount", invoice.getPaidAmount());
                if (invoice.getDate() != null) {
                    jgen.writeObjectField("date", invoice.getDate().getTime());
                } else {
                    jgen.writeNullField("date");
                }
                jgen.writeEndObject();
            }
            jgen.writeEndArray();
        }

        if(entity.getAccount()!=null && entity.getAccount().getId()!=null){
            jgen.writeObjectFieldStart("account");
                jgen.writeObjectField("id", entity.getAccount().getId());
            jgen.writeEndObject();
        }

        jgen.writeEndObject();
    }

}