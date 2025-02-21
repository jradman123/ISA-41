package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CreateReservationDto {

   public LocalDateTime resStart;
   public LocalDateTime resEnd;
   public Integer numberOfPerson;
   public Double price;
   public String clientEmail;
   public String typeOfRes;
   public String objectId;

  

   private List<ResponseUtility> utilities;




}
