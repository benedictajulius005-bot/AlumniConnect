# AlumniConnect
### Öregdiák-követő és készségalapú állásajánló rendszer

## 1. A projekt bemutatása

Az AlumniConnect egy webalapú rendszer, amely az öregdiákok szakmai életútjának nyomon követését, a szakmai kapcsolattartást és a készségalapú állásajánlást támogatja.

A rendszer lehetővé teszi az öregdiákok számára, hogy regisztráljanak, kezeljék profiljukat, rögzítsék korábbi munkahelyeiket, megadják készségeiket, megtekintsék az elérhető álláslehetőségeket, valamint készségeik alapján állásajánlásokat kapjanak.

Az adminisztrátorok egy adminisztrációs felületen keresztül kezelhetik az öregdiákok adatait, a tanszékeket, az álláslehetőségeket, a készségeket, az eseményeket és a közleményeket.

A projekt fő célja az öregdiákokkal való kapcsolattartás erősítése, valamint a releváns karrierlehetőségekhez való hozzáférés javítása készségalapú álláspárosítás segítségével.

## 2. Az alkalmazás elérhetősége

- **Élő alkalmazás:** https://alumniconnect-frontend-glni.onrender.com
- **GitHub-tároló:** https://github.com/benedictajulius005-bot/AlumniConnect

*Megjegyzés: Az alkalmazás a Render platformon van hosztolva. Ha hosszabb ideig nem használták, az első megnyitáskor némi várakozási idő előfordulhat, amíg a szolgáltatás újraindul.*

## 3. Főbb funkciók

**Öregdiákok számára**
- Felhasználói regisztráció és bejelentkezés.
- Profil létrehozása és kezelése.
- Korábbi munkahelyek és munkatapasztalatok kezelése.
- Készségek kezelése.
- Elérhető álláslehetőségek böngészése.
- Készségalapú állásajánlások megtekintése.
- Egyező és hiányzó készségek megtekintése.
- Események és közlemények megtekintése.

**Adminisztrátorok számára**
- Adminisztrációs vezérlőpult.
- Öregdiákok kezelése.
- Álláslehetőségek kezelése.
- Készségek és tanszékek kezelése.
- Események kezelése.
- Közlemények kezelése.

## 4. Alkalmazott technológiák

| Összetevő | Technológia |
|---|---|
| Programozási nyelv | Java 17 |
| Backend keretrendszer | Spring Boot 4.0.7 |
| Backend architektúra | REST API |
| Adathozzáférés | Spring Data JPA / Hibernate |
| Biztonság | Spring Security és JWT |
| Frontend | HTML, CSS, JavaScript |
| Felhasználói felület keretrendszere | Bootstrap 5 |
| Adatbázis | MySQL |
| Build eszköz | Maven |

## 5. Az állásajánlási módszer

Az AlumniConnect készségalapú párosítási módszert alkalmaz az álláslehetőségek ajánlásához.

A rendszer összehasonlítja az öregdiák profiljában regisztrált készségeket az egyes álláslehetőségekhez megadott elvárt készségekkel.

Kiszámítja az egyezési százalékot, valamint azonosítja az egyező és hiányzó készségeket. Az alapértelmezett ajánlási küszöbérték 60%. Az ajánlás során csak az aktív, még le nem járt jelentkezési határidejű álláslehetőségeket veszi figyelembe.

## 6. A projekt felépítése

```text
AlumniConnect/
│
├── Project/                 # Spring Boot backend
│   ├── src/
│   ├── pom.xml
│   └── ...
│
├── cooladmin/               # Frontend alkalmazás
│
├── database/
│   ├── alumni_system_schema.sql
│   └── sample_data.sql
│
├── Screenshots/             # Alkalmazásképernyőképek
│
├── README.md
└── README_HU.md
```

## 7. Telepítés és beállítás

### Előfeltételek

- JDK 17
- Maven
- MySQL Server vagy MariaDB
- IntelliJ IDEA vagy más Java IDE
- Modern webböngésző

### 1. lépés: A GitHub-tároló klónozása

```bash
git clone https://github.com/benedictajulius005-bot/AlumniConnect.git
cd AlumniConnect
```

### 2. lépés: Az adatbázis létrehozása

Hozz létre egy `alumni_system` nevű adatbázist MySQL-ben:

