package com.sfl.kotlin.services.user.impl

import com.sfl.kotlin.domain.user.dto.CreateUserDto
import com.sfl.kotlin.domain.user.dto.UserDto
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
class UserServiceImplUnitTest : AbstractUnitTest() {

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

    //region Create method
    @Test
    @DisplayName("Test creation of the user for the provided DTO")
    fun testCreate() {
        // Test data
        val createUserDto = getCreateUserDto()
        // Reset
        resetAll()
        // Expectations
        `when`(userRepository.save(isA(User::class.java))).thenAnswer { it.getArgument(0) }
        // Run test scenario
        val result = userService.create(createUserDto).apply {
            assertThat(this.firstName).isEqualTo(createUserDto.userDto.firstName)
            assertThat(this.lastName).isEqualTo(createUserDto.userDto.lastName)
        }
        // Verify
        verify(userRepository).save(result)
    }
    //endregion

    //region Get by id method
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
        val user = getUser().apply { this.id = id }
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

    //region Get all method
    @Test
    @DisplayName("Test retrieval of all users")
    fun testGetAll() {
        // Test data
        val users = listOf(getUser(), getUser(), getUser())
        // Reset
        resetAll()
        // Expectations
        `when`(userRepository.findAll()).thenReturn(users)
        // Run test scenario
        userService.getAll().apply { assertThat(this).isEqualTo(users) }
        // Verify
        verify(userRepository).findAll()
    }
    //endregion

    //endregion

    //region Private utility methods
    private fun resetAll() {
        reset(userRepository)
    }

    private fun getUser() = User(Randomizer.generateRandomString(), Randomizer.generateRandomString())

    private fun assertUserNotFoundForIdException(exception: UserNotFoundForIdException, id: Long) {
        assertThat(exception.id).isEqualTo(id)
    }

    private fun getCreateUserDto() = CreateUserDto(UserDto(Randomizer.generateRandomString(), Randomizer.generateRandomString()))
    //endregion
}