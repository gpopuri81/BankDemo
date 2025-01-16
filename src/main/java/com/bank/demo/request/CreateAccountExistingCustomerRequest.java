package com.bank.demo.request;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import jakarta.validation.constraints.*;

/**
 * CreateAccountExistingCustomerRequest
 */
@Validated
public class CreateAccountExistingCustomerRequest  implements CreateAccountRequest {
  @JsonProperty("customerId")

  private String customerId = null;

  @JsonProperty("balance")

  private Float balance = null;

  @JsonProperty("existingAccountNumber")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private String existingAccountNumber = null;


  public CreateAccountExistingCustomerRequest customerId(String customerId) { 

    this.customerId = customerId;
    return this;
  }

  /**
   * Get customerId
   * @return customerId
   **/
  
  @Schema(required = true, description = "")
  
  @NotNull
  public String getCustomerId() {  
    return customerId;
  }



  public void setCustomerId(String customerId) { 

    this.customerId = customerId;
  }

  public CreateAccountExistingCustomerRequest balance(Float balance) { 

    this.balance = balance;
    return this;
  }

  /**
   * Get balance
   * @return balance
   **/
  
  @Schema(required = true, description = "")
  
  @NotNull
  public Float getBalance() {  
    return balance;
  }



  public void setBalance(Float balance) { 

    this.balance = balance;
  }

  public CreateAccountExistingCustomerRequest existingAccountNumber(String existingAccountNumber) { 

    this.existingAccountNumber = existingAccountNumber;
    return this;
  }

  /**
   * Get existingAccountNumber
   * @return existingAccountNumber
   **/
  
  @Schema(description = "")
  
  public String getExistingAccountNumber() {  
    return existingAccountNumber;
  }



  public void setExistingAccountNumber(String existingAccountNumber) { 
    this.existingAccountNumber = existingAccountNumber;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateAccountExistingCustomerRequest createAccountExistingCustomerRequest = (CreateAccountExistingCustomerRequest) o;
    return Objects.equals(this.customerId, createAccountExistingCustomerRequest.customerId) &&
        Objects.equals(this.balance, createAccountExistingCustomerRequest.balance) &&
        Objects.equals(this.existingAccountNumber, createAccountExistingCustomerRequest.existingAccountNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerId, balance, existingAccountNumber);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateAccountExistingCustomerRequest {\n");
    
    sb.append("    customerId: ").append(toIndentedString(customerId)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("    existingAccountNumber: ").append(toIndentedString(existingAccountNumber)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
