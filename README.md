# DziennikBackend

## Opis projektu

DziennikBackend to backendowa aplikacja systemu dziennika elektronicznego, napisana w Javie z wykorzystaniem frameworka Spring Boot. Projekt obsługuje zarządzanie użytkownikami, studentami, kursami, obecnościami, ocenami oraz grupami studenckimi.

## Funkcjonalności

* Rejestracja i uwierzytelnianie użytkowników (JWT)
* Zarządzanie studentami, kursami, grupami i ocenami
* Obsługa obecności studentów na zajęciach
* Konfiguracja uprawnień i bezpieczeństwa
* Interfejs API REST

## Technologie

* Java 17+
* Spring Boot
* Spring Security
* JWT (JSON Web Token)
* Maven
* JPA (Hibernate)
* H2/PostgreSQL/MySQL (możliwość konfiguracji)

## Struktura projektu

* `/src/main/java/org/example/dziennikbackend/` - kod źródłowy aplikacji

  * `configs` - konfiguracja bezpieczeństwa, JWT, CORS
  * `controllers` - kontrolery REST API
  * `models` - encje i DTO
  * `repositories` - interfejsy JPA
  * `services` - logika biznesowa
* `/src/main/resources/` - pliki konfiguracyjne (m.in. `application.properties`, migracje bazy danych)
* `/src/test/` - testy jednostkowe

## Wymagania

* Java 17+
* Maven 3.8+

## Instalacja i uruchomienie

1. Sklonuj repozytorium:

```
git clone <adres_repozytorium>
```

2. Przejdź do katalogu projektu:

```
cd DziennikBackend-master
```

3. Skonfiguruj bazę danych w pliku `src/main/resources/application.properties` (domyślnie H2).

4. Zbuduj projekt:

```
mvn clean install
```

5. Uruchom aplikację:

```
mvn spring-boot:run
```

## API

Aplikacja udostępnia REST API do zarządzania danymi dziennika.

Przykładowe endpointy:

* `/api/auth/login` - logowanie
* `/api/students` - operacje na studentach
* `/api/courses` - operacje na kursach
* `/api/groups` - operacje na grupach

## Bezpieczeństwo

* Autoryzacja użytkowników realizowana przy pomocy JWT.
* Konfiguracja CORS w klasie `CorsConfig`.
* Spring Security kontroluje dostęp do zasobów.

## Migracja bazy danych

W katalogu `src/main/resources/db/migration/` znajdują się skrypty migracji (Flyway).

## Autor

Projekt edukacyjny backendu dziennika elektronicznego.

---

**Uwaga:** Ten plik README można rozbudować o dokumentację API, diagramy klas i przykładowe żądania, jeśli będzie taka potrzeba.
