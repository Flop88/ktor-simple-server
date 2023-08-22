package ru.mvlikhachev.data.repository

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import ru.mvlikhachev.data.model.CardModel
import ru.mvlikhachev.data.model.tables.CardTable
import ru.mvlikhachev.domain.repository.CardRepository
import ru.mvlikhachev.plugins.DatabaseFactory.dbQuery

class CardRepositoryImpl: CardRepository {

    override suspend fun addCard(card: CardModel) {
        dbQuery {
            CardTable.insert { table ->
                table[owner] = card.owner
                table[cardTitle] = card.cardTitle
                table[cardDescription] = card.cardDescription
                table[cardDate] = card.cardDate
                table[isVerified] = card.isVerified
            }
        }
    }

    override suspend fun getAllCards(): List<CardModel> {
        return dbQuery {
            CardTable.selectAll()
                .mapNotNull { rowToCard(it) }
        }
    }

    override suspend fun updateCard(card: CardModel, ownerId: Int) {
        dbQuery {
            CardTable.update(
                where = {
                    CardTable.owner.eq(ownerId) and CardTable.id.eq(card.id)
                }
            ) { table ->
                table[owner] = ownerId
                table[cardTitle] = card.cardTitle
                table[cardDescription] = card.cardDescription
                table[cardDate] = card.cardDate
                table[isVerified] = card.isVerified
            }
        }
    }

    override suspend fun deleteCard(cardId: Int, ownerId: Int) {
        dbQuery {
            CardTable.deleteWhere { CardTable.id.eq(cardId) and CardTable.owner.eq(ownerId) }
        }
    }


    private fun rowToCard(row: ResultRow?): CardModel? {
        if (row == null) {
            return null
        }

        return CardModel(
            id = row[CardTable.id],
            owner = row[CardTable.owner],
            cardTitle = row[CardTable.cardTitle],
            cardDescription = row[CardTable.cardDescription],
            cardDate = row[CardTable.cardDate],
            isVerified = row[CardTable.isVerified],
        )
    }

}