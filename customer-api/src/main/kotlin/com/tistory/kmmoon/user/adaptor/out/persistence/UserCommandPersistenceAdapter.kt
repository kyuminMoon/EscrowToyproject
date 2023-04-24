package com.tistory.kmmoon.order.adaptor.out.persistence

import com.tistory.kmmoon.user.application.port.out.CreateUserPort
import com.tistory.kmmoon.user.application.port.out.DeleteUserPort
import com.tistory.kmmoon.user.application.port.out.ModifyUserPort
import org.springframework.stereotype.Component

@Component
class UserCommandPersistenceAdapter: CreateUserPort, ModifyUserPort, DeleteUserPort {

}