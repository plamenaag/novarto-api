package com.plamena.novartoapi.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.plamena.novartoapi.entity.Customer;

import java.io.IOException;

public class CustomerSerializer extends JsonSerializer<Customer> {

    @Override
    public void serialize(Customer entity, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {

        jgen.writeStartObject();
        jgen.writeObjectField("id", entity.getId());
        jgen.writeObjectField("username", entity.getUsername());
        jgen.writeObjectField("firstName", entity.getFirstName());
        jgen.writeObjectField("lastName", entity.getLastName());

        if (entity.getAccount() != null && entity.getAccount().getId() != null) {
            jgen.writeObjectFieldStart("account");
            jgen.writeObjectField("id", entity.getAccount().getId());
            jgen.writeObjectField("subscription", entity.getAccount().getSubscription());
            jgen.writeEndObject();
        }

        jgen.writeEndObject();
    }

}