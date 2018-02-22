package com.sfl.kotlin.services.common

import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.RandomUtils

object Randomizer {

    //region Constants
    private const val RANDOM_STRING_LENGTH = 255
    //endregion

    //region Public interface methods
    fun generateRandomString() = RandomStringUtils.randomAscii(RANDOM_STRING_LENGTH)!!

    fun generateRandomInteger() = RandomUtils.nextInt()

    fun generateRandomLong() = RandomUtils.nextLong()
    //endregion
}