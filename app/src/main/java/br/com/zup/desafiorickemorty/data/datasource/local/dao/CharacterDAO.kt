package br.com.zup.desafiorickemorty.data.datasource.local.dao

import androidx.room.*
import br.com.zup.desafiorickemorty.data.model.CharacterResult

@Dao
interface CharacterDAO {
    @Query("SELECT * FROM characters ORDER BY id ASC")
    fun getAllCharacters(): List<CharacterResult>

    @Query("SELECT * FROM characters WHERE is_favorite = 1")
    fun getAllFavoriteCharacters(): List<CharacterResult>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllCharacters(listCharacters: List<CharacterResult>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateCharacterFavorite(character: CharacterResult)
}
