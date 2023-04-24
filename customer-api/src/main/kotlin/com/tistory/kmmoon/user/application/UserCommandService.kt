package com.tistory.kmmoon.user.application

import com.tistory.kmmoon.user.UserEntity
import com.tistory.kmmoon.user.UserRepository
import com.tistory.kmmoon.user.application.port.`in`.UserCreateUseCase
import com.tistory.kmmoon.user.application.port.`in`.UserDeleteUseCase
import com.tistory.kmmoon.user.application.port.`in`.UserModifyUseCase
import com.tistory.kmmoon.user.application.port.`in`.UserQueryUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserCommandService: UserCreateUseCase, UserModifyUseCase, UserDeleteUseCase {

}