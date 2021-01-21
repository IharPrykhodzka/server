package me.kvait.di

import io.ktor.application.*
import org.kodein.di.*
import me.kvait.db.data.DataBaseFactory
import me.kvait.repository.BookRepositoryImpl
import me.kvait.repository.UserRepositoryImpl
import me.kvait.routes.RoutingV1
import me.kvait.services.BookService
import me.kvait.services.JWTTokenService
import me.kvait.services.UserService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import java.net.URI
import javax.naming.ConfigurationException
import javax.security.auth.login.Configuration

class DIBuilder(private val environment: ApplicationEnvironment) {

    fun setup(builder: DI.MainBuilder) {
        with(builder) {

            constant(tag = "secret") with (
                    environment.config.propertyOrNull("ktor.ncraft.secret")?.getString()
                        ?: throw ConfigurationException("Secret is not specified")
                    )

            bind<DataBaseFactory>() with eagerSingleton {
                val dbUri = URI(environment.config.property("db.jdbcUrl").getString())
                val username = "user"
                val password = "password"
                val dbUrl = ("jdbc:postgresql://${dbUri.host}:${dbUri.port}${dbUri.path}")

                DataBaseFactory(
                    dbUrl = dbUrl,
                    dbPassword = password,
                    dbUser = username
                ).apply {
                    init()
                }
            }

            bind<UserRepositoryImpl>() with eagerSingleton { UserRepositoryImpl() }
            bind<UserService>() with eagerSingleton { UserService(instance(), instance(), instance()) }
            bind<JWTTokenService>() with eagerSingleton { JWTTokenService(instance(tag = "secret")) }
            bind<PasswordEncoder>() with eagerSingleton { BCryptPasswordEncoder() }
            bind<BookRepositoryImpl>() with eagerSingleton { BookRepositoryImpl() }
            bind<BookService>() with eagerSingleton { BookService(instance()) }
            bind<RoutingV1>() with eagerSingleton { RoutingV1() }
        }
    }
}