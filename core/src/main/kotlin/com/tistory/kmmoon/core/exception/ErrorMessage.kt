package com.tistory.kmmoon.core.exception

enum class ErrorMessage(val message: String) {
  SUCCESS("성공했습니다."),
  REQUIRED_SEARCH_DATE("날짜 조회 조건은 필수입니다."),
  ALREADY_IN_USE("중복된 값이 존재합니다."),
  LOGIN_FAILED("로그인에 실패했습니다. 입력 정보를 확인해주세요.")

}