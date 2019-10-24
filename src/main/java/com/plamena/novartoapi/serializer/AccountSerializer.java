package com.plamena.novartoapi.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.plamena.novartoapi.entity.Account;
import com.plamena.novartoapi.entity.Customer;

import java.io.IOException;

public class AccountSerializer extends JsonSerializer<Account> {

    @Override
    public void serialize(Account entity, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {

        jgen.writeStartObject();
        jgen.writeObjectField("id", entity.getId());
        if(entity.getCreateDate()!=null){
            jgen.writeObjectField("createDate", entity.getCreateDate().getTime());
        }else{
            jgen.writeNullField("createDate");
        }
        if(entity.getNextPaymentDate()!=null){
            jgen.writeObjectField("nextPaymentDate", entity.getNextPaymentDate().getTime());
        }else{
            jgen.writeNullField("nextPaymentDate");
        }

        jgen.writeObjectField("accountStatus", entity.getAccountStatus());

        jgen.writeObjectField("subscription", entity.getSubscription());

        if (entity.getCustomers() != null  && entity.getCustomers().size() > 0) {
            jgen.writeObjectField("customers", entity.getCustomers());
        }


        jgen.writeEndObject();
    }

}