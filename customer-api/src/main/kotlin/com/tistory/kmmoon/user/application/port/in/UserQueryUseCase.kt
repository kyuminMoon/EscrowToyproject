package com.tistory.kmmoon.user.application.port.in;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.tistory.kmmoon.user.UserEntity;

public interface UserQueryUseCase {
  @Nullable
  List<UserEntity> findAll();
}
