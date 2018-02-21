package com.sfl.kotlin.services.user.impl

import com.sfl.kotlin.domain.user.model.User
import com.sfl.kotlin.persistence.repositories.user.UserRepository
import com.sfl.kotlin.services.common.AbstractUnitTest
import com.sfl.kotlin.services.common.Randomizer
import com.sfl.kotlin.services.user.UserService
import com.sfl.kotlin.services.user.exception.UserNotFoundForIdException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.assertj.core.api.Condition
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import java.util.*


/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 2/21/18
 * Time: 11:46 AM
 */
class UserServiceImplTest : AbstractUnitTest() {

    //region Test subject and mocks
    private lateinit var userService: UserService

    @Mock
    private lateinit var userRepository: UserRepository
    //endregion

    //region Lifecycle methods
    @BeforeEach
    fun before() {
        userService = UserServiceImpl(userRepository)
    }
    //endregion

    //region Test methods
    @Test
    @DisplayName("Test retrieval of the user for the provided id when it does not exist")
    fun testGetByIdWithNotExistingId() {
        // Test data
        val id = Randomizer.generateRandomLong()
        // Reset
        resetAll()
        // Expectations
        `when`(userRepository.findById(eq(id))).thenReturn(Optional.empty())
        // Run test scenario
        assertThat(catchThrowable { userService.getById(id) }).isInstanceOf(UserNotFoundForIdException::class.java).`is`(object : Condition<Throwable>() {
            override fun matches(value: Throwable?): Boolean {
                assertUserNotFoundForIdException(value as UserNotFoundForIdException, id)
                return true
            }
        })
        // Verify
        verify(userRepository).findById(id)
    }

    @Test
    @DisplayName("Test retrieval of the user for the provided id")
    fun testGetById() {
        // Test data
        val id = Randomizer.generateRandomLong()
        val user = createUser().apply { this.id = id }
        // Reset
        resetAll()
        // Expectations
        `when`(userRepository.findById(eq(id))).thenReturn(Optional.of(user))
        // Run test scenario
        userService.getById(id).apply { assertThat(this).isEqualTo(user) }
        // Verify
        verify(userRepository).findById(id)
    }
    //endregion

    //region Private utility methods
    private fun resetAll() {
        reset(userRepository)
    }

    private fun createUser() = User(Randomizer.generateRandomString(), Randomizer.generateRandomString())

    fun assertUserNotFoundForIdException(exception: UserNotFoundForIdException, id: Long) {
        assertThat(exception.id).isEqualTo(id)
    }
    //endregion
}