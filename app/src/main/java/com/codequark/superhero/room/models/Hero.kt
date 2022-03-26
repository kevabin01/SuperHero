package com.codequark.superhero.room.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.codequark.superhero.room.RoomConstants
import java.util.*

@Entity(tableName = RoomConstants.tableHeroes)
data class Hero(
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = RoomConstants.columnId)
    val id: String,

    @NonNull
    @ColumnInfo(name = RoomConstants.columnName)
    val name: String,

    @NonNull
    @ColumnInfo(name = RoomConstants.columnIntelligence)
    val intelligence: String,

    @NonNull
    @ColumnInfo(name = RoomConstants.columnStrength)
    val strength: String,

    @NonNull
    @ColumnInfo(name = RoomConstants.columnSpeed)
    val speed: String,

    @NonNull
    @ColumnInfo(name = RoomConstants.columnDurability)
    val durability: String,

    @NonNull
    @ColumnInfo(name = RoomConstants.columnPower)
    val power: String,

    @NonNull
    @ColumnInfo(name = RoomConstants.columnCombat)
    val combat: String,

    @NonNull
    @ColumnInfo(name = RoomConstants.columnFullName)
    val fullName: String,

    @NonNull
    @ColumnInfo(name = RoomConstants.columnAlterEgos)
    val alterEgos: String,

    @NonNull
    @ColumnInfo(name = RoomConstants.columnPlaceOfBirth)
    val placeOfBirth: String,

    @NonNull
    @ColumnInfo(name = RoomConstants.columnFirstAppearance)
    val firstAppearance: String,

    @NonNull
    @ColumnInfo(name = RoomConstants.columnPublisher)
    val publisher: String,

    @NonNull
    @ColumnInfo(name = RoomConstants.columnAlignment)
    val alignment: String,

    @NonNull
    @ColumnInfo(name = RoomConstants.columnGender)
    val gender: String,

    @NonNull
    @ColumnInfo(name = RoomConstants.columnRace)
    val race: String,

    @NonNull
    @ColumnInfo(name = RoomConstants.columnHeight)
    val height: String,

    @NonNull
    @ColumnInfo(name = RoomConstants.columnWeight)
    val weight: String,

    @NonNull
    @ColumnInfo(name = RoomConstants.columnEyeColor)
    val eyeColor: String,

    @NonNull
    @ColumnInfo(name = RoomConstants.columnHairColor)
    val hairColor: String,

    @NonNull
    @ColumnInfo(name = RoomConstants.columnOccupation)
    val occupation: String,

    @NonNull
    @ColumnInfo(name = RoomConstants.columnBase)
    val base: String,

    @NonNull
    @ColumnInfo(name = RoomConstants.columnGroupAffiliation)
    val groupAffiliation: String,

    @NonNull
    @ColumnInfo(name = RoomConstants.columnRelatives)
    val relatives: String,

    @NonNull
    @ColumnInfo(name = RoomConstants.columnImageUrl)
    val imageUrl: String
) {
    override fun equals(other: Any?): Boolean {
        if(this === other) {
            return true
        }

        if(other !is Hero) {
            return false
        }

        return id == other.id &&
                name == other.name &&
                intelligence == other.intelligence &&
                strength == other.strength &&
                speed == other.speed &&
                durability == other.durability &&
                power == other.power &&
                combat == other.combat &&
                fullName == other.fullName &&
                alterEgos == other.alterEgos &&
                placeOfBirth == other.placeOfBirth &&
                firstAppearance == other.firstAppearance &&
                publisher == other.publisher &&
                alignment == other.alignment &&
                gender == other.gender &&
                race == other.race &&
                height == other.height &&
                weight == other.weight &&
                eyeColor == other.eyeColor &&
                hairColor == other.hairColor &&
                occupation == other.occupation &&
                base == other.base &&
                groupAffiliation == other.groupAffiliation &&
                relatives == other.relatives &&
                imageUrl == other.imageUrl
    }

    override fun hashCode(): Int {
        return Objects.hash(id, name, intelligence, strength, speed, durability, power, combat, fullName, alterEgos, placeOfBirth, firstAppearance, publisher, alignment, gender, race, height, weight, eyeColor, hairColor, occupation, base, groupAffiliation, relatives, imageUrl)
    }
}