```sql
CREATE DATABASE alumni_system;
```

### 3. lépés: Az adatbázisfájlok importálása

A `database` könyvtár két SQL-fájlt tartalmaz:

- **alumni_system_schema.sql:** Az adatbázis szerkezetét tartalmazza, beleértve a 13 táblát, az indexeket és a kapcsolatok meghatározását.
- **sample_data.sql:** Fiktív bemutató adatokat tartalmaz tesztelési célokra.

Először az `alumni_system_schema.sql`, majd a `sample_data.sql` fájlt importáld az `alumni_system` adatbázisba phpMyAdmin vagy MySQL parancssor segítségével.

### 4. lépés: A backend konfigurálása

Nyisd meg a következő könyvtárat:

```text
Project/src/main/resources/
```

Másold le az `application-example.properties` fájlt, majd nevezd át a másolatot `application.properties` fájlra.

Állítsd be a helyi adatbázis-kapcsolatot és a JWT titkos kulcsot.

Példa konfiguráció:

```properties
server.port=8080
spring.application.name=Project

spring.datasource.url=jdbc:mysql://localhost:3306/alumni_system?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=YOUR_DATABASE_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect

spring.jpa.open-in-view=false

app.recommendation.threshold=60
app.jwt.secret=REPLACE_WITH_YOUR_OWN_SECRET
app.jwt.expiration=86400000
```

A portot, a felhasználónevet és a jelszót igazítsd a helyi MySQL-konfigurációdhoz.

**Biztonsági megjegyzés:** A tényleges `application.properties` fájlt, az adatbázis-hitelesítési adatokat és a JWT titkos kulcsot ne töltsd fel a GitHubra.

### 5. lépés: A backend elindítása

Nyiss terminált a `Project` könyvtárban, majd futtasd:

```bash
mvn spring-boot:run
```

A backend a következő címen lesz elérhető:

```text
http://localhost:8080
```

### 6. lépés: A frontend elindítása

A frontend alkalmazás a `cooladmin` könyvtárban található.

Nyisd meg egy megfelelő helyi fejlesztői szerver segítségével, és győződj meg arról, hogy az API-konfiguráció a helyben futó backend címére mutat.

## 8. Adatbázis-tervezés

Az alkalmazás egy `alumni_system` nevű MySQL relációs adatbázist használ, amely 13 táblából áll:

| Tábla | Leírás |
|---|---|
| users | Felhasználói fiókok és szerepkörök |
| admins | Adminisztrátori adatok |
| alumni | Öregdiákok profiladatai |
| departments | Tanszéki adatok |
| alumni_id_sequence | Öregdiák-azonosítók sorszámozása |
| skills | Elérhető készségek |
| alumni_skills | Öregdiákok és készségeik kapcsolata |
| employment_history | Korábbi munkaviszonyok |
| job_opportunities | Álláslehetőségek |
| job_skills | Az állásokhoz szükséges készségek |
| events | Öregdiák-események |
| event_alumni | Események és öregdiákok kapcsolata |
| announcements | Rendszerközlemények |

Az adatbázis elsődleges és idegen kulcsokat használ az adatok integritásának és a táblák közötti kapcsolatoknak a biztosítására.

## 9. A megvalósítás hatóköre és korlátai

A jelenlegi rendszer tartalmazza az öregdiákok regisztrációját és hitelesítését, a profilkezelést, a munkatapasztalatok kezelését, a készségkezelést, az álláslehetőségeket, az állásajánlást, az eseménykezelést és a közleményeket.

Az eseményekre történő regisztráció és a munkáltatói felület nem része a jelenlegi megvalósításnak. Ezek a funkciók a rendszer későbbi továbbfejlesztése során valósíthatók meg.

## 10. Képernyőképek

A `Screenshots` könyvtár az alkalmazás felhasználói felületét és a megvalósított funkciókat bemutató képernyőképeket tartalmazza.

## 11. Projektinformációk

**Projekt címe:** Egy Spring Boot-alapú öregdiák-követő és készségalapú állásajánló rendszer tervezése és megvalósítása.

**Projekt típusa:** BSc számítástechnika szakos záróprojekt.

**Backend:** Java Spring Boot.

**Frontend:** HTML, CSS, JavaScript és Bootstrap 5.

**Adatbázis:** MySQL.