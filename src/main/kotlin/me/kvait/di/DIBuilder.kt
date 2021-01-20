package me.kvait.di

import io.ktor.application.*
import org.kodein.di.*
import me.kvait.db.data.DataBaseFactory
import me.kvait.repository.BookRepositoryImpl
import me.kvait.routes.RoutingV1
import me.kvait.services.BookService
import java.net.URI

class DIBuilder(private val environment: ApplicationEnvironment) {

    fun setup(builder: DI.MainBuilder) {
        with(builder) {

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

            bind<BookRepositoryImpl>() with eagerSingleton { BookRepositoryImpl() }
            bind<BookService>() with eagerSingleton { BookService(instance()) }
            bind<RoutingV1>() with eagerSingleton { RoutingV1() }
        }
    }
}