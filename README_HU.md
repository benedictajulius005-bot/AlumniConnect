# AlumniConnect
### Spring Boot-alapú öregdiák-követő és készségalapú állásajánló rendszer tervezése és megvalósítása

## 1. A projekt bemutatása

Az AlumniConnect egy webalapú öregdiák-követő és készségalapú állásajánló rendszer, amely támogatja az öregdiákok szakmai életútjának nyomon követését, a szakmai kapcsolattartást és a karrierlehetőségek elérését.

A rendszer lehetővé teszi az öregdiákok számára, hogy létrehozzák és kezeljék profiljukat, rögzítsék korábbi munkatapasztalataikat, kezeljék szakmai készségeiket, megtekintsék az elérhető álláslehetőségeket, valamint készségeik alapján állásajánlásokat kapjanak.

Az adminisztrátorok egy adminisztrációs felületen keresztül kezelhetik az öregdiákok adatait, a tanszékeket, az álláslehetőségeket, a készségeket, az eseményeket és a közleményeket.

A projekt célja az öregdiákokkal való kapcsolattartás támogatása, valamint a meglévő készségeikhez illeszkedő álláslehetőségek megtalálásának elősegítése.

## 2. Az alkalmazás elérhetősége

- **Élő alkalmazás:** https://alumniconnect-frontend-glni.onrender.com
- **GitHub-tároló:** https://github.com/benedictajulius005-bot/AlumniConnect

*Megjegyzés: Az alkalmazás a Render platformon van hosztolva. Hosszabb inaktivitás után az első megnyitáskor előfordulhat némi várakozási idő, amíg a szolgáltatás újraindul.*

## 3. Főbb funkciók

### Öregdiákok számára

- Felhasználói regisztráció és bejelentkezés.
- Öregdiákprofil létrehozása és kezelése.
- Korábbi munkahelyek és munkatapasztalatok kezelése.
- Szakmai készségek hozzáadása és kezelése.
- Elérhető álláslehetőségek böngészése.
- Készségalapú állásajánlások megtekintése.
- Egyező és hiányzó készségek megtekintése az ajánlott állásoknál.
- Események és közlemények megtekintése.

### Adminisztrátorok számára

- Adminisztrációs vezérlőpult.
- Öregdiák-fiókok és profilok kezelése.
- Álláslehetőségek kezelése.
- Készségek kezelése.
- Tanszékek kezelése.
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
| Frontend | HTML, CSS és JavaScript |
| Felhasználói felület keretrendszere | Bootstrap 5 |
| Adatbázis | MySQL |
| Build eszköz | Maven |
| API-tesztelés | Postman |
| Fejlesztői környezetek | IntelliJ IDEA és Visual Studio Code |

## 5. Az állásajánlási módszer

Az AlumniConnect készségalapú párosítási módszert alkalmaz az álláslehetőségek ajánlásához.

A rendszer összehasonlítja az öregdiák profiljában regisztrált készségeket az egyes álláslehetőségekhez megadott elvárt készségekkel.

A rendszer egyezési százalékot számít, és azonosítja az alábbiakat:

- **Egyező készségek:** Azok a készségek, amelyekkel az öregdiák rendelkezik, és amelyeket az adott álláslehetőség megkövetel.
- **Hiányzó készségek:** Azok az elvárt készségek, amelyek nem szerepelnek az öregdiák profiljában.
- **Egyezési százalék:** Az elvárt készségek azon százaléka, amelyek megfelelnek az öregdiák regisztrált készségeinek.

Az alapértelmezett ajánlási küszöbérték 60%. A rendszer csak az aktív, még le nem járt jelentkezési határidejű álláslehetőségeket veszi figyelembe az ajánlások elkészítésekor.

Ez a megközelítés segít az öregdiákoknak megtalálni a meglévő készségeikhez illeszkedő álláslehetőségeket, valamint felismerni azokat a területeket, ahol további készségek elsajátítására lehet szükségük.

## 6. A projekt felépítése

A projekt fő könyvtárai és fájljai a következők:

```text
AlumniConnect/
│
├── Project/                         # Spring Boot backend
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── Alumni/Project/
│   │   │   │       ├── controller/
│   │   │   │       ├── dto/
│   │   │   │       ├── entity/
│   │   │   │       ├── enums/
│   │   │   │       ├── exception/
│   │   │   │       ├── repository/
│   │   │   │       ├── security/
│   │   │   │       ├── service/
│   │   │   │       └── util/
│   │   │   └── resources/
│   │   └── test/
│   ├── pom.xml
│   ├── mvnw
│   └── mvnw.cmd
│
├── cooladmin/                       # Frontend alkalmazás
│   ├── css/
│   ├── js/
│   ├── images/
│   ├── vendor/
│   ├── login.html
│   ├── register.html
│   ├── profile.html
│   ├── jobs.html
│   ├── recommendations.html
│   └── ...
│
├── database/
│   ├── alumni_system_schema.sql
│   └── sample_data.sql
│
├── Screenshots/                     # Alkalmazásképernyőképek
│
├── README.md
└── README_HU.md
```

