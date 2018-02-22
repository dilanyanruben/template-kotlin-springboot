package com.sfl.kotlin.services.user

import com.sfl.kotlin.domain.user.dto.CreateUserDto
import com.sfl.kotlin.domain.user.dto.UserDto
import com.sfl.kotlin.services.common.AbstractIntegrationTest
import com.sfl.kotlin.services.common.Randomizer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired


/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 2/22/18
 * Time: 2:41 PM
 */
class UserServiceIntegrationTest(@Autowired private val userService: UserService) : AbstractIntegrationTest() {

    //region Test methods
    @Test
    @DisplayName("Test creation of the user for the provided DTO")
    fun testCreate() {
        // Prepare data
        val createUserDto = getCreateUserDto()
        // Create user
        userService.create(createUserDto).apply {
            assertThat(this.firstName).isEqualTo(createUserDto.userDto.firstName)
            assertThat(this.lastName).isEqualTo(createUserDto.userDto.lastName)
        }
    }


    @Test
    @DisplayName("Test retrieval of the user for the provided id")
    fun testGetById() {
        // Prepare data
        val user = createUser()
        // Load user for the id
        userService.getById(user.id).apply { this == user }
    }

    @Test
    @DisplayName("Test retrieval of all users")
    fun testGetAll() {
        // Prepare data
        val users = listOf(createUser(), createUser(), createUser())
        // Retrieve all users
        userService.getAll()
                .apply { assertThat(this.size).isEqualTo(users.size) }
                .apply { assertThat(this.containsAll(users)).isTrue() }
    }
    //endregion

    //region Private utility methods
    private fun getCreateUserDto() = CreateUserDto(UserDto(Randomizer.generateRandomString(), Randomizer.generateRandomString()))

    private fun createUser() = userService.create(CreateUserDto(UserDto(Randomizer.generateRandomString(), Randomizer.generateRandomString())))
    //endregion
}