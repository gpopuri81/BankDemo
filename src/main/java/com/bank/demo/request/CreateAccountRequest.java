package com.bank.demo.request;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
/**
* AccountsBody
*/
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = CreateAccountExistingCustomerRequest.class, name = "CreateAccountExistingCustomerRequest"),
  @JsonSubTypes.Type(value = CreateAccountNewCustomerRequest.class, name = "CreateAccountNewCustomerRequest")
})
public interface CreateAccountRequest {

}
