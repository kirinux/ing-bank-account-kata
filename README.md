
# Requirements

* A valid JDK 11 installation configured through `JAVA_HOME` environment variable.

# How to

## Build

`mvnw verify`

## Run

`${JAVA_HOME}/bin/java -jar target/bank-account-kata.jar`

# Backlog

- Adding persistence (given the current contraints any document based db would to)
- Adding possibility to switch accounts
- Separating cli logic from service and adding REST endpoints
- Cli: Adding i18n for messages
- Cli: Using javacc to actually have a more formalized language description
- Cli: Adding unit tests on the formatting of history elements
- ...


# Changelog

## 1.3.0 - With support of transaction history

- Added support for `history` command

## 1.2.0 - With support of balance

- Added support for `balance` operation
- Enhanced code coverage to also unit test the logs emitted

## 1.1.0 - With support of withdrawals

- Added support for `withdraw` operation

## 1.0.0 - Initial version

- A single account for "Jane Doe"
- No persistence
- Only `deposit` operations supported

- Initial project skeleton with dependency validations, unit tests and packaging
