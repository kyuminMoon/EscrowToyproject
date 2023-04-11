# kotlin-multi-module-sample
코프링 멀티모듈 뼈대 샘플

- SpringBoot: 3.0.1
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
