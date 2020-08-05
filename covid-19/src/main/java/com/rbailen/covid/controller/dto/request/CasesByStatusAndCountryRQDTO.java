package com.rbailen.covid.controller.dto.request;

import java.time.OffsetDateTime;
import com.rbailen.covid.type.StatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CasesByStatusAndCountryRQDTO {

  /** The country. */
  private String country;

  /** The status. */
  private StatusType status;

  /** The from. */
  private OffsetDateTime from;

  /** The to. */
  private OffsetDateTime to;

}
