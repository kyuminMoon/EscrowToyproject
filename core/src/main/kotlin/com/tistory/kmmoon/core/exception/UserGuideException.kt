package com.tistory.kmmoon.core.exception


class UserGuideException : RuntimeException {
  constructor(errorMessage: ErrorMessage) : super(errorMessage.message)
  constructor(errorMessage: String?) : super(errorMessage)
}