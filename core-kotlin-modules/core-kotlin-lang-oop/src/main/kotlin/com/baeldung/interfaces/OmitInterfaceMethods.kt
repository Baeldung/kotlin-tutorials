package com.baeldung.interfaces

object FirstExample {
    interface MyAdapter {
        fun onFocus() { /* default does nothing */ }
        fun onClick()
        fun onDrag() { /* default does nothing */ }
    }

    class MyAdapterImpl : MyAdapter {
        override fun onClick() {
            println("Clicked!")
        }
    }
}

object SecondExample {
    interface MyAdapter {
        fun onFocus()
        fun onClick()
        fun onDrag()
    }

    interface MyOnClickAdapter : MyAdapter {
        override fun onFocus() { /* default does nothing */ }
        override fun onDrag() { /* default does nothing */ }
    }

    class MyOnClickAdapterImpl : OnClickAdapter {
        override fun onClick() {
            println("Clicked!")
        }
    }
}
