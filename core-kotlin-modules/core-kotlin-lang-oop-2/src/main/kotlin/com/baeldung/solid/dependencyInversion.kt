package com.baeldung.solid

import java.util.Properties

class VeryComplexService(
    private val properties: Properties = Properties(),
    private val employeeRepository: EmployeeRepository = EmployeeRepository(createDataSource()),
    private val transformer: G = G { C() }
)