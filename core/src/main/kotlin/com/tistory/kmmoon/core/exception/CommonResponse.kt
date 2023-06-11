package com.tistory.kmmoon.core.exception

class CommonResponse<T> (
  val success: Boolean,
  val message: String?,
  val data: T?
) {
  companion object {
    fun <T> success(data: T) = CommonResponse(
      success = true,
      message = ErrorMessage.SUCCESS.message,
      data = data
    )

    fun <T> fail(errorMessage: String) = CommonResponse<T>(
      success = false,
      message = errorMessage,
      data = null
    )

  }

}