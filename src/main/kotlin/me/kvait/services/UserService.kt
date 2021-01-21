package me.kvait.services

import me.kvait.dto.AuthenticationResponseDto
import me.kvait.model.UserModel
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import me.kvait.dto.AuthenticationRequestDto
import me.kvait.exception.UserExistsException
import me.kvait.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import io.ktor.features.NotFoundException
import me.kvait.dto.UserRegisterRequestDto
import me.kvait.dto.UserResponseDto
import me.kvait.exception.InvalidPasswordException

class UserService(
    private val repo: UserRepository,
    private val tokenService: JWTTokenService,
    private val passwordEncoder: PasswordEncoder
) {

    private val mutex = Mutex()

    suspend fun getByUserName(username: String): UserModel? {
        return repo.getByUsername(username)
    }

    suspend fun getModelById(id: Int): UserModel? {
        return repo.getById(id)
    }


    suspend fun getBuID(id: Int): UserResponseDto {
        val model = repo.getById(id) ?: throw NotFoundException()
        return UserResponseDto(
            id = model.id,
            username = model.username
        )
    }


    suspend fun register(input: UserRegisterRequestDto): AuthenticationResponseDto =
        mutex.withLock {
            if(repo.getByUsername(input.username) != null) {
                throw UserExistsException("User exists!")
            }

            repo.addNewUser(
                UserModel(
                    username = input.username,
                    password = passwordEncoder.encode(input.password)
                )
            )

            val model = requireNotNull(repo.getByUsername(input.username))

            val token = tokenService.generate(model.id)
            AuthenticationResponseDto(model.id, token)
        }


    suspend fun authenticate(input: AuthenticationRequestDto): AuthenticationResponseDto =
        mutex.withLock {

            val model = repo.getByUsername(input.username) ?: throw NotFoundException()

            if (!passwordEncoder.matches(input.password, model.password)) {
                throw InvalidPasswordException("Wrong password")
            }

            val token = tokenService.generate(model.id)
            return AuthenticationResponseDto(model.id, token)
        }
}