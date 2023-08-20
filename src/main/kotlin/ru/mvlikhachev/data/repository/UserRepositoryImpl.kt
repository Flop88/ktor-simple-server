package ru.mvlikhachev.data.repository

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import ru.mvlikhachev.data.model.UserModel
import ru.mvlikhachev.data.model.getRoleByString
import ru.mvlikhachev.data.model.getStringByRole
import ru.mvlikhachev.data.model.tables.UserTable
import ru.mvlikhachev.domain.repository.UserRepository
import ru.mvlikhachev.plugins.DatabaseFactory.dbQuery

class UserRepositoryImpl: UserRepository {

    override suspend fun getUserByEmail(email: String): UserModel? {
        return dbQuery {
            UserTable.select { UserTable.email.eq(email) }
                .map { rowToUser(row = it) }
                .singleOrNull()
        }
    }

    override suspend fun insertUser(user: UserModel) {
        return dbQuery {
            UserTable.insert { table ->
                table[email] = user.email
                table[login] = user.login
                table[password] = user.password
                table[firstName] = user.firstName
                table[lastName] = user.lastName
                table[isActive] = user.isActive
                table[role] = user.role.getStringByRole()
            }
        }
    }

    private fun rowToUser(row: ResultRow?): UserModel? {
        if (row == null) {
            return null
        }

        return UserModel(
            id = row[UserTable.id],
            email = row[UserTable.email],
            login = row[UserTable.login],
            password = row[UserTable.password],
            firstName = row[UserTable.firstName],
            lastName = row[UserTable.lastName],
            isActive = row[UserTable.isActive],
            role = row[UserTable.role].getRoleByString()
        )
    }

}