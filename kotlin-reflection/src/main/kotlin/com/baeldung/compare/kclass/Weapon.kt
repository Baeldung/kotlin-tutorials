package com.baeldung.compare.kclass

open class Weapon

open class Sword : Weapon()

open class Bow : Weapon()

class Claymore : Sword()

class LongBow : Bow()