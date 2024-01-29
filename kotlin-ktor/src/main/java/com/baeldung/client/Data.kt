package com.baeldung.client

const val CARS = """
    [
        {
            "id": 0,
            "name": "Car 1",
            "driver": 0
        },
        {
            "id": 1,
            "name": "Car 2",
            "driver": 1
        }
    ]
"""

val DRIVERS = listOf(
    """
        {
            "id": 0,
            "name": "John"
        }
    """,
    """
        {
            "id": 1,
            "name": "Mike"
        }
    """
)