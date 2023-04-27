package com.tistory.kmmoon.user.application.port.out;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.tistory.kmmoon.user.UserEntity;

public interface QueryUserPort {
  @Nullable
  List<UserEntity> findAllBy();
}
