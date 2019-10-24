package com.plamena.novartoapi.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.plamena.novartoapi.entity.Customer;
import com.plamena.novartoapi.entity.Invoice;

import java.io.IOException;

public class InvoiceSerializer extends JsonSerializer<Invoice> {

    @Override
    public void serialize(Invoice entity, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {

        jgen.writeStartObject();
        jgen.writeObjectField("id", entity.getId());

        if(entity.getDate()!=null){
            jgen.writeObjectField("date", entity.getDate().getTime());
        }else{
            jgen.writeNullField("date");
        }

        jgen.writeObjectField("paidAmount", entity.getPaidAmount());



        if (entity.getAccount()!= null && entity.getAccount().getId()!= null) {
            jgen.writeObjectFieldStart("account");
            jgen.writeObjectField("id", entity.getAccount().getId());
            jgen.writeEndObject();
        }

        if (entity.getPayment()!= null && entity.getPayment().getId()!= null) {
            jgen.writeObjectFieldStart("payment");
            jgen.writeObjectField("id", entity.getPayment().getId());
            jgen.writeEndObject();
        }


        jgen.writeEndObject();
    }

}