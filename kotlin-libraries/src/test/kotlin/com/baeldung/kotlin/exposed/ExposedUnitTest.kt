package com.baeldung.kotlin.exposed

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Join
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SizedCollection
import org.jetbrains.exposed.sql.SqlExpressionBuilder
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.alias
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import org.junit.jupiter.api.Test
import java.sql.DriverManager
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ExposedUnitTest {

    @Test
    fun whenH2Database_thenConnectionSuccessful() {
        val db = Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")

        transaction(db) {
            assertEquals(2.1.toBigDecimal(), db.version)
            assertEquals("h2", db.vendor)
        }
    }

    @Test
    fun whenH2DatabaseWithCredentials_thenConnectionSuccessful() {
        val db = Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver", user = "myself", password = "secret")

        transaction(db) {
            commit()
        }
    }

    @Test
    fun whenH2DatabaseWithManualConnection_thenConnectionSuccessful() {
        var connected = false
        val db = Database.connect({ connected = true; DriverManager.getConnection("jdbc:h2:mem:test;MODE=MySQL") })
        assertFalse(connected)

        transaction(db) {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(Cities)
            assertTrue(connected)
        }
    }

    @Test
    fun whenManualCommit_thenOk() {
        val db = Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")

        transaction(db) {
            commit()
            commit()
            commit()
        }
    }

    @Test
    fun whenInsert_thenGeneratedKeys() {
        Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")

        transaction {
            SchemaUtils.create(StarWarsFilms)
            val id = StarWarsFilms.insertAndGetId {
                it[name] = "The Last Jedi"
                it[sequelId] = 8
                it[director] = "Rian Johnson"
            }
            assertEquals(1, id.value)

            val insert = StarWarsFilms.insert {
                it[name] = "The Force Awakens"
                it[sequelId] = 7
                it[director] = "J.J. Abrams"
            }
            assertEquals(2, insert[StarWarsFilms.id].value)

            StarWarsFilms.selectAll()
                    .forEach {
                        assertTrue { it[StarWarsFilms.sequelId] >= 7 }
                    }

            StarWarsFilms.slice(StarWarsFilms.name, StarWarsFilms.director)
                    .selectAll()
                    .forEach {
                        assertTrue { it[StarWarsFilms.name].startsWith("The") }
                    }

            val select = StarWarsFilms.select { (StarWarsFilms.director like "J.J.%") and (StarWarsFilms.sequelId eq 7) }
            assertEquals(1, select.count())

            StarWarsFilms.update({ StarWarsFilms.sequelId eq 8 }) {
                it[name] = "Episode VIII – The Last Jedi"
                with(SqlExpressionBuilder) {
                    it.update(sequelId, sequelId + 1)
                }
            }
        }
    }

    @Test
    fun whenForeignKey_thenAutoJoin() {
        Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")

        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(StarWarsFilms, Players)
            StarWarsFilms.insert {
                it[name] = "The Last Jedi"
                it[sequelId] = 8
                it[director] = "Rian Johnson"
            }
            StarWarsFilms.insert {
                it[name] = "The Force Awakens"
                it[sequelId] = 7
                it[director] = "J.J. Abrams"
            }
            Players.insert {
                it[name] = "Mark Hamill"
                it[sequelId] = 7
            }
            Players.insert {
                it[name] = "Mark Hamill"
                it[sequelId] = 8
            }

            val simpleInnerJoin = (StarWarsFilms innerJoin Players).selectAll()
            assertEquals(2, simpleInnerJoin.count())
            simpleInnerJoin.forEach {
                assertNotNull(it[StarWarsFilms.name])
                assertEquals(it[StarWarsFilms.sequelId], it[Players.sequelId])
                assertEquals("Mark Hamill", it[Players.name])
            }

            val innerJoinWithCondition = (StarWarsFilms innerJoin Players)
                    .select { StarWarsFilms.sequelId eq Players.sequelId }
            assertEquals(2, innerJoinWithCondition.count())

            innerJoinWithCondition.forEach {
                assertNotNull(it[StarWarsFilms.name])
                assertEquals(it[StarWarsFilms.sequelId], it[Players.sequelId])
                assertEquals("Mark Hamill", it[Players.name])
            }

            val complexInnerJoin = Join(StarWarsFilms, Players, joinType = JoinType.INNER, onColumn = StarWarsFilms.sequelId, otherColumn = Players.sequelId, additionalConstraint = {
                StarWarsFilms.sequelId eq 8
            }).selectAll()
            assertEquals(1, complexInnerJoin.count())

            complexInnerJoin.forEach {
                assertNotNull(it[StarWarsFilms.name])
                assertEquals(it[StarWarsFilms.sequelId], it[Players.sequelId])
                assertEquals("Mark Hamill", it[Players.name])
            }
        }
    }

    @Test
    fun whenJoinWithAlias_thenFun() {
        Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")

        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(StarWarsFilms, Players)
            StarWarsFilms.insert {
                it[name] = "The Last Jedi"
                it[sequelId] = 8
                it[director] = "Rian Johnson"
            }
            StarWarsFilms.insert {
                it[name] = "The Force Awakens"
                it[sequelId] = 7
                it[director] = "J.J. Abrams"
            }
            val sequel = StarWarsFilms.alias("sequel")
            Join(StarWarsFilms, sequel,
                    additionalConstraint = { sequel[StarWarsFilms.sequelId] eq StarWarsFilms.sequelId + 1 })
                    .selectAll()
                    .forEach {
                        assertEquals(it[sequel[StarWarsFilms.sequelId]], it[StarWarsFilms.sequelId] + 1)
                    }
        }
    }

    @Test
    fun whenEntity_thenDAO() {
        val database = Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")
        val connection = database.connector.invoke() //Keep a connection open so the DB is not destroyed after the first transaction

        val inserted = transaction {
            addLogger(StdOutSqlLogger)

            SchemaUtils.create(StarWarsFilms, Players)

            val theLastJedi = StarWarsFilm.new {
                name = "The Last Jedi"
                sequelId = 8
                director = "Rian Johnson"
            }

            assertEquals(1, theLastJedi.id.value)
            theLastJedi
        }

        transaction {
            val theLastJedi = StarWarsFilm.findById(1)
            assertEquals(inserted.id, theLastJedi?.id)
        }

        connection.close()
    }

    @Test
    fun whenManyToOne_thenNavigation() {
        val database = Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")
        val connection = database.connector.invoke()

        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(StarWarsFilms, Players, Users, UserRatings)

            val theLastJedi = StarWarsFilm.new {
                name = "The Last Jedi"
                sequelId = 8
                director = "Rian Johnson"
            }
            val someUser = User.new {
                name = "Some User"
            }
            val rating = UserRating.new {
                value = 9
                user = someUser
                film = theLastJedi
            }
            assertEquals(theLastJedi, rating.film)
            assertEquals(someUser, rating.user)
            assertEquals(rating, theLastJedi.ratings.first())
        }

        transaction {
            val theLastJedi = StarWarsFilm.find { StarWarsFilms.sequelId eq 8 }.first()
            val ratings = UserRating.find { UserRatings.film eq theLastJedi.id }
            assertEquals(1, ratings.count())

            val rating = ratings.first()
            assertEquals("Some User", rating.user.name)
            assertEquals(rating, theLastJedi.ratings.first())

            UserRating.new {
                value = 8
                user = rating.user
                film = theLastJedi
            }
            assertEquals(2, theLastJedi.ratings.count())
        }

        connection.close()
    }

    @Test
    fun whenManyToMany_thenAssociation() {
        val database = Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")
        val connection = database.connector.invoke()

        val film = transaction {
            SchemaUtils.create(StarWarsFilms)
            StarWarsFilm.new {
                name = "The Last Jedi"
                sequelId = 8
                director = "Rian Johnson"
            }
        }

        val actor = transaction {
            SchemaUtils.create(Actors)
            Actor.new {
                firstname = "Daisy"
                lastname = "Ridley"
            }
        }

        transaction {
            SchemaUtils.create(StarWarsFilmActors)
            film.actors = SizedCollection(listOf(actor))
        }

        connection.close()
    }

}

