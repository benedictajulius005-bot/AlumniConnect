# AlumniConnect

**Öregdiák-követő és készségalapú állásajánló rendszer**

## 1. A projekt bemutatása

Az AlumniConnect egy webalapú rendszer, amely támogatja az öregdiákok szakmai életútjának nyomon követését és a készségalapú állásajánlásokat. Lehetővé teszi a felhasználók számára profiljuk kezelését, munkatapasztalataik rögzítését, szakmai készségeik karbantartását és a készségeikhez illeszkedő álláslehetőségek megtekintését.

Az adminisztrátorok kezelhetik az öregdiákok adatait, az álláslehetőségeket, a készségeket, az eseményeket és a közleményeket.

## 2. Online alkalmazás

**Weboldal:** https://alumniconnect-frontend-glni.onrender.com

**Megjegyzés:** Az alkalmazás a Render tárhelyszolgáltatón fut. Hosszabb inaktivitás után az első kérés néhány percet vehet igénybe, amíg a szolgáltatás újra aktívvá válik.

## 3. Főbb funkciók

### Öregdiákok számára
- Regisztráció és bejelentkezés.
- Profilkezelés.
- Szakmai készségek kezelése.
- Munkatapasztalatok nyilvántartása.
- Elérhető álláslehetőségek megtekintése.
- Készségalapú állásajánlások.
- Egyező és hiányzó készségek megjelenítése.
- Események és közlemények megtekintése.

### Adminisztrátorok számára
- Adminisztrációs felület.
- Öregdiákok kezelése.
- Álláslehetőségek kezelése.
- Készségek kezelése.
- Tanszékek kezelése.
- Események kezelése.
- Közlemények kezelése.

## 4. Felhasznált technológiák

| Összetevő | Technológia |
|---|---|
| Backend | Java 17, Spring Boot 4.0.7 |
| Backend keretrendszerek | Spring Data JPA, Spring Security |
| Hitelesítés | JWT |
| Frontend | HTML, CSS, JavaScript, Bootstrap 5 |
| Adatbázis | MySQL |
| Build eszköz | Maven |

## 5. Az ajánlási módszer

A rendszer összehasonlítja az öregdiák profiljában rögzített készségeket az álláslehetőségekhez meghatározott követelményekkel. Az egyezési százalék a meglévő és az adott álláshoz szükséges készségek aránya alapján kerül kiszámításra. A rendszer megjeleníti az egyező és a hiányzó készségeket is.

Az ajánlások csak aktív, le nem járt álláslehetőségekre vonatkoznak. Az alapértelmezett ajánlási küszöbérték 60%.

## 6. Projektstruktúra

- `cooladmin/` – Frontend alkalmazás.
- `Project/` – Spring Boot backend.
- `Screenshots/` – Az alkalmazás képernyőképei.

## 7. Helyi telepítés és beállítás

### Szükséges szoftverek
- Java Development Kit (JDK) 17.
- Maven.
- MySQL Server.
- Java fejlesztői környezet, például IntelliJ IDEA.

### Adatbázis beállítása

1. Nyissa meg a `Project` mappát.
2. Navigáljon a `src/main/resources/` könyvtárba.
3. Másolja le az `application-example.properties` fájlt `application.properties` néven.
4. Állítsa be a MySQL adatbázis-kapcsolat adatait.
5. Adja meg a szükséges JWT titkos kulcsot.
6. Hozza létre vagy importálja a szükséges adatbázistáblákat az alkalmazás indítása előtt.

**Fontos:** Az alkalmazás nem hozza létre automatikusan az adatbázistáblákat. Az adatbázissémát külön kell létrehozni vagy importálni.

### A backend indítása

Nyisson terminált a `Project` mappában, majd futtassa az alábbi parancsot:

```bash
mvn spring-boot:run
```

Az alapértelmezett port: `8080`.

### A frontend indítása

A frontend fájlok a `cooladmin/` mappában találhatók. A frontend REST API-kon keresztül kommunikál a backenddel.

Helyi futtatás esetén ellenőrizze, hogy a frontend API-beállításai a helyben futó backend címére mutatnak-e.

## 8. Korlátozások

Az ajánlórendszer az adatbázisban rendelkezésre álló készségektől és álláslehetőségektől függ. A jelenlegi változat nem tartalmaz eseményregisztrációs funkciót vagy munkáltatói felületet.

## 9. Értékelés

A rendszer funkcionális tesztelésen és az ajánlási számítások ellenőrzésén esett át. Emellett kezdeti, felhasználói visszajelzéseken alapuló értékelés is történt.

---

**Végzős BSc Computer Science projektként készült.**