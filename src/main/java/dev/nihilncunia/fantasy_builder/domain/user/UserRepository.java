package dev.nihilncunia.fantasy_builder.domain.user;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 사용자 Repository
 * 
 * <p>
 * 사용자 엔티티에 대한 데이터 접근을 담당합니다.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

  /**
   * 이메일로 사용자 조회
   * 
   * @param userEml 이메일
   * @return Optional<UserEntity>
   */
  Optional<UserEntity> findByUserEml(String userEml);

  /**
   * 이메일과 삭제 여부로 사용자 조회
   * 
   * @param userEml 이메일
   * @param delYn 삭제 여부
   * @return Optional<UserEntity>
   */
  Optional<UserEntity> findByUserEmlAndDelYn(String userEml, String delYn);

  /**
   * 사용자명으로 검색 (삭제되지 않은 것만)
   * 
   * @param userNm 사용자명 (부분 일치)
   * @param delYn 삭제 여부
   * @return 사용자 목록
   */
  Page<UserEntity> findByUserNmContainingAndDelYn(String userNm, String delYn, Pageable pageable);

  /**
   * 삭제 여부로 검색
   * 
   * @param delYn 삭제 여부
   * @param pageable 페이징 정보
   * @return 사용자 페이지
   */
  Page<UserEntity> findByDelYn(String delYn, Pageable pageable);
}
