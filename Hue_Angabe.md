# Arbeitsanweisung

Entwickeln Sie, aufbauend auf der letzten Hausübung, folgende JPQL-Queries

Alle Mitarbeiter:

- die in einer bestimmten Stadt leben (zipCode der Adresse)
- mit bestimmten Vor- und Nachnamen, diese sollen als Positional Paramater übergeben werden
- alle Mitarbeiter, denen zumindest ein Task zugeordnet wurde
- die in einer bestimmten Abteilung arbeiten. Der Query soll die ganze Abteilung übergeben werden (nicht nur deren ID), übergabe als Named Parameter.
- Alle Mitarbeiter. In einer Abfrage sollen auch gleich die Adresse und die Abteilung mittels fetch join geladen werden

Als NamedQuery:

- Alle Abteilungen, die mehr als einen Mitarbeiter haben
- alle Tasks eines bestimmten Mitarbeiters.
  