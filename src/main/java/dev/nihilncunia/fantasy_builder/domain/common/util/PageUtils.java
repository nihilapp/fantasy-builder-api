package dev.nihilncunia.fantasy_builder.domain.common.util;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import dev.nihilncunia.fantasy_builder.domain.common.type.ListType;

/**
 * 페이징 유틸리티
 * 
 * <p>
 * 페이징 관련 유틸리티 메서드를 제공합니다.
 * Page<T>와 List<T>를 ListType<T>로 변환하는 기능을 제공합니다.
 */
public class PageUtils {

  /**
   * 기본 페이지 크기
   */
  public static final int DEFAULT_PAGE_SIZE = 10;

  /**
   * Pageable 생성 (페이지 번호를 받아서 PageRequest 생성)
   * 
   * <p>
   * 프론트엔드에서 페이지 번호(1-based)를 받아서
   * 서버에서 Pageable(0-based)로 변환합니다.
   * 
   * @param page 페이지 번호 (1-based)
   * @return Pageable
   */
  public static Pageable createPageable(int page) {
    return PageRequest.of(page - 1, DEFAULT_PAGE_SIZE);
  }

  /**
   * Pageable 생성 (페이지 번호와 페이지 크기 지정)
   * 
   * @param page 페이지 번호 (1-based)
   * @param pageSize 페이지 크기
   * @return Pageable
   */
  public static Pageable createPageable(int page, int pageSize) {
    return PageRequest.of(page - 1, pageSize);
  }

  /**
   * Page<T>를 ListType<T>로 변환 (페이징 있는 경우)
   * 
   * <p>
   * 페이징 정보가 있는 경우 모든 필드를 채웁니다.
   * 
   * @param <T> 리스트 항목 타입
   * @param page Page 객체
   * @param currentPage 현재 페이지 번호 (1-based)
   * @return ListType<T>
   */
  public static <T> ListType<T> toListType(Page<T> page, int currentPage) {
    return new ListType<>(
        page.getContent(),
        page.getTotalElements(),
        page.getSize(),
        currentPage,
        page.getTotalPages(),
        page.isFirst(),
        page.isLast()
    );
  }

  /**
   * List<T>를 ListType<T>로 변환 (페이징 없는 경우)
   * 
   * <p>
   * 페이징 정보가 없는 경우 list와 totalCnt만 채우고,
   * 나머지 페이징 필드는 null로 설정합니다.
   * 
   * @param <T> 리스트 항목 타입
   * @param list 리스트
   * @return ListType<T>
   */
  public static <T> ListType<T> toListType(List<T> list) {
    return new ListType<>(
        list,
        (long) list.size(),  // totalCnt (반드시 채움)
        null,  // pageSize
        null,  // page
        null,  // totalPage
        null,  // isFirst
        null   // isLast
    );
  }
}
