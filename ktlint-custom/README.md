## KTLINT Custom Module
This module contains a custom Ktlint rule.

The rule named "unit-test-convention" enforces the Baeldung test naming convention: "Unit test class names need to end in 'UnitTest', integration tests with 'IntegrationTest', etc."

This check is done in the "compile" phase of the maven task.

The output of the build of this module is a Jar that is included in the dependencies of the maven-antrun-plugin in the mail pom.xml file.
