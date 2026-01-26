package dev.nihilncunia.fantasy_builder.domain.common.type;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 리스트 응답 타입
 * 
 * <p>
 * 모든 목록 응답에 사용되는 클래스입니다.
 * 페이징 정보와 데이터 배열을 포함합니다.
 * 
 * <p>
 * 페이징이 있는 경우: 모든 필드를 채웁니다.
 * 페이징이 없는 경우: list와 totalCnt만 채우고, 나머지 페이징 필드는 null입니다.
 * 
 * @param <T> 리스트 항목 타입
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListType<T> {
  
  private List<T> list;           // 데이터 배열 (필수)
  private Long totalCnt;          // 모든 아이템 개수 (필수, 페이징 여부와 무관하게 항상 채움)
  private Integer pageSize;       // 페이지 당 아이템 개수 (페이징 시 필수, 비페이징 시 null)
  private Integer page;           // 현재 페이지 (1-based, 페이징 시 필수, 비페이징 시 null)
  private Integer totalPage;      // 전체 페이지 수 (페이징 시 필수, 비페이징 시 null)
  private Boolean isFirst;        // 첫 페이지 여부 (페이징 시 필수, 비페이징 시 null)
  private Boolean isLast;         // 마지막 페이지 여부 (페이징 시 필수, 비페이징 시 null)
}
