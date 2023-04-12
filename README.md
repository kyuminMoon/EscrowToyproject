# kotlin-multi-module-sample
코프링 멀티모듈
아임포트 결제모듈 토이프로젝트

- JDK 17
- SpringBoot: 3.0.5
- kotlinVersion: 1.7.22

> 모듈 구성

- payment-front
  - payment-front-api
    - SpringBoot + Kotlin
  - payment-front-ui
    - Vue3 + TypeScript + Vite
- payment-api
  - SpringBoot + Kotlin
- payment-entity
  - SpringBoot + Kotlin
  - MySQL
- payment-support
  - SpringBoot + Kotlin
- buildSrc
  - Project Library and Version Management

