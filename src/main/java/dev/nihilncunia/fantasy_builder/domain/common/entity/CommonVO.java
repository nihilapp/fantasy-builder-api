package dev.nihilncunia.fantasy_builder.domain.common.entity;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommonVO {

  protected String useYn;
  protected String shrnYn;
  protected String delYn;
  protected Long crtNo;
  protected LocalDateTime crtDt;
  protected Long updtNo;
  protected LocalDateTime updtDt;
  protected Long delNo;
  protected LocalDateTime delDt;

}
