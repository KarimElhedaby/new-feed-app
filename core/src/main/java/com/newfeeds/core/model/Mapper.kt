package com.newfeeds.core.model

interface Mapper<I,O> {
   fun map(input:I?):O
}