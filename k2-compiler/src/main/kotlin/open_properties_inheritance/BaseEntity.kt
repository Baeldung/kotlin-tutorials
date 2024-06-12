import java.time.Instant

// Does not compile in Both old and K2 Kotlin compilers
//open class BaseEntity {
//    open val points: Int
//    open var pages: Long?
//
//    init {
//      points = 1
//      pages = 12
//    }
//}

open class BaseEntity {
    open val points: Int = 1
    open var pages: Long? = 12
}

open class WithLateinit {
  open lateinit var point: Instant
}