package com.baeldung.accidental_override

// This example will create an accidental override error, because of the conflicting `getSound()` function.
//class Cat : Animal {
//    val sound: String = "Meow"
//}

class CatWithCodeRemoved : Animal {
// val sound: String = "Meow"
}

class CatWithValRenamed : Animal {
    val myCatSound: String = "Meow"
}

class CatWithOverride : Animal {
    override fun getSound(): String {
        return "Meow"
    }
}

class CatWithJvmName : Animal {
    @get:JvmName("cat_sound")
    val sound: String = "Meow"
}