object Cities: IntIdTable() {
    val name = varchar("name", 50)
}

object StarWarsFilms_Simple : Table() {
    val id = integer("id").autoIncrement()
    val sequelId = integer("sequel_id").uniqueIndex()
    val name = varchar("name", 50)
    val director = varchar("director", 50)
    override val primaryKey = PrimaryKey(id, name = "PK_StarWarsFilms_Id")
}

object StarWarsFilms : IntIdTable() {
    val sequelId = integer("sequel_id").uniqueIndex()
    val name = varchar("name", 50)
    val director = varchar("director", 50)
}

object Players : Table() {
    //val sequelId = integer("sequel_id").uniqueIndex().references(StarWarsFilms.sequelId)
    val sequelId = reference("sequel_id", StarWarsFilms.sequelId).uniqueIndex()
    //val filmId = reference("film_id", StarWarsFilms).nullable()
    val name = varchar("name", 50)
}

class StarWarsFilm(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, StarWarsFilm>(StarWarsFilms)

    var sequelId by StarWarsFilms.sequelId
    var name     by StarWarsFilms.name
    var director by StarWarsFilms.director
    var actors   by Actor via StarWarsFilmActors
    val ratings  by UserRating referrersOn UserRatings.film
}

object Users: IntIdTable() {
    val name = varchar("name", 50)
}

object UserRatings: IntIdTable() {
    val value = long("value")
    val film  = reference("film", StarWarsFilms)
    val user  = reference("user", Users)
}

class User(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<User>(Users)

    var name by Users.name
}

class UserRating(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<UserRating>(UserRatings)

    var value by UserRatings.value
    var film  by StarWarsFilm referencedOn UserRatings.film
    var user  by User         referencedOn UserRatings.user
}

object Actors: IntIdTable() {
    val firstname = varchar("firstname", 50)
    val lastname = varchar("lastname", 50)
}

class Actor(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<Actor>(Actors)

    var firstname by Actors.firstname
    var lastname by Actors.lastname
}

object StarWarsFilmActors : Table() {
    val starWarsFilm = reference("starWarsFilm", StarWarsFilms)
    val actor = reference("actor", Actors)
    override val primaryKey = PrimaryKey(starWarsFilm, actor, name = "PK_StarWarsFilmActors_swf_act")
}
