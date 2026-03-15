# OR-Mapper Demo mit JPA und Hibernate

Dieses Projekt zeigt, wie ein OR-Mapper (Object-Relational Mapper) in Java genutzt wird, um relationale Datenbanktabellen als Objekte zu verwalten.
Im Fokus stehen die Modellierung von Entitaeten, Beziehungen zwischen Entitaeten sowie typische CRUD-Operationen mit JPA und Hibernate.

## Ziel der Demonstration

- Verstehen, wie Objekte auf Datenbanktabellen abgebildet werden.
- Entitaeten mit Annotationen definieren.
- Beziehungen wie 1:1, 1:n und n:m korrekt modellieren.
- Persistenz, Laden, Aktualisieren und Loeschen von Daten mit Repository/EntityManager nachvollziehen.
- Unterschied zwischen fachlichem Objektmodell und physischem Datenbankschema erkennen.

## Technologien

- Java 17+
- JPA (Jakarta Persistence API)
- Hibernate (JPA-Implementierung)
- H2 oder MySQL/PostgreSQL als Datenbank
- Optional: Spring Boot + Spring Data JPA fuer schnellere Entwicklung

## Beispiel-Use-Case

Als Beispiel dient eine kleine Schuldomaene:

- Schueler
- Kurs
- Lehrer
- Einschreibung

Dadurch lassen sich alle wichtigen Beziehungstypen demonstrieren:

- Ein Lehrer unterrichtet mehrere Kurse (1:n)
- Ein Schueler belegt mehrere Kurse und ein Kurs hat mehrere Schueler (n:m)
- Einschreibung als eigene Entitaet mit Zusatzinformationen (z. B. Datum, Note)

## Kernkonzepte der Entitaetenverwaltung

### 1. Entitaet definieren

Eine Entitaet repraesentiert eine Tabelle und wird mit @Entity markiert.

```java
@Entity
@Table(name = "students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(unique = true, nullable = false)
	private String email;

	// Getter/Setter
}
```

### 2. Beziehungen modellieren

Beziehungen werden mit Annotationen wie @OneToMany, @ManyToOne oder @ManyToMany definiert.

```java
@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;
}
```

### 3. CRUD mit Repository oder EntityManager

Typische Operationen:

- Create: Neue Entitaet speichern
- Read: Entitaet nach ID oder Kriterien laden
- Update: Entitaet veraendern und erneut speichern
- Delete: Entitaet entfernen

Mit Spring Data JPA erfolgt das oft ueber Interfaces wie JpaRepository.

## Ablauf einer typischen Demo

1. Datenbankverbindung konfigurieren.
2. Tabellen automatisch ueber Hibernate erzeugen lassen (z. B. ddl-auto=update).
3. Testdaten anlegen (Schueler, Lehrer, Kurse).
4. Beziehungen setzen und speichern.
5. Daten mit Join-Abfragen lesen und validieren.
6. Aenderungen durchfuehren und Loeschvorgaenge pruefen.

## Konfiguration (Beispiel)

```properties
spring.datasource.url=jdbc:h2:mem:orm_demo
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

## Lernziele nach der Demonstration

Nach der Demo solltest du:

- die wichtigsten JPA-Annotationen sicher anwenden koennen,
- Beziehungen zwischen Entitaeten korrekt abbilden,
- Lazy/Eager Loading besser einordnen,
- typische Probleme (z. B. N+1 Select, Cascade-Fallen) erkennen,
- und ein kleines persistentes Domoenenmodell selbst aufsetzen koennen.

## Moegliche Erweiterungen

- Validierung mit Bean Validation (@NotNull, @Size, @Email)
- DTO-Mapping fuer API-Schichten
- Transaktionsmanagement mit @Transactional
- Paging und Sorting mit Spring Data
- Integrationstests mit Testcontainers

## Hinweis

Diese README ist auf eine Lehr-/Demo-Situation ausgerichtet.
Wenn du moechtest, kann ich als naechsten Schritt auch eine konkrete Projektstruktur (Entity-, Repository-, Service- und Controller-Klassen) mit Beispielcode ausarbeiten.