## 7. Telepítés és beállítás

Az alábbi lépések segítségével az AlumniConnect helyben is futtatható.

### Előfeltételek

A következő szoftverek telepítése szükséges:

- Java Development Kit (JDK) 17.
- MySQL Server vagy MariaDB.
- Visual Studio Code.
- Visual Studio Code Live Server bővítmény.
- IntelliJ IDEA vagy más Java fejlesztőkörnyezet (nem kötelező).
- Modern webböngésző.

A Maven külön is telepíthető, de a projekt tartalmaz Maven Wrappert is.

### 1. lépés: A GitHub-tároló klónozása

Nyiss meg egy terminált, majd futtasd az alábbi parancsokat:

```bash
git clone https://github.com/benedictajulius005-bot/AlumniConnect.git
cd AlumniConnect
```

### 2. lépés: Az adatbázis létrehozása

Nyisd meg a MySQL-t vagy a phpMyAdmin felületet, és hozd létre az `alumni_system` nevű adatbázist.

```sql
CREATE DATABASE alumni_system;
```

### 3. lépés: Az adatbázisfájlok importálása

A `database` könyvtár két SQL-fájlt tartalmaz:

- **alumni_system_schema.sql:** Az adatbázis szerkezetét tartalmazza, beleértve a táblákat, elsődleges kulcsokat, indexeket és idegenkulcs-kapcsolatokat.
- **sample_data.sql:** Fiktív tesztadatokat tartalmaz az alkalmazás kipróbálásához.

A fájlokat az alábbi sorrendben kell importálni:

1. Importáld az `alumni_system_schema.sql` fájlt.
2. Ezután importáld a `sample_data.sql` fájlt.

Mindkét fájlt az `alumni_system` adatbázisba kell importálni.

Az importálás elvégezhető a phpMyAdmin felületén vagy a MySQL parancssor használatával.

**Fontos:** Az adatbázis szerkezetét a backend elindítása előtt importálni kell, mivel a Hibernate automatikus táblalétrehozása nincs engedélyezve.

### 4. lépés: A backend konfigurálása

Navigálj az alábbi könyvtárba:

```text
Project/src/main/resources/
```

Keresd meg az `application-example.properties` fájlt, és készíts róla egy másolatot az alábbi néven:

```text
application.properties
```

Nyisd meg az új `application.properties` fájlt, majd állítsd be a helyi adatbázis-kapcsolatot.

Példa konfiguráció:

```properties
server.port=8080
spring.application.name=Project

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/alumni_system?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=YOUR_DATABASE_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA / Hibernate Configuration
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.open-in-view=false

# Job Recommendation Configuration
app.recommendation.threshold=60

# JWT Configuration
app.jwt.secret=REPLACE_WITH_YOUR_OWN_SECRET
app.jwt.expiration=86400000
```

Módosítsd az adatbázis portját, felhasználónevét és jelszavát a saját MySQL-konfigurációdnak megfelelően.

A JWT titkos kulcsot szintén saját, biztonságos értékre kell cserélni.

**Biztonsági megjegyzés:** Soha ne töltsd fel a tényleges `application.properties` fájlt, az adatbázis-jelszót vagy a JWT titkos kulcsot a GitHubra.

### 5. lépés: A backend elindítása

Nyisd meg a `Project` könyvtárat az IntelliJ IDEA programban, vagy nyiss egy terminált közvetlenül ebben a könyvtárban.

A Maven Wrapper használata Windows rendszeren:

```powershell
.\mvnw.cmd spring-boot:run
```

Ha a Maven külön telepítve van, az alábbi parancs is használható:

```bash
mvn spring-boot:run
```

Sikeres indítás esetén a backend az alábbi címen lesz elérhető:

```text
http://localhost:8080
```

A frontend használata közben a backendnek futnia kell.

### 6. lépés: A frontend elindítása

A frontend alkalmazás a `cooladmin` könyvtárban található.

Az alkalmazás Visual Studio Code segítségével történő elindításához:

1. Nyisd meg az `AlumniConnect` projekt teljes könyvtárát a Visual Studio Code-ban.
2. Nyisd meg a `cooladmin` könyvtárat.
3. Ha még nincs telepítve, telepítsd a **Live Server** bővítményt.
4. Keresd meg a `login.html` fájlt.
5. Kattints rá jobb egérgombbal, majd válaszd az **Open with Live Server** lehetőséget.
6. A frontend megnyílik az alapértelmezett webböngészőben.

A frontend a `cooladmin/js/api-config.js` fájl segítségével automatikusan meghatározza a backend címét.

- Helyi futtatás esetén (`localhost` vagy `127.0.0.1`) a `http://localhost:8080` címen futó backendhez csatlakozik.
- Online futtatás esetén a telepített backendhez csatlakozik.

A helyi és az online használat közötti váltáshoz nem szükséges manuálisan módosítani az API-címet.

**Megjegyzés:** A frontend statikus HTML-, CSS- és JavaScript-fájlokból áll. Nincs szükség npm-telepítésre vagy build parancs futtatására.

