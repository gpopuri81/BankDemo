package com.bank.demo.request;

import java.time.LocalDate;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * CreateAccountNewCustomerRequest
 */
@Validated
public class CreateAccountNewCustomerRequest  implements CreateAccountRequest {
  @JsonProperty("firstName")

  private String firstName = null;

  @JsonProperty("lastName")

  private String lastName = null;

  @JsonProperty("dateOfBirth")

  private LocalDate dateOfBirth = null;

  @JsonProperty("ssn")

  private String ssn = null;

  @JsonProperty("balance")

  private Float balance = null;


  public CreateAccountNewCustomerRequest firstName(String firstName) { 

    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   * @return firstName
   **/
  
  @Schema(required = true, description = "")
  
  @NotNull
  public String getFirstName() {  
    return firstName;
  }



  public void setFirstName(String firstName) { 

    this.firstName = firstName;
  }

  public CreateAccountNewCustomerRequest lastName(String lastName) { 

    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   * @return lastName
   **/
  
  @Schema(required = true, description = "")
  
  @NotNull
  public String getLastName() {  
    return lastName;
  }



  public void setLastName(String lastName) { 

    this.lastName = lastName;
  }

  public CreateAccountNewCustomerRequest dateOfBirth(LocalDate dateOfBirth) { 

    this.dateOfBirth = dateOfBirth;
    return this;
  }

  /**
   * Get dateOfBirth
   * @return dateOfBirth
   **/
  
  @Schema(required = true, description = "")
  
@Valid
  @NotNull
  public LocalDate getDateOfBirth() {  
    return dateOfBirth;
  }



  public void setDateOfBirth(LocalDate dateOfBirth) { 

    this.dateOfBirth = dateOfBirth;
  }

  public CreateAccountNewCustomerRequest ssn(String ssn) { 

    this.ssn = ssn;
    return this;
  }

  /**
   * Get ssn
   * @return ssn
   **/
  
  @Schema(required = true, description = "")
  
  @NotNull
  public String getSsn() {  
    return ssn;
  }



  public void setSsn(String ssn) { 

    this.ssn = ssn;
  }

  public CreateAccountNewCustomerRequest balance(Float balance) { 

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

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateAccountNewCustomerRequest createAccountNewCustomerRequest = (CreateAccountNewCustomerRequest) o;
    return Objects.equals(this.firstName, createAccountNewCustomerRequest.firstName) &&
        Objects.equals(this.lastName, createAccountNewCustomerRequest.lastName) &&
        Objects.equals(this.dateOfBirth, createAccountNewCustomerRequest.dateOfBirth) &&
        Objects.equals(this.ssn, createAccountNewCustomerRequest.ssn) &&
        Objects.equals(this.balance, createAccountNewCustomerRequest.balance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, dateOfBirth, ssn, balance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateAccountNewCustomerRequest {\n");
    
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    dateOfBirth: ").append(toIndentedString(dateOfBirth)).append("\n");
    sb.append("    ssn: ").append(toIndentedString(ssn)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
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
