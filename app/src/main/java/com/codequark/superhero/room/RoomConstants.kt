package com.codequark.superhero.room

class RoomConstants {
    companion object {
        const val databaseName = "SuperHero.db"
        const val databaseVersion = 1

        const val tableHeroes = "tableHeroes"

        // Hero
        const val columnId = "id"
        const val columnName = "name"
        const val columnIntelligence = "intelligence"
        const val columnStrength = "strength"
        const val columnSpeed = "speed"
        const val columnDurability = "durability"
        const val columnPower = "power"
        const val columnCombat = "combat"
        const val columnFullName = "fullName"
        const val columnAlterEgos = "alterEgos"
        const val columnPlaceOfBirth = "placeOfBirth"
        const val columnFirstAppearance = "firstAppearance"
        const val columnPublisher = "publisher"
        const val columnAlignment = "alignment"
        const val columnGender = "gender"
        const val columnRace = "race"
        const val columnHeight = "height"
        const val columnWeight = "weight"
        const val columnEyeColor = "eyeColor"
        const val columnHairColor = "hairColor"
        const val columnOccupation = "occupation"
        const val columnBase = "base"
        const val columnGroupAffiliation = "groupAffiliation"
        const val columnRelatives = "relatives"
        const val columnImageUrl = "imageUrl"
    }
}