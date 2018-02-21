package com.sfl.kotlin.services.common

import com.mockito.extensions.junit5.MockitoExtension
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.RandomUtils
import org.junit.jupiter.api.extension.ExtendWith


/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 2/21/18
 * Time: 11:47 AM
 */
@ExtendWith(MockitoExtension::class)
abstract class AbstractUnitTest {

    //region Properties
    //endregion
}

object Randomizer {

    //region Constants
    private const val RANDOM_STRING_LENGTH = 256
    //endregion

    //region Public interface methods
    fun generateRandomString() = RandomStringUtils.randomAscii(RANDOM_STRING_LENGTH)!!

    fun generateRandomInteger() = RandomUtils.nextInt()

    fun generateRandomLong() = RandomUtils.nextLong()
    //endregion
}
