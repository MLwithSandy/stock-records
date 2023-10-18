package com.banking.mlwithsandy.stockrecords.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Principal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Audit {
  private String creationUserId;
  private LocalDateTime creationDateTime;
  private String updateUserId;
  private LocalDateTime updateDateTime;

  public Audit () {
    this(LocalDateTime.now(), null);
  }

  public Audit (Principal principal) {
    this(LocalDateTime.now(), principal);
  }

  public Audit (LocalDateTime dateTime) {
    this(dateTime, null);
  }

  public Audit(LocalDateTime dateTime, Principal principal) {
    this.creationDateTime = dateTime;
    this.updateDateTime = dateTime;
    if (principal != null && principal.getName() != null && !principal.getName().isEmpty()) {
      this.creationUserId = principal.getName();
      this.updateUserId = principal.getName();
    } else {
      this.creationUserId = "SYSTEM";
      this.updateUserId = "SYSTEM";
    }
  }
}
