package com.newfeeds.core.base

abstract class BaseUseCase<I,O> {
  abstract fun execute(input:I?):O
}