## 8. Adatbázis-tervezés

A rendszer MySQL-adatbázisa az `alumni_system` nevet viseli, és 13 táblát tartalmaz.

| Tábla | Leírás |
|---|---|
| `users` | Felhasználói fiókok és szerepkörök tárolása. |
| `admins` | Adminisztrátori adatok tárolása. |
| `alumni` | Öregdiákok profiladatainak tárolása. |
| `departments` | Tanszéki adatok tárolása. |
| `alumni_id_sequence` | Az öregdiák-azonosítók sorszámának kezelése. |
| `skills` | Az elérhető készségek tárolása. |
| `alumni_skills` | Az öregdiákprofilok és a regisztrált készségek összekapcsolása. |
| `employment_history` | Korábbi munkahelyek és munkatapasztalatok tárolása. |
| `job_opportunities` | Elérhető álláslehetőségek tárolása. |
| `job_skills` | Az álláslehetőségek és az elvárt készségek összekapcsolása. |
| `events` | Öregdiák-események tárolása. |
| `event_alumni` | Az öregdiákok és az események közötti kapcsolatok tárolása. |
| `announcements` | Rendszerközlemények tárolása. |

Az adatbázis elsődleges és idegen kulcsokat használ az adatok integritásának biztosítására, valamint a különböző entitások közötti kapcsolatok kialakítására.

## 9. Tesztelés és értékelés

Az alkalmazást a fejlesztés során teszteltem annak érdekében, hogy ellenőrizzem a főbb funkciók működését, valamint a frontend, a backend és az adatbázis közötti kommunikációt.

### Funkcionális tesztelés

A backend REST API-végpontjainak teszteléséhez Postman alkalmazást használtam.

Az alkalmazás megvalósított funkcióit is ellenőriztem, többek között:

- Felhasználói regisztráció és hitelesítés.
- Öregdiákprofilok kezelése.
- Munkatapasztalatok kezelése.
- Készségek kezelése.
- Álláslehetőségek kezelése.
- Állásajánlási funkció működése.
- Események és közlemények kezelése.

### Az ajánlási rendszer értékelése

Az állásajánlások relevanciáját öt résztvevő bevonásával értékeltem.

Az ajánlási rendszer átlagosan **2,8 pontot ért el az 5 pontos skálán**.

Ez az eredmény kezdeti visszajelzést ad arról, hogy a résztvevők mennyire találták relevánsnak az ajánlásokat. Az értékelés azonban kis létszámú mintán alapul, ezért az eredmények nem tekinthetők az ajánlási rendszer hatékonyságára vonatkozó végleges bizonyítéknak.

A rendszer további értékelése nagyobb résztvevői csoport bevonásával és további ajánlási szempontok vizsgálatával javítható.

## 10. A megvalósítás állapota és korlátai

A projektben megvalósított funkciók:

- Öregdiák-fiókok regisztrációja és bejelentkezése.
- Öregdiákprofilok kezelése.
- Munkatapasztalatok kezelése.
- Készségek kezelése.
- Álláslehetőségek kezelése.
- Készségalapú állásajánlások.
- Események kezelése.
- Közlemények kezelése.
- Adminisztrációs funkciók.

A jelenlegi megvalósítás nem tartalmazza az alábbi funkciókat:

- Öregdiákok által kezdeményezhető eseményregisztráció.
- Külön munkáltatói felület.

Ezek a funkciók a rendszer későbbi továbbfejlesztése során valósíthatók meg.

## 11. Képernyőképek

A `Screenshots` könyvtár az alkalmazás felhasználói felületét és megvalósított funkcióit bemutató képernyőképeket tartalmaz.

A képernyőképek többek között az alábbi felületeket mutatják be:

- Bejelentkezési és regisztrációs oldalak.
- Öregdiák vezérlőpult.
- Öregdiákprofil.
- Munkatapasztalatok kezelése.
- Készségek kezelése.
- Álláslehetőségek.
- Állásajánlások.
- Események és közlemények.
- Adminisztrációs vezérlőpult és kezelőfelületek.

## 12. Továbbfejlesztési lehetőségek

A rendszer későbbi továbbfejlesztésének lehetséges irányai:

- Eseményregisztrációs funkció megvalósítása.
- Külön munkáltatói felület kialakítása.
- Az állásajánlási módszer továbbfejlesztése.
- Az ajánlási rendszer nagyobb résztvevői csoporttal történő további értékelése.
- További funkciók bevezetése az öregdiákok szakmai fejlődésének és kapcsolattartásának támogatására.

## 13. Projektinformációk

**Projekt címe:** Spring Boot-alapú öregdiák-követő és készségalapú állásajánló rendszer tervezése és megvalósítása.

**Projekt típusa:** BSc számítástechnika szakos záróprojekt.

**Backend:** Java és Spring Boot.

**Frontend:** HTML, CSS, JavaScript és Bootstrap 5.

**Adatbázis:** MySQL.

**GitHub-tároló:** https://github.com/benedictajulius005-bot/AlumniConnect