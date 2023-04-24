package com.tistory.kmmoon.order.adaptor.out.persistence

import com.tistory.kmmoon.user.application.port.out.CreateUserPort
import com.tistory.kmmoon.user.application.port.out.DeleteUserPort
import com.tistory.kmmoon.user.application.port.out.ModifyUserPort

class UserCommandPersistenceAdapter: CreateUserPort, ModifyUserPort, DeleteUserPort {

}