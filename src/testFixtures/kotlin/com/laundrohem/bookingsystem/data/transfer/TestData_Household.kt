package com.laundrohem.bookingsystem.data.transfer

import java.util.*

class TestData_Household {

    companion object {
        val THE_BERGMAN_HOUSEHOLD = Household(UUID.randomUUID(), "A.O & F.E Bergman")
        val THE_SMITH_HOUSEHOLD = Household(UUID.randomUUID(), "J.P Smith")
        val THE_MATTSON_HOUSEHOLD = Household(UUID.randomUUID(), "K & R Mattson")
        val THE_GRAFF_HOUSEHOLD = Household(UUID.randomUUID(), "S. Graff")
        val THE_DAHL_HOUSEHOLD = Household(UUID.randomUUID(), "P.A & F. Dahl")
        val THE_MARTIN_HOUSEHOLD = Household(UUID.randomUUID(), "K. Martin")
        val THE_BUELLER_HOUSEHOLD = Household(UUID.randomUUID(), "F. & K. Bueller")
        val THE_HERRING_HOUSEHOLD = Household(UUID.randomUUID(), "P.T Herring")
        val THE_DUNDER_HOUSEHOLD = Household(UUID.randomUUID(), "E.U Dunder")
        val THE_PINK_HOUSEHOLD = Household(UUID.randomUUID(), "T. & F.G Pink")
        val THE_LAWKINS_HOUSEHOLD = Household(UUID.randomUUID(), "S. & R.I Lawkins")
        val THE_HUNTER_HOUSEHOLD = Household(UUID.randomUUID(), "G.E Hunter")
        val THE_GRIMM_HOUSEHOLD = Household(UUID.randomUUID(), "H.R Grimm")
        val THE_JACKSON_HOUSEHOLD = Household(UUID.randomUUID(), "R.E & S.C Jackson")
        val THE_DRYDEN_HOUSEHOLD = Household(UUID.randomUUID(), "J. Dryden")
        val THE_CRUZ_HOUSEHOLD = Household(UUID.randomUUID(), "D.E & F. Cruz")
        val THE_BLAKE_HOUSEHOLD = Household(UUID.randomUUID(), "W.L Blake")
        val THE_HARRISON_HOUSEHOLD = Household(UUID.randomUUID(), "G. Harrison")
        val THE_MCLEASH_HOUSEHOLD = Household(UUID.randomUUID(), "D.E & W.S McLeash")
        val THE_BUTTERWORTH_HOUSEHOLD = Household(UUID.randomUUID(), "F.G Butterworth")

        val HOUSEHOLDS = listOf<Household>(THE_BERGMAN_HOUSEHOLD, THE_SMITH_HOUSEHOLD, THE_MATTSON_HOUSEHOLD,
            THE_GRAFF_HOUSEHOLD, THE_DAHL_HOUSEHOLD, THE_MARTIN_HOUSEHOLD, THE_BUELLER_HOUSEHOLD, THE_HERRING_HOUSEHOLD,
            THE_DUNDER_HOUSEHOLD, THE_PINK_HOUSEHOLD, THE_LAWKINS_HOUSEHOLD, THE_HUNTER_HOUSEHOLD, THE_GRIMM_HOUSEHOLD,
            THE_JACKSON_HOUSEHOLD, THE_DRYDEN_HOUSEHOLD, THE_CRUZ_HOUSEHOLD, THE_BLAKE_HOUSEHOLD, THE_HARRISON_HOUSEHOLD,
            THE_MCLEASH_HOUSEHOLD, THE_BUTTERWORTH_HOUSEHOLD)

        val TEST_NEW_VALID_HOUSEHOLD = Household(name = "Adam Bell")
    }
}